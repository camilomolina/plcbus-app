package cl.bennu.plcbus.base;

import cl.bennu.plcbus.common.domain.weather.Weather;
import cl.bennu.plcbus.common.dto.ContextDTO;
import cl.bennu.plcbus.constant.Constant;
import cl.bennu.plcbus.core.business.iface.IControlService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 03-07-13
 * Time: 03:15 AM
 */
public abstract class BaseAction extends ActionSupport implements SessionAware, Preparable, ServletRequestAware, ServletResponseAware {

    private IControlService controlService;
    protected Map<String, Object> sessionMap;
    protected HttpServletRequest httpRequest;
    protected HttpSession httpSession;
    protected HttpServletResponse httpResponse;
    public String SUCCESS_JSON = "successJSON";

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        httpRequest = httpServletRequest;
        httpSession = httpRequest.getSession();
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        httpResponse = httpServletResponse;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        sessionMap = stringObjectMap;
    }

    protected abstract void initial() throws Exception;

    public void prepare() throws Exception {
        this.initial();

        // se dejo en el interceptor
        //setea el contentType a todas las paginas
        //httpResponse.setContentType("text/html; charset=iso-8859-1");
    }

    public ContextDTO getContext() {
        ContextDTO contextDTO = new ContextDTO();
        contextDTO.setUser("cmolina");
        contextDTO.setHost(httpRequest.getRemoteHost());
        contextDTO.setSessionId(httpRequest.getSession().getId());
        return contextDTO;
    }

    public IControlService getControlService() {
        return controlService;
    }

    public void setControlService(IControlService controlService) {
        this.controlService = controlService;
    }

    public void serialize(Object o) throws Exception {
        setHeader4Ajax();
        httpResponse.getWriter().print(JSONUtil.serialize(o, null, null, false, true, false));
    }

    public void setHeader4Ajax() throws Exception {
        // prepare CALL
        try {
            httpRequest.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ServletException(e);
        }
        // Set content to xml
        httpResponse.setContentType("text/xml; charset=UTF-8");
        // Set HTTP/1.1 no-cache headers.
        httpResponse.setHeader("Cache-Control", "no-store, max-age=0, no-cache");
        httpResponse.addHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        httpResponse.setHeader("Pragma", "no-cache");
    }

    public Weather getWeather() {
        return (Weather) httpSession.getAttribute(Constant.WEATHER);
    }
}
