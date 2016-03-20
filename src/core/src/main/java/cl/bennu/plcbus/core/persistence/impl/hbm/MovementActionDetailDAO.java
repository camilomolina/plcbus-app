package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.MovementActionDetail;
import cl.bennu.plcbus.core.persistence.iface.IMovementActionDetailDAO;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class MovementActionDetailDAO extends BaseDAO<MovementActionDetail> implements IMovementActionDetailDAO {

    public MovementActionDetailDAO() {
        super(MovementActionDetail.class);
    }

    @Override
    public void deleteByMovementActionId(Long movementActionId) {
        Session session = getSession();
        String hql = "delete from MovementActionDetail where movementAction.id = :movementActionId";
        Query query = session.createQuery(hql);
        query.setLong("movementActionId", movementActionId);
        query.executeUpdate();
    }

}