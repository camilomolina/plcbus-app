package cl.bennu.plcbus.common.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 31-03-14
 * Time: 12:54 AM
 */
public enum WeatherYahooEnum {
/*
Code	Description
0	tornado
1	tropical storm
2	hurricane
3	severe thunderstorms
4	thunderstorms
5	mixed rain and snow
6	mixed rain and sleet
7	mixed snow and sleet
8	freezing drizzle
9	drizzle
10	freezing rain
11	showers
12	showers
13	snow flurries
14	light snow showers
15	blowing snow
16	snow
17	hail
18	sleet
19	dust
20	foggy
21	haze
22	smoky
23	blustery
24	windy
25	cold
26	cloudy
27	mostly cloudy (night)
28	mostly cloudy (day)
29	partly cloudy (night)
30	partly cloudy (day)
31	clear (night)
32	sunny
33	fair (night)
34	fair (day)
35	mixed rain and hail
36	hot
37	isolated thunderstorms
38	scattered thunderstorms
39	scattered thunderstorms
40	scattered showers
41	heavy snow
42	scattered snow showers
43	heavy snow
44	partly cloudy
45	thundershowers
46	snow showers
47	isolated thundershowers
3200	not available
 */

    STROM("Tormenta", 1L, 0L, "icon-lightning-3")
    , TROPIACAL_STORM("Tormenta Tropical", 2L, 1L, "icon-lightning-3")
    , HURRICANE("Huracan", 3L, 2L, "icon-lines")
    , SEVERE_THUNDERSTORMS("Tormentas Electrica Severas", 4L, 3L, "icon-lightning-3")
    , THUNDERSTORMS("Tormentas Electrica", 5L, 4L, "icon-lightning-3")
    , MIXED_RAIN_AND_SNOW("Luvia y Nieve", 6L, 5L, "icon-snowy-3")
    , MIXED_RAIN_AND_SLEET("Luvia Aguanieve", 7L, 6L, "icon-snowy-3")
    , MIXED_SNOW_AND_SLEET("Aguanieve", 8L, 7L, "icon-snowy-3")
    , FREEZING_DRIZZLE("Llovizna Helada", 9L, 8L, "icon-rainy-2")
    , DRIZZLE("Llovizna", 10L, 9L, "icon-rainy-2")
    , FREEZING_RAIN("Luvia Helada", 11L, 10L, "icon-rainy-2")
    , SHOWERS_1("Lluvia", 12L, 11L, "icon-rainy-2")
    , SHOWERS_2("Lluvia", 13L, 12L, "icon-rainy-2")
    , SNOW_FLURRIES("Copos de Nieve", 14L, 13L, "icon-snowy")
    , LIGHT_SNOW_SHOWERS("Lluvia Ligera", 15L, 14L, "icon-rainy")
    , BLOWING_SNOW("Nieve y Viento", 16L, 15L, "icon-snowy-2")
    , SNOW("Nieve", 17L, 16L, "icon-snowy")
    , HAIL("granizo", 18L, 17L, "icon-weather-4")
    , SLEET("Aguanieve", 19L, 18L, "icon-snowy-3")
    , DUST("Polvo", 20L, 19L, "icon-lines")
    , FOGGY("Brumoso", 21L, 20L, "icon-lines")
    , HAZE("Neblina", 22L, 21L, "icon-lines")
    , SMOKY("Niebla", 23L, 22L, "icon-lines")
    , BLUSTERY("Borroscoso", 24L, 23L, "icon-lines")
    , WINDY("Ventoso", 25L, 24L, "icon-wind")
    , COLD("Frio", 26L, 25L, "icon-cloud-4")
    , CLOUDY("Nublado", 27L, 26L, "icon-cloud-4")
    , MOSTLY_CLOUDY_NIGHT("Mayormente Nublado", 28L, 27L, "icon-cloud-7")
    , MOSTLY_CLOUDY_DAY("Mayormente Nublado", 29L, 28L, "icon-cloud-4")
    , PARTLY_CLOUDY_NIGHT("Parcialmente Nublado", 30L, 29L, "icon-cloudy-3")
    , PARTLY_CLOUDY_DAY("Parcialmente Nublado", 31L, 30L, "icon-cloudy")
    , CLEAR_NIGHT("Despejado", 32L, 31L, "icon-sun-3")
    , SUNNY("Soleado", 33L, 32L, "icon-sun")
    , FAIR_NIGHT("Despejado", 34L, 33L, "icon-moon-2")
    , FAIR_DAY("Despejado", 35L, 34L, "icon-sun")
    , MIXED_RAIN_AND_HAIL("Lluvia Granizo", 36L, 35L, "icon-weather-4")
    , HOT("Caluroso", 37L, 36L, "icon-sun")
    , ISOLATED_THUNDERSTORMS("Tormentas Aisladas", 38L, 37L, "icon-lightning-2")
    , SCATTERED_THUNDERSTORMS_1("Tormentas Electricas Aisladas", 39L, 38L, "icon-lightning-2")
    , SCATTERED_THUNDERSTORMS_2("Tormentas Electricas Aisladas", 40L, 39L, "icon-lightning-2")
    , SCATTERED_SHOWERS("Lluvia Dispersa", 41L, 40L, "icon-rainy")
    , HEAVY_SNOW_1("Nevadas Severas", 42L, 41L, "icon-rainy-2")
    , SCATTERED_SNOW_SHOWERS("Nieve Dispersa", 43L, 42L, "icon-snowy")
    , HEAVY_SNOW_2("Nevadas Severas", 44L, 43L, "icon-snowy-3")
    , PARTLY_CLOUDY("Parcialmente Nublado", 45L, 44L, "icon-cloudy")
    , THUNDERSHOWERS("Chubascos", 46L, 45L, "icon-rainy")
    , SNOW_SHOWERS("Nieve", 47L, 46L, "icon-snowy")
    , ISOLATED_THUNDERSHOWERS("Chubascos Aislados", 48L, 47L, "icon-rainy")
    , NOT_AVAILABLE("Sin Informacion", 49L, 3200L, "icon-thermometer")
    ;

