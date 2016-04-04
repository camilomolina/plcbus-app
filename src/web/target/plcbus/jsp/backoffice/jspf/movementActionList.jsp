<%@include file="/jsp/taglibs.jspf" %>

<div class="span8">

    <table class="striped">
        <thead>
        <tr>
            <th>Codigo</th>
            <th>Nombre</th>
            <th>Tipo</th>
            <th>Horario</th>
            <th>Detalle</th>
            <th>Activo</th>
            <th class="center">Opciones</th>
        </tr>
        </thead>

        <tbody>
        <s:iterator value="configurationBean.movementActionList">
            <tr>
                <td><s:property value="device.code" /></td>
                <td><s:property value="device.name" /></td>
                <td><s:property value="movementTypeEnum.name" /></td>
                <td><s:date name="start" format="HH:mm"/> - <s:date name="end" format="HH:mm"/></td>
                <td>
                    <s:iterator value="movementActionDetailList" status="status">
                        <s:if test="device != null"><s:property value="device.code"/> - </s:if>
                        <s:property value="movementActionTypeEnum.name"/>
                        <br/>
                    </s:iterator>
                </td>
                <td class="center" >
                    <label class="input-control switch" >
                        <input type="checkbox" name="activeArray" id="statusCheckbox_<s:property value="id" />" <s:if test="active" >checked="true" </s:if>  onchange="MovementAction.changeStatusMovementAction(<s:property value="id" />);" />
                        <span class="helper"></span>
                    </label>
                </td>
                <td class="center" >
                    <i class="icon-pencil" style="cursor: pointer;" onclick="MovementAction.get(<s:property value="id" />, '<s:property value="device.name" />');"></i>
                    <i class="icon-remove" style="cursor: pointer;" onclick="MovementAction.delete(<s:property value="id" />, '<s:property value="device.name" />');"></i>
                </td>
            </tr>
        </s:iterator>
        </tbody>

        <tfoot></tfoot>
    </table>

</div>