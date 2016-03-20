package cl.bennu.plcbus.bean;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Level;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 04-07-13
 * Time: 12:32 AM
 */
public class ControlBean {

    private String data;
    private String device;
    private Long levelId;
    private Long deviceId;
    private List<Device> deviceList;
    private List<Level> levelList;
    private Boolean statusResponse;
    private Level level;
    private Long deplay;
    private Long dimmer;
    private Boolean force;

    public Boolean getForce() {
        return force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }

    public Long getDeplay() {
        return deplay;
    }

    public void setDeplay(Long deplay) {
        this.deplay = deplay;
    }

    public Long getDimmer() {
        return dimmer;
    }

    public void setDimmer(Long dimmer) {
        this.dimmer = dimmer;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public List<Level> getLevelList() {
        return levelList;
    }

    public void setLevelList(List<Level> levelList) {
        this.levelList = levelList;
    }

    public Boolean getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(Boolean statusResponse) {
        this.statusResponse = statusResponse;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
