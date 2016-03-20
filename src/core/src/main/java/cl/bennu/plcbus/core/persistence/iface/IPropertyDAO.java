package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.Property;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface IPropertyDAO extends IBaseDAO<Property> {

    Property getSystemProperty();

}
