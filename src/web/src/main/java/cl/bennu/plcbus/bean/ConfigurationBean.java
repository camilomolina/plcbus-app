package cl.bennu.plcbus.bean;

import cl.bennu.plcbus.common.domain.*;
import cl.bennu.plcbus.common.domain.summary.ProgrammingSummary;
import cl.bennu.plcbus.common.domain.summary.TimeSummary;
import cl.bennu.plcbus.common.enums.*;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 04-07-13
 * Time: 12:32 AM
 */
public class ConfigurationBean {

    private StatusRefresh statusRefresh;
    private Long percentage;
    private Long percentageDiff;
    private Long minutes;
    private Date startSynchronized;
    private Boolean synchronizedInCurse;
    private Boolean synchronizedTimeOut;
    private List<HourEnum> hourList;
    private List<MinuteEnum> minuteList;
    private List<Device> deviceList;
    private List<Programming> programmingList;
    private ProgrammingSummary programmingSummary;
    private TimeSummary timeSummary;
    private List<DayEnum> dayList;
    private Long programmingId;
    private Boolean programmingActive;
    private Property property;
    private List<ScheduledShutdownDevice> scheduledShutdownDeviceList;
    private ScheduledShutdownDevice scheduledShutdownDevice;
    private Long scheduledShutdownDeviceId;

    private Long scheduledShutdownCount;
    private Long programmingCount;
    private Long movementActionCount;

    private List<MovementAction> movementActionList;
    private List<MovementActionTypeEnum> movementActionTypeList;
    private List<MovementTypeEnum> movementTypeList;
    private Long movementActionId;
    private List<Device> deviceMovementList;
    private MovementAction movementAction;
    private MovementActionDetail movementActionDetail;
    private Long movementActionDetailIndex;
    private Boolean movementActionActive;

    private List<Integer> temperatureList;

    public List<Integer> getTemperatureList() {
        return temperatureList;
    }

    public void setTemperatureList(List<Integer> temperatureList) {
        this.temperatureList = temperatureList;
    }

    public Boolean getSynchronizedTimeOut() {
        return synchronizedTimeOut;
    }

    public void setSynchronizedTimeOut(Boolean synchronizedTimeOut) {
        this.synchronizedTimeOut = synchronizedTimeOut;
    }

    public Boolean getMovementActionActive() {
        return movementActionActive;
    }

    public void setMovementActionActive(Boolean movementActionActive) {
        this.movementActionActive = movementActionActive;
    }

    public List<MovementTypeEnum> getMovementTypeList() {
        return movementTypeList;
    }

    public Long getMovementActionDetailIndex() {
        return movementActionDetailIndex;
    }

    public void setMovementActionDetailIndex(Long movementActionDetailIndex) {
        this.movementActionDetailIndex = movementActionDetailIndex;
    }

    public MovementAction getMovementAction() {
        return movementAction;
    }

    public void setMovementAction(MovementAction movementAction) {
        this.movementAction = movementAction;
    }

    public MovementActionDetail getMovementActionDetail() {
        return movementActionDetail;
    }

    public void setMovementActionDetail(MovementActionDetail movementActionDetail) {
        this.movementActionDetail = movementActionDetail;
    }

    public void setMovementTypeList(List<MovementTypeEnum> movementTypeList) {
        this.movementTypeList = movementTypeList;
    }

    public Long getMovementActionCount() {
        return movementActionCount;
    }

    public void setMovementActionCount(Long movementActionCount) {
        this.movementActionCount = movementActionCount;
    }

    public List<Device> getDeviceMovementList() {
        return deviceMovementList;
    }

    public void setDeviceMovementList(List<Device> deviceMovementList) {
        this.deviceMovementList = deviceMovementList;
    }

    public List<MovementActionTypeEnum> getMovementActionTypeList() {
        return movementActionTypeList;
    }

    public void setMovementActionTypeList(List<MovementActionTypeEnum> movementActionTypeList) {
        this.movementActionTypeList = movementActionTypeList;
    }


