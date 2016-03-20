package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.enums.DeviceTypeEnum;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class DeviceSetting extends BaseDomain {

    private Long alarmMinutes;
    private Boolean energySavings;

    public Long getAlarmMinutes() {
        return alarmMinutes;
    }

    public void setAlarmMinutes(Long alarmMinutes) {
        this.alarmMinutes = alarmMinutes;
    }

    public Boolean getEnergySavings() {
        return energySavings;
    }

    public void setEnergySavings(Boolean energySavings) {
        this.energySavings = energySavings;
    }
}
