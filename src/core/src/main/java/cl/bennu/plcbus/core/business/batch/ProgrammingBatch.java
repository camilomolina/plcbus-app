package cl.bennu.plcbus.core.business.batch;

import cl.bennu.plcbus.common.Constants;
import cl.bennu.plcbus.common.domain.Programming;
import cl.bennu.plcbus.common.domain.ProgrammingDetail;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.common.exception.RainException;
import cl.bennu.plcbus.core.business.iface.IConfigurationService;
import cl.bennu.plcbus.core.business.iface.IControlService;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import org.apache.commons.lang3.BooleanUtils;

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
                                System.out.println("PROGRAMMING::ON " + programming.getDevice().getCode() + "[" + programming.getDevice().getId() + "]");
                                try {
                                    controlService.on(buildContext(), programming.getDevice().getId(), EventTypeEnum.PROGRAMMING_ON);
                                } catch (RainException e) {
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
