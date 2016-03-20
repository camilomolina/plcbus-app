package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.ProgrammingDetail;
import cl.bennu.plcbus.common.domain.Property;
import cl.bennu.plcbus.core.persistence.iface.IProgrammingDetailDAO;
import cl.bennu.plcbus.core.persistence.iface.IPropertyDAO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class PropertyDAO extends BaseDAO<Property> implements IPropertyDAO {

    public PropertyDAO() {
        super(Property.class);
    }

    @Override
    public Property getSystemProperty() {
        List list = getHibernateTemplate().loadAll(Property.class);
        return list == null || list.isEmpty() ? null : (Property) list.get(0);
    }

}