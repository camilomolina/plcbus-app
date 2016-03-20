package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.ProgrammingDetail;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface IProgrammingDetailDAO extends IBaseDAO<ProgrammingDetail> {

    void deleteByProgrammingId(Long programmingId);

}
