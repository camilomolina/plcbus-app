package cl.bennu.plcbus.bean;

import cl.bennu.plcbus.common.domain.Event;

import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 04-07-13
 * Time: 12:32 AM
 */
public class StatisticsBean {

    private List<Event> matrixEventVsDevice;
    private List<Event> matrixEvent2;

    private InputStream excelStream;

    public List<Event> getMatrixEventVsDevice() {
        return matrixEventVsDevice;
    }

    public void setMatrixEventVsDevice(List<Event> matrixEventVsDevice) {
        this.matrixEventVsDevice = matrixEventVsDevice;
    }

    public List<Event> getMatrixEvent2() {
        return matrixEvent2;
    }

    public void setMatrixEvent2(List<Event> matrixEvent2) {
        this.matrixEvent2 = matrixEvent2;
    }

    public InputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }


}
