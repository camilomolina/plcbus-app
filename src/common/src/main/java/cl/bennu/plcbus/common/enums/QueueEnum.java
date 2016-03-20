package cl.bennu.plcbus.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 28-10-13
 * Time: 11:43 PM
 */
public enum QueueEnum {

    PENDENT("Pendiente", 1L)
    , QUEUE("En Espera", 2L)
    , EXECUTE("Ejecutandose", 3L)
    , FINISH("Finalizado", 4L)
    , BREAK("Interrumpido", 5L)
    , FAIL("Fallido", 6L)
    ;

    private static Map<Long, QueueEnum> valuesMap = new HashMap<Long, QueueEnum>();

    static {
        for (QueueEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;

    QueueEnum(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public static QueueEnum valueOf(Long id) {
        QueueEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum ;
    }

}
