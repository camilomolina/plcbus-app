package cl.bennu.plcbus.core.persistence.impl.jdbc;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.core.persistence.JDBCUtils;
import cl.bennu.plcbus.core.persistence.iface.IDeviceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public class DeviceDAO extends JDBCUtils<Device> implements IDeviceDAO {

    public DeviceDAO(Class<Device> persistentClass) {
        super(persistentClass);
    }

    @Override
    public List<Device> getAll() {
        Connection connection = getConnection();
        List<Device> result = new ArrayList<Device>();
        try {
            Statement st = null;
            ResultSet rs = null;

            st = connection.createStatement();
            rs = st.executeQuery("select dpst_id, nvel_id, dpst_nombre, dpst_informacion, dpst_estado, dpst_activo, dpst_usuario_creador, dpst_fecha_creacion, dpst_usuario_actualizador, dpst_fecha_actualizacion from dispositivo");

            while (rs.next()) {
                int i = 1;

                Device object = new Device();
                object.setId(rs.getLong(i++));
                object.setCode(rs.getString(i++));
                object.setName(rs.getString(i++));
                object.setStatus(rs.getBoolean(i++));
                object.setActive(rs.getBoolean(i++));

                object.setCreatorUser(rs.getString(i++));
                object.setCreate(rs.getDate(i++));
                object.setUpdateUser(rs.getString(i++));
                object.setUpdate(rs.getDate(i++));

                result.add(object);
            }
            st.close();

            return result;
        } catch (Exception e) {
            //error
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public Device get(Long id) {
        Connection connection = getConnection();

        Device result = null;
        try {
            PreparedStatement st = null;
            ResultSet rs = null;

            st = connection.prepareStatement("select dpst_id, nvel_id, dpst_nombre, dpst_informacion, dpst_estado, dpst_activo, dpst_usuario_creador, dpst_fecha_creacion, dpst_usuario_actualizador, dpst_fecha_actualizacion from dispositivo where dpst_id = ?");
            st.setLong(1, id);
            rs = st.executeQuery();

            int i = 1;
            if (rs.next()) {
                result = new Device();
                result.setId(rs.getLong(i++));
                result.setCode(rs.getString(i++));
                result.setName(rs.getString(i++));
                result.setStatus(rs.getBoolean(i++));
                result.setActive(rs.getBoolean(i++));

                result.setCreatorUser(rs.getString(i++));
                result.setCreate(rs.getDate(i++));
                result.setUpdateUser(rs.getString(i++));
                result.setUpdate(rs.getDate(i++));
            }
            st.close();

            return result;
        } catch (Exception e) {
            //error
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public Device get(String name) {
        Connection connection = getConnection();

        Device result = null;
        try {
            PreparedStatement st = null;
            ResultSet rs = null;

            st = connection.prepareStatement("select dpst_id, nvel_id, dpst_nombre, dpst_informacion, dpst_estado, dpst_activo, dpst_usuario_creador, dpst_fecha_creacion, dpst_usuario_actualizador, dpst_fecha_actualizacion from dispositivo where dpst_nombre = ?");
            st.setString(1, name);
            rs = st.executeQuery();

            int i = 1;
            if (rs.next()) {
                result = new Device();
                result.setId(rs.getLong(i++));
                //result.setLevelId(rs.getLong(i++));
                result.setCode(rs.getString(i++));
                result.setName(rs.getString(i++));
                result.setStatus(rs.getBoolean(i++));
                result.setActive(rs.getBoolean(i++));

                result.setCreatorUser(rs.getString(i++));
                result.setCreate(rs.getDate(i++));
                result.setUpdateUser(rs.getString(i++));
                result.setUpdate(rs.getDate(i++));
            }
            st.close();

            return result;
        } catch (Exception e) {
            //error
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public Device save(Device type) {
        Connection connection = getConnection();

        Device result = null;
        try {
            int i = 1;
            PreparedStatement st = null;
            if (type.getId() == null) {
                st = connection.prepareStatement("insert into dispositivo (nvel_id, dpst_nombre, dpst_informacion, dpst_estado, dpst_activo, dpst_usuario_creador, dpst_fecha_creacion, dpst_usuario_actualizador, dpst_fecha_actualizacion) values (?,?,?,?,?,?,?,?)");
                //st.setLong(i++, type.getLevelId());
                st.setString(i++, type.getCode());
                st.setString(i++, type.getName());
                st.setBoolean(i++, type.getStatus());
                st.setBoolean(i++, type.getActive());
                st.setString(i++, type.getCreatorUser());
                st.setDate(i++, getDate(type.getCreate()));
                st.setString(i++, type.getUpdateUser());
                st.setDate(i++, getDate(type.getUpdate()));
                st.executeUpdate();
            } else {
                st = connection.prepareStatement("update dispositivo set nvel_id = ?, dpst_nombre = ?, dpst_informacion = ?, dpst_estado = ?, dpst_activo = ?, dpst_usuario_creador = ?, dpst_fecha_creacion = ?, dpst_usuario_actualizador = ?, dpst_fecha_actualizacion = ? where dpst_id = ?");
                //st.setLong(i++, type.getLevelId());
                st.setString(i++, type.getCode());
                st.setString(i++, type.getName());
                st.setBoolean(i++, type.getStatus());
                st.setBoolean(i++, type.getActive());
                st.setString(i++, type.getCreatorUser());
                st.setDate(i++, getDate(type.getCreate()));
                st.setString(i++, type.getUpdateUser());
                st.setDate(i++, getDate(type.getUpdate()));
                st.setLong(i++, type.getId());
                st.executeUpdate();
            }
            st.close();

            return result;
        } catch (Exception e) {
            //error
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Device> findByLevel(Long levelId) {
        return null;
    }

    @Override
    public List<Device> findActive() {
        return null;
    }

    @Override
    public String getDeviceCodeFinal() {
        return null;
    }

    @Override
    public String getDeviceCameraCodeFinal() {
        return null;
    }
}
