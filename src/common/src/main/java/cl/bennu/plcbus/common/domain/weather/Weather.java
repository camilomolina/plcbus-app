package cl.bennu.plcbus.common.domain.weather;

import cl.bennu.plcbus.common.domain.BaseDomain;
import cl.bennu.plcbus.common.enums.WeatherYahooEnum;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 31-03-14
 * Time: 06:07 PM
 */
public class Weather extends BaseDomain {

    private WeatherYahooEnum weatherYahooEnum;
    private Long temp;
    private Date date;
    private List<WeatherDetail> weatherDetail;
    private Date pubDate;
    private Double latitude;
    private Double longitude;
    //wind
    private Double speed;
    private Long direction;
    private Long chill;
    //atmosphere
    private Long rising;
    private Long humidity;
    private Double pressure;
    private Double visibility;

    public WeatherYahooEnum getWeatherYahooEnum() {
        return weatherYahooEnum;
    }

    public void setWeatherYahooEnum(WeatherYahooEnum weatherYahooEnum) {
        this.weatherYahooEnum = weatherYahooEnum;
    }

    public Long getTemp() {
        return temp;
    }

    public void setTemp(Long temp) {
        this.temp = temp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<WeatherDetail> getWeatherDetail() {
        return weatherDetail;
    }

    public void setWeatherDetail(List<WeatherDetail> weatherDetail) {
        this.weatherDetail = weatherDetail;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Long getDirection() {
        return direction;
    }

    public void setDirection(Long direction) {
        this.direction = direction;
    }

    public Long getChill() {
        return chill;
    }

    public void setChill(Long chill) {
        this.chill = chill;
    }

    public Long getRising() {
        return rising;
    }

    public void setRising(Long rising) {
        this.rising = rising;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }
}
