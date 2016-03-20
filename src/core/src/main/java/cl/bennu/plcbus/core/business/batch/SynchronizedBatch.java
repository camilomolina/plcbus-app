package cl.bennu.plcbus.core.business.batch;

import cl.bennu.plcbus.core.business.iface.IConfigurationService;
import cl.bennu.plcbus.core.business.iface.IControlService;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 29-10-13
 * Time: 03:44 AM
 */
public class SynchronizedBatch extends BaseBatch {

    private IControlService controlService;
    private IConfigurationService configurationService;

    public void execute() {
        //System.out.println("Ejecutando SynchronizedBatch");
        try {
            configurationService.synchronizedDevice(buildContext());
        } catch (Exception e) {
            System.out.println("Error en SynchronizedTimer" + e.getCause());
        }
    }

    public IControlService getControlService() {
        return controlService;
    }

    public void setControlService(IControlService controlService) {
        this.controlService = controlService;
    }

    public IConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
