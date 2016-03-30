package cl.bennu.plcbus.core.business.batch;

import cl.bennu.plcbus.common.Constants;
import cl.bennu.plcbus.common.domain.Programming;
import cl.bennu.plcbus.common.domain.ProgrammingDetail;
import cl.bennu.plcbus.common.domain.Property;
import cl.bennu.plcbus.common.domain.weather.Weather;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.common.enums.RangeTypeEnum;
import cl.bennu.plcbus.common.exception.RainException;
import cl.bennu.plcbus.core.business.helper.MailHelper;
import cl.bennu.plcbus.core.business.iface.IConfigurationService;
import cl.bennu.plcbus.core.business.iface.IControlService;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.HtmlEmail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 29-10-13
 * Time: 03:44 AM
 */
public class ProgrammingBatch extends BaseBatch {

    private IControlService controlService;
    private IConfigurationService configurationService;
    private IMaintainerService maintainerService;

    public void execute() {
        //System.out.println("Ejecutando ProgrammingBatch");
        try {
            List<Programming> programmingList = configurationService.getAllProgramming(buildContext());
            Property property = maintainerService.getProperty(buildContext());
            Weather weather = maintainerService.getLastedWeather(buildContext());

            Calendar calendar = GregorianCalendar.getInstance();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT);
            String strNow = simpleDateFormat.format(calendar.getTime());
            //Date now = simpleDateFormat.parse(strNow);

            int hourNow = Integer.parseInt(strNow.split(":")[0]);
            int minuteNow = Integer.parseInt(strNow.split(":")[1]);

            for (Programming programming : programmingList) {
                //System.out.println("Programming " + programming.getDevice().getName());
                if (BooleanUtils.isTrue(programming.getActive())) {
                    List<ProgrammingDetail> programmingDetailList = programming.getProgrammingDetailList();
                    for (ProgrammingDetail programmingDetail : programmingDetailList) {
                        long day = calendar.get(Calendar.DAY_OF_WEEK) - 1L;
                        if (day == 0L) day = 7L; // en caso de domingo

                        if (programmingDetail.getDayEnum().getId().equals(day)) {
                            String strOn = simpleDateFormat.format(programmingDetail.getOn());
                            String strOff = simpleDateFormat.format(programmingDetail.getOff());

                            //Date on = simpleDateFormat.parse(strOn);
                            //Date off = simpleDateFormat.parse(strOff);

                            int hourOn = Integer.parseInt(strOn.split(":")[0]);
                            int minuteOn = Integer.parseInt(strOn.split(":")[1]);

                            int hourOff = Integer.parseInt(strOff.split(":")[0]);
                            int minuteOff = Integer.parseInt(strOff.split(":")[1]);

                            if (hourOn == hourNow && minuteOn == minuteNow) {
                                try {
                                    // verificar si tiene filtro por temperatura
                                    if (BooleanUtils.isTrue(programming.getTemperature())) {
                                        boolean on = false;
                                        if (RangeTypeEnum.LESS.equals(programming.getRangeTypeEnum())) {
                                            if (weather.getTemp() < programming.getMax()) {
                                                on = true;
                                            }
                                        } else if (RangeTypeEnum.GREATER.equals(programming.getRangeTypeEnum())) {
                                            if (weather.getTemp() > programming.getMin()) {
                                                on = true;
                                            }
                                        } else if (RangeTypeEnum.BETWEEN.equals(programming.getRangeTypeEnum())) {
                                            if (weather.getTemp() < programming.getMax() && weather.getTemp() > programming.getMin()) {
                                                on = true;
                                            }
                                        }

                                        if (on) {
                                            System.out.println("PROGRAMMING::ON [TEMPERATURE]" + programming.getDevice().getCode() + "[" + programming.getDevice().getId() + "]");
                                            controlService.on(buildContext(), programming.getDevice().getId(), EventTypeEnum.PROGRAMMING_ON);
                                        }
                                    } else {
                                        System.out.println("PROGRAMMING::ON " + programming.getDevice().getCode() + "[" + programming.getDevice().getId() + "]");
                                        controlService.on(buildContext(), programming.getDevice().getId(), EventTypeEnum.PROGRAMMING_ON);
                                    }
                                } catch (RainException e) {
                                    // excepcion de riego
                                    String smsUser = "camilo.molina";
                                    String smsPass = "123456";
                                    String smsPhone = "569" + property.getPhone();
                                    String smsText = "";
                                    String urlStr = "http://www.redvoiss.net/sms/single/user=" + smsUser + "&pwd=" + smsPass + "&dest=" + smsPhone + "&txt=";

                                    HtmlEmail email = MailHelper.buildHtmlEmail(Constants.MAIL_HOST, Constants.MAIL_PORT, Constants.MAIL_SSL, Constants.MAIL_TLS, Constants.MAIL_FROM, Constants.MAIL_FROM_ALIAS, Constants.MAIL_USER, Constants.MAIL_PASS);

                                    email.setSubject("Alerta de cancelacion de riego");
                                    email.addTo(property.getMail());
                                    // mails adicionales
                                    try {
                                        String[] mails = property.getMail2().split(";");
                                        for (String mail : mails) {
                                            if (StringUtils.isNotBlank(mail.trim())) {
                                                email.addBcc(mail.trim());
                                            }
                                        }
                                    } catch (Exception ee) {
                                        // excepcion no manejada
                                    }

                                    email.setHtmlMsg("Se cancela riego de dispositivo [" + programming.getDevice().getCode() + "] " + programming.getDevice().getName());
                                    email.send();

                                    System.out.println("PROGRAMMING::ALERT " + programming.getDevice().getCode() + "[" + programming.getDevice().getId() + "] OMITIDO POR LLUVIA");
                                }
                            }
                            if (hourOff == hourNow && minuteOff == minuteNow) {
                                System.out.println("PROGRAMMING::OFF " + programming.getDevice().getCode() + "[" + programming.getDevice().getId() + "]");
                                controlService.off(buildContext(), programming.getDevice().getId(), EventTypeEnum.PROGRAMMING_OFF);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error en ProgrammingTimer, " + e.getCause());
        }
    }

    public IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
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
}
