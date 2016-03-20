package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.enums.DeviceTypeEnum;
import cl.bennu.plcbus.common.enums.EventTypeEnum;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class SensorSummaryDTO extends BaseDomain {

    private List<SensorSummaryDay> sensorSummaryDays;

    public List<SensorSummaryDay> getSensorSummaryDays() {
        return sensorSummaryDays;
    }

    public void setSensorSummaryDays(List<SensorSummaryDay> sensorSummaryDays) {
        this.sensorSummaryDays = sensorSummaryDays;
    }

}