package cl.bennu.plcbus.common.enums;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 28-10-13
 * Time: 11:43 PM
 */
public enum EventTypeEnum {

    MANUAL_ON("Encendido Manual", 1L)
    , MANUAL_OFF("Apagado Manual", 2L)
    , PROGRAMMING_ON("Encendido Programado", 3L)
    , PROGRAMMING_OFF("Apagado Programado", 4L)
    , AUTOMATIC_OFF("Apagado Automatico", 5L)
    , CANCEL_IRRIGATION("Riego Cancelado", 6L)
    , ALERTED_DEVICE("Dispositivo Alertado", 7L)
    , ALERT_MAIL_SENDED_ERROR("Error en envio de Mail de alerta", 8L)
    , ALERT_SMS_SENDED_ERROR("Error en envio de SMS de alerta", 9L)
    , MAIL_SENDED("Envio de mail", 10L)
    , SMS_SENDED("Envio de SMS", 11L)
    , MANUAL_GENERAL_STATUS("Consulta Global de dispositivos Manual", 12L)
    , AUTOMATIC_GENERAL_STATUS("Consulta Global de dispositivos Automatico", 13L)
    , STATUS_DEVICE("Consulta de dispositivo", 14L)
    , MOVEMENT_SENSOR_ON("Sensor de movimiento detectando presencia", 15L)
    , MOVEMENT_SENSOR_OFF("Sensor de movimiento sin actividad", 16L)
    , ON_BY_SENSOR("Encendido por sensor", 17L)
    , OFF_BY_SENSOR("Apagado por sensor", 18L)
    , MAIL_SENDED_BY_SENSOR("Envio de mail por sensor", 19L)
    , SMS_SENDED_BY_SENSOR("Envio de SMS por sensor", 20L)
    ;

    private static Map<Long, EventTypeEnum> valuesMap = new HashMap<Long, EventTypeEnum>();

    static {
        for (EventTypeEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;

    EventTypeEnum(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public static List<EventTypeEnum> valuesList() {
        return Arrays.asList(EventTypeEnum.values());
    }

    public static EventTypeEnum valueOf(Long id) {
        EventTypeEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum;
    }

    public static List<EventTypeEnum> valuesNormal() {
        List<EventTypeEnum> eventTypeEnums = new ArrayList<EventTypeEnum>();
        eventTypeEnums.add(MANUAL_ON);
        eventTypeEnums.add(MANUAL_OFF);
        eventTypeEnums.add(PROGRAMMING_ON);
        eventTypeEnums.add(PROGRAMMING_OFF);
        eventTypeEnums.add(AUTOMATIC_OFF);
        eventTypeEnums.add(MANUAL_GENERAL_STATUS);
        eventTypeEnums.add(AUTOMATIC_GENERAL_STATUS);
        eventTypeEnums.add(STATUS_DEVICE);
        eventTypeEnums.add(MOVEMENT_SENSOR_ON);
        eventTypeEnums.add(MOVEMENT_SENSOR_OFF);

        return eventTypeEnums;
    }

    public static List<EventTypeEnum> valuesWarning() {
        List<EventTypeEnum> eventTypeEnums = new ArrayList<EventTypeEnum>();
        eventTypeEnums.add(CANCEL_IRRIGATION);
        eventTypeEnums.add(ALERTED_DEVICE);
        eventTypeEnums.add(AUTOMATIC_OFF);

        return eventTypeEnums;
    }

    public static List<EventTypeEnum> valuesError() {
        List<EventTypeEnum> eventTypeEnums = new ArrayList<EventTypeEnum>();
        eventTypeEnums.add(ALERT_MAIL_SENDED_ERROR);
        eventTypeEnums.add(ALERT_SMS_SENDED_ERROR);

        return eventTypeEnums;
    }

    public static List<EventTypeEnum> valuesInfo() {
        List<EventTypeEnum> eventTypeEnums = new ArrayList<EventTypeEnum>();
        eventTypeEnums.add(MAIL_SENDED);
        eventTypeEnums.add(SMS_SENDED);

        return eventTypeEnums;
    }

    public static List<EventTypeEnum> valuesSensor() {
        List<EventTypeEnum> eventTypeEnums = new ArrayList<EventTypeEnum>();
        eventTypeEnums.add(MOVEMENT_SENSOR_OFF);
        eventTypeEnums.add(MOVEMENT_SENSOR_ON);

        return eventTypeEnums;
    }

}
