<%@include file="/jsp/taglibs.jspf" %>

<h2>Lista de niveles</h2>
<table class="striped">
    <thead>
    <tr>
        <th>Nombre</th>
        <th>Mapas</th>
        <th>Opciones</th>
    </tr>
    </thead>

    <tbody>

    <s:iterator value="maintainerBean.levelList">
        <tr <s:if test="active">class="success"</s:if><s:else>class="error"</s:else>>
            <td><s:property value="name" /></td>
            <td>
                <s:if test="active">
                    <i class="icon-cog" style="cursor: pointer;" onclick="Level.configMap(<s:property value="id" />);" ></i>
                </s:if>
            </td>
            <td class="center" >
                <i class="icon-pencil" style="cursor: pointer;" onclick="Level.get(<s:property value="id" />);" ></i>
                <i class="icon-remove" style="cursor: pointer;" onclick="Level.delete(<s:property value="id" />, '<s:property value="name" />');" ></i>
            </td>
        </tr>
    </s:iterator>
    </tbody>

    <tfoot></tfoot>
</table>
