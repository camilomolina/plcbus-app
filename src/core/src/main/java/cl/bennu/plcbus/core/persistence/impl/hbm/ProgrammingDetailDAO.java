package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.ProgrammingDetail;
import cl.bennu.plcbus.core.persistence.iface.IProgrammingDetailDAO;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class ProgrammingDetailDAO extends BaseDAO<ProgrammingDetail> implements IProgrammingDetailDAO {

    public ProgrammingDetailDAO() {
        super(ProgrammingDetail.class);
    }

    @Override
    public void deleteByProgrammingId(Long programmingId) {
        Session session = getSession();
        String hql = "delete from ProgrammingDetail where programming.id = :programmingId";
        Query query = session.createQuery(hql);
        query.setLong("programmingId", programmingId);
        query.executeUpdate();
    }

}