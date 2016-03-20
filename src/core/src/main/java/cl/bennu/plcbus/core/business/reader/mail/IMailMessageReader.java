package cl.bennu.plcbus.core.business.reader.mail;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.enums.MovementTypeEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public interface IMailMessageReader {

    StringBuffer buildMail4Alert(Device device, List<Device> alarmedDeviceList);

    StringBuffer buildMail4Sensor(Device device, MovementTypeEnum movementTypeEnum);

    StringBuffer buildMail4Other(Device device);

}
