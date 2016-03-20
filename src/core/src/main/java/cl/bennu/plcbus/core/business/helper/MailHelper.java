package cl.bennu.plcbus.core.business.helper;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 07-11-13
 * Time: 04:18 AM
 */
public class MailHelper {

    private static void buildEmail(Email email, String host, int port, boolean ssl, boolean tls, String from, String aliasFrom, String user, String pass) throws Exception {
        email.setHostName(host);
        email.setSmtpPort(port);
        email.setSSL(ssl);
        email.setTLS(tls);

        email.setFrom(from, aliasFrom);
        email.setAuthentication(user, pass);
    }

    public static HtmlEmail buildHtmlEmail(String host, int port, boolean ssl, boolean tls, String from, String aliasFrom, String user, String pass) throws Exception {
        HtmlEmail htmlMail = new HtmlEmail();
        buildEmail(htmlMail, host, port, ssl, tls, from, aliasFrom, user, pass);

        htmlMail.setTextMsg("email can't html type message");

        return htmlMail;
    }

    public static SimpleEmail buildSimpleEmail(String host, int port, boolean ssl, boolean tls, String from, String aliasFrom, String user, String pass) throws Exception {
        SimpleEmail email = new SimpleEmail();
        buildEmail(email, host, port, ssl, tls, from, aliasFrom, user, pass);

        return email;
    }

    public static MultiPartEmail buildMultiPartEmail(String host, int port, boolean ssl, boolean tls, String from, String aliasFrom, String user, String pass) throws Exception {
        MultiPartEmail email = new MultiPartEmail();
        buildEmail(email, host, port, ssl, tls, from, aliasFrom, user, pass);

        return email;
    }

}
