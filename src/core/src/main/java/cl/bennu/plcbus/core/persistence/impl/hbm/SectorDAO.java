package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.Property;
import cl.bennu.plcbus.common.domain.Sector;
import cl.bennu.plcbus.core.persistence.iface.IPropertyDAO;
import cl.bennu.plcbus.core.persistence.iface.ISectorDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class SectorDAO extends BaseDAO<Sector> implements ISectorDAO {

    public SectorDAO() {
        super(Sector.class);
    }


    @Override
    public List<Sector> findActive() {
        Criteria criteria = getSession().createCriteria(Sector.class);
        criteria.add(Restrictions.eq("active", true));
        return criteria.list();
    }

    @Override
    public Sector get(String name) {
        Criteria criteria = getSession().createCriteria(Sector.class);
        criteria.add(Restrictions.eq("name", name));
        return (Sector) criteria.uniqueResult();
    }

}