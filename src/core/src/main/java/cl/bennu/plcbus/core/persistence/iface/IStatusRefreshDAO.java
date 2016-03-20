package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.StatusRefresh;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface IStatusRefreshDAO extends IBaseDAO<StatusRefresh> {

    StatusRefresh getLasted();


}
