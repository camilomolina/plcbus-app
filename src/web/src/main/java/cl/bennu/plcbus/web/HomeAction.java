package cl.bennu.plcbus.web;

import cl.bennu.plcbus.base.BaseAction;
import cl.bennu.plcbus.bean.HomeBean;
import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.domain.Level;
import cl.bennu.plcbus.common.domain.weather.Weather;
import cl.bennu.plcbus.common.enums.GeneralDeviceTypeEnum;
import cl.bennu.plcbus.constant.Constant;
import cl.bennu.plcbus.core.business.iface.IControlService;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import cl.bennu.plcbus.core.business.iface.IStatisticsService;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 03-07-13
 * Time: 03:21 AM
 */
public class HomeAction extends BaseAction {

    private String HOME_CONTROL_SUCCESS = "homeControlSuccess";
    private String CONFIGURATION_SUCCESS = "configurationSuccess";
    private String REPORT_SUCCESS = "reportSuccess";

    private HomeBean homeBean = new HomeBean();

    private IMaintainerService maintainerService;
    private IControlService controlService;
    private IStatisticsService statisticsService;

    @Override
    protected void initial() throws Exception {
    }

    public String start() throws Exception {
        List<Level> levelList = maintainerService.findActiveLevel(getContext());
        httpSession.setAttribute(Constant.LEVEL, levelList);

        Weather weather = maintainerService.getLastedWeather(getContext());
        httpSession.setAttribute(Constant.WEATHER, weather);

        long lightOn = 0L;
        long electricOutletOn = 0L;
        long irrigationOn = 0L;
        long curtainOn = 0L;
        List<Device> deviceList = maintainerService.findActiveDevice(getContext());
        for (Device device : deviceList) {
            if (device.getStatus()) {
                if (GeneralDeviceTypeEnum.LIGHT.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())) {
                    lightOn++;
                } else if (GeneralDeviceTypeEnum.ELECTRIC_OUTLET.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())) {
                    electricOutletOn++;
                } else if (GeneralDeviceTypeEnum.IRRIGATION.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())) {
                    irrigationOn++;
                } else if (GeneralDeviceTypeEnum.CURTAIN.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())) {
                    curtainOn++;
                }
            }
        }


        Map messageQueue = controlService.viewMessageQueue(getContext());
        Long restatCount = controlService.restartPLCBusCount(getContext());
        List<Event> errorEvent = statisticsService.findErrorEvent(getContext());
        List<Event> warningEvent = statisticsService.findWarningEvent(getContext());
        List<Device> alertedDevice = maintainerService.findAlertedDevice(getContext());


        homeBean.setLightOn(lightOn);
        homeBean.setElectricOutletOn(electricOutletOn);
        homeBean.setIrrigationOn(irrigationOn);
        homeBean.setCurtainOn(curtainOn);
        homeBean.setErrorEvent(errorEvent);
        homeBean.setWarningEvent(warningEvent);
        homeBean.setMessageQueue(messageQueue);
        homeBean.setRestatCount(restatCount);
        homeBean.setAlertedDevice(alertedDevice);

        return SUCCESS;
    }


    public HomeBean getHomeBean() {
        return homeBean;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public IMaintainerService getMaintainerService() {
        return maintainerService;
    }

    public void setMaintainerService(IMaintainerService maintainerService) {
        this.maintainerService = maintainerService;
    }

    public IControlService getControlService() {
        return controlService;
    }

    public void setControlService(IControlService controlService) {
        this.controlService = controlService;
    }

    public IStatisticsService getStatisticsService() {
        return statisticsService;
    }

    public void setStatisticsService(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
}
