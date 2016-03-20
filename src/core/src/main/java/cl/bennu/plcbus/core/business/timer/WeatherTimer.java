package cl.bennu.plcbus.core.business.timer;

import cl.bennu.plcbus.common.domain.weather.Weather;
import cl.bennu.plcbus.common.domain.weather.WeatherDetail;
import cl.bennu.plcbus.common.enums.WeatherYahooEnum;
import cl.bennu.plcbus.core.business.iface.IMaintainerService;
import org.apache.struts2.json.JSONUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 29-10-13
 * Time: 03:44 AM
 */
public class WeatherTimer extends TimerTask {

    private IMaintainerService maintainerService;
    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);

    public void run() {
        try {
            String dataType = "json";                                                                                            //349859
            String urlStr = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid=349859%20and%20u=%27c%27&format=" + dataType;
            URL url = new URL(urlStr);
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            //http://weather.yahooapis.com/forecastrss?w=23424782&u=c
            Weather weather = null;
            while ((inputLine = in.readLine()) != null) {
                HashMap hashMap = (HashMap) JSONUtil.deserialize(inputLine);
                HashMap query = (HashMap) hashMap.get("query");
                HashMap results = (HashMap) query.get("results");
                HashMap channel = (HashMap) results.get("channel");

                HashMap wind = (HashMap) channel.get("wind");
                HashMap atmosphere = (HashMap) channel.get("atmosphere");
                HashMap item = (HashMap) channel.get("item");
                HashMap condition = (HashMap) item.get("condition");


                weather = new Weather();
                weather.setTemp(getLong(condition.get("temp")));
                weather.setWeatherYahooEnum(WeatherYahooEnum.valueOf4YahooCode(getLong(condition.get("code"))));
                weather.setDate(getDate2(condition.get("date")));
                weather.setLatitude(getDouble(item.get("long")));
                weather.setLongitude(getDouble(item.get("lat")));
                weather.setPubDate(getDate2(item.get("pubDate")));

                weather.setHumidity(getLong(atmosphere.get("humidity")));
                weather.setPressure(getDouble(atmosphere.get("pressure")));
                weather.setRising(getLong(atmosphere.get("rising")));
                weather.setVisibility(getDouble(atmosphere.get("visibility")));

                weather.setChill(getLong(wind.get("chill")));
                weather.setDirection(getLong(wind.get("direction")));
                weather.setSpeed(getDouble(wind.get("speed")));

                List forecastList = (List) item.get("forecast");
                //WeatherDetail[] weatherDetail = new WeatherDetail[forecastList.size()];
                List<WeatherDetail> weatherDetailList = new ArrayList<WeatherDetail>(forecastList.size());
                //int i = 0;
                for (Object o : forecastList) {
                    HashMap forecast = (HashMap) o;

                    WeatherDetail weatherDetail = new WeatherDetail();
                    weatherDetail.setWeatherYahooEnum(WeatherYahooEnum.valueOf4YahooCode(getLong(forecast.get("code"))));
                    weatherDetail.setHigh(getLong(forecast.get("high")));
                    weatherDetail.setLow(getLong(forecast.get("low")));
                    weatherDetail.setDate(getDate(forecast.get("date")));

                    weatherDetailList.add(weatherDetail);
                }
                weather.setWeatherDetail(weatherDetailList);

                System.out.println(weather);
            }

            in.close();

            if (weather != null) {
                maintainerService.saveWeather(buildContext(), weather);
            }
        } catch (Exception e) {
            System.out.println("Error en WeatherTimer, " + e.getCause());
        }
    }

    private static Date getDate(Object dateTxt) {
        try {
            return simpleDateFormat2.parse(dateTxt.toString());
        } catch (Exception e) {
            return null;
        }
    }

    private static Date getDate2(Object dateTxt) {
        try {
            return simpleDateFormat2.parse(dateTxt.toString().substring(5, 16));
        } catch (Exception e) {
            return null;
        }
    }

    private static Long getLong(Object value) {
        try {
            return Long.valueOf(value.toString());
        } catch (Exception e) {
            return null;
        }
    }

    private static Double getDouble(Object value) {
        try {
            return Double.valueOf(value.toString());
        } catch (Exception e) {
            return null;
        }
    }


    public IMaintainerService getMaintainerService() {
        return maintainerService;
    }

    public void setMaintainerService(IMaintainerService maintainerService) {
        this.maintainerService = maintainerService;
    }
}
