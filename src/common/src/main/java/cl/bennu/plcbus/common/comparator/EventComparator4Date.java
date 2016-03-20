package cl.bennu.plcbus.common.comparator;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.domain.Sector;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 12-12-13
 * Time: 03:25 AM
 */
public class EventComparator4Date implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
