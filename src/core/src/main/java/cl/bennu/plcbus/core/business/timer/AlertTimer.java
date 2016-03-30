package cl.bennu.plcbus.core.business.timer;

import cl.bennu.plcbus.common.Constants;
import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.domain.Property;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.core.business.helper.EventHelper;
import cl.bennu.plcbus.core.business.helper.MailHelper;
import cl.bennu.plcbus.core.business.iface.IConfigurationService;
import cl.bennu.plcbus.core.business.iface.IControlService;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.mail.HtmlEmail;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 29-10-13
 * Time: 03:44 AM
 */
public class AlertTimer extends TimerTask {

    private IControlService controlService;
    private IMaintainerService maintainerService;
    private IConfigurationService configurationService;

    public void run() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT);

            List<Device> deviceList = maintainerService.findActiveDevice(buildContext());
            if (deviceList == null) return;

            Property property = maintainerService.getProperty(buildContext());

            String from = "plcbus@viruscorp.noip.me";
            String host = "smtp.gmail.com";
            String fromAlias = "";
            String user = "djmac.j@gmail.com";
            String pass = "mac11042";
            int port = 465;
            boolean ssl = true;
            boolean tls = false;

            String smsUser = "camilo.molina";
            String smsPass = "123456";
            String smsPhone = "569" + property.getPhone();
            String smsText = "";
            String urlStr = "http://www.redvoiss.net/sms/single/user=" + smsUser + "&pwd=" + smsPass + "&dest=" + smsPhone + "&txt=";

            HtmlEmail email = MailHelper.buildHtmlEmail(host, port, ssl, tls, from, fromAlias, user, pass);

            email.setSubject("Alerta de dispositivo encendido");
            email.addTo(property.getMail());
            // mails adicionales
            try {
                String[] mails = property.getMail2().split(";");
                for (String mail : mails) {
                    if (StringUtils.isNotBlank(mail.trim())) {
                        email.addBcc(mail.trim());
                    }
                }
            } catch (Exception e) {
                // excepcion no manejada
            }

            long time = System.currentTimeMillis() / 1000;
            for (Device device : deviceList) {
                if (BooleanUtils.isTrue(device.getActive())
                        && BooleanUtils.isTrue(device.getStatus())
                        && !BooleanUtils.isTrue(device.getAlarmed())
                        && BooleanUtils.isTrue(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum().getTimeControl())) {

                    long lastPower = device.getLastPower().getTime() / 1000;

                    String strLastPower = simpleDateFormat.format(device.getLastPower());
                    int hourLastPower = Integer.parseInt(strLastPower.split(":")[0]);
                    //int minuteLastPower = Integer.parseInt(strLastPower.split(":")[1]);


                    if (hourLastPower < 21 && hourLastPower > 8) {
                        if ((lastPower + (60 * 60 * 2)) < time) {
                            System.out.println("Alert " + device.getCode());
                            String mailTxt = "Dispositivo " + device.getCode() + " - " + device.getName() + " encendido mas de 2 horas.";
                            System.out.println(mailTxt);

                            //marcamos como alarmado el dispositivo
                            controlService.alarmedDevice(buildContext(), device.getId());

                            // mail
                            try {
                                String body = "<html><body>" + mailTxt + ", apague en http://viruscorp.noip.me:8088/plcbus/</body></html>";
                                email.setHtmlMsg(body);
                                email.send();

                                // guarda evento de de envio de sms por alarma
                                Event event = EventHelper.buildEvent(device, EventTypeEnum.MAIL_SENDED);
                                event.setDesc(mailTxt);
                                configurationService.saveEvent(buildContext(), event);

                            } catch (Exception e) {
                                // guarda evento de error de envio de correo
                                Event event = EventHelper.buildEvent(device, EventTypeEnum.ALERT_MAIL_SENDED_ERROR);
                                String error = e.getCause() + "" + e.getMessage();
                                event.setDesc(StringUtils.substring(error, 0, 5000));
                                configurationService.saveEvent(buildContext(), event);
                            }

                            // mensaje de texto
                            StringBuffer stringBuffer = new StringBuffer();
                            try {
                                smsText = "Dispositivo " + device.getCode() + " - " + device.getName() + " encendido por mas de 2 horas, apague en http://viruscorp.noip.me:8088/plcbus/";


                                /*
                                HttpClient client = new DefaultHttpClient();
                                HttpGet request = new HttpGet(urlStr);

                                // add request header
                                //request.addHeader("User-Agent", USER_AGENT);
                                HttpResponse response = client.execute(request);
                                //System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

                                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                                String line = "";
                                while ((line = rd.readLine()) != null) {
                                    stringBuffer.append(line);
                                }

*/
                                // guarda evento de de envio de sms por alarma
                                Event event = EventHelper.buildEvent(device, EventTypeEnum.SMS_SENDED);
                                event.setDesc(smsText);
                                configurationService.saveEvent(buildContext(), event);

                            } catch (Exception e) {
                                // guarda evento de error de envio de sms
                                Event event = EventHelper.buildEvent(device, EventTypeEnum.ALERT_MAIL_SENDED_ERROR);
                                String error = e.getCause() + "" + e.getMessage();
                                event.setDesc(StringUtils.substring(error, 0, 5000));
                                event.setData1(StringUtils.substring(stringBuffer.toString(), 0, 1000));
                                configurationService.saveEvent(buildContext(), event);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error en AlertTimer, " + e.getCause());
        }
    }

    public IControlService getControlService() {
        return controlService;
    }

    public void setControlService(IControlService controlService) {
        this.controlService = controlService;
    }

    public IMaintainerService getMaintainerService() {
        return maintainerService;
    }

    public void setMaintainerService(IMaintainerService maintainerService) {
        this.maintainerService = maintainerService;
    }

    public IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
