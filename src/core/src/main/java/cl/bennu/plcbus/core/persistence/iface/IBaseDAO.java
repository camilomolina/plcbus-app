package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.BaseDomain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:17 AM
 */
public interface IBaseDAO<T extends BaseDomain> {

    List<T> getAll();
    List<T> find(T type);
    T get(Long id);
    T save(T type);
    void delete(Long id);
    Long count();

}
