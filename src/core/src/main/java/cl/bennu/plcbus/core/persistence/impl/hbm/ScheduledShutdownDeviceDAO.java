package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.ScheduledShutdownDevice;
import cl.bennu.plcbus.core.persistence.iface.IScheduledShutdownDeviceDAO;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class ScheduledShutdownDeviceDAO extends BaseDAO<ScheduledShutdownDevice> implements IScheduledShutdownDeviceDAO {

    public ScheduledShutdownDeviceDAO() {
        super(ScheduledShutdownDevice.class);
    }


}