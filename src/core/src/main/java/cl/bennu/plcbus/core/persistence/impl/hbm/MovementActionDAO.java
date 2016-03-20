package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.MovementAction;
import cl.bennu.plcbus.core.persistence.iface.IMovementActionDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class MovementActionDAO extends BaseDAO<MovementAction> implements IMovementActionDAO {

    public MovementActionDAO() {
        super(MovementAction.class);
    }

    @Override
    public MovementAction getByDeviceId(Long deviceId) {
        Criteria criteria = getSession().createCriteria(MovementAction.class);
        criteria.createCriteria("device").add(Restrictions.eq("id", deviceId));
        return (MovementAction) criteria.uniqueResult();
    }
}