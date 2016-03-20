package cl.bennu.plcbus.common.predicate;

import cl.bennu.plcbus.common.dto.ConsumerDTO;
import org.apache.commons.collections.Predicate;

/**
 * Created by Camilo on 10-03-2015.
 */
public class ConsumerPredicate implements Predicate {

    private String deviceCode;

    public ConsumerPredicate(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    @Override
    public boolean evaluate(Object o) {
        if (o instanceof ConsumerDTO) {
            return ((ConsumerDTO) o).getDeviceName().equals(deviceCode);
        }
        return false;
    }

}
