package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.StatusRefreshDetail;
import cl.bennu.plcbus.core.persistence.iface.IDeviceDAO;
import cl.bennu.plcbus.core.persistence.iface.IStatusRefreshDetailDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class StatusRefreshDetailDAO extends BaseDAO<StatusRefreshDetail> implements IStatusRefreshDetailDAO {

    public StatusRefreshDetailDAO() {
        super(StatusRefreshDetail.class);
    }

    @Override
    public void deleteByStatusRefreshId(Long statusRefreshId) {
        Session session = getSession();
        String hql = "delete from StatusRefreshDetail where statusRefresh.id = :statusRefreshId";
        Query query = session.createQuery(hql);
        query.setLong("statusRefreshId", statusRefreshId);
        query.executeUpdate();
    }

}