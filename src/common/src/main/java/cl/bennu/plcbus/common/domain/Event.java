package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.enums.DeviceTypeEnum;
import cl.bennu.plcbus.common.enums.EventTypeEnum;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class Event extends BaseDomain {

    private EventTypeEnum eventTypeEnum;
    private Date date;
    private DeviceTypeEnum deviceTypeEnum;
    private String deviceCode;
    private String deviceName;
    private String desc;
    private String data1;
    private String data2;

    public EventTypeEnum getEventTypeEnum() {
        return eventTypeEnum;
    }

    public void setEventTypeEnum(EventTypeEnum eventTypeEnum) {
        this.eventTypeEnum = eventTypeEnum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DeviceTypeEnum getDeviceTypeEnum() {
        return deviceTypeEnum;
    }

    public void setDeviceTypeEnum(DeviceTypeEnum deviceTypeEnum) {
        this.deviceTypeEnum = deviceTypeEnum;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }
}