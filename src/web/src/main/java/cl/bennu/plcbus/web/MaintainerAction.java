package cl.bennu.plcbus.web;

import cl.bennu.plcbus.base.BaseAction;
import cl.bennu.plcbus.bean.MaintainerBean;
import cl.bennu.plcbus.common.domain.*;
import cl.bennu.plcbus.common.enums.*;
import cl.bennu.plcbus.common.exception.ForeignKeyException;
import cl.bennu.plcbus.common.exception.UniqueException;
import cl.bennu.plcbus.core.business.iface.IConfigurationService;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.ServletException;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 03-07-13
 * Time: 03:21 AM
 */
public class MaintainerAction extends BaseAction {

    private static final String DEVICE_SUCCESS = "deviceSuccess";
    private static final String DEVICE_LIST = "deviceList";
    private static final String LEVEL_SUCCESS = "levelSuccess";
    private static final String LEVEL_LIST = "levelList";
    private static final String SECTOR_SUCCESS = "sectorSuccess";
    private static final String SECTOR_LIST = "sectorList";
    private static final String MAP_SUCCESS = "mapSuccess";

    private MaintainerBean maintainerBean = new MaintainerBean();

    private IMaintainerService maintainerService;
    private IConfigurationService configurationService;

    @Override
    protected void initial() throws Exception {

    }

    public String startDevice() throws Exception {
        List<Device> deviceList = maintainerService.getAllDevice(getContext());
        maintainerBean.setDeviceList(deviceList);

        List<Sector> sectorList = maintainerService.getAllSector(getContext());
        maintainerBean.setSectorList(sectorList);

        List<DeviceTypeEnum> deviceTypeList = DeviceTypeEnum.valuesList();
        maintainerBean.setDeviceTypeEnumList(deviceTypeList);

        List<DeviceCodeEnum> deviceCodeList = DeviceCodeEnum.valuesList();
        List<OtherDeviceCodeEnum> otherDeviceCodeList = OtherDeviceCodeEnum.valuesList();

        List<DeviceCodeEnum> deviceCodeFilterList = new ArrayList<DeviceCodeEnum>();
        List<OtherDeviceCodeEnum> otherDeviceCodeFilterList = new ArrayList<OtherDeviceCodeEnum>();
        if (deviceList != null) {
            for (DeviceCodeEnum deviceCodeEnum : deviceCodeList) {
                if (!contains(deviceCodeEnum, deviceList)) {
                    deviceCodeFilterList.add(deviceCodeEnum);
                }
            }
            for (OtherDeviceCodeEnum otherDeviceCodeEnum : otherDeviceCodeList) {
                if (!contains(otherDeviceCodeEnum, deviceList)) {
                    otherDeviceCodeFilterList.add(otherDeviceCodeEnum);
                }
            }
        }
        maintainerBean.setDeviceCodeEnumList(deviceCodeFilterList);
        maintainerBean.setOtherDeviceCodeEnumList(otherDeviceCodeFilterList);

        DeviceCodeEnum deviceCodeEnumFinal = maintainerService.getDeviceCodeEnumFinal(getContext());
        maintainerBean.setDeviceCodeEnum(deviceCodeEnumFinal);

        OtherDeviceCodeEnum otherDeviceCodeEnumFinal = maintainerService.getDeviceCameraCodeEnumFinal(getContext());
        maintainerBean.setOtherDeviceCodeEnum(otherDeviceCodeEnumFinal);

        List<CameraTypeEnum> cameraTypeEnumList = CameraTypeEnum.valuesList();
        maintainerBean.setCameraTypeEnumList(cameraTypeEnumList);

        return DEVICE_SUCCESS;
    }

    private boolean contains(DeviceCodeEnum deviceCodeEnum, List<Device> deviceList) {
        if (deviceList == null) return false;
        for (Device device : deviceList) {
            if (deviceCodeEnum.getName().equals(device.getCode())) return true;
        }
        return false;
    }

    private boolean contains(OtherDeviceCodeEnum otherDeviceCodeEnum, List<Device> deviceList) {
        if (deviceList == null) return false;
        for (Device device : deviceList) {
            if (otherDeviceCodeEnum.getName().equals(device.getCode())) return true;
        }
        return false;
    }

    public String deviceList() throws Exception {
        List<Device> deviceList = maintainerService.getAllDevice(getContext());
        maintainerBean.setDeviceList(deviceList);

        return DEVICE_LIST;
    }

    public void saveDevice() throws Exception {
        try {
            maintainerService.saveDevice(getContext(), maintainerBean.getDevice());
            serialize(SUCCESS_JSON);
        } catch (UniqueException e) {
            serialize("unique_error");
        }
    }

    public void getDevice() throws Exception {
        Device device = maintainerService.getDeviceById(getContext(), maintainerBean.getDeviceId());
        serialize(device);
    }

