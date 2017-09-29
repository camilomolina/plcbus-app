<%@include file="/jsp/taglibs.jspf" %>

<s:if test="configurationBean.synchronizedInCurse">
    <p class="tertiary-info-secondary-text">Sincronizaci&oacute;n en curso, <s:property value="configurationBean.percentage" />% de avance en <s:property value="configurationBean.minutes" /> minutos</p>
    <div class="progress-bar" data-role="progress-bar">
        <div class="bar bg-color-green" data-bar="<s:property value="configurationBean.percentage" />" style="width: <s:property value="configurationBean.percentage" />%;"></div>
        <div class="bar bg-color-yellow" data-bar="<s:property value="configurationBean.percentageDiff" />" style="width: <s:property value="configurationBean.percentageDiff" />%;"></div>
    </div>
</s:if>
<s:else>
    <p class="tertiary-info-secondary-text">Sincronizaci&oacute;n completada, ultima sincronizacion <s:date name="configurationBean.startSynchronized" format="dd/MM/yyyy HH:mm:ss" /> y tardo <s:property value="configurationBean.minutes" /> minutos</p>
    <div class="progress-bar" data-role="progress-bar">
        <div class="bar bg-color-green" data-bar="100" style="width: 100%;"></div>
    </div>
</s:else>

<table>
    <thead>
    <tr>
        <th>Codigo</th>
        <th>Nombre</th>
        <th class="right">Nivel de senal</th>
        <th class="right">Nivel de ruido</th>
    </tr>
    </thead>

    <tbody>
    <s:iterator value="configurationBean.statusRefresh.statusRefreshDetailList">
        <tr <s:if test="synchronizedEnum.id == 2">class="success"</s:if><s:if test="synchronizedEnum.id == 3">class="error"</s:if>>
        <td><s:property value="device.code" /></td>
        <td><s:property value="device.name" /></td>
        <td class="right"><s:property value="device.signal" /></td>
        <td class="right"><s:property value="device.noize" /></td>
        </tr>
    </s:iterator>
    </tbody>
    <tfoot></tfoot>
</table>

<br/>
<br/>
<!-- forceSynchronized -->
<button id="forceButton" type="button" onclick="Synchronized.forceButton(<s:property value="configurationBean.synchronizedInCurse"/>, <s:property value="configurationBean.synchronizedTimeOut"/>);" class="image-button bg-color-pink fg-color-white">Forzar sincronizaci&oacute;n<img class="bg-color-red" src="/plcbus/images/metro/armor.png"/></button>


