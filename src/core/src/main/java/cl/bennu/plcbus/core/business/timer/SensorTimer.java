package cl.bennu.plcbus.core.business.timer;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.common.enums.GeneralDeviceTypeEnum;
import cl.bennu.plcbus.core.business.helper.EventHelper;
import cl.bennu.plcbus.core.business.iface.IConfigurationService;
import cl.bennu.plcbus.core.business.iface.IControlService;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import cl.bennu.plcbus.domain.StatusResponse;
import org.apache.commons.lang3.BooleanUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 29-10-13
 * Time: 03:44 AM
 */
public class SensorTimer extends TimerTask {

    private IControlService controlService;
    private IMaintainerService maintainerService;
    private IConfigurationService configurationService;

    public void run() {
        try {
            List<Device> deviceList = maintainerService.findActiveDevice(buildContext());
            if (deviceList == null) return;

            for (Device device : deviceList) {
                if (GeneralDeviceTypeEnum.SENSOR.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())) {


                    StatusResponse statusResponse = controlService.check(buildContext(), device.getId());
                    if (statusResponse != null) {
                        System.out.println("Sensor " + device.getCode());

                        Device c3 = maintainerService.getDeviceByCode(buildContext(), "B7");
                        if (BooleanUtils.isTrue(statusResponse.getStatus())) {
                            System.out.println("Sensor [ON]" + c3.getCode());
                            controlService.on(buildContext(), c3.getId(), null);

                            // guarda evento de movimiento
                            Event event = EventHelper.buildEvent(device, EventTypeEnum.MOVEMENT_SENSOR_ON);
                            configurationService.saveEvent(buildContext(), event);
                        } else {
                            System.out.println("Sensor [OFF]" + c3.getCode());
                            controlService.off(buildContext(), c3.getId(), null);

                            // guarda evento de sensor si movimiento
                            Event event = EventHelper.buildEvent(device, EventTypeEnum.MOVEMENT_SENSOR_OFF);
                            configurationService.saveEvent(buildContext(), event);
                        }
                    }
                }
            }
            //controlService.

        } catch (Exception e) {
            System.out.println("Error en SensorTimer" + e.getCause());
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
