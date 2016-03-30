package cl.bennu.plcbus.core.business.impl;

import cl.bennu.plcbus.common.Constants;
import cl.bennu.plcbus.common.domain.*;
import cl.bennu.plcbus.common.domain.Map;
import cl.bennu.plcbus.common.domain.summary.ProgrammingSummary;
import cl.bennu.plcbus.common.domain.summary.TimeSummary;
import cl.bennu.plcbus.common.dto.ContextDTO;
import cl.bennu.plcbus.common.enums.*;
import cl.bennu.plcbus.core.business.helper.ConsumerHelper;
import cl.bennu.plcbus.core.business.helper.EventHelper;
import cl.bennu.plcbus.core.business.iface.IConfigurationService;
import cl.bennu.plcbus.core.business.iface.IControlService;
import cl.bennu.plcbus.core.persistence.iface.*;
import cl.bennu.plcbus.domain.StatusResponse4Unit;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.BooleanUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public class ConfigurationService implements IConfigurationService {

    private final static IConfigurationService instance = new ConfigurationService();
    private IDeviceDAO deviceDAO;
    private IStatusRefreshDAO statusRefreshDAO;
    private IStatusRefreshDetailDAO statusRefreshDetailDAO;
    private IProgrammingDAO programmingDAO;
    private IProgrammingDetailDAO programmingDetailDAO;
    private IPropertyDAO propertyDAO;
    private ICoordinateDAO coordinateDAO;
    private IScheduledShutdownDeviceDAO scheduledShutdownDeviceDAO;
    private IEventDAO eventDAO;
    private IConsumerDAO consumerDAO;
    private IMovementActionDAO movementActionDAO;
    private IMovementActionDetailDAO movementActionDetailDAO;

    private IControlService controlService;

    public static synchronized IConfigurationService getInstance() {
        return instance;
    }

    private ConfigurationService() {
    }

    @Override
    public StatusRefresh getStatusRefreshLasted(ContextDTO contextDTO) {
        return statusRefreshDAO.getLasted();
    }

    @Override
    public synchronized void synchronizedDevice(ContextDTO contextDTO) {
        StatusRefresh sr = statusRefreshDAO.getLasted();
        if (sr != null && sr.getEnd() == null) {
            if (((sr.getStart().getTime() / 1000) + 3600) < (new Date().getTime() / 1000)) {
                statusRefreshDetailDAO.deleteByStatusRefreshId(sr.getId());
                statusRefreshDAO.delete(sr.getId());
            } else {
                // si esta en curso la sincronización, se cierra el proceso
                return;
            }
        }

        // inicia nuevo proceso de sincronizacion
        StatusRefresh statusRefresh = new StatusRefresh();
        statusRefresh.setStart(new Date());
        statusRefreshDAO.save(statusRefresh);

        HashMap<String, String> deviceLetter = new HashMap<String, String>();

        // busca todos los dispositivos activos y los guarda en el detalle de la sincronizacion
        List<Device> deviceList = deviceDAO.findActive();
        for (Device device : deviceList) {
            StatusRefreshDetail statusRefreshDetail = new StatusRefreshDetail();
            statusRefreshDetail.setStatusRefresh(statusRefresh);
            statusRefreshDetail.setDevice(device);
            statusRefreshDetail.setSynchronizedEnum(SynchronizedEnum.PENDENT);
            statusRefreshDetailDAO.save(statusRefreshDetail);

            String letter = device.getCode().substring(0, 1);
            deviceLetter.put(letter, letter);
        }
        //deviceLetter.put("A", "A");
        /*
        HashMap<String, String> deviceLetter = new HashMap<String, String>();
        // buscamos todos los dispotividos ingresados en el detalle de la sincronizacion y
        // verificamos los estados cada la frecuencia indicada en Constants.SYNCHRONIZED_DELAY
        StatusRefresh statusRefreshPersist = statusRefreshDAO.get(statusRefresh.getId());
        for (StatusRefreshDetail statusRefreshDetail : statusRefreshPersist.getStatusRefreshDetailList()) {
            String letter = statusRefreshDetail.getDevice().getName().substring(0,1);

            deviceLetter.put(letter, letter);
        }
          */
        StatusRefresh statusRefreshPersist = statusRefreshDAO.get(statusRefresh.getId());

        boolean result;
        Device device;
        for (String s : deviceLetter.keySet()) {
            StatusResponse4Unit statusResponse4Unit = controlService.statusBySynchronized(contextDTO, s, EventTypeEnum.AUTOMATIC_GENERAL_STATUS);

            String letter = statusResponse4Unit.getHomeLetter();

            try {
                for (int i = 1; i <= 16; i++) {
                    //result = statusResponse4Unit.getUnit1();
                    //System.out.println("propertyUtils," + letter + i);
                    result = (Boolean) PropertyUtils.getSimpleProperty(statusResponse4Unit, "unit" + i);
                    //System.out.println("getDevice," + letter + i);
                    device = deviceDAO.get(letter + i);
                    if (device != null) {
                        if (!GeneralDeviceTypeEnum.CURTAIN.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                                && !GeneralDeviceTypeEnum.CAMERA.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                                && !GeneralDeviceTypeEnum.IR.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                                && !GeneralDeviceTypeEnum.SENSOR.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                                ) {
                            // las cortinas electricas no responden si estan abiertas o cerradas, solo
                            // la condicion electrica del momento (abriendo/cerrando o no)
                            // tambien se omiten camaras e infrarojos

                            //System.out.println("check," + letter + i);
                            check(device, result);
                        }
                    }
                    //System.out.println("mark," + letter + i);
                    mark(device, statusRefreshPersist);
                    //System.out.println("sleep," + letter + i);

                    TimeUnit.SECONDS.sleep(Constants.SYNCHRONIZED_DELAY_4_UNIT);
                }
            } catch (Exception e) {
                System.out.println("Error en for, " + e.getCause());
                e.printStackTrace();
            }

            try {
                TimeUnit.SECONDS.sleep(Constants.SYNCHRONIZED_DELAY_4_LETTER);
            } catch (InterruptedException e) {
                System.out.println("Error en sleep, " + e.getCause());
                e.printStackTrace();
            }
        }

        for (StatusRefreshDetail statusRefreshDetail : statusRefreshPersist.getStatusRefreshDetailList()) {
            statusRefreshDetail.setSynchronizedEnum(SynchronizedEnum.FINISH);
            statusRefreshDetailDAO.save(statusRefreshDetail);
        }
        /*
        for (StatusRefreshDetail statusRefreshDetail : statusRefreshPersist.getStatusRefreshDetailList()) {

            Device device = statusRefreshDetail.getDevice();

            System.out.println("Status|Noize|Signal [" + Calendar.getInstance().get(Calendar.HOUR) + "] " + device.getName());
            controlService.statusBySynchronized(contextDTO, device.getId());
            //controlService.noize(device.getId());
            //controlService.signal(device.getId());

            statusRefreshDetail.setSynchronizedEnum(SynchronizedEnum.FINISH);
            statusRefreshDetailDAO.save(statusRefreshDetail);

            try {
                TimeUnit.SECONDS.sleep(Constants.SYNCHRONIZED_DELAY);
            } catch (InterruptedException e) {
                System.out.println("Error en sleep, " + e.getCause());
                e.printStackTrace();
            }
        }
        */
        StatusRefresh statusRefresh1 = new StatusRefresh();
        statusRefresh1.setId(statusRefreshPersist.getId());
        statusRefresh1.setStart(statusRefreshPersist.getStart());
        statusRefresh1.setElements(statusRefreshPersist.getElements());
        statusRefresh1.setMinutes(statusRefreshPersist.getMinutes());
        statusRefresh1.setCreate(statusRefreshPersist.getCreate());
        statusRefresh1.setUpdate(statusRefreshPersist.getUpdate());
        statusRefresh1.setCreatorUser(statusRefreshPersist.getCreatorUser());
        statusRefresh1.setUpdateUser(statusRefreshPersist.getUpdateUser());

        // marca finalizacion de sincronizacion
        statusRefresh1.setEnd(new Date());
        statusRefreshDAO.save(statusRefresh1);
    }

    private void check(Device device, boolean result) {
        if (device == null) return;

        boolean status = false;
        if (device.getDeviceTypeEnum().getGeneralDeviceTypeEnum().getTimeControl()) {
            if (result) {
                status = true;
                if (!BooleanUtils.isTrue(device.getStatus())) {
                    device.setLastPower(new Date());
                }
            } else {
                device.setLastPower(null);
            }
        } else {
            status = result;
        }

        device.setStatus(status);
        deviceDAO.save(device);

        ConsumerHelper.markConsumerByStatus(consumerDAO, device, status);
    }


    private void mark(Device device, StatusRefresh statusRefresh) {
        if (device == null) return;

        for (StatusRefreshDetail statusRefreshDetail : statusRefresh.getStatusRefreshDetailList()) {
            if (statusRefreshDetail.getDevice().getId().equals(device.getId())) {
                statusRefreshDetail.setSynchronizedEnum(SynchronizedEnum.FINISH);
                statusRefreshDetailDAO.save(statusRefreshDetail);
                break;
            }
        }
    }

    @Override
    public List<Programming> getAllProgramming(ContextDTO contextDTO) {
        return programmingDAO.getAll();
    }

    @Override
    public Programming getProgramming(ContextDTO contextDTO, Long programmingId) {
        Programming programming = programmingDAO.get(programmingId);

        /*
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(programming.getProgrammingDetailList().get(0).getOn());

        timeSummary.setStartMinuteEnum(MinuteEnum.valueOf((long) calendar.get(Calendar.HOUR_OF_DAY)));
        */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT);
        String strOn = simpleDateFormat.format(programming.getProgrammingDetailList().get(0).getOn());
        String strOff = simpleDateFormat.format(programming.getProgrammingDetailList().get(0).getOff());

        long hourOn = Integer.parseInt(strOn.split(":")[0]);
        long minuteOn = Integer.parseInt(strOn.split(":")[1]);

        long hourOff = Integer.parseInt(strOff.split(":")[0]);
        long minuteOff = Integer.parseInt(strOff.split(":")[1]);

        TimeSummary timeSummary = new TimeSummary();
        timeSummary.setStartMinuteEnum(MinuteEnum.valueOf4Minute(minuteOn));
        timeSummary.setStartHourEnum(HourEnum.valueOf4Hour(hourOn));
        timeSummary.setEndMinuteEnum(MinuteEnum.valueOf4Minute(minuteOff));
        timeSummary.setEndHourEnum(HourEnum.valueOf4Hour(hourOff));
        programming.setTimeSummary(timeSummary);

        return programming;
    }

    @Override
    public Programming getProgrammingByDeviceId(ContextDTO contextDTO, Long deviceId) {
        return programmingDAO.getByDeviceId(deviceId);
    }

    @Override
    public void updateCalendarProgramming(ContextDTO contextDTO, Long programmingId, TimeSummary timeSummary) {
        Programming programming = programmingDAO.get(programmingId);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT);
        Date on;
        Date off;
        try {
            on = simpleDateFormat.parse(timeSummary.getStartHourEnum().getName() + ":" + timeSummary.getStartMinuteEnum().getName());
            off = simpleDateFormat.parse(timeSummary.getEndHourEnum().getName() + ":" + timeSummary.getEndMinuteEnum().getName());
        } catch (Exception e) {
            on = new Date();
            off = new Date();
        }

        for (ProgrammingDetail programmingDetail : programming.getProgrammingDetailList()) {
            programmingDetail.setOn(on);
            programmingDetail.setOff(off);

            programmingDetailDAO.save(programmingDetail);
        }
    }

    @Override
    public void updateStatusProgramming(ContextDTO contextDTO, Long programmingId, Boolean active) {
        Programming programming = programmingDAO.get(programmingId);
        programming.setActive(active);

        programmingDAO.save(programming);
    }

    @Override
    public void saveProgramming(ContextDTO contextDTO, ProgrammingSummary programmingSummary) {
        /*
        no eliminamos las programaciones para poder tener varias acciones sobre un mismo dispositivo

        Programming programming = programmingDAO.getByDeviceId(programmingSummary.getDeviceId());
        if (programming == null) {
            programming = new Programming();
        } else {
            programmingDetailDAO.deleteByProgrammingId(programming.getId());
            programmingDAO.delete(programming.getId());
        }
        */
        Programming programming = new Programming();
        Device device = new Device();
        device.setId(programmingSummary.getDeviceId());
        programming.setDevice(device);
        programming.setReason(programmingSummary.getReason());
        programming.setProgrammingTypeEnum(programmingSummary.getProgrammingTypeEnum());
        programming.setActive(programmingSummary.getActive());
        if (BooleanUtils.isTrue(programmingSummary.getTemperature())) {
            programming.setTemperature(Boolean.TRUE);
            programming.setRangeTypeEnum(programmingSummary.getRangeTypeEnum());
            if (RangeTypeEnum.BETWEEN.equals(programmingSummary.getRangeTypeEnum())) {
                programming.setMin(programmingSummary.getTemperatureSummary().getMin());
                programming.setMax(programmingSummary.getTemperatureSummary().getMax());
            } else if (RangeTypeEnum.LESS.equals(programmingSummary.getRangeTypeEnum())) {
                //programming.setMin(null);
                programming.setMax(programmingSummary.getTemperatureSummary().getMax());
            } else if (RangeTypeEnum.GREATER.equals(programmingSummary.getRangeTypeEnum())) {
                programming.setMin(programmingSummary.getTemperatureSummary().getMin());
                //programming.setMax(null);
            }
        } else {
            programming.setTemperature(Boolean.FALSE);
        }

        programming.setCreate(new Date());
        programming.setCreatorUser(contextDTO.getUser());

        programmingDAO.save(programming);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT);
        Date on;
        Date off;
        try {
            on = simpleDateFormat.parse(programmingSummary.getTimeSummary().getStartHourEnum().getName() + ":" + programmingSummary.getTimeSummary().getStartMinuteEnum().getName());
            off = simpleDateFormat.parse(programmingSummary.getTimeSummary().getEndHourEnum().getName() + ":" + programmingSummary.getTimeSummary().getEndMinuteEnum().getName());
        } catch (Exception e) {
            on = new Date();
            off = new Date();
        }

        List<DayEnum> dayEnumList = new ArrayList<DayEnum>();
        if (ProgrammingTypeEnum.DAILY.equals(programmingSummary.getProgrammingTypeEnum())) {
            dayEnumList = DayEnum.valuesList();
        } else if (ProgrammingTypeEnum.WEEKLY.equals(programmingSummary.getProgrammingTypeEnum())) {
            dayEnumList = programmingSummary.getDayList();
        }

        for (DayEnum dayEnum : dayEnumList) {
            ProgrammingDetail programmingDetail = new ProgrammingDetail();
            programmingDetail.setProgramming(programming);
            programmingDetail.setDayEnum(dayEnum);

            programmingDetail.setOn(on);
            programmingDetail.setOff(off);

            programmingDetailDAO.save(programmingDetail);
        }
    }

    @Override
    public void deleteProgramming(ContextDTO contextDTO, Long programmingId) {
        programmingDetailDAO.deleteByProgrammingId(programmingId);
        programmingDAO.delete(programmingId);
    }

    @Override
    public Coordinate getCoordenateByDeviceIdAndMapId(ContextDTO contextDTO, Long deviceId, Long mapId) {
        return coordinateDAO.getByDeviceIdAndMapId(deviceId, mapId);
    }

    @Override
    public void deleteCoordenateByDeviceIdAndMapId(ContextDTO contextDTO, Long deviceId, Long mapId) {
        coordinateDAO.deleteByDeviceIdAndMapId(deviceId, mapId);
    }

    @Override
    public void deleteCoordenateById(ContextDTO contextDTO, Long coordenateId) {
        coordinateDAO.delete(coordenateId);
    }

    @Override
    public void saveCoordenate(ContextDTO contextDTO, Coordinate coordinate) {
        coordinateDAO.save(coordinate);
    }

    @Override
    public List<ScheduledShutdownDevice> getAllScheduledShutdownDevice(ContextDTO contextDTO) {
        return scheduledShutdownDeviceDAO.getAll();
    }

    @Override
    public ScheduledShutdownDevice getScheduledShutdownDevice(ContextDTO contextDTO, Long scheduledShutdownDeviceId) {
        return scheduledShutdownDeviceDAO.get(scheduledShutdownDeviceId);
    }

    @Override
    public void saveScheduledShutdownDevice(ContextDTO contextDTO, ProgrammingSummary programmingSummary) {
        ScheduledShutdownDevice scheduledShutdownDevice = new ScheduledShutdownDevice();
        scheduledShutdownDevice.setCreate(new Date());
        scheduledShutdownDevice.setCreatorUser(contextDTO.getUser());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT);
        Date off;
        try {
            off = simpleDateFormat.parse(programmingSummary.getTimeSummary().getEndHourEnum().getName() + ":" + programmingSummary.getTimeSummary().getEndMinuteEnum().getName());
        } catch (Exception e) {
            off = new Date();
        }

        Device device = new Device();
        device.setId(programmingSummary.getDeviceId());
        scheduledShutdownDevice.setDevice(device);
        scheduledShutdownDevice.setOff(off);

        scheduledShutdownDeviceDAO.save(scheduledShutdownDevice);
    }

    @Override
    public void deleteScheduledShutdownDevice(ContextDTO contextDTO, Long scheduledShutdownDeviceId) {
        scheduledShutdownDeviceDAO.delete(scheduledShutdownDeviceId);
    }

    @Override
    public void saveEvent(ContextDTO contextDTO, Event event) {
        eventDAO.save(event);
    }

    @Override
    public List<MovementAction> getAllMovementAction(ContextDTO context) {
        return movementActionDAO.getAll();
    }

    @Override
    public void saveMovementAction(ContextDTO context, MovementAction movementAction) {
        movementAction.setCreate(new Date());
        movementAction.setCreatorUser(context.getUser());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.TIME_FORMAT);
        Date start;
        Date end;
        try {
            start = simpleDateFormat.parse(movementAction.getTimeSummary().getStartHourEnum().getName() + ":" + movementAction.getTimeSummary().getStartMinuteEnum().getName());
            end = simpleDateFormat.parse(movementAction.getTimeSummary().getEndHourEnum().getName() + ":" + movementAction.getTimeSummary().getEndMinuteEnum().getName());
        } catch (Exception e) {
            start = new Date();
            end = new Date();
        }

        movementAction.setStart(start);
        movementAction.setEnd(end);

        movementActionDAO.save(movementAction);

        for (MovementActionDetail movementActionDetail : movementAction.getMovementActionDetailList()) {
            movementActionDetail.setMovementAction(movementAction);
            if (!MovementActionTypeEnum.ON.equals(movementActionDetail.getMovementActionTypeEnum()) && !MovementActionTypeEnum.OFF.equals(movementActionDetail.getMovementActionTypeEnum())) {
                movementActionDetail.setDevice(null);
            }
            movementActionDetailDAO.save(movementActionDetail);
        }
    }

    @Override
    public void updateStatusMovementAction(ContextDTO context, Long movementActionId, Boolean movementActionActive) {
        MovementAction movementAction = movementActionDAO.get(movementActionId);
        movementAction.setActive(movementActionActive);

        movementActionDAO.save(movementAction);
    }

    @Override
    public void deleteMovementAction(ContextDTO context, Long movementActionId) {
        movementActionDetailDAO.deleteByMovementActionId(movementActionId);
        movementActionDAO.delete(movementActionId);
    }

    public IScheduledShutdownDeviceDAO getScheduledShutdownDeviceDAO() {
        return scheduledShutdownDeviceDAO;
    }

    public void setScheduledShutdownDeviceDAO(IScheduledShutdownDeviceDAO scheduledShutdownDeviceDAO) {
        this.scheduledShutdownDeviceDAO = scheduledShutdownDeviceDAO;
    }

    public IControlService getControlService() {
        return controlService;
    }

    public ICoordinateDAO getCoordinateDAO() {
        return coordinateDAO;
    }

    public void setCoordinateDAO(ICoordinateDAO coordinateDAO) {
        this.coordinateDAO = coordinateDAO;
    }

    public void setControlService(IControlService controlService) {
        this.controlService = controlService;
    }

    public IStatusRefreshDAO getStatusRefreshDAO() {
        return statusRefreshDAO;
    }

    public void setStatusRefreshDAO(IStatusRefreshDAO statusRefreshDAO) {
        this.statusRefreshDAO = statusRefreshDAO;
    }

    public IStatusRefreshDetailDAO getStatusRefreshDetailDAO() {
        return statusRefreshDetailDAO;
    }

    public void setStatusRefreshDetailDAO(IStatusRefreshDetailDAO statusRefreshDetailDAO) {
        this.statusRefreshDetailDAO = statusRefreshDetailDAO;
    }

    public IDeviceDAO getDeviceDAO() {
        return deviceDAO;
    }

    public void setDeviceDAO(IDeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public IProgrammingDAO getProgrammingDAO() {
        return programmingDAO;
    }

    public void setProgrammingDAO(IProgrammingDAO programmingDAO) {
        this.programmingDAO = programmingDAO;
    }

    public IProgrammingDetailDAO getProgrammingDetailDAO() {
        return programmingDetailDAO;
    }

    public void setProgrammingDetailDAO(IProgrammingDetailDAO programmingDetailDAO) {
        this.programmingDetailDAO = programmingDetailDAO;
    }

    public IPropertyDAO getPropertyDAO() {
        return propertyDAO;
    }

    public void setPropertyDAO(IPropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }

    public IEventDAO getEventDAO() {
        return eventDAO;
    }

    public void setEventDAO(IEventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public IConsumerDAO getConsumerDAO() {
        return consumerDAO;
    }

    public void setConsumerDAO(IConsumerDAO consumerDAO) {
        this.consumerDAO = consumerDAO;
    }

    public IMovementActionDAO getMovementActionDAO() {
        return movementActionDAO;
    }

    public void setMovementActionDAO(IMovementActionDAO movementActionDAO) {
        this.movementActionDAO = movementActionDAO;
    }

    public IMovementActionDetailDAO getMovementActionDetailDAO() {
        return movementActionDetailDAO;
    }

    public void setMovementActionDetailDAO(IMovementActionDetailDAO movementActionDetailDAO) {
        this.movementActionDetailDAO = movementActionDetailDAO;
    }
}
