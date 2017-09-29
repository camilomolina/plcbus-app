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
    <script type="text/javascript" src="/plcbus/js/backoffice/administration/camera.js"></script>

    <title>PLCBus</title>

    <script type="text/javascript">
        // funcionaes de camara

        var CAMARA_CODE_ARRAY = [];

        <s:iterator value="administrationBean.cameraList" var="camera" id="camera">

            var hostCamera_<s:property value='code' /> = "<s:property value='camera.ip' />" + ":" + "<s:property value='camera.port' />";
            var cameraW_<s:property value='code' />;

            CAMARA_CODE_ARRAY.push('<s:property value="code"/>');
            var cameraW_<s:property value='code' />_status = false;
            function camera_<s:property value='code' />() {
                cameraOff();

                /*
                $("#up").attr("onmousedown", "Camera.cameraUp('<s:property value='code' />')");
                $("#up").attr("onmouseup", "Camera.cameraCommandOff('<s:property value='code' />')");

                $("#up-right").attr("onmousedown", "Camera.cameraUpRight('<s:property value='code' />')");
                $("#up-right").attr("onmouseup", "Camera.cameraCommandOff('<s:property value='code' />')");

                $("#right").attr("onmousedown", "Camera.cameraRight('<s:property value='code' />')");
                $("#right").attr("onmouseup", "Camera.cameraCommandOff('<s:property value='code' />')");

                $("#down-right").attr("onmousedown", "Camera.cameraDownRight('<s:property value='code' />')");
                $("#down-right").attr("onmouseup", "Camera.cameraCommandOff('<s:property value='code' />')");

                $("#down").attr("onmousedown", "Camera.cameraDown('<s:property value='code' />')");
                $("#down").attr("onmouseup", "Camera.cameraCommandOff('<s:property value='code' />')");

                $("#down-left").attr("onmousedown", "Camera.cameraDownLeft('<s:property value='code' />')");
                $("#down-left").attr("onmouseup", "Camera.cameraCommandOff('<s:property value='code' />')");

                $("#left").attr("onmousedown", "Camera.cameraLeft('<s:property value='code' />')");
                $("#left").attr("onmouseup", "Camera.cameraCommandOff('<s:property value='code' />')");

                $("#up-left").attr("onmousedown", "Camera.cameraUpLeft('<s:property value='code' />')");
                $("#up-left").attr("onmouseup", "Camera.cameraCommandOff('<s:property value='code' />')");

                $("#up-left").attr("onmousedown", "Camera.cameraUpLeft('<s:property value='code' />')");
                $("#up-left").attr("onmouseup", "Camera.cameraCommandOff('<s:property value='code' />')");

                $("#save-1").attr("onclick", "Camera.saveCameraPosition('<s:property value='code' />', 1)");
                $("#save-2").attr("onclick", "Camera.saveCameraPosition('<s:property value='code' />', 2)");
                $("#save-3").attr("onclick", "Camera.saveCameraPosition('<s:property value='code' />', 3)");
                $("#save-4").attr("onclick", "Camera.saveCameraPosition('<s:property value='code' />', 4)");

                $("#view-1").attr("onclick", "Camera.viewCameraPosition('<s:property value='code' />', 1)");
                $("#view-2").attr("onclick", "Camera.viewCameraPosition('<s:property value='code' />', 2)");
                $("#view-3").attr("onclick", "Camera.viewCameraPosition('<s:property value='code' />', 3)");
                $("#view-4").attr("onclick", "Camera.viewCameraPosition('<s:property value='code' />', 4)");

                $("#flipHorizontally").attr("onchange", "Camera.flipCamera('<s:property value='code' />', 'flipHorizontally', 'flipVertically')");
                $("#flipVertically").attr("onchange", "Camera.flipCamera('<s:property value='code' />', 'flipHorizontally', 'flipVertically')");

                */
                Camera.setFunctionCamera('<s:property value='code' />');

                cameraW_<s:property value='code' />_status = true;
                <%--changeDeviceIconOn('<s:include value="/jsp/common/deviceType2.jsp"/>', '<s:property value='code' />', true);--%>
                //document.getElementById('camera_<s:property value='code' />').style.display = "block";
                cameraW_<s:property value='code' /> = window.setInterval('refreshCamera_<s:property value='code' />()', 100);
            };

            function cameraOff_<s:property value='code' />() {
                //document.getElementById('camera_<s:property value='code' />').style.display = "none";
                window.clearInterval(cameraW_<s:property value='code' />);
                cameraW_<s:property value='code' />_status = false;
                <%--changeDeviceIconOff('<s:include value="/jsp/common/deviceType2.jsp"/>', '<s:property value='code' />');--%>
            };

            function refreshCamera_<s:property value='code' />(){
                var ctx = document.getElementById('cameraView').getContext('2d');
                var img = new Image();
                img.src = "http://"+hostCamera_<s:property value='code' />+"/snapshot.jpg?user=admin&resolution=1&time=" + new Date().getTime();
                img.onload = function() {
                    ctx.drawImage(img, 0, 0);
                };
            };

        </s:iterator>

        function cameraOff() {
            <s:iterator value="administrationBean.cameraList" var="camera" id="camera">
                window.clearInterval(cameraW_<s:property value='code' />);
                cameraW_<s:property value='code' />_status = false;
                $("#camera_<s:property value='code' />").attr("class", "tile image outline-color-green");
            </s:iterator>
        }

        function loadCamera(code) {
            Camera.getCamera(code);
            //Camera.getParameterCamera(code);
            $("#camera_" + code).attr("class", "tile image outline-color-green selected");
            var f = "camera_" + code + "()";
            eval(f);
        }

    </script>
