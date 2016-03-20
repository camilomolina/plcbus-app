package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.StatusRefreshDetail;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface IStatusRefreshDetailDAO extends IBaseDAO<StatusRefreshDetail> {

    void deleteByStatusRefreshId(Long statusRefreshId);

}
