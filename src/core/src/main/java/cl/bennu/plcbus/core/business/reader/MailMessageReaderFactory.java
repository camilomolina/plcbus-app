package cl.bennu.plcbus.core.business.reader;

import cl.bennu.plcbus.core.business.reader.mail.IMailMessageReader;
import cl.bennu.plcbus.core.business.reader.mail.MailMessageReaderVelocity;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 07:00 AM
 */
public class MailMessageReaderFactory {

    public static IMailMessageReader getMailMessageReader() {
        return new MailMessageReaderVelocity();
    }

}
