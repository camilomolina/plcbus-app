package cl.bennu.plcbus.common.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 28-02-14
 * Time: 11:46 PM
 */
public class ContextDTO implements Serializable {

    private String sessionId;
    private String host;
    private String user;
    private String data1;
    private String data2;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }
}