</head>
<body class="metrouicss">
<form action="/plcbus/backoffice/administration/startCamera.do" id="cameraId">
    <jsp:include page="/jsp/common/menu.jsp"/>

    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Administraci&oacute;n
                    <small>camaras</small>
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
                        <li id="maintainerLi" class="active"><a href="#maintainerFrame"><i class="icon-equalizer"></i>Administrador</a></li>
                        <li id="deviceListLi" ><a href="#deviceListFrame">Lista</a></li>
                        <li class="place-right"><a href="#helpFrame"><i class="icon-help-2 nrm"></i></a></li>
                    </ul>

                    <div class="frames">
                        <div class="frame active" id="maintainerFrame">
                            <div class="grid">
                                <div class="span9">

                                    <h2>Administrador de camaras</h2>

                                    <div class="tile-group tile-drag">

                                        <s:iterator value="administrationBean.cameraList" var="camera" id="camera">
                                                <div class="tile image outline-color-green" id="camera_<s:property value='code' />" onclick="loadCamera('<s:property value='code' />');">
                                                    <div class="tile-content">
                                                        <img src="http://<s:property value='camera.ip' />:<s:property value='camera.port' />/snapshot.jpg?user=admin&resolution=1" alt="">
                                                    </div>
                                                    <div class="brand bg-color-red">
                                                        <p class="text"><s:property value='camera.name' /></p>
                                                    </div>
                                                </div>
                                        </s:iterator>

                                    </div>

                                    <fieldset>
                                        <legend></legend>
                                        <input type="hidden" name="administrationBean.camera.id" id="deviceId" >

                                        <div class="row">
                                            <div class="span4">
                                                <p class="tertiary-info-secondary-text">Nombre</p>
                                                <div class="input-control text disabled">
                                                    <input type="text" name="administrationBean.camera.name" id="name" autofocus="" placeholder="Nombre" disabled>
                                                    <button type="button" class="btn-clear" />
                                                </div>
                                            </div>
                                            <div class="span2">
                                                <p class="tertiary-info-secondary-text">Codigo</p>
                                                <div class="input-control text disabled">
                                                    <input type="text" name="administrationBean.camera.code" id="code" autofocus="" placeholder="Codigo" disabled>
                                                    <button type="button" class="btn-clear" />
                                                </div>
                                            </div>
                                            <div class="span2">
                                                <p class="tertiary-info-secondary-text">Tipo</p>
                                                <div class="input-control text disabled">
                                                    <input type="text" name="administrationBean.camera.deviceTypeEnum.name" id="deviceTypeEnum" autofocus="" placeholder="Tipo" disabled>
                                                    <button type="button" class="btn-clear" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row" >
                                            <div class="span4">
                                                <p class="tertiary-info-secondary-text">IP</p>
                                                <div class="input-control text">
                                                    <input type="text" name="maintainerBean.camera.camera.ip" id="ip" autofocus="" placeholder="IP">
                                                    <button type="button" class="btn-clear" />
                                                </div>
                                            </div>
                                            <div class="span2">
                                                <p class="tertiary-info-secondary-text">Port</p>
                                                <div class="input-control text">
                                                    <input type="text" name="administrationBean.camera.camera.port" id="port" autofocus="" placeholder="Puerto">
                                                    <button type="button" class="btn-clear" />
                                                </div>
                                            </div>
                                            <div class="span2">
                                                <p class="tertiary-info-secondary-text">Tipo</p>
                                                <div class="input-control select">
                                                    <s:select name="administrationBean.camera.camera.cameraTypeEnum" headerKey="-1" headerValue="Seleccione tipo de camara" id="cameraTypeEnumId" list="administrationBean.cameraTypeEnumList" listKey="id" listValue="name"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row" >
                                            <div class="span7">
                                                <p class="tertiary-info-secondary-text">Nombre Camara</p>
                                                <div class="input-control text">
                                                    <input type="text" name="administrationBean.camera.camera.name" id="nameCamera" autofocus="" placeholder="Nombre Camara">
                                                    <button type="button" class="btn-clear" />
                                                </div>
                                            </div>
                                            <div class="span1">
                                                <p class="tertiary-info-secondary-text">Activo</p>
                                                <label class="input-control switch" >
                                                    <input type="checkbox" name="administrationBean.camera.active" id="active" checked="true" />
                                                    <span class="helper"></span>
                                                </label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="span4">
                                                <p class="tertiary-info-secondary-text">Controles</p>
                                                <i id="up" class="icon-arrow-up" style="font-size: 20px;"> </i>
                                                <i id="up-right" class="icon-arrow-up-right" style="font-size: 20px;"> </i>
                                                <i id="right" class="icon-arrow-right" style="font-size: 20px;"> </i>
                                                <i id="down-right" class="icon-arrow-down-right" style="font-size: 20px;"> </i>
                                                <i id="down" class="icon-arrow-down" style="font-size: 20px;"> </i>
                                                <i id="down-left" class="icon-arrow-down-left" style="font-size: 20px;"> </i>
                                                <i id="left" class="icon-arrow-left" style="font-size: 20px;"> </i>
                                                <i id="up-left" class="icon-arrow-up-left" style="font-size: 20px;"> </i>

                                                <p class="tertiary-info-secondary-text">Reflejos</p>
                                                <label class="input-control checkbox" onclick="">
                                                    <input type="checkbox" name="administrationBean.camera.camera.horizontally" id="flipHorizontally" checked="true" />
                                                    <span class="helper">Horizontal</span>
                                                </label>

                                                <label class="input-control checkbox" onclick="">
                                                    <input type="checkbox" name="administrationBean.camera.camera.vertically" id="flipVertically" checked="true" />
                                                    <span class="helper">Vertical</span>
                                                </label>

                                                <p class="tertiary-info-secondary-text">Guardar posiciones favoritas</p>
                                                <i class="icon-save" id="save-1" style="font-size: 20px;"> 1</i>
                                                <i class="icon-save" id="save-2" style="font-size: 20px;"> 2</i>
                                                <i class="icon-save" id="save-3" style="font-size: 20px;"> 3</i>
                                                <i class="icon-save" id="save-4" style="font-size: 20px;"> 4</i>

                                                <p class="tertiary-info-secondary-text">Ver posiciones favoritas</p>
                                                <i class="icon-eye" id="view-1" style="font-size: 20px;"> 1</i>
                                                <i class="icon-eye" id="view-2" style="font-size: 20px;"> 2</i>
                                                <i class="icon-eye" id="view-3" style="font-size: 20px;"> 3</i>
                                                <i class="icon-eye" id="view-4" style="font-size: 20px;"> 4</i>


                                            </div>
                                            <div class="span4">
                                                <p class="tertiary-info-secondary-text">Vista</p>
                                                <canvas id='cameraView' width='320px' height='240px' style='border:1px solid #d3d3d3'  ></canvas>
                                            </div>
                                        </div>

                                    </fieldset>

                                    <fieldset>
                                        <div class="row">
                                            <div class="span8">
                                                <input type="button" value="Cancelar" >
                                                <input type="button" value="Volver" class="bg-color-orange place-right" >
                                                <input type="button" value="Guardar" class="standart default place-right" disabled  onclick="">
                                            </div>
                                        </div>
                                    </fieldset>

                                </div>
                            </div>
                        </div>
                        <div class="frame" id="cameraListFrame">
                            <%--<jsp:include page="/jsp/backoffice/maintainer/jspf/deviceList.jsp" flush="true"/>--%>
                        </div>
                        <div class="frame" id="helpFrame">
                            <h2>Ayuda</h2>
                            <p>Administracion de camaras.</p>
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
