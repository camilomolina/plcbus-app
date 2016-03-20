package cl.bennu.plcbus.common.domain;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class SensorSummaryHour extends BaseDomain {

    private Long event;

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }
}