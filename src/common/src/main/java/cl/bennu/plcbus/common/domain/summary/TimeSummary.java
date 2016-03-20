package cl.bennu.plcbus.common.domain.summary;

import cl.bennu.plcbus.common.enums.HourEnum;
import cl.bennu.plcbus.common.enums.MinuteEnum;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 01-02-14
 * Time: 04:55 PM
 */
public class TimeSummary implements Serializable {

    private HourEnum startHourEnum;
    private MinuteEnum startMinuteEnum;
    private HourEnum endHourEnum;
    private MinuteEnum endMinuteEnum;

    public HourEnum getStartHourEnum() {
        return startHourEnum;
    }

    public void setStartHourEnum(HourEnum startHourEnum) {
        this.startHourEnum = startHourEnum;
    }

    public MinuteEnum getStartMinuteEnum() {
        return startMinuteEnum;
    }

    public void setStartMinuteEnum(MinuteEnum startMinuteEnum) {
        this.startMinuteEnum = startMinuteEnum;
    }

    public HourEnum getEndHourEnum() {
        return endHourEnum;
    }

    public void setEndHourEnum(HourEnum endHourEnum) {
        this.endHourEnum = endHourEnum;
    }

    public MinuteEnum getEndMinuteEnum() {
        return endMinuteEnum;
    }

    public void setEndMinuteEnum(MinuteEnum endMinuteEnum) {
        this.endMinuteEnum = endMinuteEnum;
    }
}
