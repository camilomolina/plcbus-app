package cl.bennu.plcbus.core.business.iface;

import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.domain.Sensor4DayDTO;
import cl.bennu.plcbus.common.domain.Sensor4DeviceDTO;
import cl.bennu.plcbus.common.domain.SensorSummaryDTO;
import cl.bennu.plcbus.common.dto.ConsumerDTO;
import cl.bennu.plcbus.common.dto.ContextDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public interface IStatisticsService {

    List<Event> findSensorEvent(ContextDTO contextDTO);

    List<Event> findSensorEventLastWeek(ContextDTO contextDTO);

    List<Event> findErrorEvent(ContextDTO contextDTO);

    List<Event> findErrorEventLastWeek(ContextDTO contextDTO);

    List<Event> findWarningEvent(ContextDTO contextDTO);

    List<Event> findWarningEventLastWeek(ContextDTO contextDTO);

    List<Event> findNormalEvent(ContextDTO contextDTO);

    List<Event> findNormalEventLastWeek(ContextDTO contextDTO);

    List<Event> findEvent(ContextDTO contextDTO);

    List<Event> findEventLastWeek(ContextDTO contextDTO);

    List<Event> matrixDeviceVsEvent(ContextDTO contextDTO);

    List<Event> matrixDevice2(ContextDTO contextDTO);

    List<ConsumerDTO> matrixConsumer(ContextDTO contextDTO);

    Long consumption(ContextDTO contextDTO);

    List<Sensor4DayDTO> matrixSensor(ContextDTO context);

    List<Sensor4DeviceDTO> matrixSensor2(ContextDTO context);

    List<Sensor4DeviceDTO> matrixSensorWeek(ContextDTO context);

}
