package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.Sector;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface ISectorDAO extends IBaseDAO<Sector> {

    List<Sector> findActive();

    Sector get(String name);

}
