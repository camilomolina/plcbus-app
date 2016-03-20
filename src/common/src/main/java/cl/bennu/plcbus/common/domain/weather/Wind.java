package cl.bennu.plcbus.common.domain.weather;

import cl.bennu.plcbus.common.domain.BaseDomain;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 31-03-14
 * Time: 06:07 PM
 */
public class Wind extends BaseDomain {

    private Double speed;
    private Long direction;
    private Long chill;

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", direction=" + direction +
                ", chill=" + chill +
                '}';
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
}
