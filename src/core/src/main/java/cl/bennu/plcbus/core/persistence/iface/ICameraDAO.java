package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.Camera;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface ICameraDAO extends IBaseDAO<Camera> {

    void save2(Camera camera);

}
