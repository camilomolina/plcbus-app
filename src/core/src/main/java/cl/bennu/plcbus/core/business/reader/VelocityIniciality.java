package cl.bennu.plcbus.core.business.reader;

import cl.bennu.plcbus.common.util.ResourcesFinder;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public class VelocityIniciality {


    public VelocityIniciality() {
        try {
            InputStream inputStream = ResourcesFinder.getResourceAsStream("cl/bennu/plcbus/core/reader/velocity/velocity.properties");
            Properties prop = new Properties();
            prop.load(inputStream);
            Velocity.init(prop);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public VelocityContext getVelocityContext() {
        return new VelocityContext();
    }
}
