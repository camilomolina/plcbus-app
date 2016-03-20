package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.domain.summary.TimeSummary;
import cl.bennu.plcbus.common.enums.MovementTypeEnum;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class MovementAction extends BaseDomain {

    private Device device;
    private MovementTypeEnum movementTypeEnum;
    private String reason;
    private List<MovementActionDetail> movementActionDetailList;
    private Boolean active;
    private Date start;
    private Date end;

    private TimeSummary timeSummary;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public TimeSummary getTimeSummary() {
        return timeSummary;
    }

    public void setTimeSummary(TimeSummary timeSummary) {
        this.timeSummary = timeSummary;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public MovementTypeEnum getMovementTypeEnum() {
        return movementTypeEnum;
    }

    public void setMovementTypeEnum(MovementTypeEnum movementTypeEnum) {
        this.movementTypeEnum = movementTypeEnum;
    }

    public List<MovementActionDetail> getMovementActionDetailList() {
        return movementActionDetailList;
    }

    public void setMovementActionDetailList(List<MovementActionDetail> movementActionDetailList) {
        this.movementActionDetailList = movementActionDetailList;
    }
}
