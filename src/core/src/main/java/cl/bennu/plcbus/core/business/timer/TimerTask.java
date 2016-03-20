package cl.bennu.plcbus.core.business.timer;

import cl.bennu.plcbus.common.dto.ContextDTO;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 01-03-14
 * Time: 12:35 AM
 */
public abstract class TimerTask extends java.util.TimerTask {

    public static ContextDTO buildContext() {
        ContextDTO contextDTO = new ContextDTO();
        contextDTO.setHost("local");
        contextDTO.setUser("plcbus");
        return contextDTO;
    }
}
