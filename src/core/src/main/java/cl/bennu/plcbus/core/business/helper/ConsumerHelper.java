package cl.bennu.plcbus.core.business.helper;

import cl.bennu.plcbus.common.domain.Consumer;
import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.domain.Event;
import cl.bennu.plcbus.common.enums.EventTypeEnum;
import cl.bennu.plcbus.common.enums.GeneralDeviceTypeEnum;
import cl.bennu.plcbus.core.persistence.iface.IConsumerDAO;
import cl.bennu.plcbus.core.persistence.impl.hbm.ConsumerDAO;

import java.util.Date;
import java.util.List;

/**
 * Created by Camilo on 23-02-2015.
 */
public class ConsumerHelper {

    public static Consumer buildConsumer(Device device) {
        Consumer consumer = new Consumer();
        consumer.setStart(new Date());
        consumer.setDeviceCode(device.getCode());
        consumer.setDeviceName(device.getName());
        consumer.setDeviceTypeEnum(device.getDeviceTypeEnum());
        return consumer;
    }

    public static void markConsumerByStatus(IConsumerDAO consumerDAO, Device device, boolean status) {
        if (!GeneralDeviceTypeEnum.CURTAIN.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                && !GeneralDeviceTypeEnum.CAMERA.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                && !GeneralDeviceTypeEnum.IR.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                && !GeneralDeviceTypeEnum.SENSOR.equals(device.getDeviceTypeEnum().getGeneralDeviceTypeEnum())
                ) {
            // no se guardan indicadores de consumo para cortinas camaras e infrarojos

            // buscamos los dispositivos para marcar su termina consumo o inicia consumo
            List<Consumer> consumerList = consumerDAO.getByCode(device.getCode());
            if (consumerList == null || consumerList.isEmpty()) {
                if (status) {
                    Consumer consumer = ConsumerHelper.buildConsumer(device);
                    consumerDAO.save(consumer);
                }
            } else {
                if (!status) {
                    for (Consumer consumer : consumerList) {
                        // marcamos como el termino del uso del dispositivo
                        consumer.setEnd(new Date());
                        consumerDAO.save(consumer);
                    }
                }
            }
        }
    }


}
