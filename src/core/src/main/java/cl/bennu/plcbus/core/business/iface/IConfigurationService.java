package cl.bennu.plcbus.core.business.iface;

import cl.bennu.plcbus.common.domain.*;
import cl.bennu.plcbus.common.domain.summary.ProgrammingSummary;
import cl.bennu.plcbus.common.domain.summary.TimeSummary;
import cl.bennu.plcbus.common.dto.ContextDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public interface IConfigurationService {

    StatusRefresh getStatusRefreshLasted(ContextDTO contextDTO);

    void synchronizedDevice(ContextDTO contextDTO);

    List<Programming> getAllProgramming(ContextDTO contextDTO);

    Programming getProgramming(ContextDTO contextDTO, Long id);

    Programming getProgrammingByDeviceId(ContextDTO contextDTO, Long deviceId);

    void updateCalendarProgramming(ContextDTO contextDTO, Long programmingId, TimeSummary timeSummary);

    void updateStatusProgramming(ContextDTO contextDTO, Long programmingId, Boolean active);

    void saveProgramming(ContextDTO contextDTO, ProgrammingSummary programmingSummary);

    void deleteProgramming(ContextDTO contextDTO, Long programmingId);

    Coordinate getCoordenateByDeviceIdAndMapId(ContextDTO contextDTO, Long deviceId, Long mapId);

    void deleteCoordenateByDeviceIdAndMapId(ContextDTO contextDTO, Long deviceId, Long mapId);

    void deleteCoordenateById(ContextDTO contextDTO, Long coordenateId);

    void saveCoordenate(ContextDTO contextDTO, Coordinate coordinate);

    List<ScheduledShutdownDevice> getAllScheduledShutdownDevice(ContextDTO contextDTO);

    ScheduledShutdownDevice getScheduledShutdownDevice(ContextDTO contextDTO, Long scheduledShutdownDeviceId);

    void saveScheduledShutdownDevice(ContextDTO contextDTO, ProgrammingSummary programmingSummary);

    void deleteScheduledShutdownDevice(ContextDTO contextDTO, Long scheduledShutdownDeviceId);

    void saveEvent(ContextDTO contextDTO, Event event);

    List<MovementAction> getAllMovementAction(ContextDTO context);

    void saveMovementAction(ContextDTO context, MovementAction movementAction);

    void updateStatusMovementAction(ContextDTO context, Long movementActionId, Boolean movementActionActive);

    void deleteMovementAction(ContextDTO context, Long movementActionId);
}
