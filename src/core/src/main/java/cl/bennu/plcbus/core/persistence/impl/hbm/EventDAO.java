package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.Constants;
import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.core.persistence.iface.IEventDAO;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class EventDAO extends BaseDAO<Event> implements IEventDAO {


    public EventDAO() {
        super(Event.class);
    }

    @Override
    public List<Event> findByEventType(EventTypeEnum eventTypeEnum) {
        Date now = new Date();
        Date dateLo = getLo2(now);
        Date dateHi = getHi2(now);

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.eq("eventTypeEnum", eventTypeEnum));
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> findByEventTypeLastMonth(EventTypeEnum eventTypeEnum) {
        Date now = new Date();
        Date dateLo = DateUtils.addMonths(now, -1);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.eq("eventTypeEnum", eventTypeEnum));
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> findByEventTypeLastWeek(EventTypeEnum eventTypeEnum) {
        Date now = new Date();
        Date dateLo = DateUtils.addDays(now, -7);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.eq("eventTypeEnum", eventTypeEnum));
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> findByEventTypeLastDay(EventTypeEnum eventTypeEnum) {
        Date now = new Date();
        Date dateLo = DateUtils.addDays(now, -1);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.eq("eventTypeEnum", eventTypeEnum));
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> findByEventType(List<EventTypeEnum> eventTypeEnums) {
        Date now = new Date();
        Date dateLo = getLo(now);
        Date dateHi = getHi(now);

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.in("eventTypeEnum", eventTypeEnums));
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> findByEventTypeLastMonth(List<EventTypeEnum> eventTypeEnums) {
        Date now = new Date();
        Date dateLo = DateUtils.addMonths(now, -1);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.in("eventTypeEnum", eventTypeEnums));
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> findByEventTypeLastWeek(List<EventTypeEnum> eventTypeEnums) {
        Date now = new Date();
        Date dateLo = DateUtils.addDays(now, -7);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.in("eventTypeEnum", eventTypeEnums));
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> findByEventTypeLastDay(List<EventTypeEnum> eventTypeEnums) {
        Date now = new Date();
        Date dateLo = DateUtils.addDays(now, -1);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.in("eventTypeEnum", eventTypeEnums));
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> find() {
        Date now = new Date();
        Date dateLo = getLo(now);
        Date dateHi = getHi(now);

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> findLastMonth() {
        Date now = new Date();
        Date dateLo = DateUtils.addMonths(now, -1);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> findLastWeek() {
        Date now = new Date();
        Date dateLo = DateUtils.addDays(now, -7);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> findLastDay() {
        Date now = new Date();
        Date dateLo = DateUtils.addDays(now, -1);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        return criteria.list();
    }

    @Override
    public List<Event> matrixByEvent() {
        Date now = new Date();
        Date dateLo = getLo(now);
        Date dateHi = getHi(now);

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        criteria.setProjection(Projections.projectionList()
//                .add(Projections.property("deviceCode"))
                //.add(Projections.property("deviceName"))
                        //.add(Projections.property("eventTypeEnum"))
                        .add(Projections.count("eventTypeEnum"))
                        .add(Projections.groupProperty("eventTypeEnum"))
        );
        return criteria.list();
    }

    @Override
    public List<Event> matrixByEventLastMonth() {
        Date now = new Date();
        Date dateLo = DateUtils.addMonths(now, -1);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        criteria.setProjection(Projections.projectionList()
//                .add(Projections.property("deviceCode"))
                        //.add(Projections.property("deviceName"))
                        //.add(Projections.property("eventTypeEnum"))
                        .add(Projections.count("eventTypeEnum"))
                        .add(Projections.groupProperty("eventTypeEnum"))
        );
        return criteria.list();
    }

    @Override
    public List<Event> matrixByEventLastWeek() {
        Date now = new Date();
        Date dateLo = DateUtils.addMonths(now, -7);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        criteria.setProjection(Projections.projectionList()
//                .add(Projections.property("deviceCode"))
                        //.add(Projections.property("deviceName"))
                        //.add(Projections.property("eventTypeEnum"))
                        .add(Projections.count("eventTypeEnum"))
                        .add(Projections.groupProperty("eventTypeEnum"))
        );
        return criteria.list();
    }

    @Override
    public List<Event> matrixByEventLastDay() {
        Date now = new Date();
        Date dateLo = DateUtils.addDays(now, -1);
        Date dateHi = now;

        Criteria criteria = getSession().createCriteria(Event.class);
        criteria.add(Restrictions.between("date", dateLo, dateHi));
        criteria.setProjection(Projections.projectionList()
//                .add(Projections.property("deviceCode"))
                        //.add(Projections.property("deviceName"))
                        //.add(Projections.property("eventTypeEnum"))
                        .add(Projections.count("eventTypeEnum"))
                        .add(Projections.groupProperty("eventTypeEnum"))
        );
        return criteria.list();
    }

    @Override
    public List<Event> matrixByDeviceCode() {
        Date now = new Date();
        Date dateLo = getLo(now);
        Date dateHi = getHi(now);

        /*
        select e.dpst_codigo, count(e.dpst_codigo)
        from evento e
        where e.tpev_id = 7
        group by e.dpst_codigo

         */
        Criteria criteria = getSession().createCriteria(Event.class);
        //criteria.add(Restrictions.between("date", dateLo, dateHi));
        criteria.add(Restrictions.eq("eventTypeEnum", EventTypeEnum.ALERTED_DEVICE));
        criteria.setProjection(Projections.projectionList()
                .add(Projections.groupProperty("deviceCode"))
                .add(Projections.count("deviceCode")));
        return criteria.list();
    }
}