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
public class TemperatureSummary implements Serializable {

    private Integer min;
    private Integer max;

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
