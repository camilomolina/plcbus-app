package cl.bennu.plcbus.converter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.text.ParseException;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 01-02-14
 * Time: 05:03 PM
 */
public class DateTypeConverter extends StrutsTypeConverter {

    public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
    private DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);

    public DateTypeConverter() {
        super();
    }

    public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values.length == 1) {
            try {
                return ((StringUtils.isNotBlank(values[0])) ? dateFormat.parse(values[0]) : null);
            } catch (ParseException e) {
                throw new TypeConversionException("Error al parsear fecha " + values[0] + ". Patron: " + DEFAULT_DATE_PATTERN);
            }
        } else if (values.length > 1) {
            try {
                Date[] dates = new Date[values.length];
                for (int i = 0; i < values.length; i++) {
                    dates[i] = ((StringUtils.isNotBlank(values[i])) ? dateFormat.parse(values[i]) : null);
                }

                return dates;
            } catch (ParseException e) {
                throw new TypeConversionException("Error al parsear arreglo de fechas");
            }
        }

        return null;
    }

    public String convertToString(Map map, Object dateObj) {
        return (dateObj != null) ? dateFormat.format((Date) dateObj) : null;
    }
}
