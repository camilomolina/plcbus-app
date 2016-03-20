package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.StatusRefresh;
import cl.bennu.plcbus.core.persistence.iface.IDeviceDAO;
import cl.bennu.plcbus.core.persistence.iface.IStatusRefreshDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class StatusRefreshDAO extends BaseDAO<StatusRefresh> implements IStatusRefreshDAO {

    public StatusRefreshDAO() {
        super(StatusRefresh.class);
    }


    @Override
    public StatusRefresh getLasted() {
        Criteria criteria = getSession().createCriteria(StatusRefresh.class);
        criteria.addOrder(Order.desc("start"));
        List<StatusRefresh> list = criteria.list();
        return list != null && list.size() != 0 ? list.get(0) : null;
    }
}