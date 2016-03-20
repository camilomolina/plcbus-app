package cl.bennu.plcbus.core.persistence.iface;

import cl.bennu.plcbus.common.domain.weather.Weather;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:49 AM
 */
public interface IWeatherDAO extends IBaseDAO<Weather> {

    Weather getLasted();

    List<Weather> findLasted12Hour();

    List<Weather> findLasted24Hour();

    List<Weather> findLastedHour(int hours);

}
