package cl.bennu.plcbus.common.domain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class SensorSummaryDay extends BaseDomain {

    private List<SensorSummaryHour> sensorSummaryHours;

    public List<SensorSummaryHour> getSensorSummaryHours() {
        return sensorSummaryHours;
    }

    public void setSensorSummaryHours(List<SensorSummaryHour> sensorSummaryHours) {
        this.sensorSummaryHours = sensorSummaryHours;
    }
}