package cl.bennu.plcbus.build;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.domain.Level;
import cl.bennu.plcbus.common.domain.Sector;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XLSBuild {

    private static CellStyle buildCellStyleHeader(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        //font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        return cellStyle;
    }

    private static CellStyle buildCellStyleDetail(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 9);
        font.setColor(IndexedColors.BLUE_GREY.getIndex());
        font.setBold(false);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        return cellStyle;
    }
    
    public static InputStream deviceXLS(List<Device> deviceList) throws IOException {
        if (deviceList != null) {
            int i = 0;
            int f = 0;

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Dispositivos");

            Header header = sheet.getHeader();
            header.setCenter("Center Header");
            header.setLeft("Left Header");
            header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") + HSSFHeader.fontSize((short) 16) + "Right w/ Stencil-Normal Italic font and size 16");

            Row row = sheet.createRow(f++);

            Cell cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Nombre");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Codigo");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Descripcion");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Zona");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Activo");

            for (Device device : deviceList) {
                i = 0;

                row = sheet.createRow(f++);

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(device.getName());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(device.getCode());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(device.getDesc());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(device.getSector() == null ? "" : device.getSector().getName());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(device.getActive());
            }

            // ajusta celdas
            for (int x = 0; x < i; x++) {
                sheet.setColumnWidth(x, 20 * 256);
            }

            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            workbook.write(boas);

            return new ByteArrayInputStream(boas.toByteArray());
        }

        return null;
    }

    public static InputStream levelXLS(List<Level> levelList) throws IOException {
        if (levelList != null) {
            int i = 0;
            int f = 0;

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Niveles");

            Row row = sheet.createRow(f++);

            Cell cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Nombre");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Activo");

            for (Level level : levelList) {
                i = 0;

                row = sheet.createRow(f++);

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(level.getName());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(level.getActive());

            }

            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            workbook.write(boas);

            return new ByteArrayInputStream(boas.toByteArray());
        }

        return null;
    }

    public static InputStream sectorXLS(List<Sector> sectorList) throws IOException {
        if (sectorList != null) {
            int i = 0;
            int f = 0;

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sectores");

            Row row = sheet.createRow(f++);

            Cell cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Nombre");


            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Nivel");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Activo");

            for (Sector sector : sectorList) {
                i = 0;

                row = sheet.createRow(f++);

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(sector.getName());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(sector.getLevel() == null ? "" : sector.getLevel().getName());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(sector.getActive());
            }

            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            workbook.write(boas);

            return new ByteArrayInputStream(boas.toByteArray());
        }

        return null;
    }

    public static InputStream eventXLS(List<Event> eventList) throws IOException {
        if (eventList != null) {
            int i = 0;
            int f = 0;

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Eventos");

            Row row = sheet.createRow(f++);

            Cell cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Codigo dispositivo");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Nombre dispositivo");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Tipo de dispositivo general");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Tipo de dispositivo");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Fecha");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Tipo de evento");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Descripcion");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Dato 1");

            cell = row.createCell(i++);
            //cell.setCellStyle(buildCellStyleHeader(workbook));
            cell.setCellValue("Dato 2");

            for (Event event : eventList) {
                i = 0;

                row = sheet.createRow(f++);

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(event.getDeviceCode());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(event.getDeviceName());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(event.getDeviceTypeEnum() == null ? "" : event.getDeviceTypeEnum().getGeneralDeviceTypeEnum().getName());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(event.getDeviceTypeEnum() == null ? "" : event.getDeviceTypeEnum().getName());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(event.getDate());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(event.getEventTypeEnum() == null ? "" : event.getEventTypeEnum().getName());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(event.getDesc());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(event.getData1());

                cell = row.createCell(i++);
                //cell.setCellStyle(buildCellStyleDetail(workbook));
                cell.setCellValue(event.getData2());
            }

            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            workbook.write(boas);

            return new ByteArrayInputStream(boas.toByteArray());
        }

        return null;
    }

}
