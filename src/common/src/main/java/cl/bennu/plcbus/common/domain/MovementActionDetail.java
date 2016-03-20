package cl.bennu.plcbus.common.domain;

import cl.bennu.plcbus.common.enums.MovementActionTypeEnum;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class MovementActionDetail extends BaseDomain {

    private MovementAction movementAction;
    private Device device;
    private MovementActionTypeEnum movementActionTypeEnum;

    public MovementAction getMovementAction() {
        return movementAction;
    }

    public void setMovementAction(MovementAction movementAction) {
        this.movementAction = movementAction;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public MovementActionTypeEnum getMovementActionTypeEnum() {
        return movementActionTypeEnum;
    }

    public void setMovementActionTypeEnum(MovementActionTypeEnum movementActionTypeEnum) {
        this.movementActionTypeEnum = movementActionTypeEnum;
    }
}
