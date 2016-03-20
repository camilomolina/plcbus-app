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
public enum DayEnum {

    MONDAY("Lunes", 1L, 1L)
    , TUESDAY("Martes", 2L, 2L)
    , WEDNESDAY("Miercoles", 3L, 3L)
    , THURSDAY("Jueves", 4L, 4L)
    , FRIDAY("Viernes", 5L, 5L)
    , SATURDAY("Sabado", 6L, 6L)
    , SUNDAY("Domingo", 7L, 7L)
    ;

    private static Map<Long, DayEnum> valuesMap = new HashMap<Long, DayEnum>();

    static {
        for (DayEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;
    private final Long value;

    DayEnum(String name, Long id, Long value) {
        this.name = name;
        this.id = id;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Long getValue() {
        return value;
    }

    public static List<DayEnum> valuesList() {
        return Arrays.asList(DayEnum.values());
    }

    public static DayEnum valueOf(Long id) {
        DayEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum ;
    }

}
