package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.weather.Weather;
import cl.bennu.plcbus.core.persistence.iface.IWeatherDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class WeatherDAO extends BaseDAO<Weather> implements IWeatherDAO {

    public WeatherDAO() {
        super(Weather.class);
    }


    @Override
    public Weather getLasted() {
        Criteria criteria = getSession().createCriteria(Weather.class);
        criteria.addOrder(Order.desc("date"));
        criteria.setMaxResults(1);
        List<Weather> weatherList = criteria.list();
        return weatherList == null && !weatherList.isEmpty() ? null : weatherList.get(0);
    }

    @Override
    public List<Weather> findLasted12Hour() {
        Criteria criteria = getSession().createCriteria(Weather.class);
        criteria.addOrder(Order.desc("date"));
        criteria.setMaxResults(12);
        return criteria.list();
    }

    @Override
    public List<Weather> findLasted24Hour() {
        Criteria criteria = getSession().createCriteria(Weather.class);
        criteria.addOrder(Order.desc("date"));
        criteria.setMaxResults(24);
        return criteria.list();
    }

    @Override
    public List<Weather> findLastedHour(int hours) {
        Criteria criteria = getSession().createCriteria(Weather.class);
        criteria.addOrder(Order.desc("date"));
        criteria.setMaxResults(hours);
        return criteria.list();
    }
}