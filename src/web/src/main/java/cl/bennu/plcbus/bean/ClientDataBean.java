package cl.bennu.plcbus.bean;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Programming;
import cl.bennu.plcbus.common.domain.Property;
import cl.bennu.plcbus.common.domain.StatusRefresh;
import cl.bennu.plcbus.common.domain.summary.ProgrammingSummary;
import cl.bennu.plcbus.common.enums.DayEnum;
import cl.bennu.plcbus.common.enums.HourEnum;
import cl.bennu.plcbus.common.enums.MinuteEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 04-07-13
 * Time: 12:32 AM
 */
public class ClientDataBean {

    private Property property;

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