    public List<MovementAction> getMovementActionList() {
        return movementActionList;
    }

    public void setMovementActionList(List<MovementAction> movementActionList) {
        this.movementActionList = movementActionList;
    }

    public Long getMovementActionId() {
        return movementActionId;
    }

    public void setMovementActionId(Long movementActionId) {
        this.movementActionId = movementActionId;
    }

    public Boolean getProgrammingActive() {
        return programmingActive;
    }

    public void setProgrammingActive(Boolean programmingActive) {
        this.programmingActive = programmingActive;
    }

    public Long getScheduledShutdownCount() {
        return scheduledShutdownCount;
    }

    public void setScheduledShutdownCount(Long scheduledShutdownCount) {
        this.scheduledShutdownCount = scheduledShutdownCount;
    }

    public Long getProgrammingCount() {
        return programmingCount;
    }

    public void setProgrammingCount(Long programmingCount) {
        this.programmingCount = programmingCount;
    }

    public Long getScheduledShutdownDeviceId() {
        return scheduledShutdownDeviceId;
    }

    public void setScheduledShutdownDeviceId(Long scheduledShutdownDeviceId) {
        this.scheduledShutdownDeviceId = scheduledShutdownDeviceId;
    }

    public List<ScheduledShutdownDevice> getScheduledShutdownDeviceList() {
        return scheduledShutdownDeviceList;
    }

    public void setScheduledShutdownDeviceList(List<ScheduledShutdownDevice> scheduledShutdownDeviceList) {
        this.scheduledShutdownDeviceList = scheduledShutdownDeviceList;
    }

    public ScheduledShutdownDevice getScheduledShutdownDevice() {
        return scheduledShutdownDevice;
    }

    public void setScheduledShutdownDevice(ScheduledShutdownDevice scheduledShutdownDevice) {
        this.scheduledShutdownDevice = scheduledShutdownDevice;
    }

    public Date getStartSynchronized() {
        return startSynchronized;
    }

    public void setStartSynchronized(Date startSynchronized) {
        this.startSynchronized = startSynchronized;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Long getProgrammingId() {
        return programmingId;
    }

    public void setProgrammingId(Long programmingId) {
        this.programmingId = programmingId;
    }

    public List<DayEnum> getDayList() {
        return dayList;
    }

    public void setDayList(List<DayEnum> dayList) {
        this.dayList = dayList;
    }

    public ProgrammingSummary getProgrammingSummary() {
        return programmingSummary;
    }

    public void setProgrammingSummary(ProgrammingSummary programmingSummary) {
        this.programmingSummary = programmingSummary;
    }

    public List<Programming> getProgrammingList() {
        return programmingList;
    }

    public void setProgrammingList(List<Programming> programmingList) {
        this.programmingList = programmingList;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<HourEnum> getHourList() {
        return hourList;
    }

    public void setHourList(List<HourEnum> hourList) {
        this.hourList = hourList;
    }

    public List<MinuteEnum> getMinuteList() {
        return minuteList;
    }

    public void setMinuteList(List<MinuteEnum> minuteList) {
        this.minuteList = minuteList;
    }

    public Long getPercentageDiff() {
        return percentageDiff;
    }

    public void setPercentageDiff(Long percentageDiff) {
        this.percentageDiff = percentageDiff;
    }

    public Boolean getSynchronizedInCurse() {
        return synchronizedInCurse;
    }

    public void setSynchronizedInCurse(Boolean synchronizedInCurse) {
        this.synchronizedInCurse = synchronizedInCurse;
    }

    public Long getPercentage() {
        return percentage;
    }

    public void setPercentage(Long percentage) {
        this.percentage = percentage;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public StatusRefresh getStatusRefresh() {
        return statusRefresh;
    }

    public void setStatusRefresh(StatusRefresh statusRefresh) {
        this.statusRefresh = statusRefresh;
    }

    public TimeSummary getTimeSummary() {
        return timeSummary;
    }

    public void setTimeSummary(TimeSummary timeSummary) {
        this.timeSummary = timeSummary;
    }
}
