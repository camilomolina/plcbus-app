package cl.bennu.plcbus.core.business.batch;

import cl.bennu.plcbus.common.Constants;
import cl.bennu.plcbus.common.domain.*;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.common.enums.GeneralDeviceTypeEnum;
import cl.bennu.plcbus.common.enums.MovementActionTypeEnum;
import cl.bennu.plcbus.common.enums.MovementTypeEnum;
import cl.bennu.plcbus.core.business.helper.EventHelper;
import cl.bennu.plcbus.core.business.helper.MailHelper;
import cl.bennu.plcbus.core.business.iface.IConfigurationService;
import cl.bennu.plcbus.core.business.iface.IControlService;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import cl.bennu.plcbus.core.business.reader.MailMessageReaderFactory;
import cl.bennu.plcbus.core.business.reader.mail.IMailMessageReader;
import cl.bennu.plcbus.domain.StatusResponse;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.HtmlEmail;

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
public class SensorBatch extends BaseBatch {

    private IControlService controlService;
    private IMaintainerService maintainerService;
    private IConfigurationService configurationService;

    private Date clock = new Date();
    private boolean init = true;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT);

    private static List<Device> deviceList = null;
    private static List<MovementAction> movementActionList = null;
    private static Property property = null;

    public void execute() {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT_HH_MM_SS);
        //System.out.println("Ejecutando SensorBatch - ini : " + simpleDateFormat.format(new Date()));
        try {
            Date now = new Date();
            if (((clock.getTime() / 1000) + 300) < (now.getTime() / 1000) || init) {
                deviceList = maintainerService.findActiveDevice(buildContext(), GeneralDeviceTypeEnum.SENSOR);
                movementActionList = configurationService.getAllMovementAction(buildContext());
                property = maintainerService.getProperty(buildContext());
                clock = new Date();

                init = false;
            }

            HtmlEmail email = MailHelper.buildHtmlEmail(Constants.MAIL_HOST, Constants.MAIL_PORT, Constants.MAIL_SSL, Constants.MAIL_TLS, Constants.MAIL_FROM, Constants.MAIL_FROM_ALIAS, Constants.MAIL_USER, Constants.MAIL_PASS);
            email.setSubject("Alerta de sensor");
            email.addTo(property == null ? "" : property.getMail());
            //TODO: cambiar por mail de propiedad
            email.addBcc("camilo.molina.orth@gmail.com");

            if (deviceList == null) return;

            for (Device device : deviceList) {
                //if (GeneralDeviceTypeEnum.SENSOR.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())) {
                StatusResponse statusResponse = controlService.check(buildContext(), device.getCode());
                if (statusResponse != null) {
                    //System.out.println("Sensor " + device.getCode());

                    //Device c3 = maintainerService.getDeviceByCode(buildContext(), "B7");
                    if (BooleanUtils.isTrue(statusResponse.getStatus())) {
                        //System.out.println("Sensor [ON]" + c3.getCode());
                        //controlService.on(buildContext(), c3.getId(), null);

                        // guarda evento de movimiento
                        Event event = EventHelper.buildEvent(device, EventTypeEnum.MOVEMENT_SENSOR_ON);
                        configurationService.saveEvent(buildContext(), event);

                        if (movementActionList != null) {
                            for (MovementAction movementAction : movementActionList) {
                                if (MovementTypeEnum.ON.equals(movementAction.getMovementTypeEnum())) {
                                    movementActionDetail(movementAction, device, email, MovementTypeEnum.ON);
                                }
                            }
                        }
                    } else {
                        //System.out.println("Sensor [OFF]" + c3.getCode());
                        //controlService.off(buildContext(), c3.getId(), null);

                        // guarda evento de sensor si movimiento
                        Event event = EventHelper.buildEvent(device, EventTypeEnum.MOVEMENT_SENSOR_OFF);
                        configurationService.saveEvent(buildContext(), event);

                        if (movementActionList != null) {
                            for (MovementAction movementAction : movementActionList) {
                                if (MovementTypeEnum.OFF.equals(movementAction.getMovementTypeEnum())) {
                                    movementActionDetail(movementAction, device, email, MovementTypeEnum.OFF);
                                }
                            }
                        }
                    }
                }
                //}
            }
        } catch (Exception e) {
            System.out.println("Error en SensorTimer" + e.getCause());
        }

        //System.out.println("Ejecutando SensorBatch - fin : " + simpleDateFormat.format(new Date()));
    }

    private void movementActionDetail(MovementAction movementAction, Device device, HtmlEmail email, MovementTypeEnum movementTypeEnum) throws Exception {
        if (!BooleanUtils.isTrue(movementAction.getActive())) return;

        Calendar calendar = GregorianCalendar.getInstance();

        String strOn = simpleDateFormat.format(movementAction.getStart());
        String strOff = simpleDateFormat.format(movementAction.getEnd());

        Calendar start = GregorianCalendar.getInstance();
        Calendar end = GregorianCalendar.getInstance();

        start.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strOn.split(":")[0]));
        start.set(Calendar.MINUTE, Integer.parseInt(strOn.split(":")[1]));

        end.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strOff.split(":")[0]));
        end.set(Calendar.MINUTE, Integer.parseInt(strOff.split(":")[1]));

        if (calendar.compareTo(start) == 1 && calendar.compareTo(end) == -1) {
            // revisa que este dentro del horario de alertas del sensor

            if (movementAction.getDevice().getCode().equals(device.getCode())) {
                // revisa que el sensor este activo

                String mailTxt = "";
                String smsText = "";
                if (MovementTypeEnum.ON.equals(movementTypeEnum)) {
                    mailTxt = "Dispositivo " + device.getCode() + " - " + device.getName() + " a detectado presencia.";
                    smsText = "Dispositivo " + device.getCode() + " - " + device.getName() + " a detectado presencia.";
                } else if (MovementTypeEnum.OFF.equals(movementTypeEnum)) {
                    mailTxt = "Dispositivo " + device.getCode() + " - " + device.getName() + " a detectado que no hay actividad.";
                    smsText = "Dispositivo " + device.getCode() + " - " + device.getName() + " a detectado que no hay actividad.";
                }

                for (MovementActionDetail movementActionDetail : movementAction.getMovementActionDetailList()) {
                    if (MovementActionTypeEnum.ON.equals(movementActionDetail.getMovementActionTypeEnum())) {
                        controlService.on(buildContext(), movementActionDetail.getDevice().getId(), EventTypeEnum.ON_BY_SENSOR, true);
                    } else if (MovementActionTypeEnum.OFF.equals(movementActionDetail.getMovementActionTypeEnum())) {
                        controlService.off(buildContext(), movementActionDetail.getDevice().getId(), EventTypeEnum.OFF_BY_SENSOR);
                    } else if (MovementActionTypeEnum.MAIL.equals(movementActionDetail.getMovementActionTypeEnum())) {
                        //String body = "<html><body>" + mailTxt + "";
                        IMailMessageReader mailMessageReader = MailMessageReaderFactory.getMailMessageReader();
                        StringBuffer body = mailMessageReader.buildMail4Sensor(device, movementTypeEnum);
                        email.setHtmlMsg(body.toString());
                        email.send();

                        // guarda evento envio de mail por sensor
                        Event event = EventHelper.buildEvent(device, EventTypeEnum.MAIL_SENDED_BY_SENSOR);
                        configurationService.saveEvent(buildContext(), event);
                    } else if (MovementActionTypeEnum.SMS.equals(movementActionDetail.getMovementActionTypeEnum())) {
                        // mensaje de texto
                        StringBuffer stringBuffer = new StringBuffer();
                        try {



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
                            Event event = EventHelper.buildEvent(device, EventTypeEnum.SMS_SENDED_BY_SENSOR);
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
