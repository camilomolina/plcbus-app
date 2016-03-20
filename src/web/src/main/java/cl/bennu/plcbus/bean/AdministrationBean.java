package cl.bennu.plcbus.bean;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.enums.CameraTypeEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 04-07-13
 * Time: 12:32 AM
 */
public class AdministrationBean {


    //camera
    private List<Device> cameraList;
    private List<CameraTypeEnum> cameraTypeEnumList;
    private Device camera;
    private Long deviceId;
    private String deviceCode;

    private String ip;
    private String port;

    public List<CameraTypeEnum> getCameraTypeEnumList() {
        return cameraTypeEnumList;
    }


    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public void setCameraTypeEnumList(List<CameraTypeEnum> cameraTypeEnumList) {
        this.cameraTypeEnumList = cameraTypeEnumList;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Device getCamera() {
        return camera;
    }

    public void setCamera(Device camera) {
        this.camera = camera;
    }

    public List<Device> getCameraList() {
        return cameraList;
    }

    public void setCameraList(List<Device> cameraList) {
        this.cameraList = cameraList;
    }
}
