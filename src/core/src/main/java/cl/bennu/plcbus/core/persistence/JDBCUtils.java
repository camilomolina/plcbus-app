package cl.bennu.plcbus.core.persistence;

import cl.bennu.plcbus.common.domain.BaseDomain;
import cl.bennu.plcbus.core.persistence.impl.hbm.BaseDAO;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 10-07-13
 * Time: 04:45 AM
 */
public abstract class JDBCUtils<T extends BaseDomain> extends BaseDAO<T> {

    private static final String fileDB = "c:\\database\\plcbus_db.hsqldb";

    public JDBCUtils(Class<T> persistentClass) {
        super(persistentClass);
    }

    public static Connection getConnection() {
        try {
            //Context context = new InitialContext();

            //DataSource dataSource = (DataSource) context.lookup("jboss/datasources/plcbusDS");
            //return dataSource.getConnection();

            //DataSource dataSource = ServiceLocator.getDataSource("jdbc/s2");
            //return dataSource.getConnection();
            Class.forName("org.hsqldb.jdbcDriver");
            //return DriverManager.getConnection("jdbc:hsqldb:" + fileDB, "sa", "");
            return DriverManager.getConnection("jdbc:hsqldb:hsql://localhost");

        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public static void createDB() {
        try {
            Connection connection = getConnection();
            Statement st = connection.createStatement();
            /*


            */

            st.executeUpdate("drop table dispositivo ");
            st.executeUpdate("drop table nivel ");

            st.executeUpdate("create table dispositivo (dpst_id integer identity, nvel_id integer, dpst_nombre varchar(2), dpst_informacion varchar(1000), dpst_activo boolean, dpst_estado boolean, dpst_usuario_creador varchar(30), dpst_fecha_creacion datetime, dpst_usuario_actualizador varchar(30), dpst_fecha_actualizacion datetime)");
            st.executeUpdate("create table nivel (nvel_id integer identity, nvel_nombre varchar(60), nvel_usuario_creador varchar(30), nvel_fecha_creacion datetime, nvel_usuario_actualizador varchar(30), nvel_fecha_actualizacion datetime)");

            // carga inicial
            st.executeUpdate("INSERT INTO nivel(nvel_nombre) VALUES('Primer Piso')");
            st.executeUpdate("INSERT INTO nivel(nvel_nombre) VALUES('Segundo Piso')");

            st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, nvel_id, dpst_estado, dpst_informacion) VALUES('A1', 1, 1, 'Living1')");
            st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, nvel_id, dpst_estado, dpst_informacion) VALUES('A2', 1, 1, 'Living2')");
            st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, nvel_id, dpst_estado, dpst_informacion) VALUES('A3', 1, 1, 'Frontis')");
            st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, nvel_id, dpst_estado, dpst_informacion) VALUES('A4', 1, 1, '')");
            st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, nvel_id, dpst_estado, dpst_informacion) VALUES('A5', 1, 1, 'Cocina Exterior')");
            st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, nvel_id, dpst_estado, dpst_informacion) VALUES('A6', 1, 1, 'Enchufe Cocina')");
            st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, nvel_id, dpst_estado, dpst_informacion) VALUES('A7', 1, 1, 'Pasillo 1° Piso')");
            st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, nvel_id, dpst_estado, dpst_informacion) VALUES('A8', 1, 1, 'Cocina')");

            st.close();
            closeConnection(connection);
        } catch (Exception e) {

        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if ((connection != null) && (!connection.isClosed())) {
                Statement st = connection.createStatement();
                st.execute("SHUTDOWN");
                connection.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static String callDB(String pkgs, String method, int parameters) {
        //"{ call PCKG_ENSAYO_CRUD.get_All(?) }";
        StringBuffer callSP = new StringBuffer();
        callSP.append("{ call ");
        callSP.append(pkgs);
        callSP.append(".");
        callSP.append(method);
        callSP.append("(");

        if (parameters > 0) {
            callSP.append("?");
            for (int i = 1; i < parameters; i++) {
                callSP.append(",?");
            }
        }
        callSP.append(")");

        return callSP.toString();
    }

    public static Date getDate(java.util.Date date) {
        if (date == null) return null;
        return new Date(date.getTime());
    }

    public static String getStringLike(String value) {
        if (value == null) return null;
        return "%" + value + "%";
    }

    public static java.util.Date toDate(java.sql.Date date) {
        if (date == null) return null;
        return new java.util.Date(date.getTime());
    }

    public static void main(String[] args) throws Exception{
        Connection connection = getConnection();

        System.out.println(connection);


        try {
            Statement st = null;
            ResultSet rs = null;
                              String sql = "select dpst_id\" +\n" +
                                      "                    \", nvel_id\" +\n" +
                                      "                    \", dpst_nombre\" +\n" +
                                      "                    \", dpst_informacion\" +\n" +
                                      "                    \", dpst_estado\" +\n" +
                                      "                    \", dpst_activo\" +\n" +
                                      "                    \", dpst_nivel_ruido\" +\n" +
                                      "                    \", dpst_nivel_senhal\" +\n" +
                                      "                    \", dpst_usuario_creador\" +\n" +
                                      "                    \", dpst_fecha_creacion\" +\n" +
                                      "                    \", dpst_usuario_actualizador\" +\n" +
                                      "                    \", dpst_fecha_actualizacion \" +\n" +
                                      "                    \"from dispositivo";

          //   sql = "select * from dispositivo";

            st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int i = 1;


                System.out.println(rs.getString(i++));

            }
            st.close();


        } catch (Exception e) {
            //error
        } finally {
            //closeConnection(connection);
        }

    }

    public static void main2(String[] args) throws Exception{
        /*
        Connection connection = getConnection();

        Statement st = connection.createStatement();

        st.executeUpdate("delete from dispositivo");

        st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, dpst_informacion) VALUES('A1', 'Living 1')");
        st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, dpst_informacion) VALUES('A2', 'Living 2')");
        st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, dpst_informacion) VALUES('A3', 'Frontis')");
        st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, dpst_informacion) VALUES('A4', '')");

        st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, dpst_informacion) VALUES('A5', 'Cocina Exterior')");
        st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, dpst_informacion) VALUES('A6', 'Enchufe Cocina')");

        st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, dpst_informacion) VALUES('A8', 'Cocina')");
        st.executeUpdate("INSERT INTO dispositivo(dpst_nombre, dpst_informacion) VALUES('A7', 'Pasillo 1° Piso')");


        ResultSet resultSet = st.executeQuery("select * from dispositivo");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
        }

        closeConnection(connection);
*/

        Connection connection = getConnection();
        createDB();
        closeConnection(connection);

    }

}
