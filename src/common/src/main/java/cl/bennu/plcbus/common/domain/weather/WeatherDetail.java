package cl.bennu.plcbus.common.domain.weather;

import cl.bennu.plcbus.common.domain.BaseDomain;
import cl.bennu.plcbus.common.enums.WeatherYahooEnum;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 31-03-14
 * Time: 06:07 PM
 */
public class WeatherDetail extends BaseDomain {

    private Weather weather;
    private WeatherYahooEnum weatherYahooEnum;
    private Long high;
    private Long low;
    private Date date;

    @Override
    public String toString() {
        return "WeatherDetail{" +
                "weatherYahooEnum=" + weatherYahooEnum +
                ", high=" + high +
                ", low=" + low +
                ", date=" + date +
                '}';
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public WeatherYahooEnum getWeatherYahooEnum() {
        return weatherYahooEnum;
    }

    public void setWeatherYahooEnum(WeatherYahooEnum weatherYahooEnum) {
        this.weatherYahooEnum = weatherYahooEnum;
    }

    public Long getHigh() {
        return high;
    }

    public void setHigh(Long high) {
        this.high = high;
    }

    public Long getLow() {
        return low;
    }

    public void setLow(Long low) {
        this.low = low;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
