package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.Map;
import cl.bennu.plcbus.core.persistence.iface.IMapDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class MapDAO extends BaseDAO<Map> implements IMapDAO {

    public MapDAO() {
        super(Map.class);
    }

    @Override
    public Map get(String name) {
        Criteria criteria = getSession().createCriteria(Map.class);
        criteria.add(Restrictions.eq("name", name));
        return (Map) criteria.uniqueResult();
    }

}