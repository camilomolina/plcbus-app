package cl.bennu.plcbus.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 28-10-13
 * Time: 11:43 PM
 */
public enum SynchronizedEnum {

    PENDENT("Pendiente", 1L)
    , FINISH("Terminado", 2L)
    , FAIL("Fallido", 3L)
    ;

    private static Map<Long, SynchronizedEnum> valuesMap = new HashMap<Long, SynchronizedEnum>();

    static {
        for (SynchronizedEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;

    SynchronizedEnum(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public static SynchronizedEnum valueOf(Long id) {
        SynchronizedEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum ;
    }

}
