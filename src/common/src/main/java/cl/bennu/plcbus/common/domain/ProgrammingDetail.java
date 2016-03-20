package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.enums.DayEnum;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class ProgrammingDetail extends BaseDomain {

    private DayEnum dayEnum;
    private Programming programming;
    private Date on;
    private Date off;


    public DayEnum getDayEnum() {
        return dayEnum;
    }

    public void setDayEnum(DayEnum dayEnum) {
        this.dayEnum = dayEnum;
    }

    public Programming getProgramming() {
        return programming;
    }

    public void setProgramming(Programming programming) {
        this.programming = programming;
    }

    public Date getOn() {
        return on;
    }

    public void setOn(Date on) {
        this.on = on;
    }

    public Date getOff() {
        return off;
    }

    public void setOff(Date off) {
        this.off = off;
    }
}
