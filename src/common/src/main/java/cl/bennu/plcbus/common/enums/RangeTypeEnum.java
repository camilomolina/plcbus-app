package cl.bennu.plcbus.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 28-10-13
 * Time: 11:43 PM
 */
public enum RangeTypeEnum {

    LESS("Menor", 1L)
    , GREATER("Mayor", 2L)
    , BETWEEN("Entre", 3L)
    ;

    private static Map<Long, RangeTypeEnum> valuesMap = new HashMap<Long, RangeTypeEnum>();

    static {
        for (RangeTypeEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;

    RangeTypeEnum(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public static RangeTypeEnum valueOf(Long id) {
        RangeTypeEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum ;
    }

}
