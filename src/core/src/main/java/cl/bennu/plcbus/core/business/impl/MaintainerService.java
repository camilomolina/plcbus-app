package cl.bennu.plcbus.core.business.impl;

import cl.bennu.plcbus.common.comparator.DeviceComparator;
import cl.bennu.plcbus.common.domain.*;
import cl.bennu.plcbus.common.domain.weather.Weather;
import cl.bennu.plcbus.common.domain.weather.WeatherDetail;
import cl.bennu.plcbus.common.dto.ContextDTO;
import cl.bennu.plcbus.common.enums.OtherDeviceCodeEnum;
import cl.bennu.plcbus.common.enums.DeviceCodeEnum;
import cl.bennu.plcbus.common.enums.GeneralDeviceTypeEnum;
import cl.bennu.plcbus.common.exception.ForeignKeyException;
import cl.bennu.plcbus.common.exception.UniqueException;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import cl.bennu.plcbus.core.persistence.iface.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public class MaintainerService implements IMaintainerService {

    private final static IMaintainerService instance = new MaintainerService();
    private IDeviceDAO deviceDAO;
    private ILevelDAO levelDAO;
    private IMapDAO mapDAO;
    private ISectorDAO sectorDAO;
    private IPropertyDAO propertyDAO;
    private IWeatherDAO weatherDAO;
    private IWeatherDetailDAO weatherDetailDAO;
    private ICameraDAO cameraDAO;

    public static synchronized IMaintainerService getInstance() {
        return instance;
    }

    private MaintainerService() {
    }

    @Override
    public List<Device> getAllDevice(ContextDTO contextDTO) {
        List<Device> deviceList = deviceDAO.getAll();
        if (deviceList == null) return null;

        Collections.sort(deviceList, new DeviceComparator());
        return deviceList;
    }

    @Override
    public List<Device> findActiveDevice(ContextDTO contextDTO) {
        List<Device> deviceList = deviceDAO.findActive();
        if (deviceList == null) return null;

        Collections.sort(deviceList, new DeviceComparator());
        return deviceList;
    }

    @Override
    public List<Device> findActiveDevice(ContextDTO contextDTO, GeneralDeviceTypeEnum generalDeviceTypeEnum) {
        List<Device> deviceList = deviceDAO.findActive();
        if (deviceList == null) return null;

        List<Device> sensorDeviceList = new ArrayList<Device>();
        for (Device device : deviceList) {
            if (generalDeviceTypeEnum.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())) {
                sensorDeviceList.add(device);
            }
        }
        
        Collections.sort(sensorDeviceList, new DeviceComparator());
        return sensorDeviceList;
    }

    @Override
    public List<Device> findDevice(ContextDTO contextDTO, Long levelId) {
        List<Device> deviceList = deviceDAO.findByLevel(levelId);
        if (deviceList == null) return null;

        Collections.sort(deviceList, new DeviceComparator());
        return deviceList;
    }

    @Override
    public List<Device> findAlertedDevice(ContextDTO contextDTO) {
        Device device = new Device();
        device.setStatus(true);
        device.setAlarmed(true);
        List<Device> deviceList = deviceDAO.find(device);
        //Collections.sort(deviceList, new DeviceComparator());
        return deviceList;
    }

    @Override
    public void saveDevice(ContextDTO contextDTO, Device device) throws UniqueException {
        if (device.getId() == null) {
            // validacion
            Device deviceTmp = deviceDAO.get(device.getCode());
            if (deviceTmp != null) throw new UniqueException();

            device.setStatus(false);
            device.setAlarmed(false);
            device.setCreate(new Date());
            device.setDelay(0L);
            device.setDimmer(0L);
            device.setCreatorUser(contextDTO.getUser());
        } else {
            // rescata y mantiene datos de control del dispositivo
            Device deviceDB = deviceDAO.get(device.getId());
            if (!deviceDB.getCode().equalsIgnoreCase(device.getCode())) {
                // validacion
                Device deviceTmp = deviceDAO.get(device.getCode());
                if (deviceTmp != null) throw new UniqueException();
            }

            device.setStatus(deviceDB.getStatus());
            device.setSignal(deviceDB.getSignal());
            device.setNoize(deviceDB.getNoize());
            device.setCreate(deviceDB.getCreate());
            device.setCreatorUser(deviceDB.getCreatorUser());
            device.setAlarmed(deviceDB.getAlarmed());
            device.setLastPower(deviceDB.getLastPower());
            device.setDelay(deviceDB.getDelay());
            device.setDimmer(deviceDB.getDimmer());

            device.setUpdate(new Date());
            device.setUpdateUser(contextDTO.getUser());
        }
        deviceDAO.save(device);

        if (GeneralDeviceTypeEnum.CAMERA.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())) {
            // esta malo, hay que arreglar esta wea
            if (device.getCamera() != null) {
                //cameraDAO.save(device.getCamera());
                device.getCamera().setDeviceId(device.getId());
                device.getCamera().setName(device.getName());
                cameraDAO.save2(device.getCamera());
            }
        }
    }

    @Override
    public Device getDeviceById(ContextDTO contextDTO, Long deviceId) {
        return deviceDAO.get(deviceId);
    }

    @Override
    public Device getDeviceByCode(ContextDTO contextDTO, String deviceCode) {
        return deviceDAO.get(deviceCode);
    }

    @Override
    public void deleteDevice(ContextDTO contextDTO, Long deviceId) throws ForeignKeyException {
        try {
            deviceDAO.delete(deviceId);
        } catch (Exception e) {
            throw new ForeignKeyException();
        }
    }

    @Override
    public void deleteSector(ContextDTO contextDTO, Long sectorId) {
        sectorDAO.delete(sectorId);
    }

    @Override
    public Sector getSectorById(ContextDTO contextDTO, Long sectorId) {
        return sectorDAO.get(sectorId);
    }

    @Override
    public void saveSector(ContextDTO contextDTO, Sector sector) throws UniqueException {
        if (sector.getId() == null) {
            // validacion
            Sector sectorTmp = sectorDAO.get(sector.getName());
            if (sectorTmp != null) throw new UniqueException();

            sector.setCreatorUser(contextDTO.getUser());
            sector.setCreate(new Date());
        } else {
            Sector sectorDB = sectorDAO.get(sector.getId());
            if (!sectorDB.getName().equalsIgnoreCase(sector.getName())) {
                // validacion
                Sector sectorTmp = sectorDAO.get(sector.getName());
                if (sectorTmp != null) throw new UniqueException();
            }
            sector.setUpdateUser(contextDTO.getUser());
            sector.setUpdate(new Date());
        }

        sectorDAO.save(sector);
    }

    @Override
    public List<Sector> getAllSector(ContextDTO contextDTO) {
        return sectorDAO.getAll();
    }

    @Override
    public List<Sector> findActiveSector(ContextDTO contextDTO) {
        return sectorDAO.findActive();
    }

    @Override
    public void deleteMap(ContextDTO contextDTO, Long mapId) {
        mapDAO.delete(mapId);
    }

    @Override
    public Map getMapById(ContextDTO contextDTO, Long mapId) {
        return mapDAO.get(mapId);
    }

    @Override
    public void saveMap(ContextDTO contextDTO, Map map) throws UniqueException {
        if (map.getId() == null) {
            // validacion
            Map mapTmp = mapDAO.get(map.getName());
            if (mapTmp != null) throw new UniqueException();

            map.setCreatorUser(contextDTO.getUser());
            map.setCreate(new Date());
        } else {
            Map mapDB = mapDAO.get(map.getId());
            if (!mapDB.getName().equalsIgnoreCase(map.getName())) {
                // validacion
                Map mapTmp = mapDAO.get(map.getName());
                if (mapTmp != null) throw new UniqueException();
            }

            map.setUpdateUser(contextDTO.getUser());
            map.setUpdate(new Date());
        }

        mapDAO.save(map);
    }

    @Override
    public List<Map> getAllMap(ContextDTO contextDTO) {
        return mapDAO.getAll();
    }

    @Override
    public void deleteLevel(ContextDTO contextDTO, Long levelId) {
        levelDAO.delete(levelId);
    }

    @Override
    public Level getLevelById(ContextDTO contextDTO, Long levelId) {
        return levelDAO.get(levelId);
    }

    @Override
    public void saveLevel(ContextDTO contextDTO, Level level) throws UniqueException {
        if (level.getId() == null) {
            // validacion
            Level levelTmp = levelDAO.get(level.getName());
            if (levelTmp != null) throw new UniqueException();

            level.setCreatorUser(contextDTO.getUser());
            level.setCreate(new Date());
        } else {
            Level levelDB = levelDAO.get(level.getId());
            if (!levelDB.getName().equalsIgnoreCase(level.getName())) {
                // validacion
                Level levelTmp = levelDAO.get(level.getName());
                if (levelTmp != null) throw new UniqueException();
            }

            level.setUpdateUser(contextDTO.getUser());
            level.setUpdate(new Date());
        }

        levelDAO.save(level);
    }

    @Override
    public List<Level> getAllLevel(ContextDTO contextDTO) {
        return levelDAO.getAll();
    }

    @Override
    public List<Level> findActiveLevel(ContextDTO contextDTO) {
        return levelDAO.findActive();
    }

    @Override
    public Property getProperty(ContextDTO contextDTO) {
        return propertyDAO.getSystemProperty();
    }

    @Override
    public void saveProperty(ContextDTO contextDTO, Property property) {
        if (property.getId() == null) {
            property.setCreatorUser(contextDTO.getUser());
            property.setCreate(new Date());
        } else {
            property.setUpdateUser(contextDTO.getUser());
            property.setUpdate(new Date());
        }

        propertyDAO.save(property);
    }

    @Override
    public DeviceCodeEnum getDeviceCodeEnumFinal(ContextDTO context) {
        String name = deviceDAO.getDeviceCodeFinal();
        return name == null ? null : DeviceCodeEnum.valueOf(name);
    }

    @Override
    public OtherDeviceCodeEnum getDeviceCameraCodeEnumFinal(ContextDTO context) {
        String name = deviceDAO.getDeviceCameraCodeFinal();
        return name == null ? null : OtherDeviceCodeEnum.valueOf(name);
    }

    @Override
    public Weather getLastedWeather(ContextDTO contextDTO) {
        return weatherDAO.getLasted();
    }

    @Override
    public void saveWeather(ContextDTO contextDTO, Weather weather) {
        weatherDAO.save(weather);

        for (WeatherDetail weatherDetail : weather.getWeatherDetail()) {
            weatherDetail.setWeather(weather);

            weatherDetailDAO.save(weatherDetail);
        }
    }


    public IWeatherDetailDAO getWeatherDetailDAO() {
        return weatherDetailDAO;
    }

    public void setWeatherDetailDAO(IWeatherDetailDAO weatherDetailDAO) {
        this.weatherDetailDAO = weatherDetailDAO;
    }

    public IWeatherDAO getWeatherDAO() {
        return weatherDAO;
    }

    public void setWeatherDAO(IWeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
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

    public ICameraDAO getCameraDAO() {
        return cameraDAO;
    }

    public void setCameraDAO(ICameraDAO cameraDAO) {
        this.cameraDAO = cameraDAO;
    }
}
