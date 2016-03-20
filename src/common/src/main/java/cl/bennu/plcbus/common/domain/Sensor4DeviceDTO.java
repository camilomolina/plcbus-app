package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.enums.DeviceTypeEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class Sensor4DeviceDTO extends BaseDomain {

    private String deviceCode;
    private String deviceName;
    private DeviceTypeEnum deviceTypeEnum;
    private List<Sensor4Day2DTO> sensor4Day2List;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public DeviceTypeEnum getDeviceTypeEnum() {
        return deviceTypeEnum;
    }

    public void setDeviceTypeEnum(DeviceTypeEnum deviceTypeEnum) {
        this.deviceTypeEnum = deviceTypeEnum;
    }

    public List<Sensor4Day2DTO> getSensor4Day2List() {
        return sensor4Day2List;
    }

    public void setSensor4Day2List(List<Sensor4Day2DTO> sensor4Day2List) {
        this.sensor4Day2List = sensor4Day2List;
    }
}