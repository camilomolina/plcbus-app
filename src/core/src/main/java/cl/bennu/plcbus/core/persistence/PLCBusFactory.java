package cl.bennu.plcbus.core.persistence;


import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Level;
import cl.bennu.plcbus.core.persistence.iface.IDeviceDAO;
import cl.bennu.plcbus.core.persistence.iface.ILevelDAO;
import cl.bennu.plcbus.core.persistence.impl.jdbc.DeviceDAO;
import cl.bennu.plcbus.core.persistence.impl.jdbc.LevelDAO;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:49 AM
 */
public class PLCBusFactory {
    private static PLCBusFactory ourInstance = new PLCBusFactory();

    public static PLCBusFactory getInstance() {
        return ourInstance;
    }

    private PLCBusFactory() {
        JDBCUtils.createDB();
    }

    public IDeviceDAO getDeviceDAO() {
        return new DeviceDAO(Device.class);
    }

    public ILevelDAO getLevelDAO() {
        return new LevelDAO(Level.class);
    }


}
