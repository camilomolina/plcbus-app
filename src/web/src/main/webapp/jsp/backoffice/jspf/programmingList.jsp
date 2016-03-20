<%@include file="/jsp/taglibs.jspf" %>

<div class="span8">

    <table class="striped">
        <thead>
        <tr>
            <th>Codigo</th>
            <th>Nombre</th>
            <th>Tipo</th>
            <th>Detalle</th>
            <th>Activo</th>
            <th class="center">Opciones</th>
        </tr>
        </thead>

        <tbody>
        <s:iterator value="configurationBean.programmingList">
            <tr>
                <td><s:property value="device.code" /></td>
                <td><s:property value="device.name" /></td>
                <td><s:property value="programmingTypeEnum.name" /></td>
                <td>
                    <s:if test="programmingTypeEnum.id == 1">
                        <s:iterator value="programmingDetailList" status="status">
                            <s:if test="#status.index == 0">
                                <s:date name="on" format="HH:mm"/>-<s:date name="off" format="HH:mm"/>
                            </s:if>
                        </s:iterator>
                    </s:if>
                    <s:elseif test="programmingTypeEnum.id == 2">
                        <s:iterator value="programmingDetailList" status="status">
                            <s:property value="dayEnum.name"/>
                            <s:date name="on" format="HH:mm"/>-<s:date name="off" format="HH:mm"/>
                            <br/>
                        </s:iterator>
                    </s:elseif>
                </td>
                <td class="center" >
                    <label class="input-control switch" >
                        <input type="checkbox" name="activeArray" id="statusCheckbox_<s:property value="id" />" <s:if test="active" >checked="true" </s:if>  onchange="Programming.changeStatusProgramming(<s:property value="id" />);" />
                        <span class="helper"></span>
                    </label>
                </td>
                <td class="center" >
                    <i class="icon-pencil" style="cursor: pointer;" onclick="Programming.get(<s:property value="id" />, '<s:property value="device.name" />');"></i>
                    <i class="icon-remove" style="cursor: pointer;" onclick="Programming.delete(<s:property value="id" />, '<s:property value="device.name" />');"></i>
                </td>
            </tr>
        </s:iterator>
        </tbody>

        <tfoot></tfoot>
    </table>

</div>