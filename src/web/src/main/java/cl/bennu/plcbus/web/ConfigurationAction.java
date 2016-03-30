package cl.bennu.plcbus.web;

import cl.bennu.plcbus.base.BaseAction;
import cl.bennu.plcbus.bean.ClientDataBean;
import cl.bennu.plcbus.bean.ConfigurationBean;
import cl.bennu.plcbus.bean.DeviceDistributionBean;
import cl.bennu.plcbus.common.domain.*;
import cl.bennu.plcbus.common.domain.helper.TemperatureHelper;
import cl.bennu.plcbus.common.domain.summary.ProgrammingSummary;
import cl.bennu.plcbus.common.domain.summary.TemperatureSummary;
import cl.bennu.plcbus.common.enums.*;
import cl.bennu.plcbus.constant.Constant;
import cl.bennu.plcbus.core.business.iface.IConfigurationService;
import cl.bennu.plcbus.core.business.iface.IControlService;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 03-07-13
 * Time: 03:21 AM
 */
public class ConfigurationAction extends BaseAction {

    private String CONFIGURATION_SUCCESS = "configurationSuccess";
    private String PROPERTY_DATA_SUCCESS = "propertyDataSuccess";
    private String PROGRAMMING_SUCCESS = "programmingSuccess";
    private String SYNCHRONIZED_SUCCESS = "synchronizedSuccess";
    private String SYNCHRONIZED_LIST = "synchronizedList";
    private String PROGRAMMING_LIST = "programmingList";
    private String DEVICE_DISTRIBUTION_SUCCESS = "deviceDistributionSuccess";
    private String SCHEDULED_SHUTDOWN_DEVICE_SUCCESS = "scheduledShutdownDeviceSuccess";
    private String SCHEDULED_SHUTDOWN_DEVICE_LIST = "scheduledShutdownDeviceList";
    private String MOVEMENT_ACTION_SUCCESS = "movementActionSuccess";
    private String MOVEMENT_ACTION_LIST = "movementActionList";
    private String MOVEMENT_ACTION_DETAIL_LIST = "movementActionDetailList";


    private ConfigurationBean configurationBean = new ConfigurationBean();
    private ClientDataBean clientDataBean = new ClientDataBean();
    private DeviceDistributionBean deviceDistributionBean = new DeviceDistributionBean();

    private IControlService controlService;
    private IConfigurationService configurationService;
    private IMaintainerService maintainerService;

    @Override
    protected void initial() throws Exception {

    }

    public String startConfiguration() throws Exception {
        List programmingList = configurationService.getAllProgramming(getContext());
        List scheduledShutdownList = configurationService.getAllScheduledShutdownDevice(getContext());
        List movementActionList = configurationService.getAllMovementAction(getContext());

        Property property = maintainerService.getProperty(getContext());

        configurationBean.setProgrammingCount(programmingList == null ? 0L : programmingList.size());
        configurationBean.setScheduledShutdownCount(scheduledShutdownList == null ? 0L : scheduledShutdownList.size());
        configurationBean.setMovementActionCount(movementActionList == null ? 0L : movementActionList.size());
        configurationBean.setProperty(property);

        return CONFIGURATION_SUCCESS;
    }

    public String startPropertyData() throws Exception {
        Property property = maintainerService.getProperty(getContext());
        configurationBean.setProperty(property);

        return PROPERTY_DATA_SUCCESS;
    }

    public void savePropertyData() throws Exception {
        maintainerService.saveProperty(getContext(), configurationBean.getProperty());
        serialize(SUCCESS_JSON);
    }

    public String startSynchronized() throws Exception {
        StatusRefresh statusRefresh = configurationService.getStatusRefreshLasted(getContext());
        fillProcessData(statusRefresh);

        return SYNCHRONIZED_SUCCESS;
    }

