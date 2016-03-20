package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Programming;
import cl.bennu.plcbus.core.persistence.iface.IProgrammingDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class ProgrammingDAO extends BaseDAO<Programming> implements IProgrammingDAO {

    public ProgrammingDAO() {
        super(Programming.class);
    }

    @Override
    public Programming getByDeviceId(Long deviceId) {
        Criteria criteria = getSession().createCriteria(Programming.class);
        criteria.createCriteria("device").add(Restrictions.eq("id", deviceId));
        return (Programming) criteria.uniqueResult();
    }
}