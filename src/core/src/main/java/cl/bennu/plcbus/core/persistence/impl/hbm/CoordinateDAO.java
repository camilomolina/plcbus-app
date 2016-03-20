package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.Coordinate;
import cl.bennu.plcbus.core.persistence.iface.ICoordinateDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class CoordinateDAO extends BaseDAO<Coordinate> implements ICoordinateDAO {

    public CoordinateDAO() {
        super(Coordinate.class);
    }


    @Override
    public Coordinate getByDeviceIdAndMapId(Long deviceId, Long mapId) {
        Criteria criteria = getSession().createCriteria(Coordinate.class);
        criteria.createCriteria("device").add(Restrictions.eq("id", deviceId));
        criteria.createCriteria("map").add(Restrictions.eq("id", mapId));
        return (Coordinate) criteria.uniqueResult();
    }

    @Override
    public void deleteByDeviceIdAndMapId(Long deviceId, Long mapId) {
        Session session = getSession();
        String hql = "delete from Coordinate where device.id = :deviceId and map.id = :mapId";
        Query query = session.createQuery(hql);
        query.setLong("deviceId", deviceId);
        query.setLong("mapId", mapId);
        query.executeUpdate();
    }
}