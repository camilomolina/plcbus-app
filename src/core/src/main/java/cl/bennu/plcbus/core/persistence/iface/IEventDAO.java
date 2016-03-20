package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.enums.EventTypeEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface IEventDAO extends IBaseDAO<Event> {

    List<Event> findByEventType(EventTypeEnum eventTypeEnum);

    List<Event> findByEventTypeLastMonth(EventTypeEnum eventTypeEnum);

    List<Event> findByEventTypeLastWeek(EventTypeEnum eventTypeEnum);

    List<Event> findByEventTypeLastDay(EventTypeEnum eventTypeEnum);

    List<Event> findByEventType(List<EventTypeEnum> eventTypeEnums);

    List<Event> findByEventTypeLastMonth(List<EventTypeEnum> eventTypeEnums);

    List<Event> findByEventTypeLastWeek(List<EventTypeEnum> eventTypeEnums);

    List<Event> findByEventTypeLastDay(List<EventTypeEnum> eventTypeEnums);

    List<Event> findLastMonth();

    List<Event> find();

    List<Event> findLastWeek();

    List<Event> findLastDay();

    List<Event> matrixByEvent();

    List<Event> matrixByDeviceCode();


}
