package cl.bennu.plcbus.common.domain.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Camilo on 30-03-2016.
 */
public class TemperatureHelper {

    public static List<Integer> getTemperatureList() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = -50; i < 51; i++) {
            list.add(i);
        }

        return list;
    }

}
