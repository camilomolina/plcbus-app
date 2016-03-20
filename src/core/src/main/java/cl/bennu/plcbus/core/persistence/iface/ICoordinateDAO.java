package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.Coordinate;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface ICoordinateDAO extends IBaseDAO<Coordinate> {

    Coordinate getByDeviceIdAndMapId(Long deviceId, Long mapId);

    void deleteByDeviceIdAndMapId(Long deviceId, Long mapId);


}
