package cl.bennu.plcbus.core.business.impl;

import cl.bennu.plcbus.comm.IPLCBusController;
import cl.bennu.plcbus.comm.PLCBusController;
import cl.bennu.plcbus.common.Constants;
import cl.bennu.plcbus.common.domain.Consumer;
import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.domain.weather.Weather;
import cl.bennu.plcbus.common.dto.ContextDTO;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.common.enums.GeneralDeviceTypeEnum;
import cl.bennu.plcbus.common.enums.WeatherYahooEnum;
import cl.bennu.plcbus.common.exception.RainException;
import cl.bennu.plcbus.core.business.helper.ConsumerHelper;
import cl.bennu.plcbus.core.business.helper.EventHelper;
import cl.bennu.plcbus.core.business.iface.IControlService;
import cl.bennu.plcbus.core.persistence.iface.*;
import cl.bennu.plcbus.domain.StatusResponse;
import cl.bennu.plcbus.domain.StatusResponse4Unit;
import org.apache.commons.lang3.BooleanUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public class ControlService implements IControlService {

    private final static IControlService instance = new ControlService();
    private IDeviceDAO deviceDAO;
    private ILevelDAO levelDAO;
    private IMapDAO mapDAO;
    private ICoordinateDAO coordinateDAO;
    private IProgrammingDAO programmingDAO;
    private IProgrammingDetailDAO programmingDetailDAO;
    private ISectorDAO sectorDAO;
    private IPropertyDAO propertyDAO;
    private IWeatherDAO weatherDAO;
    private IEventDAO eventDAO;
    private IConsumerDAO consumerDAO;

    public static synchronized IControlService getInstance() {
        return instance;
    }

    private IPLCBusController plcBusController;

    private ControlService() {
        try {
            if (Constants.DEVELOMPENT_MODE) {
                plcBusController = null;
            } else {
                plcBusController = PLCBusController.getInstance(Constants.PORT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //deviceDAO = PLCBusFactory.getInstance().getDeviceDAO();
        //levelDAO = PLCBusFactory.getInstance().getLevelDAO();
    }

    @Override
    public void on(ContextDTO contextDTO, Long deviceId, EventTypeEnum eventTypeEnum, Boolean force) throws RainException {
        on_(contextDTO, deviceId, eventTypeEnum, force);
    }

    @Override
    public void on(ContextDTO contextDTO, Long deviceId, EventTypeEnum eventTypeEnum) throws RainException {
        on_(contextDTO, deviceId, eventTypeEnum, Boolean.FALSE);
    }

    private void on_(ContextDTO contextDTO, Long deviceId, EventTypeEnum eventTypeEnum, Boolean force) throws RainException {
        Device device = deviceDAO.get(deviceId);

        if (!BooleanUtils.isTrue(force)) {
            // solo validamos en caso que no estemos forzando el encendido
            if (GeneralDeviceTypeEnum.IRRIGATION.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())) {
                // validaciones
                List<Weather> weatherList = weatherDAO.findLasted12Hour();
                if (weatherList != null) {
                    for (Weather weather : weatherList) {
                        // recorremos las ultimas 12 horas y buscamos lluvia
                        if (WeatherYahooEnum.rain(weather.getWeatherYahooEnum())) {
                            // guarda evento de cancelado de riego por clima
                            Event event = EventHelper.buildEvent(device, EventTypeEnum.CANCEL_IRRIGATION);
                            eventDAO.save(event);

                            throw new RainException();
                        }
                    }
                }
            }
        }

        try {
            plcBusController.on(device.getCode());
        } catch (Exception e) {
            // sin error controlado
        }

        device.setStatus(true);
        if (device.getDeviceTypeEnum().getGeneralDeviceTypeEnum().getTimeControl()) {
            device.setLastPower(new Date());
        }

        deviceDAO.save(device);

        // guarda evento de encendido
        if (eventTypeEnum != null) {
            Event event = EventHelper.buildEvent(device, eventTypeEnum);
            eventDAO.save(event);
        }

        if (!GeneralDeviceTypeEnum.CURTAIN.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                && !GeneralDeviceTypeEnum.CAMERA.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                && !GeneralDeviceTypeEnum.IR.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                && !GeneralDeviceTypeEnum.SENSOR.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                ) {
            // no se guardan indicadores de consumo para cortinas camaras e infrarojos

            // si no hay dispositivos en consumo, agregamos un para iniciar el calculo de consumo electrico
            List<Consumer> consumerList = consumerDAO.getByCode(device.getCode());
            if (consumerList == null || consumerList.isEmpty()) {
                Consumer consumer = ConsumerHelper.buildConsumer(device);
                consumerDAO.save(consumer);
            }

        }
    }

    @Override
    public void off(ContextDTO contextDTO, Long deviceId, EventTypeEnum eventTypeEnum) {
        Device device = deviceDAO.get(deviceId);

        try {
            plcBusController.off(device.getCode());
        } catch (Exception e) {
            // sin error controlado
        }

        device.setStatus(false);
        device.setAlarmed(false);
        device.setLastPower(null);

        deviceDAO.save(device);

        // guarda evento de apagado
        if (eventTypeEnum != null) {
            Event event = EventHelper.buildEvent(device, eventTypeEnum);
            eventDAO.save(event);
        }

        if (!GeneralDeviceTypeEnum.CURTAIN.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                && !GeneralDeviceTypeEnum.CAMERA.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                && !GeneralDeviceTypeEnum.IR.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                && !GeneralDeviceTypeEnum.SENSOR.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                ) {
            // no se guardan indicadores de consumo para cortinas, camaras, infrarojos y sensores

            // busca si quedo algun dispositivo de consumo sin marcar como finalizado
            List<Consumer> consumerList = consumerDAO.getByCode(device.getCode());
            if (consumerList != null) {
                for (Consumer consumer : consumerList) {
                    // marcamos como el termino del uso del dispositivo
                    consumer.setEnd(new Date());
                    consumerDAO.save(consumer);
                }
            }
        }
    }

    @Override
    public StatusResponse status(ContextDTO contextDTO, Long deviceId) {
        Device device = deviceDAO.get(deviceId);

        StatusResponse result = null;

        try {
            result = plcBusController.status(device.getCode());
        } catch (Exception e) {
            // sin error controlado
        }

        boolean status = false;
        if (device.getDeviceTypeEnum().getGeneralDeviceTypeEnum().getTimeControl()) {
            if (result != null && result.getStatus()) {
                status = true;
                if (!BooleanUtils.isTrue(device.getStatus())) {
                    device.setLastPower(new Date());
                }
            } else {
                device.setLastPower(null);
            }
        }

        device.setStatus(status);
        deviceDAO.save(device);

        // guarda evento de consulta de estado de dispositivo
        Event event = EventHelper.buildEvent(device, EventTypeEnum.STATUS_DEVICE);
        event.setDesc("Estado : " + status);
        eventDAO.save(event);

        ConsumerHelper.markConsumerByStatus(consumerDAO, device, status);

        return result;
    }

    @Override
    public Long noize(ContextDTO contextDTO, Long deviceId) {
        Device device = deviceDAO.get(deviceId);

        Long noize = 0L;
        try {
            noize = plcBusController.noiseLevel(device.getCode());
        } catch (Exception e) {
            // sin error controlado
        }

        device.setNoize(noize);
        deviceDAO.save(device);

        // guarda evento de consulta de estado de dispositivo
        Event event = EventHelper.buildEvent(device, EventTypeEnum.STATUS_DEVICE);
        event.setDesc("Nivel de ruido : " + noize);
        eventDAO.save(event);

        return noize;
    }

    @Override
    public Long signal(ContextDTO contextDTO, Long deviceId) {
        Device device = deviceDAO.get(deviceId);

        Long signal = 0L;
        try {
            signal = plcBusController.signalLevel(device.getCode());
        } catch (Exception e) {
            // sin error controlado
        }

        device.setSignal(signal);
        deviceDAO.save(device);

        // guarda evento de consulta de estado de dispositivo
        Event event = EventHelper.buildEvent(device, EventTypeEnum.STATUS_DEVICE);
        event.setDesc("Nivel de senhal : " + signal);
        eventDAO.save(event);

        return signal;
    }

    @Override
    public void alarmedDevice(ContextDTO contextDTO, Long deviceId) {
        Device device = deviceDAO.get(deviceId);
        device.setAlarmed(true);

        deviceDAO.save(device);

        // guarda evento de dispositivo alarmado
        Event event = EventHelper.buildEvent(device, EventTypeEnum.ALERTED_DEVICE);
        eventDAO.save(event);
    }

    @Override
    public StatusResponse4Unit statusBySynchronized(ContextDTO contextDTO, String deviceLetter, EventTypeEnum eventTypeEnum) {
        try {
            StatusResponse4Unit statusResponse4Unit = plcBusController.enquiryONAddress(deviceLetter);

            // guarda evento de consulta masiva de estados
            Event event = EventHelper.buildEvent(null, eventTypeEnum);
            event.setDesc(statusResponse4Unit.toString());
            eventDAO.save(event);

            return statusResponse4Unit;
        } catch (Exception e) {
            // sin error controlado
        }

        return null;
    }

    @Override
    public void dimmer(ContextDTO contextDTO, Long deviceId, Long brightnessLevel, Long dimmerFadeRate) {
        Device device = deviceDAO.get(deviceId);

        try {
            plcBusController.presetDim(device.getCode(), brightnessLevel, dimmerFadeRate);
        } catch (Exception e) {
            // sin error controlado
        }

        boolean status;
        if (brightnessLevel > 0L) {
            status = true;
            device.setStatus(status);
            if (device.getDeviceTypeEnum().getGeneralDeviceTypeEnum().getTimeControl()) {
                device.setLastPower(new Date());
            }
        } else {
            status = false;
            device.setStatus(status);
            device.setAlarmed(false);
            device.setLastPower(null);
        }
        device.setDimmer(brightnessLevel);
        device.setDelay(dimmerFadeRate);
        deviceDAO.save(device);

        // guarda evento de encendido de dimmer
        Event event = EventHelper.buildEvent(device, device.getStatus() ? EventTypeEnum.MANUAL_ON : EventTypeEnum.MANUAL_OFF);
        event.setDesc("Brillo de dimmer : " + brightnessLevel + ", retardo : " + dimmerFadeRate);
        eventDAO.save(event);

        ConsumerHelper.markConsumerByStatus(consumerDAO, device, status);
    }

    @Override
    public StatusResponse check(ContextDTO contextDTO, Long deviceId) {
        Device device = deviceDAO.get(deviceId);

        try {
            return plcBusController.check(device.getCode());
        } catch (Exception e) {
            // sin error controlado
        }

        return null;
    }

    @Override
    public StatusResponse check(ContextDTO contextDTO, String deviceCode) {
        try {
            return plcBusController.check(deviceCode);
        } catch (Exception e) {
            // sin error controlado
        }

        return null;
    }

    @Override
    public Map viewMessageQueue(ContextDTO contextDTO) {
        try {
            return plcBusController.viewMessageQueue();
        } catch (Exception e) {
            // sin error controlado
        }
        return null;
    }

    @Override
    public Long restartPLCBusCount(ContextDTO contextDTO) {
        try {
            return plcBusController.restartPLCBusCount();
        } catch (Exception e) {
            // sin error controlado
        }
        return null;
    }


    public IDeviceDAO getDeviceDAO() {
        return deviceDAO;
    }

    public void setDeviceDAO(IDeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public ILevelDAO getLevelDAO() {
        return levelDAO;
    }

    public void setLevelDAO(ILevelDAO levelDAO) {
        this.levelDAO = levelDAO;
    }

    public IMapDAO getMapDAO() {
        return mapDAO;
    }

    public void setMapDAO(IMapDAO mapDAO) {
        this.mapDAO = mapDAO;
    }

    public ICoordinateDAO getCoordinateDAO() {
        return coordinateDAO;
    }

    public void setCoordinateDAO(ICoordinateDAO coordinateDAO) {
        this.coordinateDAO = coordinateDAO;
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

    public ISectorDAO getSectorDAO() {
        return sectorDAO;
    }

    public void setSectorDAO(ISectorDAO sectorDAO) {
        this.sectorDAO = sectorDAO;
    }

    public IPropertyDAO getPropertyDAO() {
        return propertyDAO;
    }

    public void setPropertyDAO(IPropertyDAO propertyDAO) {
        this.propertyDAO = propertyDAO;
    }

    public IWeatherDAO getWeatherDAO() {
        return weatherDAO;
    }

    public void setWeatherDAO(IWeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
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
}
