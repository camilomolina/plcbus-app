package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.Device;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface IDeviceDAO extends IBaseDAO<Device> {

    Device get(String code);

    List<Device> findByLevel(Long levelId);

    List<Device> findActive();

    String getDeviceCodeFinal();

    String getDeviceCameraCodeFinal();

}
