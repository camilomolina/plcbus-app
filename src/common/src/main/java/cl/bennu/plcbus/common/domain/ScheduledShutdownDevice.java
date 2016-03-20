package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.enums.ProgrammingTypeEnum;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class ScheduledShutdownDevice extends BaseDomain {

    private Device device;
    private Date off;


    public Date getOff() {
        return off;
    }

    public void setOff(Date off) {
        this.off = off;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
