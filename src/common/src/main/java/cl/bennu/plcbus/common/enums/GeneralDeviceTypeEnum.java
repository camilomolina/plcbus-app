package cl.bennu.plcbus.common.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 28-10-13
 * Time: 11:43 PM
 */
public enum GeneralDeviceTypeEnum {

    LIGHT("Luz", 1L, true, true)
    , ELECTRIC_OUTLET("Enchufe", 2L, true, false)
    , IRRIGATION("Regadio", 3L, true, false)
    , CURTAIN("Cortina", 4L, true, false)
    , SENSOR("Sensor", 5L, true, false)
    , CAMERA("Camara", 6L, false, false)
    , IR("IR", 7L, false, false)
    ;

    private static Map<Long, GeneralDeviceTypeEnum> valuesMap = new HashMap<Long, GeneralDeviceTypeEnum>();

    static {
        for (GeneralDeviceTypeEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;
    private final Boolean active;
    private final Boolean timeControl;

    GeneralDeviceTypeEnum(String name, Long id, Boolean active, Boolean timeControl) {
        this.name = name;
        this.id = id;
        this.active = active;
        this.timeControl = timeControl;
    }

    public static List<GeneralDeviceTypeEnum> valuesList() {
        return Arrays.asList(GeneralDeviceTypeEnum.values());
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

    public Boolean getTimeControl() {
        return timeControl;
    }

    public static GeneralDeviceTypeEnum valueOf(Long id) {
        GeneralDeviceTypeEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum ;
    }

}
