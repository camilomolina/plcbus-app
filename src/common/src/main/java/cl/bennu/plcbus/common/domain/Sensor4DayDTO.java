package cl.bennu.plcbus.common.domain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class Sensor4DayDTO extends BaseDomain {

    private Integer hour;
    private Integer day;
    private List<SensorStatisticsDTO> sensorStatisticsList;

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

    public List<SensorStatisticsDTO> getSensorStatisticsList() {
        return sensorStatisticsList;
    }

    public void setSensorStatisticsList(List<SensorStatisticsDTO> sensorStatisticsList) {
        this.sensorStatisticsList = sensorStatisticsList;
    }
}