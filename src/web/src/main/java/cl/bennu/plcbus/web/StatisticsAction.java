package cl.bennu.plcbus.web;

import cl.bennu.plcbus.base.BaseAction;
import cl.bennu.plcbus.bean.StatisticsBean;
import cl.bennu.plcbus.build.XLSBuild;
import cl.bennu.plcbus.common.domain.*;
import cl.bennu.plcbus.common.dto.ConsumerDTO;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import cl.bennu.plcbus.core.business.iface.IStatisticsService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 03-07-13
 * Time: 03:21 AM
 */
public class StatisticsAction extends BaseAction {

    private String REPORT_SUCCESS = "reportSuccess";
    private String GRAPHICS_SUCCESS = "graphicsSuccess";
    private String STATISTICS_SUCCESS = "statisticsSuccess";

    private String DEVICE_XLS_REPORT = "deviceXLS";
    private String LEVEL_XLS_REPORT = "levelXLS";
    private String SECTOR_XLS_REPORT = "sectorXLS";
    private String EVENT_XLS_REPORT = "eventXLS";


    private StatisticsBean statisticsBean = new StatisticsBean();

    private IStatisticsService statisticsService;
    private IMaintainerService maintainerService;

    @Override
    protected void initial() throws Exception {

    }

    public String startStatistics() throws Exception {

        return STATISTICS_SUCCESS;
    }

    public String startReport() throws Exception {

        return REPORT_SUCCESS;
    }

    public String startGraphics() throws Exception {

        return GRAPHICS_SUCCESS;
    }

    public void matrixDeviceVsEvent() throws Exception {
        List<Event> matrixDeviceVsEvent = statisticsService.matrixDeviceVsEvent(getContext());
        serialize(matrixDeviceVsEvent);
    }

    public void matrixDevice2() throws Exception {
        List<Event> matrix2 = statisticsService.matrixDevice2(getContext());
        serialize(matrix2);
    }

    public void matrixConsumer() throws Exception {
        List<ConsumerDTO> matrix3 = statisticsService.matrixConsumer(getContext());
        serialize(matrix3);
    }

    public void consumption() throws Exception {
        Long consumption = statisticsService.consumption(getContext());
        serialize(consumption);
    }

    public void matrixSensor() throws Exception {
        List<Sensor4DeviceDTO> sensor4Day2List = statisticsService.matrixSensor2(getContext());
        serialize(sensor4Day2List);
    }

    public void matrixSensorWeek() throws Exception {
        List<Sensor4DeviceDTO> sensor4Day2List = statisticsService.matrixSensorWeek(getContext());
        serialize(sensor4Day2List);
    }

    public String deviceXls() throws Exception {
        List<Device> deviceList = maintainerService.getAllDevice(getContext());

        InputStream inputStream = XLSBuild.deviceXLS(deviceList);
        statisticsBean.setExcelStream(inputStream);

        return DEVICE_XLS_REPORT;
    }

    public String levelXls() throws Exception {
        List<Level> levelList = maintainerService.getAllLevel(getContext());

        InputStream inputStream = XLSBuild.levelXLS(levelList);
        statisticsBean.setExcelStream(inputStream);

        return LEVEL_XLS_REPORT;
    }

    public String sectorXls() throws Exception {
        List<Sector> sectorList = maintainerService.getAllSector(getContext());

        InputStream inputStream = XLSBuild.sectorXLS(sectorList);
        statisticsBean.setExcelStream(inputStream);

        return SECTOR_XLS_REPORT;
    }

    public String eventXls() throws Exception {
        List<Event> eventList = statisticsService.findEventLastWeek(getContext());

        InputStream inputStream = XLSBuild.eventXLS(eventList);
        statisticsBean.setExcelStream(inputStream);

        return EVENT_XLS_REPORT;
    }

    public String sensorXls() throws Exception {
        List<Event> eventList = statisticsService.findSensorEvent(getContext());

        InputStream inputStream = XLSBuild.eventXLS(eventList);
        statisticsBean.setExcelStream(inputStream);

        return EVENT_XLS_REPORT;
    }


    public StatisticsBean getStatisticsBean() {
        return statisticsBean;
    }

    public void setStatisticsBean(StatisticsBean statisticsBean) {
        this.statisticsBean = statisticsBean;
    }

    public IStatisticsService getStatisticsService() {
        return statisticsService;
    }

    public void setStatisticsService(IStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public IMaintainerService getMaintainerService() {
        return maintainerService;
    }

    public void setMaintainerService(IMaintainerService maintainerService) {
        this.maintainerService = maintainerService;
    }
}
