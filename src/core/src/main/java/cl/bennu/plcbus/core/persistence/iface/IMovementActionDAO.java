package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.MovementAction;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface IMovementActionDAO extends IBaseDAO<MovementAction> {

    MovementAction getByDeviceId(Long deviceId);

}
