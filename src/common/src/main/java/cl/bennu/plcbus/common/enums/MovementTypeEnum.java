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
public enum MovementTypeEnum {

    ON("Con actividad", 1L)
    , OFF("Sin actividad", 2L)
    ;

    private static Map<Long, MovementTypeEnum> valuesMap = new HashMap<Long, MovementTypeEnum>();

    static {
        for (MovementTypeEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;

    MovementTypeEnum(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public static List<MovementTypeEnum> valuesList() {
        return Arrays.asList(MovementTypeEnum.values());
    }

    public static MovementTypeEnum valueOf(Long id) {
        MovementTypeEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum;
    }

}
