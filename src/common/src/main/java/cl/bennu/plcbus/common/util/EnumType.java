package cl.bennu.plcbus.common.util;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.AbstractStandardBasicType;
import org.hibernate.type.SingleColumnType;
import org.hibernate.type.TypeResolver;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 29-10-13
 * Time: 12:10 AM
 */
public class EnumType implements UserType, ParameterizedType {
    private static final long serialVersionUID = -1854479466843620961L;
    private static final String DEFAULT_IDENTIFIER_METHOD_NAME = "getId";
    private static final String DEFAULT_VALUE_OF_METHOD_NAME = "valueOf";
    private Class<? extends Enum> enumClass;
    private Class<?> identifierType;
    private Method identifierMethod;
    private Method valueOfMethod;
    private AbstractStandardBasicType<? extends Object> type;
    private int[] sqlTypes;

    public EnumType(){

    }

    @Override
    public void setParameterValues(Properties parameters) {
        String enumClassName = parameters.getProperty("enumClass");

        try {
            enumClass = Class.forName(enumClassName).asSubclass(Enum.class);
        } catch (ClassNotFoundException cfne) {
            throw new HibernateException("Enum class not found", cfne);
        }

        String identifierMethodName = parameters.getProperty("identifierMethod", DEFAULT_IDENTIFIER_METHOD_NAME);

        try {
            identifierMethod = enumClass.getMethod(identifierMethodName, new Class[0]);
            identifierType = identifierMethod.getReturnType();
        } catch (Exception e) {
            throw new HibernateException("Failed to obtain identifier method", e);
        }

        type = (AbstractSingleColumnStandardBasicType<? extends Object>) new TypeResolver().heuristicType(identifierType.getName(), parameters);

        if (type == null) {
            throw new HibernateException("Unsupported identifier type " + identifierType.getName());
        }

        sqlTypes = new int[]{((AbstractSingleColumnStandardBasicType<?>) type).sqlType()};

        String valueOfMethodName = parameters.getProperty("valueOfMethod", DEFAULT_VALUE_OF_METHOD_NAME);

        try {
            valueOfMethod = enumClass.getMethod(valueOfMethodName, new Class[]{identifierType});
        } catch (Exception e) {
            throw new HibernateException("Failed to obtain valueOf method", e);
        }
    }

    @Override
    public Class<? extends Enum> returnedClass() {
        return enumClass;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        Object identifier = type.get(rs, names[0], null);
        if (rs.wasNull()) {
            return null;
        }

        try {
            return valueOfMethod.invoke(enumClass, new Object[]{identifier});
        } catch (Exception e) {
            throw new HibernateException("Exception while invoking valueOf method '" + valueOfMethod.getName() + "' of "
                    + "enumeration class '" + enumClass + "'", e);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        try {
            if (value == null) {
                st.setNull(index, ((AbstractSingleColumnStandardBasicType<?>) type).sqlType());
            } else {
                Object identifier = identifierMethod.invoke(value, new Object[0]);
                type.nullSafeSet(st, identifier, index, null);
            }
        } catch (Exception e) {
            throw new HibernateException("Exception while invoking identifierMethod '" + identifierMethod.getName() + "' of "
                    + "enumeration class '" + enumClass + "'", e);
        }
    }

    @Override
    public int[] sqlTypes() {
        return sqlTypes;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}

