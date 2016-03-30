package cl.bennu.plcbus.common.domain.summary;

import cl.bennu.plcbus.common.enums.DayEnum;
import cl.bennu.plcbus.common.enums.ProgrammingTypeEnum;
import cl.bennu.plcbus.common.enums.RangeTypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 01-02-14
 * Time: 04:53 PM
 */
public class ProgrammingSummary implements Serializable {

    private ProgrammingTypeEnum programmingTypeEnum;
    private String reason;
    private Long deviceId;
    private TimeSummary timeSummary;
    private List<DayEnum> dayList;
    private Long[] dayIdArray;
    private String days;
    private Boolean active;
    private Boolean temperature;
    private RangeTypeEnum rangeTypeEnum;
    private TemperatureSummary temperatureSummary;

    public Boolean getTemperature() {
        return temperature;
    }

    public void setTemperature(Boolean temperature) {
        this.temperature = temperature;
    }

    public TemperatureSummary getTemperatureSummary() {
        return temperatureSummary;
    }

    public void setTemperatureSummary(TemperatureSummary temperatureSummary) {
        this.temperatureSummary = temperatureSummary;
    }

    public RangeTypeEnum getRangeTypeEnum() {
        return rangeTypeEnum;
    }

    public void setRangeTypeEnum(RangeTypeEnum rangeTypeEnum) {
        this.rangeTypeEnum = rangeTypeEnum;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Long[] getDayIdArray() {
        return dayIdArray;
    }

    public void setDayIdArray(Long[] dayIdArray) {
        this.dayIdArray = dayIdArray;
    }

    public ProgrammingTypeEnum getProgrammingTypeEnum() {
        return programmingTypeEnum;
    }

    public void setProgrammingTypeEnum(ProgrammingTypeEnum programmingTypeEnum) {
        this.programmingTypeEnum = programmingTypeEnum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public TimeSummary getTimeSummary() {
        return timeSummary;
    }

    public void setTimeSummary(TimeSummary timeSummary) {
        this.timeSummary = timeSummary;
    }

    public List<DayEnum> getDayList() {
        return dayList;
    }

    public void setDayList(List<DayEnum> dayList) {
        this.dayList = dayList;
    }
}
