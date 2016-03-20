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
public enum MovementActionTypeEnum {

    ON("Encender dispositivo", 1L)
    , OFF("Apagar dispositivo", 2L)
    , MAIL("Envio de Mail", 3L)
    , SMS("Envio de SMS", 4L)
    ;

    private static Map<Long, MovementActionTypeEnum> valuesMap = new HashMap<Long, MovementActionTypeEnum>();

    static {
        for (MovementActionTypeEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;

    MovementActionTypeEnum(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public static List<MovementActionTypeEnum> valuesList() {
        return Arrays.asList(MovementActionTypeEnum.values());
    }

    public static MovementActionTypeEnum valueOf(Long id) {
        MovementActionTypeEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum;
    }

}
