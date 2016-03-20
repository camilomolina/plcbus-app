package cl.bennu.plcbus.converter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 01-02-14
 * Time: 05:03 PM
 */
public class NumberTypeConverter extends StrutsTypeConverter {

    public static final String DEFAULT_NUMBER_PATTERN = "###.###";
    private DecimalFormat decimalFormat = new DecimalFormat(DEFAULT_NUMBER_PATTERN);

    public NumberTypeConverter() {
        super();
    }

    public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values.length == 1) {
            try {
                return ((StringUtils.isNotBlank(values[0])) ? decimalFormat.parse(values[0]) : null);
            } catch (ParseException e) {
                throw new TypeConversionException("Error al parsear fecha " + values[0] + ". Patron: " + DEFAULT_NUMBER_PATTERN);
            }
        } else if (values.length > 1) {
            try {
                Number[] numbers = new Number[values.length];
                for (int i = 0; i < values.length; i++) {
                    numbers[i] = ((StringUtils.isNotBlank(values[i])) ? decimalFormat.parse(values[i]) : null);
                }

                return numbers;
            } catch (ParseException e) {
                throw new TypeConversionException("Error al parsear arreglo de fechas");
            }
        }

        return null;
    }

    public String convertToString(Map map, Object dateObj) {
        return (dateObj != null) ? decimalFormat.format(dateObj) : null;
    }
}
