<%@include file="/jsp/taglibs.jspf" %>

<s:if test="#session.MOVEMENT_ACTION_LIST != null">
    <div class="span8">
        <table class="striped">
            <thead>
            <tr>
                <th>Codigo</th>
                <th>Nombre</th>
                <th>Tipo</th>
                <th class="center">Opciones</th>
            </tr>
            </thead>

            <tbody>
            <s:iterator value="#session.MOVEMENT_ACTION_LIST" status="status">
                <s:if test="#status.index == 0"><input type="hidden" name="haveDetail" id="haveDetail" value="true" /></s:if>
                <tr>
                    <td><s:if test="device != null"><s:property value="device.code" /></s:if></td>
                    <td><s:if test="device != null"><s:property value="device.name" /></s:if></td>
                    <td><s:property value="movementActionTypeEnum.name" /></td>
                    <td class="center" >
                        <i class="icon-remove" style="cursor: pointer;" onclick="MovementAction.remove(<s:property value="#status.index" />);"></i>
                    </td>
                </tr>
            </s:iterator>
            </tbody>

            <tfoot></tfoot>
        </table>

    </div>
</s:if>