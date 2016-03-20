package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.domain.weather.WeatherDetail;
import cl.bennu.plcbus.core.persistence.iface.IWeatherDetailDAO;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 02-08-13
 * Time: 04:34 AM
 */
public class WeatherDetailDAO extends BaseDAO<WeatherDetail> implements IWeatherDetailDAO {

    public WeatherDetailDAO() {
        super(WeatherDetail.class);
    }


}