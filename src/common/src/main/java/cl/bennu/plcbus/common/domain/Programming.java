package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.domain.summary.TimeSummary;
import cl.bennu.plcbus.common.enums.ProgrammingTypeEnum;
import cl.bennu.plcbus.common.enums.RangeTypeEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class Programming extends BaseDomain {

    private String reason;
    private Device device;
    private ProgrammingTypeEnum programmingTypeEnum;
    private List<ProgrammingDetail> programmingDetailList;
    private Boolean active;
    private Boolean temperature;
    private RangeTypeEnum rangeTypeEnum;
    private Integer min;
    private Integer max;

    private TimeSummary timeSummary;

    public Boolean getTemperature() {
        return temperature;
    }

    public void setTemperature(Boolean temperature) {
        this.temperature = temperature;
    }

    public RangeTypeEnum getRangeTypeEnum() {
        return rangeTypeEnum;
    }

    public void setRangeTypeEnum(RangeTypeEnum rangeTypeEnum) {
        this.rangeTypeEnum = rangeTypeEnum;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<ProgrammingDetail> getProgrammingDetailList() {
        return programmingDetailList;
    }

    public void setProgrammingDetailList(List<ProgrammingDetail> programmingDetailList) {
        this.programmingDetailList = programmingDetailList;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public ProgrammingTypeEnum getProgrammingTypeEnum() {
        return programmingTypeEnum;
    }

    public void setProgrammingTypeEnum(ProgrammingTypeEnum programmingTypeEnum) {
        this.programmingTypeEnum = programmingTypeEnum;
    }

    public TimeSummary getTimeSummary() {
        return timeSummary;
    }

    public void setTimeSummary(TimeSummary timeSummary) {
        this.timeSummary = timeSummary;
    }
}
