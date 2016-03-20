package cl.bennu.plcbus.core.persistence.impl.jdbc;

import cl.bennu.plcbus.common.domain.Level;
import cl.bennu.plcbus.core.persistence.JDBCUtils;
import cl.bennu.plcbus.core.persistence.iface.ILevelDAO;

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
public class LevelDAO extends JDBCUtils<Level> implements ILevelDAO {

    public LevelDAO(Class<Level> persistentClass) {
        super(persistentClass);
    }

    @Override
    public List<Level> getAll() {
        Connection connection = getConnection();
        List<Level> result = new ArrayList<Level>();
        try {
            Statement st = null;
            ResultSet rs = null;

            st = connection.createStatement();
            rs = st.executeQuery("select nvel_id, nvel_nombre, nvel_usuario_creador, nvel_fecha_creacion, nvel_usuario_actualizador, nvel_fecha_actualizacion from nivel");

            while (rs.next()) {
                int i = 1;

                Level object = new Level();
                object.setId(rs.getLong(i++));
                object.setName(rs.getString(i++));

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
    public Level get(Long id) {
        Connection connection = getConnection();

        Level result = null;
        try {
            PreparedStatement st = null;
            ResultSet rs = null;

            st = connection.prepareStatement("select nvel_id, nvel_nombre, nvel_usuario_creador, nvel_fecha_creacion, nvel_usuario_actualizador, nvel_fecha_actualizacion from nivel where nvel_id = ?");
            st.setLong(1, id);
            rs = st.executeQuery();

            int i = 1;
            if (rs.next()) {
                result = new Level();
                result.setId(rs.getLong(i++));
                result.setName(rs.getString(i++));

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
    public Level save(Level type) {
        Connection connection = getConnection();

        Level result = null;
        try {
            PreparedStatement st = null;

            int i = 1;
            if (type.getId() == null) {
                st = connection.prepareStatement("insert into nivel (nvel_nombre, nvel_usuario_creador, nvel_fecha_creacion, nvel_usuario_actualizador, nvel_fecha_actualizacion) values (?,?,?,?,?)");
                st.setString(i++, type.getName());
                st.setString(i++, type.getCreatorUser());
                st.setDate(i++, getDate(type.getCreate()));
                st.setString(i++, type.getUpdateUser());
                st.setDate(i++, getDate(type.getUpdate()));

                st.executeUpdate();
            } else {
                st = connection.prepareStatement("update nivel set nvel_nombre = ?, nvel_usuario_creador = ?, nvel_fecha_creacion = ?, nvel_usuario_actualizador = ?, nvel_fecha_actualizacion = ? where nvel_id = ?");
                st.setString(i++, type.getName());
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
    public List<Level> findActive() {
        return null;
    }

    @Override
    public Level get(String name) {
        return null;
    }
}
