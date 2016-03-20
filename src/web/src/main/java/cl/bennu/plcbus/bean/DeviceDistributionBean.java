package cl.bennu.plcbus.bean;

import cl.bennu.plcbus.common.domain.Coordinate;
import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Level;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 04-07-13
 * Time: 12:32 AM
 */
public class DeviceDistributionBean {

    private Long levelId;
    private Long mapId;
    private Long coordinateId;
    private Level level;
    private Coordinate coordinate;
    private List<Device> deviceList;
    private Boolean clone;

    public Boolean getClone() {
        return clone;
    }

    public void setClone(Boolean clone) {
        this.clone = clone;
    }

    public Long getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(Long coordinateId) {
        this.coordinateId = coordinateId;
    }

    public Long getMapId() {
        return mapId;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
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

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
