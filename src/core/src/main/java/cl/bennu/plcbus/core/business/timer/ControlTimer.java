package cl.bennu.plcbus.core.business.timer;

import cl.bennu.plcbus.core.business.iface.IConfigurationService;
import cl.bennu.plcbus.core.business.iface.IControlService;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 29-10-13
 * Time: 03:44 AM
 */
public class ControlTimer extends TimerTask {

    private IControlService controlService;
    private IConfigurationService configurationService;

    private Boolean running = true;
    private int hourProcess = 0;

    public void run() {
        try {
            Calendar calendar = GregorianCalendar.getInstance();
            if (running) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                if (minute == 2 && (hour == 6 || hour == 13 || hour == 21)) {
                    if (hourProcess != hour) {
                        running = false;
                        hourProcess = hour;

                        configurationService.synchronizedDevice(buildContext());
                    }
                    running = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en ControlTimer" + e.getCause());
        }
    }

    public IControlService getControlService() {
        return controlService;
    }

    public void setControlService(IControlService controlService) {
        this.controlService = controlService;
    }

    public IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
