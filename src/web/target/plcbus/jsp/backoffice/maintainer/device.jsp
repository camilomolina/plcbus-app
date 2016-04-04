<%@include file="/jsp/taglibs.jspf" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="target-densitydpi=device-dpi, width=device-width, initial-scale=1.0, maximum-scale=1">
    <meta name="description" content="PLCBus">
    <meta name="author" content="bennu">
    <meta name="keywords" content="plcbus">

    <link href="/plcbus/css/modern.css" rel="stylesheet">
    <link href="/plcbus/css/modern-responsive.css" rel="stylesheet">
    <link href="/plcbus/css/site.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/plcbus/js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="/plcbus/js/jquery.mousewheel.min.js"></script>

    <script type="text/javascript" src="/plcbus/js/moment.js"></script>
    <script type="text/javascript" src="/plcbus/js/moment_langs.js"></script>


    <script type="text/javascript" src="/plcbus/js/modern/dropdown.js"></script>
    <script type="text/javascript" src="/plcbus/js/modern/accordion.js"></script>
    <script type="text/javascript" src="/plcbus/js/modern/buttonset.js"></script>
    <script type="text/javascript" src="/plcbus/js/modern/carousel.js"></script>
    <script type="text/javascript" src="/plcbus/js/modern/input-control.js"></script>
    <script type="text/javascript" src="/plcbus/js/modern/pagecontrol.js"></script>
    <script type="text/javascript" src="/plcbus/js/modern/rating.js"></script>
    <script type="text/javascript" src="/plcbus/js/modern/slider.js"></script>
    <script type="text/javascript" src="/plcbus/js/modern/tile-slider.js"></script>
    <script type="text/javascript" src="/plcbus/js/modern/tile-drag.js"></script>
    <script type="text/javascript" src="/plcbus/js/modern/calendar.js"></script>
    <script type="text/javascript" src="/plcbus/js/modern/dialog.js"></script>

    <script type="text/javascript" src="/plcbus/js/common/generic.js"></script>
    <script type="text/javascript" src="/plcbus/js/backoffice/maintainer/device.js"></script>

    <title>PLCBus</title>

