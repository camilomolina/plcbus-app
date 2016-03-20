package cl.bennu.plcbus.core.persistence.impl.mybatis;

import cl.bennu.plcbus.common.domain.Level;
import cl.bennu.plcbus.core.persistence.iface.ILevelDAO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 05-11-13
 * Time: 12:37 PM
 */
public class LevelDAO extends BaseDAO<Level> implements ILevelDAO {

    public LevelDAO() {
        super(Level.class);
    }

    @Override
    public List<Level> findActive() {
        return null;
    }

    @Override
    public Level get(String name) {
        return null;
    }
}
