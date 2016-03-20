package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.Level;
import cl.bennu.plcbus.core.persistence.iface.ILevelDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class LevelDAO extends BaseDAO<Level> implements ILevelDAO {

    public LevelDAO() {
        super(Level.class);
    }

    @Override
    public List<Level> findActive() {
        Criteria criteria = getSession().createCriteria(Level.class);
        criteria.add(Restrictions.eq("active", true));
        return criteria.list();
    }

    @Override
    public Level get(String name) {
        Criteria criteria = getSession().createCriteria(Level.class);
        criteria.add(Restrictions.eq("name", name));
        return (Level) criteria.uniqueResult();
    }

}