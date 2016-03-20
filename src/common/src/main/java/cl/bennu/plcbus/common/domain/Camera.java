package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.enums.CameraTypeEnum;
import cl.bennu.plcbus.common.enums.DeviceTypeEnum;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class Camera extends BaseDomain {

    private Device device;
    private Long deviceId;
    private String name;
    private String ip;
    private Long port;
    private CameraTypeEnum cameraTypeEnum;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public CameraTypeEnum getCameraTypeEnum() {
        return cameraTypeEnum;
    }

    public void setCameraTypeEnum(CameraTypeEnum cameraTypeEnum) {
        this.cameraTypeEnum = cameraTypeEnum;
    }
}
