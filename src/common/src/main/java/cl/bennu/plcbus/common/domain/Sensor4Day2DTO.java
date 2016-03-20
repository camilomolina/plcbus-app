package cl.bennu.plcbus.common.domain;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class Sensor4Day2DTO extends BaseDomain {

    private Integer hour;
    private Integer day;
    private Long eventCount;

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Long getEventCount() {
        return eventCount;
    }

    public void setEventCount(Long eventCount) {
        this.eventCount = eventCount;
    }
}