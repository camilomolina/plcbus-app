package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.MovementActionDetail;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface IMovementActionDetailDAO extends IBaseDAO<MovementActionDetail> {

    void deleteByMovementActionId(Long movementActionId);

}
