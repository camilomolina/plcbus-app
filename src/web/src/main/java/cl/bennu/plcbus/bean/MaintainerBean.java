package cl.bennu.plcbus.bean;

import cl.bennu.plcbus.common.domain.*;
import cl.bennu.plcbus.common.enums.CameraTypeEnum;
import cl.bennu.plcbus.common.enums.OtherDeviceCodeEnum;
import cl.bennu.plcbus.common.enums.DeviceCodeEnum;
import cl.bennu.plcbus.common.enums.DeviceTypeEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 04-07-13
 * Time: 12:32 AM
 */
public class MaintainerBean {


    //device
    private Long deviceId;
    private Device device;
    private List<Device> deviceList;
    private List<Sector> sectorList;
    private List<DeviceTypeEnum> deviceTypeEnumList;
    private List<DeviceCodeEnum> deviceCodeEnumList;
    private List<OtherDeviceCodeEnum> otherDeviceCodeEnumList;
    private DeviceCodeEnum deviceCodeEnum;
    private OtherDeviceCodeEnum otherDeviceCodeEnum;
    private List<CameraTypeEnum> cameraTypeEnumList;

    //level
    private Property property;
    private Long levelId;
    private Level level;
    private List<Level> levelList;

    //sector
    private Long sectorId;
    private Sector sector;

    //map
    private List<Map> mapList;
    private Map map;


    public List<CameraTypeEnum> getCameraTypeEnumList() {
        return cameraTypeEnumList;
    }

    public void setCameraTypeEnumList(List<CameraTypeEnum> cameraTypeEnumList) {
        this.cameraTypeEnumList = cameraTypeEnumList;
    }

    public OtherDeviceCodeEnum getOtherDeviceCodeEnum() {
        return otherDeviceCodeEnum;
    }

    public void setOtherDeviceCodeEnum(OtherDeviceCodeEnum otherDeviceCodeEnum) {
        this.otherDeviceCodeEnum = otherDeviceCodeEnum;
    }

    public List<OtherDeviceCodeEnum> getOtherDeviceCodeEnumList() {
        return otherDeviceCodeEnumList;
    }

    public void setOtherDeviceCodeEnumList(List<OtherDeviceCodeEnum> otherDeviceCodeEnumList) {
        this.otherDeviceCodeEnumList = otherDeviceCodeEnumList;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public DeviceCodeEnum getDeviceCodeEnum() {
        return deviceCodeEnum;
    }

    public void setDeviceCodeEnum(DeviceCodeEnum deviceCodeEnum) {
        this.deviceCodeEnum = deviceCodeEnum;
    }

    public List<Map> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map> mapList) {
        this.mapList = mapList;
    }

    public Long getSectorId() {
        return sectorId;
    }

    public void setSectorId(Long sectorId) {
        this.sectorId = sectorId;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Level> getLevelList() {
        return levelList;
    }

    public void setLevelList(List<Level> levelList) {
        this.levelList = levelList;
    }

    public List<DeviceCodeEnum> getDeviceCodeEnumList() {
        return deviceCodeEnumList;
    }

    public void setDeviceCodeEnumList(List<DeviceCodeEnum> deviceCodeEnumList) {
        this.deviceCodeEnumList = deviceCodeEnumList;
    }

    public List<DeviceTypeEnum> getDeviceTypeEnumList() {
        return deviceTypeEnumList;
    }

    public void setDeviceTypeEnumList(List<DeviceTypeEnum> deviceTypeEnumList) {
        this.deviceTypeEnumList = deviceTypeEnumList;
    }

    public List<Sector> getSectorList() {
        return sectorList;
    }

    public void setSectorList(List<Sector> sectorList) {
        this.sectorList = sectorList;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}
