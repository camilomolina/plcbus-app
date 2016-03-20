package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.enums.DeviceTypeEnum;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class Device extends BaseDomain {

    private String code;
    private String name;
    private String desc;
    private Long signal;
    private Long noize;
    private Boolean active;
    private Boolean status;
    private Sector sector;
    private DeviceTypeEnum deviceTypeEnum;
    private Date lastPower;
    private Boolean alarmed;
    private Long delay;
    private Long dimmer;
    private Camera camera;


    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public Long getDimmer() {
        return dimmer;
    }

    public void setDimmer(Long dimmer) {
        this.dimmer = dimmer;
    }

    public Boolean getAlarmed() {
        return alarmed;
    }

    public void setAlarmed(Boolean alarmed) {
        this.alarmed = alarmed;
    }

    public Date getLastPower() {
        return lastPower;
    }

    public void setLastPower(Date lastPower) {
        this.lastPower = lastPower;
    }

    public DeviceTypeEnum getDeviceTypeEnum() {
        return deviceTypeEnum;
    }

    public void setDeviceTypeEnum(DeviceTypeEnum deviceTypeEnum) {
        this.deviceTypeEnum = deviceTypeEnum;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Long getSignal() {
        return signal;
    }

    public void setSignal(Long signal) {
        this.signal = signal;
    }

    public Long getNoize() {
        return noize;
    }

    public void setNoize(Long noize) {
        this.noize = noize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
