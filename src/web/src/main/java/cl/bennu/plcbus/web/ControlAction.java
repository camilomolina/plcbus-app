package cl.bennu.plcbus.web;

import cl.bennu.plcbus.base.BaseAction;
import cl.bennu.plcbus.bean.ControlBean;
import cl.bennu.plcbus.bean.HomeBean;
import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Level;
import cl.bennu.plcbus.common.domain.weather.Weather;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.common.enums.WeatherYahooEnum;
import cl.bennu.plcbus.common.exception.RainException;
import cl.bennu.plcbus.constant.Constant;
import cl.bennu.plcbus.core.business.iface.IControlService;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import cl.bennu.plcbus.core.business.impl.ControlService;
import cl.bennu.plcbus.domain.StatusResponse;
import org.apache.struts2.json.JSONUtil;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 03-07-13
 * Time: 03:21 AM
 */
public class ControlAction extends BaseAction {

    public String HOME_CONTROL_SUCCESS = "homeControlSuccess";
    private String HOME_LEVEL_CONTROL_SUCCESS = "levelControlSuccess";
    private String HOME_FULL_CONTROL_SUCCESS = "homeFullControlSuccess";
    private String CONFIGURATION_SUCCESS = "configurationSuccess";
    private String REPORT_SUCCESS = "reportSuccess";


    private ControlBean controlBean = new ControlBean();

    private IControlService controlService;
    private IMaintainerService maintainerService;

    @Override
    protected void initial() throws Exception {

    }

    public String startTableControl() throws Exception {
        // busca todos los dispositivos
        List<Device> deviceList = maintainerService.findActiveDevice(getContext());
        controlBean.setDeviceList(deviceList);

        return HOME_FULL_CONTROL_SUCCESS;
    }

    public String startControlByLevel() throws Exception {
        List<Level> levelList = (List<Level>) httpSession.getAttribute(Constant.LEVEL);
        if (levelList != null) {
            if (levelList.size() == 1) {
                controlBean.setLevelId(levelList.get(0).getId());
                return startControl();
            } else {
                return HOME_LEVEL_CONTROL_SUCCESS;
            }
        }

        return HOME_FULL_CONTROL_SUCCESS;
    }

    public String startControl() throws Exception {
        // busca todos los dispositivos del nivel
        List<Device> deviceList = maintainerService.findDevice(getContext(), controlBean.getLevelId());
        controlBean.setDeviceList(deviceList);

        // buscamos el nivel con sus mapas
        Level level = maintainerService.getLevelById(getContext(), controlBean.getLevelId());
        controlBean.setLevel(level);

        return HOME_CONTROL_SUCCESS;
    }

    public void on() throws Exception {
        try {
            controlService.on(getContext(), controlBean.getDeviceId(), EventTypeEnum.MANUAL_ON, Boolean.TRUE);
            serialize(SUCCESS_JSON);
        } catch (RainException e) {
            serialize("rain_error");
        }
    }

    public String off() throws Exception {
        controlService.off(getContext(), controlBean.getDeviceId(), EventTypeEnum.MANUAL_OFF);
        return SUCCESS_JSON;
    }

    public void status() throws Exception {
        StatusResponse statusResponse = controlService.status(getContext(), controlBean.getDeviceId());
        controlBean.setStatusResponse(statusResponse == null ? false : statusResponse.getStatus());

        serialize(controlBean);
    }

    public String dimmer() throws Exception {
        Long delay = controlBean.getDeplay();
        Long dimmer = controlBean.getDimmer();
        if (delay == 0L) {
            delay = 1L;
        }

        controlService.dimmer(getContext(), controlBean.getDeviceId(), dimmer, delay);

        return SUCCESS_JSON;
    }

    public void getDevice() throws Exception {
        Device device = maintainerService.getDeviceById(getContext(), controlBean.getDeviceId());
        serialize(device);
    }

    public ControlBean getControlBean() {
        return controlBean;
    }

    public void setControlBean(ControlBean controlBean) {
        this.controlBean = controlBean;
    }

    public IControlService getControlService() {
        return controlService;
    }

    public void setControlService(IControlService controlService) {
        this.controlService = controlService;
    }

    public IMaintainerService getMaintainerService() {
        return maintainerService;
    }

    public void setMaintainerService(IMaintainerService maintainerService) {
        this.maintainerService = maintainerService;
    }
}
