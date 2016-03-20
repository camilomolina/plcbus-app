package cl.bennu.plcbus.common.comparator;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Sector;
import cl.bennu.plcbus.common.enums.DeviceTypeEnum;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 12-12-13
 * Time: 03:25 AM
 */
public class DeviceTypeComparator implements Comparator<DeviceTypeEnum> {

    @Override
    public int compare(DeviceTypeEnum o1, DeviceTypeEnum o2) {
        return o1.getGeneralDeviceTypeEnum().getId().compareTo(o2.getGeneralDeviceTypeEnum().getId());
    }
}
