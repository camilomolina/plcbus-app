package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.enums.DeviceTypeEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class SensorStatisticsDTO extends BaseDomain {

    private String deviceCode;
    private String deviceName;
    private DeviceTypeEnum deviceTypeEnum;
    private Long eventCount;

    public DeviceTypeEnum getDeviceTypeEnum() {
        return deviceTypeEnum;
    }

    public void setDeviceTypeEnum(DeviceTypeEnum deviceTypeEnum) {
        this.deviceTypeEnum = deviceTypeEnum;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Long getEventCount() {
        return eventCount;
    }

    public void setEventCount(Long eventCount) {
        this.eventCount = eventCount;
    }
}