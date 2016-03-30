package cl.bennu.plcbus.core.business.impl;

import cl.bennu.plcbus.common.Constants;
import cl.bennu.plcbus.common.comparator.EventComparator4Date;
import cl.bennu.plcbus.common.domain.*;
import cl.bennu.plcbus.common.dto.ConsumerDTO;
import cl.bennu.plcbus.common.dto.ContextDTO;
import cl.bennu.plcbus.common.enums.DeviceTypeEnum;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.common.enums.GeneralDeviceTypeEnum;
import cl.bennu.plcbus.core.business.iface.IStatisticsService;
import cl.bennu.plcbus.core.persistence.iface.IConsumerDAO;
import cl.bennu.plcbus.core.persistence.iface.IEventDAO;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public class StatisticsService implements IStatisticsService {

    private IEventDAO eventDAO;
    private IConsumerDAO consumerDAO;

    private final static IStatisticsService instance = new StatisticsService();

    public static synchronized IStatisticsService getInstance() {
        return instance;
    }

    @Override
    public List<Event> findSensorEvent(ContextDTO contextDTO) {
        return eventDAO.findByEventType(EventTypeEnum.valuesSensor());
    }

    @Override
    public List<Event> findSensorEventLastWeek(ContextDTO contextDTO) {
        return eventDAO.findByEventTypeLastWeek(EventTypeEnum.valuesSensor());
    }

    @Override
    public List<Event> findErrorEvent(ContextDTO contextDTO) {
        return eventDAO.findByEventType(EventTypeEnum.valuesError());
    }

    @Override
    public List<Event> findErrorEventLastWeek(ContextDTO contextDTO) {
        return eventDAO.findByEventTypeLastWeek(EventTypeEnum.valuesError());
    }

    @Override
    public List<Event> findWarningEvent(ContextDTO contextDTO) {
        return eventDAO.findByEventType(EventTypeEnum.valuesInfo());
    }

    @Override
    public List<Event> findWarningEventLastWeek(ContextDTO contextDTO) {
        return eventDAO.findByEventTypeLastWeek(EventTypeEnum.valuesInfo());
    }

    @Override
    public List<Event> findNormalEvent(ContextDTO contextDTO) {
        return eventDAO.findByEventType(EventTypeEnum.valuesNormal());
    }

    @Override
    public List<Event> findNormalEventLastWeek(ContextDTO contextDTO) {
        return eventDAO.findByEventTypeLastWeek(EventTypeEnum.valuesNormal());
    }

    @Override
    public List<Event> findEvent(ContextDTO contextDTO) {
        return eventDAO.find();
    }

    @Override
    public List<Event> findEventLastWeek(ContextDTO contextDTO) {
        return eventDAO.findLastWeek();
    }

    @Override
    public List<Event> matrixDeviceVsEvent(ContextDTO contextDTO) {
        return eventDAO.matrixByEventLastMonth();
    }


    @Override
    public List<Event> matrixDevice2(ContextDTO contextDTO) {
        return eventDAO.matrixByDeviceCode();
    }

    @Override
    public List<ConsumerDTO> matrixConsumer(ContextDTO contextDTO) {
        List<Consumer> consumerList = consumerDAO.matrixByDevice();

        List<ConsumerDTO> consumerDTOList = new ArrayList<ConsumerDTO>();
        for (Consumer consumer : consumerList) {
            if (GeneralDeviceTypeEnum.IR.equals(consumer.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                    || GeneralDeviceTypeEnum.SENSOR.equals(consumer.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                    || GeneralDeviceTypeEnum.CAMERA.equals(consumer.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                    ) {
                // expluye los dispositivos como sensores infrarojos y camaras
                continue;
            }

            ConsumerDTO consumerDTO = new ConsumerDTO();
            consumerDTO.setDeviceName(consumer.getDeviceName());
            consumerDTO.setDeviceCode(consumer.getDeviceCode());
            consumerDTO.setDeviceTypeEnum(consumer.getDeviceTypeEnum());
            consumerDTO.setCost(0D);
            consumerDTO.setMinute(0L);
            consumerDTO.setHour(0L);

            if (!consumerDTOList.contains(consumerDTO)) {
                consumerDTOList.add(consumerDTO);
            }
        }

        for (Consumer consumer : consumerList) {
            //ConsumerPredicate consumerPredicate = new ConsumerPredicate(consumer.getDeviceCode());
            //ConsumerDTO consumerDTO = (ConsumerDTO) CollectionUtils.find(consumerDTOList, consumerPredicate);
            ConsumerDTO consumerDTO = find(consumerDTOList, consumer);
            if (consumerDTO == null) continue;

            double second = ((consumer.getEnd().getTime() / 1000) - (consumer.getStart().getTime() / 1000));
            long minute = (long) second / 60;
            long hour = minute / 60;

            double cost = 0D;
            if (DeviceTypeEnum.FILAMENT.equals(consumerDTO.getDeviceTypeEnum())) {
                cost = minute / 60 * Constants.DEFAULT_WATTS_FILAMENT * Constants.COST_kWh_CLP / 1000;
            } else if (DeviceTypeEnum.HALOGEN.equals(consumerDTO.getDeviceTypeEnum())) {
                cost = minute / 60 * Constants.DEFAULT_WATTS_HALOGEN * Constants.COST_kWh_CLP / 1000;
            } else if (DeviceTypeEnum.DIMMER.equals(consumerDTO.getDeviceTypeEnum())) {
                cost = minute / 60 * Constants.DEFAULT_WATTS_DIMMER * Constants.COST_kWh_CLP / 1000;
            } else if (DeviceTypeEnum.LED.equals(consumerDTO.getDeviceTypeEnum())) {
                cost = minute / 60 * Constants.DEFAULT_WATTS_LED * Constants.COST_kWh_CLP / 1000;
            } else if (DeviceTypeEnum.FLUORESCENT.equals(consumerDTO.getDeviceTypeEnum())) {
                cost = minute / 60 * Constants.DEFAULT_WATTS_FLUORESCENT * Constants.COST_kWh_CLP / 1000;
            } else if (DeviceTypeEnum.LOW.equals(consumerDTO.getDeviceTypeEnum())) {
                cost = minute / 60 * Constants.DEFAULT_WATTS_LOW * Constants.COST_kWh_CLP / 1000;
            } else {
                cost = minute / 60 * Constants.DEFAULT_WATTS_FILAMENT * Constants.COST_kWh_CLP / 1000;
            }

            consumerDTO.setMinute(consumerDTO.getMinute() + minute);
            consumerDTO.setCost(consumerDTO.getCost() + cost);
            consumerDTO.setHour(consumerDTO.getHour() + hour);
        }

        return consumerDTOList;
    }

    @Override
    public Long consumption(ContextDTO contextDTO) {
        List<ConsumerDTO> consumerDTOList = matrixConsumer(contextDTO);
        if (consumerDTOList == null) return 0L;

        long consumption = 0;
        for (ConsumerDTO consumerDTO : consumerDTOList) {
            consumption += consumerDTO.getCost();
        }

        return consumption * 100 / Constants.COST_MAX_CLP;
    }

    @Override
    public List<Sensor4DeviceDTO> matrixSensor2(ContextDTO context) {
        List<Event> eventList = eventDAO.findByEventTypeLastMonth(EventTypeEnum.MOVEMENT_SENSOR_ON);
        Collections.sort(eventList, new EventComparator4Date());

        List<Sensor4DeviceDTO> sensor4DeviceList = new ArrayList<Sensor4DeviceDTO>();
        for (Event event : eventList) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(event.getDate());
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);

            Sensor4DeviceDTO sensor4DeviceDTO = buildSensor4DayDTO(sensor4DeviceList, event);
            buildSensor4DayDTO(sensor4DeviceDTO, day, hour);
        }

        return sensor4DeviceList;
    }

    @Override
    public List<Sensor4DeviceDTO> matrixSensorWeek(ContextDTO context) {
        List<Event> eventList = eventDAO.findByEventTypeLastWeek(EventTypeEnum.MOVEMENT_SENSOR_ON);
        Collections.sort(eventList, new EventComparator4Date());

        List<Sensor4DeviceDTO> sensor4DeviceList = new ArrayList<Sensor4DeviceDTO>();
        for (Event event : eventList) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(event.getDate());
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);

            Sensor4DeviceDTO sensor4DeviceDTO = buildSensor4DayDTO(sensor4DeviceList, event);
            buildSensor4DayDTO(sensor4DeviceDTO, day, hour);
        }

        return sensor4DeviceList;
    }

    private void buildSensor4DayDTO(Sensor4DeviceDTO sensor4DeviceDTO, int day, int hour) {
        for (Sensor4Day2DTO sensor4DayDTO : sensor4DeviceDTO.getSensor4Day2List()) {
            if (sensor4DayDTO.getDay() == day && sensor4DayDTO.getHour() == hour) {
                sensor4DayDTO.setEventCount(sensor4DayDTO.getEventCount() + 1L);
                return;
            }
        }

        Sensor4Day2DTO sensor4Day2DTO = new Sensor4Day2DTO();
        sensor4Day2DTO.setDay(day);
        sensor4Day2DTO.setHour(hour);
        sensor4Day2DTO.setEventCount(1L);

        sensor4DeviceDTO.getSensor4Day2List().add(sensor4Day2DTO);
    }

    private Sensor4DeviceDTO buildSensor4DayDTO(List<Sensor4DeviceDTO> sensor4DeviceList, Event event) {
        for (Sensor4DeviceDTO sensor4DeviceDTO : sensor4DeviceList) {
            if (sensor4DeviceDTO.getDeviceCode().equals(event.getDeviceCode())) {
                return sensor4DeviceDTO;
            }
        }

        Sensor4DeviceDTO sensor4DeviceDTO = new Sensor4DeviceDTO();
        sensor4DeviceDTO.setDeviceTypeEnum(event.getDeviceTypeEnum());
        sensor4DeviceDTO.setDeviceCode(event.getDeviceCode());
        sensor4DeviceDTO.setDeviceName(event.getDeviceName());
        sensor4DeviceDTO.setSensor4Day2List(new ArrayList<Sensor4Day2DTO>());

        sensor4DeviceList.add(sensor4DeviceDTO);

        return sensor4DeviceDTO;
    }

    @Override
    public List<Sensor4DayDTO> matrixSensor(ContextDTO context) {
        List<Event> eventList = eventDAO.findByEventTypeLastMonth(EventTypeEnum.MOVEMENT_SENSOR_ON);
        Collections.sort(eventList, new EventComparator4Date());


        List<Sensor4DayDTO> sensor4DayList = new ArrayList<Sensor4DayDTO>();
        for (Event event : eventList) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(event.getDate());
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);

            Sensor4DayDTO sensor4DayDTO = buildSensor4DayDTO(sensor4DayList, day, hour);
            buildSensorStatisticsDTO(sensor4DayDTO, event);
        }

        return sensor4DayList;
    }

    private void buildSensorStatisticsDTO(Sensor4DayDTO sensor4DayDTO, Event event) {
        for (SensorStatisticsDTO sensorStatisticsDTO : sensor4DayDTO.getSensorStatisticsList()) {
            if (sensorStatisticsDTO.getDeviceCode().equals(event.getDeviceCode())) {
                sensorStatisticsDTO.setEventCount(sensorStatisticsDTO.getEventCount() + 1L);
                return;
            }
        }

        SensorStatisticsDTO sensorStatisticsDTO = new SensorStatisticsDTO();
        sensorStatisticsDTO.setDeviceCode(event.getDeviceCode());
        sensorStatisticsDTO.setDeviceName(event.getDeviceName());
        sensorStatisticsDTO.setDeviceTypeEnum(event.getDeviceTypeEnum());
        sensorStatisticsDTO.setEventCount(1L);

        sensor4DayDTO.getSensorStatisticsList().add(sensorStatisticsDTO);
    }

    private Sensor4DayDTO buildSensor4DayDTO(List<Sensor4DayDTO> sensor4DayList, int day, int hour) {
        for (Sensor4DayDTO sensor4DayDTO : sensor4DayList) {
            if (sensor4DayDTO.getDay() == day && sensor4DayDTO.getHour() == hour) {
                return sensor4DayDTO;
            }
        }

        Sensor4DayDTO sensor4DayDTO = new Sensor4DayDTO();
        sensor4DayDTO.setDay(day);
        sensor4DayDTO.setHour(hour);
        sensor4DayDTO.setSensorStatisticsList(new ArrayList<SensorStatisticsDTO>());

        sensor4DayList.add(sensor4DayDTO);

        return sensor4DayDTO;
    }

    private ConsumerDTO find(List<ConsumerDTO> consumerDTOList, Consumer consumer) {
        if (consumerDTOList == null) return null;

        for (ConsumerDTO c : consumerDTOList) {
            if (c.getDeviceCode().equals(consumer.getDeviceCode())) return c;
        }

        return null;
    }


    public IEventDAO getEventDAO() {
        return eventDAO;
    }

    public void setEventDAO(IEventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public IConsumerDAO getConsumerDAO() {
        return consumerDAO;
    }

    public void setConsumerDAO(IConsumerDAO consumerDAO) {
        this.consumerDAO = consumerDAO;
    }
}