    private void fillProcessData(StatusRefresh statusRefresh) {
        if (statusRefresh == null) return;

        if (statusRefresh.getEnd() == null) {
            configurationBean.setSynchronizedInCurse(true);

            if (((statusRefresh.getStart().getTime() / 1000) + 3600) < (new Date().getTime() / 1000)) {
                configurationBean.setSynchronizedTimeOut(true);
            } else {
                // si esta en curso la sincronización, se cierra el proceso
                configurationBean.setSynchronizedTimeOut(false);
            }

            Date date = new Date();
            long millisDiff = date.getTime() - statusRefresh.getStart().getTime();
            configurationBean.setMinutes(millisDiff / 1000 / 60);

            int size = statusRefresh.getStatusRefreshDetailList().size();
            int pendent = 0;
            for (StatusRefreshDetail statusRefreshDetail : statusRefresh.getStatusRefreshDetailList()) {
                if (statusRefreshDetail.getSynchronizedEnum().equals(SynchronizedEnum.FINISH)) {
                    pendent++;
                }
            }

            long percentage = pendent * 100L / size;
            configurationBean.setPercentage(percentage);
            configurationBean.setPercentageDiff(100L - percentage);
        } else {
            configurationBean.setSynchronizedInCurse(false);
            configurationBean.setSynchronizedTimeOut(false);

            long millisDiff = statusRefresh.getEnd().getTime() - statusRefresh.getStart().getTime();
            configurationBean.setMinutes(millisDiff / 1000 / 60);
            configurationBean.setPercentage(100L);
            configurationBean.setPercentageDiff(0L);

            configurationBean.setStartSynchronized(statusRefresh.getStart());
        }

        configurationBean.setStatusRefresh(statusRefresh);
    }

    public String synchronizedList() throws Exception {
        StatusRefresh statusRefresh = configurationService.getStatusRefreshLasted(getContext());
        fillProcessData(statusRefresh);

        return SYNCHRONIZED_LIST;
    }

    public void forceSynchronized() throws Exception {
        configurationService.synchronizedDevice(getContext());
        serialize(SUCCESS_JSON);
    }

    public String startProgramming() throws Exception {
        List<Programming> programmingList = configurationService.getAllProgramming(getContext());
        configurationBean.setProgrammingList(programmingList);

        List<Device> deviceList = maintainerService.findActiveDevice(getContext());
        configurationBean.setDeviceList(deviceList);

        configurationBean.setDayList(DayEnum.valuesList());
        configurationBean.setHourList(HourEnum.valuesList());
        configurationBean.setMinuteList(MinuteEnum.valuesList());

        // filtros de temperatura
        configurationBean.setTemperatureList(TemperatureHelper.getTemperatureList());
        TemperatureSummary temperatureSummary = new TemperatureSummary();
        temperatureSummary.setMin(0);
        temperatureSummary.setMax(20);

        ProgrammingSummary programmingSummary = new ProgrammingSummary();
        programmingSummary.setTemperatureSummary(temperatureSummary);
        configurationBean.setProgrammingSummary(programmingSummary);

        return PROGRAMMING_SUCCESS;
    }

    public void saveProgramming() throws Exception {
        ProgrammingSummary programmingSummary = configurationBean.getProgrammingSummary();

        List<DayEnum> dayEnums = new ArrayList<DayEnum>();
        if (StringUtils.isNotBlank(programmingSummary.getDays())) {
            String[] dyasId = programmingSummary.getDays().split(",");
            for (String s : dyasId) {
                dayEnums.add(DayEnum.valueOf(new Long(s)));
            }
            programmingSummary.setDayList(dayEnums);
        }

        // busca una programacion para el dispositivo, se podrian enviar validaciones para alertar que se
        // esta cambiando una programacion a un dispositivo existente
        /*
        Programming programming = configurationService.getProgrammingByDeviceId(getContext(), programmingSummary.getDeviceId());
        if (programming != null) {
            configurationService.deleteProgramming(getContext(), programming.getId());
        }
        */

        configurationService.saveProgramming(getContext(), programmingSummary);
        serialize(SUCCESS_JSON);
    }

    public void deleteProgramming() throws Exception {
        configurationService.deleteProgramming(getContext(), configurationBean.getProgrammingId());
        serialize(SUCCESS_JSON);
    }

    public void updateCalendarProgramming() throws Exception {
        configurationService.updateCalendarProgramming(getContext(), configurationBean.getProgrammingId(), configurationBean.getTimeSummary());
        serialize(SUCCESS_JSON);
    }

    public void updateStatusProgramming() throws Exception {
        configurationService.updateStatusProgramming(getContext(), configurationBean.getProgrammingId(), configurationBean.getProgrammingActive());
        serialize(SUCCESS_JSON);
    }

    public void getProgramming() throws Exception {
        Programming programming = configurationService.getProgramming(getContext(), configurationBean.getProgrammingId());
        serialize(programming);
    }