</head>
<body class="metrouicss">
<form action="/plcbus/backoffice/configuration/startDevice.do" id="homeControlId">
    <jsp:include page="/jsp/common/menu.jsp"/>

    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Configuraci&oacute;n
                    <small>dispositivos</small>
                </h1>
                <a href="/plcbus/backoffice/configuration/startConfiguration.do" class="back-button big page-back"></a>
            </div>
        </div>


        <div class="page-region">
            <div class="page-region-content">

                <div class="page-control span10" data-role="page-control">
                    <span class="menu-pull"></span>
                    <div class="menu-pull-bar"></div>

                    <ul>
                        <li id="maintainerLi" class="active"><a href="#maintainerFrame"><i class="icon-equalizer"></i>Mantenedor</a></li>
                        <li id="deviceListLi" ><a href="#deviceListFrame">Lista</a></li>
                        <li class="place-right"><a href="#helpFrame"><i class="icon-help-2 nrm"></i></a></li>
                    </ul>

                    <div class="frames">
                        <div class="frame active" id="maintainerFrame">
                            <div class="grid">
                                <div class="span9">

                                    <h2>Mantenedor de dispositivos</h2>

                                    <fieldset>
                                        <legend>Datos del dispositivo</legend>
                                        <div class="row">
                                            <div class="span8">
                                                <s:if test="maintainerBean.deviceCodeEnum != null">ultimo dispositivo agregado <s:property value="maintainerBean.deviceCodeEnum" /></s:if>
                                            </div>
                                        </div>
                                        <input type="hidden" name="maintainerBean.device.id" id="deviceId" >

                                        <div class="row">
                                            <div class="span4">
                                                <p class="tertiary-info-secondary-text">Nombre</p>
                                                <div class="input-control text">
                                                    <input type="text" name="maintainerBean.device.name" id="name" autofocus="" placeholder="Nombre">
                                                    <button type="button" class="btn-clear" />
                                                </div>
                                            </div>
                                            <div class="span2">
                                                <p class="tertiary-info-secondary-text">Codigo</p>
                                                <div class="input-control select">
                                                    <s:select name="maintainerBean.device.code" headerKey="-1" headerValue="--" id="code" list="maintainerBean.deviceCodeEnumList" listKey="name" listValue="name"/>
                                                    <s:select name="maintainerBean.device.code" headerKey="-1" headerValue="--" id="otherCode" list="maintainerBean.otherDeviceCodeEnumList" listKey="name" listValue="name" cssStyle="display: none;"/>
                                                </div>
                                            </div>
                                            <div class="span2">
                                                <p class="tertiary-info-secondary-text">Tipo</p>
                                                <div class="input-control select">
                                                    <s:select name="maintainerBean.device.deviceTypeEnum.id" headerKey="-1" headerValue="--" id="deviceTypeEnumId" list="maintainerBean.deviceTypeEnumList" listKey="id" listValue="name" onchange="Device.changeDeviceType();" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="span8">
                                                <p class="tertiary-info-secondary-text">Descripcion</p>
                                                <div class="input-control textarea">
                                                    <textarea name="maintainerBean.device.desc" id="desc" placeholder="Descripcion del dispositivo..."></textarea>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="span7">
                                                <p class="tertiary-info-secondary-text">Sector</p>
                                                <div class="input-control select">
                                                    <s:select name="maintainerBean.device.sector.id" headerKey="-1" headerValue="Seleccione sector" id="sectorId" list="maintainerBean.sectorList" listKey="id" listValue="%{level.name + ' - ' + name}"/>
                                                </div>
                                            </div>
                                            <div class="span1">
                                                <p class="tertiary-info-secondary-text">Activo</p>
                                                <label class="input-control switch" >
                                                    <input type="checkbox" name="maintainerBean.device.active" id="active" checked="true" />
                                                    <span class="helper"></span>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="row" id="cameraDiv" style="display: none;">
                                            <input type="hidden" name="maintainerBean.device.camera.id" id="cameraId" >
                                            <div class="span4">
                                                <p class="tertiary-info-secondary-text">IP</p>
                                                <div class="input-control text">
                                                    <input type="text" name="maintainerBean.device.camera.ip" id="ip" autofocus="" placeholder="IP">
                                                    <button type="button" class="btn-clear" />
                                                </div>
                                            </div>
                                            <div class="span2">
                                                <p class="tertiary-info-secondary-text">Port</p>
                                                <div class="input-control text">
                                                    <input type="text" name="maintainerBean.device.camera.port" id="port" autofocus="" placeholder="Puerto">
                                                    <button type="button" class="btn-clear" />
                                                </div>
                                            </div>
                                            <div class="span2">
                                                <p class="tertiary-info-secondary-text">Tipo</p>
                                                <div class="input-control select">
                                                    <s:select name="maintainerBean.device.camera.cameraTypeEnum.id" headerKey="-1" headerValue="Seleccione tipo de camara" id="cameraTypeEnumId" list="maintainerBean.cameraTypeEnumList" listKey="id" listValue="name"/>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>

                                    <fieldset>
                                        <div class="row">
                                            <div class="span8">
                                                <input type="button" value="Cancelar" >
                                                <input type="button" value="Volver" class="bg-color-orange place-right">
                                                <input type="button" value="Guardar" class="standart default place-right" onclick="Device.save();">
                                            </div>
                                        </div>
                                    </fieldset>


                                </div>
                            </div>
                        </div>
                        <div class="frame" id="deviceListFrame">
                            <jsp:include page="/jsp/backoffice/maintainer/jspf/deviceList.jsp" flush="true"/>
                        </div>
                        <div class="frame" id="helpFrame">
                            <h2>Ayuda</h2>
                            <p>Esta session permite crear, eliminar o actualizar los dispositivos instalados, se debe tener
                                en consideracion que cada dispositivo debe ser correctamente instalado por un electrico certificado.
                                Cada micromodulo debe responder a las señales electricas que le son enviadas.
                                Una eliminación de dispositivo, puede alterar las programaciones y las coordenadas en donde se configuran.
                            </p>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>

    <jsp:include page="/jsp/common/footer.jsp"/>

</form>
</body>
</html>
