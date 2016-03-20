package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.Level;
import cl.bennu.plcbus.common.domain.Sector;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface ILevelDAO extends IBaseDAO<Level> {

    List<Level> findActive();

    Level get(String name);

}