    public String programmingList() throws Exception {
        List<Programming> programmingList = configurationService.getAllProgramming(getContext());
        configurationBean.setProgrammingList(programmingList);

        return PROGRAMMING_LIST;
    }

    public String startPassChange() throws Exception {


        return PROGRAMMING_SUCCESS;
    }

    public String startLightDistribution() throws Exception {
        // busca todos los dispositivos del nivel
        List<Device> deviceList = maintainerService.findDevice(getContext(), deviceDistributionBean.getLevelId());
        deviceDistributionBean.setDeviceList(deviceList);

        // buscamos el nivel con sus mapas
        Level level = maintainerService.getLevelById(getContext(), deviceDistributionBean.getLevelId());
        deviceDistributionBean.setLevel(level);
        if (deviceDistributionBean.getMapId() == null && level.getMapList() != null && level.getMapList().size() > 0) {
            deviceDistributionBean.setMapId(level.getMapList().get(0).getId());
        }

        return DEVICE_DISTRIBUTION_SUCCESS;
    }

    public void saveCoordenate() throws Exception {
        Long deviceId = deviceDistributionBean.getCoordinate().getDevice().getId();
        Long mapId = deviceDistributionBean.getCoordinate().getMap().getId();

        //if (!BooleanUtils.isTrue(deviceDistributionBean.getClone())) {
        //para aplicar la clonacion hay que modificar los js para la visualizacion de los dispositivos
            // buscamos una coordenada para el mapa y dispositivo para eliminarla y notificar al usuario
            Coordinate coordinate = configurationService.getCoordenateByDeviceIdAndMapId(getContext(), deviceId, mapId);
            if (coordinate != null) {
                configurationService.deleteCoordenateByDeviceIdAndMapId(getContext(), deviceId, mapId);
            }
        //}

        configurationService.saveCoordenate(getContext(), deviceDistributionBean.getCoordinate());
        deviceDistributionBean.setMapId(mapId);
        //deviceDistributionBean.setLevelId(deviceDistributionBean.getCoordinate().getMap().getLevel().getId());

        //serialize(deviceDistributionBean);

        serialize(SUCCESS_JSON);
    }

    public String deleteCoordenate() throws Exception {
        configurationService.deleteCoordenateById(getContext(), deviceDistributionBean.getCoordinateId());

        return SUCCESS_JSON;
    }


    public String startScheduledShutdownDevice() throws Exception {
        // busca todos los dispositivos activos
        List<Device> deviceList = maintainerService.findActiveDevice(getContext());
        configurationBean.setDeviceList(deviceList);

        List<ScheduledShutdownDevice> scheduledShutdownDeviceList = configurationService.getAllScheduledShutdownDevice(getContext());
        configurationBean.setScheduledShutdownDeviceList(scheduledShutdownDeviceList);

        configurationBean.setHourList(HourEnum.valuesList());
        configurationBean.setMinuteList(MinuteEnum.valuesList());


        return SCHEDULED_SHUTDOWN_DEVICE_SUCCESS;
    }

    public void saveScheduledShutdownDevice() throws Exception {
        configurationService.saveScheduledShutdownDevice(getContext(), configurationBean.getProgrammingSummary());
        serialize(SUCCESS_JSON);
    }

    public String scheduledShutdownDeviceList() throws Exception {
        List<ScheduledShutdownDevice> scheduledShutdownDeviceList = configurationService.getAllScheduledShutdownDevice(getContext());
        configurationBean.setScheduledShutdownDeviceList(scheduledShutdownDeviceList);

        return SCHEDULED_SHUTDOWN_DEVICE_LIST;
    }

    public void deleteScheduledShutdownDevice() throws Exception {
        configurationService.deleteScheduledShutdownDevice(getContext(), configurationBean.getScheduledShutdownDeviceId());
        serialize(SUCCESS_JSON);
    }

    public String startMovementAction() throws Exception {
        // busca todos los dispositivos activos
        List<Device> deviceList = maintainerService.findActiveDevice(getContext());

        List<Device> sensorDeviceList = new ArrayList<Device>();
        List<Device> otherDeviceList = new ArrayList<Device>();
        for (Device device : deviceList) {
            if (GeneralDeviceTypeEnum.SENSOR.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())) {
                sensorDeviceList.add(device);
            } else {
                otherDeviceList.add(device);
            }
        }

