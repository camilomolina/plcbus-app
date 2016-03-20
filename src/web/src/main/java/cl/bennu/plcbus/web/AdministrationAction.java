package cl.bennu.plcbus.web;

import cl.bennu.plcbus.base.BaseAction;
import cl.bennu.plcbus.bean.AdministrationBean;
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
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 03-07-13
 * Time: 03:21 AM
 */
public class AdministrationAction extends BaseAction {

    private static final String CAMERA_SUCCESS = "cameraSuccess";

    private AdministrationBean administrationBean = new AdministrationBean();

    private IMaintainerService maintainerService;
    private IConfigurationService configurationService;

    @Override
    protected void initial() throws Exception {

    }

    public String commandCamera() throws Exception {
        String url = "http://" + administrationBean.getIp() + "/snapshot.jpg?user=admin&resolution=1&time=" + new Date().getTime();

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String line = "";
        while ((line = rd.readLine()) != null) {
            httpResponse.getWriter().write(line);
        }

        return CAMERA_SUCCESS;
    }

    public String startCamera() throws Exception {
        List<Device> cameraList = maintainerService.findActiveDevice(getContext(), GeneralDeviceTypeEnum.CAMERA);
        administrationBean.setCameraList(cameraList);

        administrationBean.setCameraTypeEnumList(CameraTypeEnum.valuesList());

        return CAMERA_SUCCESS;
    }

    public void getCamera() throws Exception {
        Device device = maintainerService.getDeviceByCode(getContext(), administrationBean.getDeviceCode());
        serialize(device);
    }

    public AdministrationBean getAdministrationBean() {
        return administrationBean;
    }

    public void setAdministrationBean(AdministrationBean administrationBean) {
        this.administrationBean = administrationBean;
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
