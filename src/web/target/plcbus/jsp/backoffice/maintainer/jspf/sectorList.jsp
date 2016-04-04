<%@include file="/jsp/taglibs.jspf" %>

<h2>Lista de zonas</h2>
<table class="striped">
    <thead>
    <tr>
        <th>Nombre</th>
        <th>Nivel</th>
        <th>Opciones</th>
    </tr>
    </thead>

    <tbody>

    <s:iterator value="maintainerBean.sectorList">
        <tr <s:if test="active">class="success"</s:if><s:else>class="error"</s:else>>
            <td><s:property value="name" /></td>
            <td><s:property value="level.name" /></td>
            <td class="center" >
                <i class="icon-pencil" style="cursor: pointer;" onclick="Sector.get(<s:property value="id" />);" ></i>
                <i class="icon-remove" style="cursor: pointer;" onclick="Sector.delete(<s:property value="id" />, '<s:property value="name" />');" ></i>
            </td>
        </tr>
    </s:iterator>
    </tbody>

    <tfoot></tfoot>
</table>
