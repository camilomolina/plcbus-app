<%@include file="/jsp/taglibs.jspf" %>

<div class="span8">

    <table class="striped">
        <thead>
        <tr>
            <th>Codigo</th>
            <th>Nombre</th>
            <th>Detalle</th>
            <th class="center">Eliminar</th>
        </tr>
        </thead>

        <tbody>
        <s:iterator value="configurationBean.scheduledShutdownDeviceList">
            <tr>
                <td><s:property value="device.code" /></td>
                <td><s:property value="device.name" /></td>
                <td><s:date name="off" format="HH:mm"/></td>
                <td class="center" >
                    <i class="icon-remove" style="cursor: pointer;" onclick="ScheduledShutdownDevice.delete(<s:property value="id" />, '<s:property value="device.name" />');"></i>
                </td>
            </tr>
        </s:iterator>
        </tbody>

        <tfoot></tfoot>
    </table>

</div>