package cl.bennu.plcbus.core.persistence.impl.mybatis;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.core.persistence.iface.IDeviceDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 05-11-13
 * Time: 12:37 PM
 */
public class DeviceDAO extends BaseDAO<Device> implements IDeviceDAO {

    public DeviceDAO() {
        super(Device.class);
    }

    @Override
    public List<Device> findByLevel(Long levelId) {
        return null;
    }

    @Override
    public List<Device> findActive() {
        return null;
    }

    @Override
    public String getDeviceCodeFinal() {
        return null;
    }

    @Override
    public String getDeviceCameraCodeFinal() {
        return null;
    }

    @Override
    public Device get(String name) {
        return null;
    }
}
