package cl.bennu.plcbus.bean;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.domain.Level;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 04-07-13
 * Time: 12:32 AM
 */
public class HomeBean {

    private Long lightOn;
    private Long electricOutletOn;
    private Long irrigationOn;
    private Long curtainOn;
    private Map messageQueue;
    private Long restatCount;

    private List<Event> errorEvent;
    private List<Event> warningEvent;
    private List<Device> alertedDevice;

    public Long getRestatCount() {
        return restatCount;
    }

    public void setRestatCount(Long restatCount) {
        this.restatCount = restatCount;
    }

    public List<Event> getErrorEvent() {
        return errorEvent;
    }

    public void setErrorEvent(List<Event> errorEvent) {
        this.errorEvent = errorEvent;
    }

    public List<Event> getWarningEvent() {
        return warningEvent;
    }

    public void setWarningEvent(List<Event> warningEvent) {
        this.warningEvent = warningEvent;
    }

    public Map getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(Map messageQueue) {
        this.messageQueue = messageQueue;
    }

    public Long getLightOn() {
        return lightOn;
    }

    public void setLightOn(Long lightOn) {
        this.lightOn = lightOn;
    }

    public Long getElectricOutletOn() {
        return electricOutletOn;
    }

    public void setElectricOutletOn(Long electricOutletOn) {
        this.electricOutletOn = electricOutletOn;
    }

    public Long getIrrigationOn() {
        return irrigationOn;
    }

    public void setIrrigationOn(Long irrigationOn) {
        this.irrigationOn = irrigationOn;
    }

    public Long getCurtainOn() {
        return curtainOn;
    }

    public void setCurtainOn(Long curtainOn) {
        this.curtainOn = curtainOn;
    }

    public List<Device> getAlertedDevice() {
        return alertedDevice;
    }

    public void setAlertedDevice(List<Device> alertedDevice) {
        this.alertedDevice = alertedDevice;
    }
}