    public void deleteDevice() throws Exception {
        try {
            maintainerService.deleteDevice(getContext(), maintainerBean.getDeviceId());
            serialize(SUCCESS_JSON);
        } catch (ForeignKeyException e) {
            serialize("foreignkey_error");
        }
    }

    public String startLevel() throws Exception {

        Property property = maintainerService.getProperty(getContext());
        maintainerBean.setProperty(property);

        List<Level> levelList = maintainerService.getAllLevel(getContext());
        maintainerBean.setLevelList(levelList);

        return LEVEL_SUCCESS;
    }

    public String levelList() throws Exception {
        List<Level> levelList = maintainerService.getAllLevel(getContext());
        maintainerBean.setLevelList(levelList);

        return LEVEL_LIST;
    }

    public void saveLevel() throws Exception {
        try {
            maintainerService.saveLevel(getContext(), maintainerBean.getLevel());
            serialize(SUCCESS_JSON);
        } catch (UniqueException e) {
            serialize("unique_error");
        }
    }

    public void getLevel() throws Exception {
        Level level = maintainerService.getLevelById(getContext(), maintainerBean.getLevelId());
        serialize(level);
    }

    public void deleteLevel() throws Exception {
        try {
            maintainerService.deleteLevel(getContext(), maintainerBean.getLevelId());
            serialize(SUCCESS_JSON);
        } catch (ForeignKeyException e) {
            serialize("foreignkey_error");
        }
    }

    public String startSector() throws Exception {
        List<Sector> sectorList = maintainerService.getAllSector(getContext());
        maintainerBean.setSectorList(sectorList);

        List<Level> levelList = maintainerService.findActiveLevel(getContext());
        maintainerBean.setLevelList(levelList);

        return SECTOR_SUCCESS;
    }

    public String sectorList() throws Exception {
        List<Sector> sectorList = maintainerService.getAllSector(getContext());
        maintainerBean.setSectorList(sectorList);

        return SECTOR_LIST;
    }

    public void saveSector() throws Exception {
        try {
            maintainerService.saveSector(getContext(), maintainerBean.getSector());
            serialize(SUCCESS_JSON);
        } catch (UniqueException e) {
            serialize("unique_error");
        }
    }

    public void getSector() throws Exception {
        Sector sector = maintainerService.getSectorById(getContext(), maintainerBean.getSectorId());
        serialize(sector);
    }

    public void deleteSector() throws Exception {
        try {
            maintainerService.deleteSector(getContext(), maintainerBean.getSectorId());
            serialize(SUCCESS_JSON);
        } catch (ForeignKeyException e) {
            serialize("foreignkey_error");
        }
    }

    public String startMap() throws Exception {
        List<Level> levelList = maintainerService.findActiveLevel(getContext());
        maintainerBean.setLevelList(levelList);

        return MAP_SUCCESS;
    }

    public String configMap() throws Exception {
        Level level = maintainerService.getLevelById(getContext(), maintainerBean.getLevelId());
        maintainerBean.setLevel(level);

        List<Level> levelList = new ArrayList<Level>();
        levelList.add(level);
        maintainerBean.setLevelList(levelList);

        List<Map> mapList = level.getMapList();
        maintainerBean.setMapList(mapList);

        return MAP_SUCCESS;
    }

    public void saveMap() throws Exception {
        try {
            File file = maintainerBean.getMap().getImage();
            maintainerService.saveMap(getContext(), maintainerBean.getMap());
            serialize(SUCCESS_JSON);
        } catch (UniqueException e) {
            serialize("unique_error");
        }
    }

    public void testFile() throws Exception {
        String ajaxUpdateResult = "";
        try {

            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(httpRequest);
            for (FileItem item : items) {

                if (item.isFormField()) {
                    ajaxUpdateResult += "Field " + item.getFieldName() + " with value: " + item.getString() + " is successfully read\n\r";
                } else {
                    String fileName = item.getName();
                    InputStream content = item.getInputStream();

                    httpResponse.setContentType("text/plain");
                    httpResponse.setCharacterEncoding("UTF-8");

                    // Do whatever with the content InputStream.
                    System.out.println(Streams.asString(content));
                    ajaxUpdateResult += "File " + fileName + " is successfully uploaded\n\r";
                }

            }

        } catch (FileUploadException e) {
            throw new ServletException("Parsing file upload failed.", e);
        }

        httpResponse.getWriter().print(ajaxUpdateResult);
    }

    public MaintainerBean getMaintainerBean() {
        return maintainerBean;
    }

    public void setMaintainerBean(MaintainerBean maintainerBean) {
        this.maintainerBean = maintainerBean;
    }

    public IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public IMaintainerService getMaintainerService() {
        return maintainerService;
    }

    public void setMaintainerService(IMaintainerService maintainerService) {
        this.maintainerService = maintainerService;
    }


}
