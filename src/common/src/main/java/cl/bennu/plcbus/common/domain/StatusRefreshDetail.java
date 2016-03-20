package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.enums.SynchronizedEnum;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class StatusRefreshDetail extends BaseDomain {

    private Device device;
    private StatusRefresh statusRefresh;
    private SynchronizedEnum synchronizedEnum;


    public StatusRefresh getStatusRefresh() {
        return statusRefresh;
    }

    public void setStatusRefresh(StatusRefresh statusRefresh) {
        this.statusRefresh = statusRefresh;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public SynchronizedEnum getSynchronizedEnum() {
        return synchronizedEnum;
    }

    public void setSynchronizedEnum(SynchronizedEnum synchronizedEnum) {
        this.synchronizedEnum = synchronizedEnum;
    }
}
