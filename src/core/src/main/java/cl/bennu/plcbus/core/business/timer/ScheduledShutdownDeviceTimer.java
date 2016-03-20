package cl.bennu.plcbus.core.business.timer;

import cl.bennu.plcbus.common.Constants;
import cl.bennu.plcbus.common.domain.Programming;
import cl.bennu.plcbus.common.domain.ProgrammingDetail;
import cl.bennu.plcbus.common.domain.ScheduledShutdownDevice;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.core.business.iface.IConfigurationService;
import cl.bennu.plcbus.core.business.iface.IControlService;

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
public class ScheduledShutdownDeviceTimer extends TimerTask {

    private IControlService controlService;
    private IConfigurationService configurationService;


    public void run() {
        try {
            List<ScheduledShutdownDevice> scheduledShutdownDeviceList = configurationService.getAllScheduledShutdownDevice(buildContext());
            Calendar calendar = GregorianCalendar.getInstance();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT);
            String strNow = simpleDateFormat.format(calendar.getTime());
            Date now = simpleDateFormat.parse(strNow);

            int hourNow = Integer.parseInt(strNow.split(":")[0]);
            int minuteNow = Integer.parseInt(strNow.split(":")[1]);

            for (ScheduledShutdownDevice scheduledShutdownDevice : scheduledShutdownDeviceList) {
                String strOff = simpleDateFormat.format(scheduledShutdownDevice.getOff());

                Date off = simpleDateFormat.parse(strOff);

                int hourOff = Integer.parseInt(strOff.split(":")[0]);
                int minuteOff = Integer.parseInt(strOff.split(":")[1]);

                if (hourOff == hourNow && minuteOff == minuteNow) {
                    System.out.println("SCHEDULED SHUTDOWN::OFF " + scheduledShutdownDevice.getDevice().getCode() + "[" + scheduledShutdownDevice.getDevice().getId() + "]");
                    controlService.off(buildContext(), scheduledShutdownDevice.getDevice().getId(), EventTypeEnum.AUTOMATIC_OFF);
                }
            }
        } catch (Exception e) {
            System.out.println("Error en ScheduledShutdownDeviceTimer, " + e.getCause());
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
}
