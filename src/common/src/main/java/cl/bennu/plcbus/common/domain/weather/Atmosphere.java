package cl.bennu.plcbus.common.domain.weather;

import cl.bennu.plcbus.common.domain.BaseDomain;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 31-03-14
 * Time: 06:07 PM
 */
public class Atmosphere extends BaseDomain {

    private Long rising;
    private Long humidity;
    private Double pressure;
    private Double visibility;


    @Override
    public String toString() {
        return "Atmosphere{" +
                "rising=" + rising +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", visibility=" + visibility +
                '}';
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
