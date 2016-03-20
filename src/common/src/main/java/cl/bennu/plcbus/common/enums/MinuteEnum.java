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
public enum MinuteEnum {

    _00("00", 1L, 0L)
    , _05("05", 2L, 5L)
    , _10("10", 3L, 10L)
    , _15("15", 4L, 15L)
    , _20("20", 5L, 20L)
    , _25("25", 6L, 25L)
    , _30("30", 7L, 30L)
    , _35("35", 8L, 35L)
    , _40("40", 9L, 40L)
    , _45("45", 10L, 45L)
    , _50("50", 11L, 50L)
    , _55("55", 12L, 55L);

    private static Map<Long, MinuteEnum> valuesMap = new HashMap<Long, MinuteEnum>();

    static {
        for (MinuteEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;
    private final Long minute;

    MinuteEnum(String name, Long id, Long minute) {
        this.name = name;
        this.id = id;
        this.minute = minute;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Long getMinute() {
        return minute;
    }

    public static List<MinuteEnum> valuesList() {
        return Arrays.asList(MinuteEnum.values());
    }

    public static MinuteEnum valueOf(Long id) {
        MinuteEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum;
    }

    public static MinuteEnum valueOf4Minute(Long minute) {
        for (MinuteEnum minuteEnum: valuesList()) {
            if (minuteEnum.getMinute().equals(minute)) return minuteEnum;
        }

        return null;
    }

}
