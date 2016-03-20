package cl.bennu.plcbus.common.comparator;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Sector;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 12-12-13
 * Time: 03:25 AM
 */
public class DeviceComparator implements Comparator<Device> {

    @Override
    public int compare(Device o1, Device o2) {
        int cmp = 0;
        Sector s1 = o1.getSector();
        Sector s2 = o2.getSector();
        cmp = s1.getName().compareTo(s2.getName());
        if (cmp == 0) {
            cmp = o1.getCode().compareTo(o2.getCode());
        }

        return cmp;
    }
}
