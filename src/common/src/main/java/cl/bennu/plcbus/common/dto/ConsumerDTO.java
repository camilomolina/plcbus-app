package cl.bennu.plcbus.common.dto;

import cl.bennu.plcbus.common.domain.Consumer;

/**
 * Created by Camilo on 10-03-2015.
 */
public class ConsumerDTO extends Consumer {

    private Long minute;
    private Long hour;
    private Double cost;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConsumerDTO) {
            ConsumerDTO consumerDTO = (ConsumerDTO) obj;
            return consumerDTO.getDeviceName().equals(this.getDeviceName());
        }

        return super.equals(obj);
    }

    public Long getHour() {
        return hour;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

    public Long getMinute() {
        return minute;
    }

    public void setMinute(Long minute) {
        this.minute = minute;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
