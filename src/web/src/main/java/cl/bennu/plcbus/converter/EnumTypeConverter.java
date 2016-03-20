package cl.bennu.plcbus.converter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts2.util.StrutsTypeConverter;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 01-02-14
 * Time: 05:04 PM
 */
public class EnumTypeConverter extends StrutsTypeConverter {

    private static final String DEFAULT_IDENTIFIER_METHOD_NAME = "getId";
    private static final String DEFAULT_VALUE_OF_METHOD_NAME = "valueOf";

    public EnumTypeConverter() {
        super();
    }

    /**
     * Convierte valores String a Enum. El valor String corresponde a la propiedad id del Enum. Si el enum no tiene
     * propiedad id, lanza TypeConversionException.
     * @param context context
     * @param values  values
     * @param toClass  toClass
     * @return Enum segun id
     */
    public Object convertFromString(Map context, String[] values, Class toClass) {
        String value = values[0];
        if (value != null && value.length() > 0) {
            try {
                Class<?> identifierType = toClass.getMethod(DEFAULT_IDENTIFIER_METHOD_NAME).getReturnType();
                Method valueOfMethod = toClass.getMethod(DEFAULT_VALUE_OF_METHOD_NAME, identifierType);
                return valueOfMethod.invoke(null, ConvertUtils.convert(value, identifierType));
            } catch (Exception e) {
                throw new TypeConversionException("Error al convertir String " + value + " a Enum");
            }
        }

        return null;
    }

    /**
     *  Retorna representacion String del Id del Enum
     * @param context Contexto de Struts
     * @param o Objecto Enum
     * @return Representacion String del Id del Enum
     */
    public String convertToString(Map context, Object o) {
        try {
            Method identifierMethod = o.getClass().getMethod(DEFAULT_IDENTIFIER_METHOD_NAME);
            return identifierMethod.invoke(o).toString();
        } catch (Exception e) {
            return null;
        }
    }


}
