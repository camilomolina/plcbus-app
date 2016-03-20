package cl.bennu.plcbus.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 28-10-13
 * Time: 11:43 PM
 */
public enum ProgrammingTypeEnum {

    DAILY("Diaria", 1L)
    , WEEKLY("Semanal", 2L)
    , MONTHLY("Mensual", 3L)
    , ANNUAL("Anual", 4L)
    ;

    private static Map<Long, ProgrammingTypeEnum> valuesMap = new HashMap<Long, ProgrammingTypeEnum>();

    static {
        for (ProgrammingTypeEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;

    ProgrammingTypeEnum(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public static ProgrammingTypeEnum valueOf(Long id) {
        ProgrammingTypeEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum ;
    }

}
