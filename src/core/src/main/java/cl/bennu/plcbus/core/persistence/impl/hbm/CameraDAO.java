package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.Camera;
import cl.bennu.plcbus.core.persistence.iface.ICameraDAO;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class CameraDAO extends BaseDAO<Camera> implements ICameraDAO {

    public CameraDAO() {
        super(Camera.class);
    }


    @Override
    public void save2(Camera camera) {
        Session session = this.getHibernateTemplate().getSessionFactory().openSession();
        /*
        Query query = session.getNamedQuery("saveCamera");
        query.setLong(0, camera.getDevice().getId()); //:deviceId, :cameraTypeEnum, :name, :ip, :port
        query.setLong(1, camera.getCameraTypeEnum().getId()); //:deviceId, :cameraTypeEnum, :name, :ip, :port
        query.setString(2, camera.getName()); //:deviceId, :cameraTypeEnum, :name, :ip, :port
        query.setString(3, camera.getIp()); //:deviceId, :cameraTypeEnum, :name, :ip, :port
        query.setLong(4, camera.getPort()); //:deviceId, :cameraTypeEnum, :name, :ip, :port

        query.executeUpdate();
*/
        if (camera.getId() == null) {
            SQLQuery sqlQuery = session.createSQLQuery("INSERT INTO CAMARA(dpst_id, tpcm_id, cmra_nombre, cmra_ip, cmra_port) VALUES(?, ?, ?, ?, ?)");
            sqlQuery.setParameter(0, camera.getDeviceId());
            sqlQuery.setParameter(1, camera.getCameraTypeEnum().getId());
            sqlQuery.setParameter(2, camera.getName());
            sqlQuery.setParameter(3, camera.getIp());
            sqlQuery.setParameter(4, camera.getPort());

            sqlQuery.executeUpdate();
        } else {
            SQLQuery sqlQuery = session.createSQLQuery("UPDATE CAMARA SET dpst_id = ?, tpcm_id = ?, cmra_nombre = ?, cmra_ip = ?, cmra_port = ? WHERE cmra_id = ?");
            sqlQuery.setParameter(0, camera.getDeviceId());
            sqlQuery.setParameter(1, camera.getCameraTypeEnum().getId());
            sqlQuery.setParameter(2, camera.getName());
            sqlQuery.setParameter(3, camera.getIp());
            sqlQuery.setParameter(4, camera.getPort());
            sqlQuery.setParameter(5, camera.getId());

            sqlQuery.executeUpdate();
        }
    }
}