package cl.bennu.plcbus.common.enums;

import cl.bennu.plcbus.common.comparator.DeviceTypeComparator;

import java.util.*;

import static java.util.Collections.*;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 28-10-13
 * Time: 11:43 PM
 */
public enum DeviceTypeEnum {

    FILAMENT("Filamento", 1L, true, GeneralDeviceTypeEnum.LIGHT)
    , FLUORESCENT("Fluorescente", 2L, true, GeneralDeviceTypeEnum.LIGHT)
    , LED("LED", 3L, true, GeneralDeviceTypeEnum.LIGHT)
    , DIMMER("Dimmer", 4L, true, GeneralDeviceTypeEnum.LIGHT)
    , HALOGEN("Halogeno", 5L, true, GeneralDeviceTypeEnum.LIGHT)

    , ELECTRIC_OUTLET("Enchufe", 6L, true, GeneralDeviceTypeEnum.ELECTRIC_OUTLET)
    , ELECTRIC_OUTLET_HIGH("Alta Carga", 7L, true, GeneralDeviceTypeEnum.ELECTRIC_OUTLET)
    , ROUTER("Router", 8L, true, GeneralDeviceTypeEnum.ELECTRIC_OUTLET)
    , FAN("Ventilador", 9L, true, GeneralDeviceTypeEnum.ELECTRIC_OUTLET)
    , WARM_BED("Calienta Cama", 10L, true, GeneralDeviceTypeEnum.ELECTRIC_OUTLET)
    , RADIO("Radio", 11L, true, GeneralDeviceTypeEnum.ELECTRIC_OUTLET)
    , TV("TV", 12L, true, GeneralDeviceTypeEnum.ELECTRIC_OUTLET)
    , DVD("DVD", 13L, true, GeneralDeviceTypeEnum.ELECTRIC_OUTLET)
    , AUDIO("Audio", 14L, true, GeneralDeviceTypeEnum.ELECTRIC_OUTLET)
    , LAMP("Lampara", 15L, true, GeneralDeviceTypeEnum.ELECTRIC_OUTLET)
    , CHARGER("Cargador", 16L, true, GeneralDeviceTypeEnum.ELECTRIC_OUTLET)

    , SPRINKLER("Aspersor", 17L, true, GeneralDeviceTypeEnum.IRRIGATION)
    , DIFFUSER("Difusor", 18L, true, GeneralDeviceTypeEnum.IRRIGATION)
    , DRIP("Goteo", 19L, true, GeneralDeviceTypeEnum.IRRIGATION)
    , EXUDATION("Exudacion", 20L, true, GeneralDeviceTypeEnum.IRRIGATION)
    , UNDERGROUND("Subterraneo", 21L, true, GeneralDeviceTypeEnum.IRRIGATION)
    , MICROSPRINKLER("MicroAspersor", 22L, true, GeneralDeviceTypeEnum.IRRIGATION)

    , ROLLER("Roller", 23L, true, GeneralDeviceTypeEnum.CURTAIN)
    , METALIC("Metalica", 24L, true, GeneralDeviceTypeEnum.CURTAIN)

    , SMOKE("Humo", 25L, true, GeneralDeviceTypeEnum.SENSOR)
    , PROXIMITY("Proximidad", 26L, true, GeneralDeviceTypeEnum.SENSOR)
    , MOVEMENT("Movimiento", 27L, true, GeneralDeviceTypeEnum.SENSOR)
    , TEMEPRATURE("Temperatura", 28L, true, GeneralDeviceTypeEnum.SENSOR)

    , IP("IP", 29L, true, GeneralDeviceTypeEnum.CAMERA)

    , IR("IR", 30L, true, GeneralDeviceTypeEnum.IR)

    , LOW("Bajo Consumo", 31L, true, GeneralDeviceTypeEnum.LIGHT)
    ;

    private static Map<Long, DeviceTypeEnum> valuesMap = new HashMap<Long, DeviceTypeEnum>();

    static {
        for (DeviceTypeEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;
    private final Boolean active;
    private final GeneralDeviceTypeEnum generalDeviceTypeEnum;

    DeviceTypeEnum(String name, Long id, Boolean active, GeneralDeviceTypeEnum generalDeviceTypeEnum) {
        this.name = name;
        this.id = id;
        this.active = active;
        this.generalDeviceTypeEnum = generalDeviceTypeEnum;
    }

    public static List<DeviceTypeEnum> valuesList() {
        List list = Arrays.asList(DeviceTypeEnum.values());
        sort(list, new DeviceTypeComparator());
        return list;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public GeneralDeviceTypeEnum getGeneralDeviceTypeEnum() {
        return generalDeviceTypeEnum;
    }

    public static DeviceTypeEnum valueOf(Long id) {
        DeviceTypeEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum ;
    }

}
