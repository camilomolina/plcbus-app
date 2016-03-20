package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.enums.DeviceTypeEnum;
import cl.bennu.plcbus.core.persistence.iface.IDeviceDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class DeviceDAO extends BaseDAO<Device> implements IDeviceDAO {

    public DeviceDAO() {
        super(Device.class);
    }

    @Override
    public List findByLevel(Long levelId) {
        Criteria criteria = getSession().createCriteria(Device.class);
        criteria.createCriteria("sector").createCriteria("level").add(Restrictions.eq("id", levelId));
        criteria.addOrder(Order.asc("sector"));
        criteria.addOrder(Order.asc("name"));
        return criteria.list();
    }

    @Override
    public List findActive() {
        Criteria criteria = getSession().createCriteria(Device.class);
        criteria.add(Restrictions.eq("active", true));
        criteria.addOrder(Order.asc("sector"));
        criteria.addOrder(Order.asc("name"));
        return criteria.list();
    }

    @Override
    public String getDeviceCodeFinal() {
        Criteria criteria = getSession().createCriteria(Device.class);
        //criteria.add(Restrictions.ne("deviceTypeEnum", DeviceTypeEnum.IP));
        criteria.addOrder(Order.desc("code"));
        criteria.setMaxResults(1);
        List<Device> deviceList = criteria.list();
        return deviceList == null || deviceList.isEmpty() ? null : deviceList.get(0).getCode();
    }

    public String getDeviceCameraCodeFinal() {
        Criteria criteria = getSession().createCriteria(Device.class);
        criteria.add(Restrictions.eq("deviceTypeEnum", DeviceTypeEnum.IP));
        criteria.addOrder(Order.desc("code"));
        criteria.setMaxResults(1);
        List<Device> deviceList = criteria.list();
        return deviceList == null || deviceList.isEmpty() ? null : deviceList.get(0).getCode();
    }

    @Override
    public Device get(String code) {
        Criteria criteria = getSession().createCriteria(Device.class);
        criteria.add(Restrictions.eq("code", code));
        return (Device) criteria.uniqueResult();
    }

}