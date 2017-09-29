package cl.bennu.plcbus.common;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 06:05 AM
 */
public class Constants {

    public final static Boolean DEVELOMPENT_MODE = false;
    public final static String PORT = "COM4";
    //public final static String PORT = "/dev/ttyUSB0";
    public final static int SYNCHRONIZED_DELAY_4_UNIT = 1;
    public final static int SYNCHRONIZED_DELAY_4_LETTER = 3;
    public final static String DATE_FORMAT = "dd/MM/yyyy";
    public final static String DATE_FORMAT_MM_YYYY = "MM/yyyy";
    public final static String TIME_FORMAT = "HH:mm";
    public final static String TIME_FORMAT_HH_MM_SS = "HH:mm:ss";
    public final static String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public final static int ALERT_HOURS = 3;
    public final static int COST_kWh_CLP = 88;
    public final static int COST_MAX_CLP = 5000;

    public final static int DEFAULT_WATTS_FILAMENT = 75;
    public final static int DEFAULT_WATTS_HALOGEN = 150;
    public final static int DEFAULT_WATTS_LED = 8;
    public final static int DEFAULT_WATTS_FLUORESCENT = 24;
    public final static int DEFAULT_WATTS_DIMMER = 60;
    public final static int DEFAULT_WATTS_LOW = 16;

    public final static String MAIL_FROM = "plcbus@viruscorp.noip.me";
    public final static String MAIL_HOST = "smtp.gmail.com";
    public final static String MAIL_FROM_ALIAS = "plcbus";
    public final static String MAIL_USER = "djmac.j@gmail.com";
    public final static String MAIL_PASS = "mac11042";
    public final static int MAIL_PORT = 465;
    public final static boolean MAIL_SSL = true;
    public final static boolean MAIL_TLS = false;

    public final static String SMS_USER = "camilo.molina";
    public final static String SMS_PASS = "123456";

}
