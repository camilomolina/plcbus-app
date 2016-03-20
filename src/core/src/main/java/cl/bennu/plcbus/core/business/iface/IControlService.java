package cl.bennu.plcbus.core.business.iface;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Level;
import cl.bennu.plcbus.common.dto.ContextDTO;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.common.exception.RainException;
import cl.bennu.plcbus.domain.StatusResponse;
import cl.bennu.plcbus.domain.StatusResponse4Unit;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public interface IControlService {


    void on(ContextDTO contextDTO, Long deviceId, EventTypeEnum eventTypeEnum) throws RainException;

    void on(ContextDTO contextDTO, Long deviceId, EventTypeEnum eventTypeEnum, Boolean force) throws RainException;

    void off(ContextDTO contextDTO, Long deviceId, EventTypeEnum eventTypeEnum);

    StatusResponse status(ContextDTO contextDTO, Long deviceId);

    Long noize(ContextDTO contextDTO, Long deviceId);

    Long signal(ContextDTO contextDTO, Long deviceId);

    void alarmedDevice(ContextDTO contextDTO, Long deviceId);

    StatusResponse4Unit statusBySynchronized(ContextDTO contextDTO, String deviceLetter, EventTypeEnum eventTypeEnum);

    void dimmer(ContextDTO contextDTO, Long deviceId, Long brightnessLevel, Long dimmerFadeRate);

    StatusResponse check(ContextDTO contextDTO, Long deviceId);

    StatusResponse check(ContextDTO contextDTO, String deviceCode);

    Map viewMessageQueue(ContextDTO contextDTO);

    Long restartPLCBusCount(ContextDTO contextDTO);

}
