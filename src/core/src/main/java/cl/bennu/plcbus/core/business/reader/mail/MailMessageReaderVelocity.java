package cl.bennu.plcbus.core.business.reader.mail;

import cl.bennu.plcbus.common.domain.Device;
import cl.bennu.plcbus.common.enums.MovementTypeEnum;
import cl.bennu.plcbus.core.business.reader.VelocityIniciality;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public class MailMessageReaderVelocity implements IMailMessageReader {

    public static VelocityContext getVelocityContext(Device device) {
        VelocityContext velocityContext = new VelocityIniciality().getVelocityContext();
        velocityContext.put("date", new DateTool());
        velocityContext.put("now", new Date());
        velocityContext.put("device", device);

        return velocityContext;
    }

    @Override
    public StringBuffer buildMail4Alert(Device device, List<Device> alarmedDeviceList) {
        StringBuffer buffer = null;
        try {
            VelocityContext velocityContext = getVelocityContext(device);
            velocityContext.put("alarmedDeviceList", alarmedDeviceList);

            Template t1 = Velocity.getTemplate("cl/bennu/plcbus/core/reader/velocity/template/alert.vm");
            StringWriter sw = new StringWriter();

            t1.merge(velocityContext, sw);
            buffer = new StringBuffer(sw.toString());

            sw.flush();
            sw.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return buffer;
    }

    @Override
    public StringBuffer buildMail4Sensor(Device device, MovementTypeEnum movementTypeEnum) {
        StringBuffer buffer = null;
        try {
            VelocityContext velocityContext = getVelocityContext(device);
            velocityContext.put("movementTypeEnum", movementTypeEnum);

            Template t1 = Velocity.getTemplate("cl/bennu/plcbus/core/reader/velocity/template/sensor.vm");
            StringWriter sw = new StringWriter();

            t1.merge(velocityContext, sw);
            buffer = new StringBuffer(sw.toString());

            sw.flush();
            sw.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return buffer;
    }

    @Override
    public StringBuffer buildMail4Other(Device device) {
        StringBuffer buffer = null;
        try {
            VelocityContext velocityContext = getVelocityContext(device);

            Template t1 = Velocity.getTemplate("cl/bennu/plcbus/core/reader/template/alert.vm");
            StringWriter sw = new StringWriter();

            t1.merge(velocityContext, sw);
            buffer = new StringBuffer(sw.toString());

            sw.flush();
            sw.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return buffer;
    }

}
