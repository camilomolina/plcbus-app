package cl.bennu.plcbus.core.business.helper;

import cl.bennu.plcbus.common.domain.Consumer;
import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.core.persistence.impl.hbm.ConsumerDAO;

import java.util.Date;
import java.util.List;

/**
 * Created by Camilo on 23-02-2015.
 */
public class EventHelper {

    public static Event buildEvent(Device device, EventTypeEnum eventTypeEnum) {
        Event event = new Event();
        event.setDate(new Date());
        event.setEventTypeEnum(eventTypeEnum);
        if (device != null) {
            event.setDeviceCode(device.getCode());
            event.setDeviceName(device.getName());
            event.setDeviceTypeEnum(device.getDeviceTypeEnum());
        }
        return event;
    }

}
