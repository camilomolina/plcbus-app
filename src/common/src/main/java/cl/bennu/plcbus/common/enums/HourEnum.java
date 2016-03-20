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
public enum HourEnum {

    _00("00", 1L, 0L)
    , _01("01", 2L, 1L)
    , _02("02", 3L, 2L)
    , _03("03", 4L, 3L)
    , _04("04", 5L, 4L)
    , _05("05", 6L, 5L)
    , _06("06", 7L, 6L)
    , _07("07", 8L, 7L)
    , _08("08", 9L, 8L)
    , _09("09", 10L, 9L)
    , _10("10", 11L, 10L)
    , _11("11", 12L, 11L)
    , _12("12", 13L, 12L)
    , _13("13", 14L, 13L)
    , _14("14", 15L, 14L)
    , _15("15", 16L, 15L)
    , _16("16", 17L, 16L)
    , _17("17", 18L, 17L)
    , _18("18", 19L, 18L)
    , _19("19", 20L, 19L)
    , _20("20", 21L, 20L)
    , _21("21", 22L, 21L)
    , _22("22", 23L, 22L)
    , _23("23", 24L, 23L)
    ;

    private static Map<Long, HourEnum> valuesMap = new HashMap<Long, HourEnum>();

    static {
        for (HourEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;
    private final Long hour;

    HourEnum(String name, Long id, Long hour) {
        this.name = name;
        this.id = id;
        this.hour = hour;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Long getHour() {
        return hour;
    }

    public static List<HourEnum> valuesList() {
        return Arrays.asList(HourEnum.values());
    }

    public static HourEnum valueOf(Long id) {
        HourEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum ;
    }

    public static HourEnum valueOf4Hour(Long hour) {
        for (HourEnum hourEnum : valuesList()) {
            if (hourEnum.getHour().equals(hour)) return hourEnum;
        }

        return null;
    }

}
