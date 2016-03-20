package cl.bennu.plcbus.core.business.iface;

import cl.bennu.plcbus.common.domain.*;
import cl.bennu.plcbus.common.domain.weather.Weather;
import cl.bennu.plcbus.common.dto.ContextDTO;
import cl.bennu.plcbus.common.enums.OtherDeviceCodeEnum;
import cl.bennu.plcbus.common.enums.DeviceCodeEnum;
import cl.bennu.plcbus.common.enums.GeneralDeviceTypeEnum;
import cl.bennu.plcbus.common.exception.ForeignKeyException;
import cl.bennu.plcbus.common.exception.UniqueException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public interface IMaintainerService {


    List<Device> getAllDevice(ContextDTO contextDTO);

    List<Device> findActiveDevice(ContextDTO contextDTO);

    List<Device> findActiveDevice(ContextDTO contextDTO, GeneralDeviceTypeEnum generalDeviceTypeEnum);

    List<Device> findDevice(ContextDTO contextDTO, Long levelId);

    List<Device> findAlertedDevice(ContextDTO contextDTO);

    void saveDevice(ContextDTO contextDTO, Device device) throws UniqueException;

    Device getDeviceById(ContextDTO contextDTO, Long deviceId);

    Device getDeviceByCode(ContextDTO contextDTO, String deviceCode);

    void deleteDevice(ContextDTO contextDTO, Long deviceId) throws ForeignKeyException;

    List<Level> getAllLevel(ContextDTO contextDTO);

    List<Level> findActiveLevel(ContextDTO contextDTO);

    void saveLevel(ContextDTO contextDTO, Level level) throws UniqueException;

    Level getLevelById(ContextDTO contextDTO, Long levelId);

    void deleteLevel(ContextDTO contextDTO, Long levelId) throws ForeignKeyException;

    List<Map> getAllMap(ContextDTO contextDTO);

    void saveMap(ContextDTO contextDTO, Map map) throws UniqueException;

    Map getMapById(ContextDTO contextDTO, Long mapId);

    void deleteMap(ContextDTO contextDTO, Long mapId) throws ForeignKeyException;

    List<Sector> getAllSector(ContextDTO contextDTO);

    List<Sector> findActiveSector(ContextDTO contextDTO);

    void saveSector(ContextDTO contextDTO, Sector sector) throws UniqueException;

    Sector getSectorById(ContextDTO contextDTO, Long sectorId);

    void deleteSector(ContextDTO contextDTO, Long sectorId) throws ForeignKeyException;

    Property getProperty(ContextDTO contextDTO);

    void saveProperty(ContextDTO contextDTO, Property property);

    DeviceCodeEnum getDeviceCodeEnumFinal(ContextDTO context);

    OtherDeviceCodeEnum getDeviceCameraCodeEnumFinal(ContextDTO context);

    void saveWeather(ContextDTO contextDTO, Weather weather);

    Weather getLastedWeather(ContextDTO contextDTO);

}
