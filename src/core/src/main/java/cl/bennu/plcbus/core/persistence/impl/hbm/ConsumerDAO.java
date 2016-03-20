package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.Consumer;
import cl.bennu.plcbus.common.enums.DeviceTypeEnum;
import cl.bennu.plcbus.core.persistence.iface.IConsumerDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class ConsumerDAO extends BaseDAO<Consumer> implements IConsumerDAO {

    public ConsumerDAO() {
        super(Consumer.class);
    }

    @Override
    public List<Consumer> getByCode(String deviceCode) {
        Criteria criteria = getSession().createCriteria(Consumer.class);
        criteria.add(Restrictions.eq("deviceCode", deviceCode));
        criteria.add(Restrictions.isNull("end"));
        criteria.addOrder(Order.asc("start"));
        return criteria.list();
    }

    @Override
    public List<Consumer> matrixByDevice() {
        Date now = new Date();
        Date dateLo = getLo2(now);
        Date dateHi = getHi2(now);

        /*
        select c.dpst_codigo
        , c.cnsm_fecha_inicio
        , c.cnsm_fecha_fin
        from consumo c
        where c.cnsm_fecha_fin is not null
        and c.cnsm_fecha_fin between '2015-02-01 00:00:00.000' and '2015-02-02 23:59:59.000'
        group by c.dpst_codigo
        , c.cnsm_fecha_inicio
        , c.cnsm_fecha_fin
        order by c.cnsm_fecha_inicio
         */
        Criteria criteria = getSession().createCriteria(Consumer.class);
        criteria.add(Restrictions.isNotNull("end"));
        criteria.add(Restrictions.between("end", dateLo, dateHi));
        //criteria.add(Restrictions.in("deviceTypeEnum", new DeviceTypeEnum[]{DeviceTypeEnum.AUDIO}));
        criteria.setProjection(Projections.projectionList()
                        .add(Projections.groupProperty("deviceCode"))
                        .add(Projections.groupProperty("deviceName"))
                        .add(Projections.groupProperty("deviceTypeEnum"))
                        .add(Projections.groupProperty("start"))
                        .add(Projections.groupProperty("end"))
                        .add(Projections.property("deviceCode"), "deviceCode")
                        .add(Projections.property("deviceName"), "deviceName")
                        .add(Projections.property("deviceTypeEnum"), "deviceTypeEnum")
                        .add(Projections.property("start"), "start")
                        .add(Projections.property("end"), "end")
        );
        criteria.addOrder(Order.asc("start"));
        criteria.setResultTransformer(Transformers.aliasToBean(Consumer.class));
        /*criteria.setProjection(Projections.projectionList()
                .add(Projections.groupProperty("deviceCode"))
                .add(Projections.count("deviceCode")));
        */
        return criteria.list();
    }
}