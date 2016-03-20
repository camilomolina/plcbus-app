package cl.bennu.plcbus.common.domain;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class StatusRefresh extends BaseDomain {

    private List<StatusRefreshDetail> statusRefreshDetailList;
    private Date start;
    private Date end;

    private Long minutes;
    private Long elements;

    public List<StatusRefreshDetail> getStatusRefreshDetailList() {
        return statusRefreshDetailList;
    }

    public void setStatusRefreshDetailList(List<StatusRefreshDetail> statusRefreshDetailList) {
        this.statusRefreshDetailList = statusRefreshDetailList;
    }

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

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public Long getElements() {
        return elements;
    }

    public void setElements(Long elements) {
        this.elements = elements;
    }
}