    private static Map<Long, WeatherYahooEnum> valuesMap = new HashMap<Long, WeatherYahooEnum>();

    static {
        for (WeatherYahooEnum typeEnum : values()) {
            valuesMap.put(typeEnum.getId(), typeEnum);
        }
    }

    private final String name;
    private final Long id;
    private final Long value;
    private final String icon;

    WeatherYahooEnum(String name, Long id, Long value, String icon) {
        this.name = name;
        this.id = id;
        this.value = value;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }

    public static List<WeatherYahooEnum> valuesList() {
        return Arrays.asList(WeatherYahooEnum.values());
    }

    public static WeatherYahooEnum valueOf(Long id) {
        WeatherYahooEnum typeEnum = valuesMap.get(id);
        if (typeEnum == null) {
            throw new IllegalArgumentException("Illegal argument, id:" + id);
        }
        return typeEnum ;
    }

    public static WeatherYahooEnum valueOf4YahooCode(Long id) {
        List<WeatherYahooEnum> weatherYahooEnums = valuesList();
        for (WeatherYahooEnum weatherYahooEnum : weatherYahooEnums) {
            if (weatherYahooEnum.getValue().equals(id)) return weatherYahooEnum;
        }
        return null;
    }

    public static boolean rain(WeatherYahooEnum weatherYahooEnum) {
        if (weatherYahooEnum.equals(STROM)
                || weatherYahooEnum.equals(TROPIACAL_STORM)
                || weatherYahooEnum.equals(SEVERE_THUNDERSTORMS)
                || weatherYahooEnum.equals(THUNDERSTORMS)
                || weatherYahooEnum.equals(MIXED_RAIN_AND_SNOW)
                || weatherYahooEnum.equals(MIXED_RAIN_AND_SLEET)
                || weatherYahooEnum.equals(MIXED_SNOW_AND_SLEET)
                || weatherYahooEnum.equals(FREEZING_DRIZZLE)
                || weatherYahooEnum.equals(DRIZZLE)
                || weatherYahooEnum.equals(FREEZING_RAIN)
                || weatherYahooEnum.equals(SHOWERS_1)
                || weatherYahooEnum.equals(SHOWERS_2)
                || weatherYahooEnum.equals(SNOW_FLURRIES)
                || weatherYahooEnum.equals(LIGHT_SNOW_SHOWERS)
                || weatherYahooEnum.equals(BLOWING_SNOW)
                || weatherYahooEnum.equals(SNOW)
                || weatherYahooEnum.equals(HAIL)
                || weatherYahooEnum.equals(SLEET)
                || weatherYahooEnum.equals(MIXED_RAIN_AND_HAIL)
                || weatherYahooEnum.equals(ISOLATED_THUNDERSTORMS)
                || weatherYahooEnum.equals(SCATTERED_THUNDERSTORMS_1)
                || weatherYahooEnum.equals(SCATTERED_THUNDERSTORMS_2)
                || weatherYahooEnum.equals(SCATTERED_SHOWERS)
                || weatherYahooEnum.equals(HEAVY_SNOW_1)
                || weatherYahooEnum.equals(SCATTERED_SNOW_SHOWERS)
                || weatherYahooEnum.equals(HEAVY_SNOW_2)
                || weatherYahooEnum.equals(THUNDERSHOWERS)
                || weatherYahooEnum.equals(SNOW_SHOWERS)
                || weatherYahooEnum.equals(ISOLATED_THUNDERSHOWERS)
                ) {
            return true;
        }
        return false;
    }

}
