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
public enum CameraTypeEnum {

    IPC100("IPC-100", 1L)
    ;

    private static Map<Long, CameraTypeEnum> valuesMap = new HashMap<Long, CameraTypeEnum>();

    static {
        for (CameraTypeEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;

    CameraTypeEnum(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public static List<CameraTypeEnum> valuesList() {
        return Arrays.asList(CameraTypeEnum.values());
    }

    public static CameraTypeEnum valueOf(Long id) {
        CameraTypeEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum;
    }

}