        configurationBean.setDeviceList(otherDeviceList);
        configurationBean.setDeviceMovementList(sensorDeviceList);

        configurationBean.setMovementActionTypeList(MovementActionTypeEnum.valuesList());
        configurationBean.setMovementTypeList(MovementTypeEnum.valuesList());

        configurationBean.setHourList(HourEnum.valuesList());
        configurationBean.setMinuteList(MinuteEnum.valuesList());

        // busca acciones de movimiento activas
        List<MovementAction> movementActionList = configurationService.getAllMovementAction(getContext());
        configurationBean.setMovementActionList(movementActionList);

        return MOVEMENT_ACTION_SUCCESS;
    }

    public String movementActionList() throws Exception {
        List<MovementAction> movementActionList = configurationService.getAllMovementAction(getContext());
        configurationBean.setMovementActionList(movementActionList);

        return MOVEMENT_ACTION_LIST;
    }

    public String addMovementAction() throws Exception {
        List<MovementActionDetail> movementActionDetailList = (List<MovementActionDetail>)httpSession.getAttribute(Constant.MOVEMENT_ACTION_LIST);
        if (movementActionDetailList == null)  {
            movementActionDetailList = new ArrayList<MovementActionDetail>();
            httpSession.setAttribute(Constant.MOVEMENT_ACTION_LIST, movementActionDetailList);
        }

        Device device = maintainerService.getDeviceById(getContext(), configurationBean.getMovementActionDetail().getDevice().getId());

        MovementActionDetail movementActionDetail = new MovementActionDetail();
        movementActionDetail.setDevice(device);
        movementActionDetail.setMovementActionTypeEnum(configurationBean.getMovementActionDetail().getMovementActionTypeEnum());

        movementActionDetailList.add(movementActionDetail);

        return MOVEMENT_ACTION_DETAIL_LIST;
    }

    public String removeMovementAction() throws Exception {
        List<MovementActionDetail> movementActionDetailList = (List<MovementActionDetail>)httpSession.getAttribute(Constant.MOVEMENT_ACTION_LIST);
        movementActionDetailList.remove(configurationBean.getMovementActionDetailIndex().intValue());

        if(movementActionDetailList.isEmpty()) httpSession.removeAttribute(Constant.MOVEMENT_ACTION_LIST);

        return MOVEMENT_ACTION_DETAIL_LIST;
    }

    public void saveMovementAction() throws Exception {
        MovementAction movementAction = configurationBean.getMovementAction();
        movementAction.setMovementActionDetailList((List<MovementActionDetail>) httpSession.getAttribute(Constant.MOVEMENT_ACTION_LIST));

        configurationService.saveMovementAction(getContext(), movementAction);

        httpSession.removeAttribute(Constant.MOVEMENT_ACTION_LIST);

        serialize(SUCCESS_JSON);
    }

    public void updateStatusMovementAction() throws Exception {
        configurationService.updateStatusMovementAction(getContext(), configurationBean.getMovementActionId(), configurationBean.getMovementActionActive());
        serialize(SUCCESS_JSON);
    }

    public void deleteMovementAction() throws Exception {
        configurationService.deleteMovementAction(getContext(), configurationBean.getMovementActionId());
        serialize(SUCCESS_JSON);
    }





    public DeviceDistributionBean getDeviceDistributionBean() {
        return deviceDistributionBean;
    }

    public void setDeviceDistributionBean(DeviceDistributionBean deviceDistributionBean) {
        this.deviceDistributionBean = deviceDistributionBean;
    }

    public ClientDataBean getClientDataBean() {
        return clientDataBean;
    }

    public void setClientDataBean(ClientDataBean clientDataBean) {
        this.clientDataBean = clientDataBean;
    }

    public ConfigurationBean getConfigurationBean() {
        return configurationBean;
    }

    public void setConfigurationBean(ConfigurationBean configurationBean) {
        this.configurationBean = configurationBean;
    }

    public IControlService getControlService() {
        return controlService;
    }

    public void setControlService(IControlService controlService) {
        this.controlService = controlService;
    }

    public IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public IMaintainerService getMaintainerService() {
        return maintainerService;
    }

    public void setMaintainerService(IMaintainerService maintainerService) {
        this.maintainerService = maintainerService;
    }
}
