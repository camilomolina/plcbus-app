package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.Consumer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface IConsumerDAO extends IBaseDAO<Consumer> {

    List<Consumer> getByCode(String deviceCode);

    List<Consumer> matrixByDevice();



}
