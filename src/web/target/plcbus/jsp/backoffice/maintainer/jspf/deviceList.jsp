<%@include file="/jsp/taglibs.jspf" %>

<h2>Lista de dispositivos</h2>
<table class="striped">
    <thead>
    <tr>
        <th>Codigo</th>
        <th>Nombre</th>
        <th>Descripcion</th>
        <th>Tipo</th>
        <th>Opciones</th>
    </tr>
    </thead>

    <tbody>

    <s:iterator value="maintainerBean.deviceList">
        <tr <s:if test="active">class="success"</s:if><s:else>class="error"</s:else>>
            <td><s:property value="code" /></td>
            <td><s:property value="name" /></td>
            <td>
                <abbr title="<s:property value="desc" />">
                    <i class="icon-search" style="cursor: pointer;" ></i>
                </abbr>
            </td>
            <td><s:property value="deviceTypeEnum.name" /></td>
            <td class="center" >
                <i class="icon-pencil" style="cursor: pointer;" onclick="Device.get(<s:property value="id" />);" ></i>
                <i class="icon-remove" style="cursor: pointer;" onclick="Device.delete(<s:property value="id" />, '<s:property value="name" />');" ></i>
            </td>
        </tr>
    </s:iterator>
    </tbody>

    <tfoot></tfoot>
</table>
