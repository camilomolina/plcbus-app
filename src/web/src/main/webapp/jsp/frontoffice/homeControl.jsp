<%@ page import="cl.bennu.plcbus.common.enums.WeatherYahooEnum" %>
<%@ page import="cl.bennu.plcbus.constant.Constant" %>
<%@ page import="cl.bennu.plcbus.common.domain.weather.Weather" %>
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

    <link href="/plcbus/css/plcbus.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/plcbus/js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="/plcbus/js/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="/plcbus/js/jquery-ui-1.10.3.custom.min.js"></script>

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
    <script type="text/javascript" src="/plcbus/js/frontoffice/homeControl.js"></script>
    <script type="text/javascript" src="/plcbus/js/backoffice/administration/camera.js"></script>

    <title>PLCBus</title>

    <script type="text/javascript">
        var MAP_ID = null;
        var MAP_ID_ARRAY = [];
        <s:iterator value="controlBean.level.mapList" status="status" >
            <s:if test="#status.index == 0">
                MAP_ID = <s:property value="id"/>;
            </s:if>
            MAP_ID_ARRAY.push(<s:property value="id"/>);
        </s:iterator>

        function changeStatusDevice(deviceType, deviceId, deviceCode, isTable) {
            if (isTable) {
                changeDevice(deviceType, deviceId, deviceCode);
            } else {
                $("#deviceTypeTMP").val(deviceType);
                $("#deviceIdTMP").val(deviceId);
                $("#deviceCodeTMP").val(deviceCode);

                if (deviceType == 'micro_sprinkler'
                        || deviceType == 'underground'
                        || deviceType == 'diffuser'
                        || deviceType == 'sprinkler'
                        ) {
                    if ($("#" + MAP_ID_ARRAY[0] + "_" + deviceCode + "_Hidden").val() == 'false') {
                        var f = <%=WeatherYahooEnum.rain(((Weather)session.getAttribute(Constant.WEATHER)).getWeatherYahooEnum())%>;
                        if (f) {
                            $("#irrigationSpan").show();
                        } else {
                            changeDevice(deviceType, deviceId, deviceCode);
                        }
                    } else {
                        changeDevice(deviceType, deviceId, deviceCode);
                    }
                } else if (deviceType == 'dimmer') {
                    $.ajax({
                        url: "/plcbus/frontoffice/control/getDevice.do",
                        method: "POST",
                        data: "controlBean.deviceId=" + deviceId,
                        dataType: "json",
                        success: function (json) {
                            $("#dimmer").children(".complete").css("height", json.dimmer);
                            $("#dimmer").children(".marker").css("top", 88 - json.dimmer);
                            $("#delay").children(".complete").css("height", json.delay);
                            $("#delay").children(".marker").css("top", 88 - json.delay);
                            $("#dimmer").attr("data-param-init-value", json.dimmer);
                            $("#delay").attr("data-param-init-value", json.delay);
                            $("#dimmerSpan").show();
                        }
                    });
                } else if (deviceType == 'roller' || deviceType == 'metallic') {
                    //$("#curtainSpan").show();
                    changeDevice(deviceType, deviceId, deviceCode);
                } else if (deviceType == 'ip') {
                    var f = "camera_" + deviceCode + "()";
                    eval(f);
                } else {
                    changeDevice(deviceType, deviceId, deviceCode);
                }
            }
        }

        function changeDevice(deviceType, deviceId, deviceCode) {
            if ($("#" + MAP_ID_ARRAY[0] + "_" + deviceCode + "_Hidden").val() == 'true') {
                changeDeviceIconOff(deviceType, deviceCode);
                Control.offDevice(deviceId, true);
            } else {
                changeDeviceIconOn(deviceType, deviceCode, false);
                Control.onDevice(deviceId, true);
            }
        }

        function changeDeviceIconOff(deviceType, deviceCode) {
            var deviceHTMLId = "#statusCheckbox_" + deviceCode;
            for (var i = 0; i < MAP_ID_ARRAY.length; i++) {
                $("#" + MAP_ID_ARRAY[i] + "_" + deviceCode + "_Hidden").val("false");
                $("#" + MAP_ID_ARRAY[i] + "_" + deviceCode + "_Div").attr('class', deviceType + '_false');
                if (i == 0) {
                    $(deviceHTMLId).removeAttr("checked");
                }
            }
        }

        function changeDeviceIconOn(deviceType, deviceCode, alarmed) {
            var deviceHTMLId = "#statusCheckbox_" + deviceCode;
            var deviceStatus = "_true";
            if (alarmed) {
                deviceStatus = "_alert";
            }

            for (var i = 0; i < MAP_ID_ARRAY.length; i++) {
                $("#" + MAP_ID_ARRAY[i] + "_" + deviceCode + "_Hidden").val("true");
                $("#" + MAP_ID_ARRAY[i] + "_" + deviceCode + "_Div").attr('class', deviceType + deviceStatus);
                if (i == 0) {
                    $(deviceHTMLId).prop("checked", "checked");
                }
            }
        }

        function statusDevice(deviceType, deviceId, deviceCode) {
            var deviceHTMLId = "#statusDevice_" + deviceCode;
            var deviceIHTMLId = "#statusDeviceI_" + deviceCode;
            var deviceCheckHTMLId = "#statusCheckbox_" + deviceCode;
            var deviceCheckHTMLId2 = "statusCheckbox_" + deviceCode;

            $(deviceIHTMLId).attr('class', 'icon-loading');
            $(deviceIHTMLId).attr('disabled', true);

            $.ajax({
                url: "/plcbus/frontoffice/control/status.do",
                method: "POST",
                data: "controlBean.deviceId=" + deviceId,
                dataType: "json",
                success: function (json) {
                    //$(deviceCheckHTMLId).attr('checked', json.statusResponse);
                    document.getElementById(deviceCheckHTMLId2).checked = json.statusResponse;
                    if (json.statusResponse) {
                        changeDeviceIconOn(deviceType, deviceCode, false);
                    } else {
                        changeDeviceIconOff(deviceType, deviceCode);
                    }

                    $(deviceIHTMLId).attr('class', 'icon-eye');
                    $(deviceIHTMLId).attr('disabled', false);
                }
            });
        }

        function calculateDivPosition(mapId) {
            var w = 640;
            var h = 480;
            if (mapId == null) {
                mapId = MAP_ID;
            } else {
                MAP_ID = mapId;
            }

            <s:iterator value="controlBean.level.mapList" status="status" >
                if (mapId == <s:property value="id" />) {
                    w = $("#planner<s:property value="id"/>Img").width();
                    h = $("#planner<s:property value="id"/>Img").height();
                }
            </s:iterator>

            var wResult = 0;
            var hResult = 0;


            //$("#dimmerSpan").attr("style", "position:absolute;top:" + hResult + "px;left:" + wResult + "px;");

            <s:iterator value="controlBean.level.mapList" var="map">
                <s:iterator value="coordinateList" >
                    wResult = w / 100 * $("#<s:property value="map.id"/>_<s:property value="device.code"/>_Div").attr("sizex");
                    hResult = h / 100 * $("#<s:property value="map.id"/>_<s:property value="device.code"/>_Div").attr("sizey");
                    $("#<s:property value="map.id"/>_<s:property value="device.code"/>_Div").attr("style", "position:absolute;top:" + hResult + "px;left:" + wResult + "px;display:;");
                </s:iterator>
            </s:iterator>
        }

        function dimmerDiv() {
            var dimmerNumber = Number($('#dimmerHidden').val());
            Control.dimmerDevice($('#deviceIdTMP').val(), $('#delayHidden').val(), $('#dimmerHidden').val());
            if (dimmerNumber.toFixed(0) > 0) {
                changeDeviceIconOn($('#deviceTypeTMP').val(), $('#deviceCodeTMP').val(), false);
            } else {
                changeDeviceIconOff($('#deviceTypeTMP').val(), $('#deviceCodeTMP').val());
            }
            $('#dimmerSpan').hide();
        }

        function dimmerOffDiv() {
            Control.dimmerDevice($('#deviceIdTMP').val(), $('#delayHidden').val(), 0);
            changeDeviceIconOff($('#deviceTypeTMP').val(), $('#deviceCodeTMP').val());

            $('#dimmerSpan').hide();
        }


        function curtainDiv(mode) {
            if (mode == 'on') {
                $("#curtainOpenText").show();
                Control.onDevice($('#deviceIdTMP').val(), false);
                changeDeviceIconOn($('#deviceTypeTMP').val(), $('#deviceCodeTMP').val(), false);
                $("#curtainOpenText").delay(30000).slideUp();
            } else {
                $("#curtainCloseText").show();
                Control.offDevice($('#deviceIdTMP').val(), false);
                changeDeviceIconOff($('#deviceTypeTMP').val(), $('#deviceCodeTMP').val());
                $("#curtainCloseText").delay(30000).slideUp();
            }
        }

        function irrigationDiv() {
            changeDeviceIconOn($('#deviceTypeTMP').val(), $('#deviceCodeTMP').val(), false);
            Control.onDevice($('#deviceIdTMP').val(), true);
            $("#irrigationSpan").hide();
        }

        $(function(){
            $(window).resize(function(){
                calculateDivPosition(null);
            });
        });

        $(document).ready(function () {
            calculateDivPosition(null);
        });


        $(function(){
            $("#dimmer").on("changed", function(e, val){
                color = 'green';
                if (val > 30) {
                    color = 'orange';
                }
                if (val > 70) {
                    color = 'red';
                }
                $("#dimmerHidden").val(val);
            })
        });

        $(function(){
            $("#delay").on("changed", function(e, val){
                color = 'green';
                if (val > 30) {
                    color = 'orange';
                }
                if (val > 70) {
                    color = 'red';
                }
                $("#delayHidden").val(val);
            })
        });


        // funcionaes de camara
        <s:iterator value="controlBean.deviceList" var="device" id="device">
            <s:if test="deviceTypeEnum.id == 29">

                var hostCamera_<s:property value='code' /> = "<s:property value='camera.ip' />" + ":" + "<s:property value='camera.port' />";
                var cameraW_<s:property value='code' />;
                var cameraW_<s:property value='code' />_status = false;
                function camera_<s:property value='code' />() {
                    if (!cameraW_<s:property value='code' />_status) {
                        cameraW_<s:property value='code' />_status = true;
                        changeDeviceIconOn('<s:include value="/jsp/common/deviceType2.jsp"/>', '<s:property value='code' />', true);
                        document.getElementById('camera_<s:property value='code' />').style.display = "block";
                        cameraW_<s:property value='code' /> = window.setInterval('refreshCamera_<s:property value='code' />()', 100);
                    }
                };

                function cameraOff_<s:property value='code' />() {
                    document.getElementById('camera_<s:property value='code' />').style.display = "none";
                    window.clearInterval(cameraW_<s:property value='code' />);
                    cameraW_<s:property value='code' />_status = false;
                    changeDeviceIconOff('<s:include value="/jsp/common/deviceType2.jsp"/>', '<s:property value='code' />');
                };

                function refreshCamera_<s:property value='code' />(){
                    var ctx = document.getElementById('camera_canvas_<s:property value='code' />').getContext('2d');
                    var img = new Image();
                    img.src = "http://"+hostCamera_<s:property value='code' />+"/snapshot.jpg?user=admin&resolution=1&time=" + new Date().getTime();
                    //img.src = "<%=request.getContextPath()%>/backoffice/administration/commandCamera.do?ip="+hostCamera_<s:property value='code' />+"&port="+hostCamera_<s:property value='code' />+"&time=" + new Date().getTime();
                    img.onload = function() {
                        ctx.drawImage(img, 0, 0);
                    };
                };


            </s:if>
        </s:iterator>

        $(function() {
            <s:iterator value="controlBean.deviceList" var="device" id="device">
                <s:if test="deviceTypeEnum.id == 29">
                    $("#camera_<s:property value='code' />").draggable();
                </s:if>
            </s:iterator>
            $("#dimmerSpan").draggable();
            $("#curtainSpan").draggable();
            $("#irrigationSpan").draggable();
        });

    </script>
</head>
<body class="metrouicss" onload="calculateDivPosition(null);">
<form action="/plcbus/frontoffice/home/start.do" id="homeControlId" >
    <jsp:include page="/jsp/common/menu.jsp"/>

    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Controles
                    <small><s:property value="controlBean.level.name"/></small>
                </h1>
                <a href="/plcbus/frontoffice/control/startControlByLevel.do" class="back-button big page-back"></a>
            </div>
        </div>

        <div class="page-region">
            <div class="page-region-content">

                <div class="page-control" data-role="page-control">
                    <span class="menu-pull"></span>
                    <div class="menu-pull-bar"></div>

                    <ul>
                        <s:iterator value="controlBean.level.mapList" status="st">
                            <li <s:if test="#st.index == 0">class="active"</s:if> onclick="calculateDivPosition(<s:property value="id" />);"><a href="#planner<s:property value="id" />Frame"><s:property value="name" /></a></li>
                        </s:iterator>
                        <li><a href="#tableFrame">Tabla</a></li>
                    </ul>

                    <input type="hidden" name="deviceTypeTMP" id="deviceTypeTMP" />
                    <input type="hidden" name="deviceIdTMP" id="deviceIdTMP" />
                    <input type="hidden" name="deviceCodeTMP" id="deviceCodeTMP" />

                    <div class="dialog" id="dimmerSpan" style="display: none;position:absolute;top:30%;left:30%;background-color:rgba(255, 255, 255, 255);">
                        <div class="header"><span>Dimmer</span>
                            <div><button type="button" onclick="$('#dimmerSpan').hide();"><i class="icon-cancel-2"></i></button></div>
                        </div>


                        <div class="content">
                            <div id="dimmer" class="slider vertical place-left" style="height: 100px;" data-param-accuracy="0" data-param-init-value="30"></div>
                            <input type="hidden" name="dimmerHidden" id="dimmerHidden" />

                            <div id="delay" class="slider vertical place-left" style="height: 100px;" data-param-accuracy="0" data-param-init-value="30"></div>
                            <input type="hidden" name="delayHidden" id="delayHidden" />

                            <br/>

                            <input type="button" class="mini" value="Dimmer" onclick="dimmerDiv();"/>
                            <input type="button" class="mini" value="Off" onclick="dimmerOffDiv();"/>
                        </div>
                    </div>

                    <div class="dialog" id="curtainSpan" style="display: none;position:absolute;top:30%;left:30%;background-color:rgba(255, 255, 255, 255);">
                        <div class="header"><span>Cortina</span>
                            <div><button type="button" onclick="$('#curtainSpan').hide();"><i class="icon-cancel-2"></i></button></div>
                        </div>


                        <div class="content">
                            <i class="icon-arrow-up-3" id="iconOpenId" style="color: green;" onclick="curtainDiv('on');"></i>
                            <span id="curtainOpenText" style="display: none;">
                                Subiendo cortina.. <i class="icon-stop" style="font: 32px; color: red;" onclick="Control.onDevice($('#deviceIdTMP').val(), false);$('#curtainOpenText').hide();"></i>
                            </span>
                            <br/>
                            <br/>
                            <i class="icon-arrow-down-3" id="iconCloseId" style="color: green;" onclick="curtainDiv('off');" ></i>
                            <span id="curtainCloseText" style="display: none;">
                                Bajando cortina.. <i class="icon-stop" style="font: 32px; color: red;" onclick="Control.offDevice($('#deviceIdTMP').val(), false);$('#curtainCloseText').hide();"></i>
                            </span>
                            <br/>
                            <br/>
                        </div>
                    </div>

                    <div class="dialog" id="irrigationSpan" style="display: none;position:absolute;top:30%;left:30%;background-color:rgba(255, 255, 255, 255);">
                        <div class="header"><span>Riego</span>
                            <div><button type="button" onclick="$('#irrigationSpan').hide();"><i class="icon-cancel-2"></i></button></div>
                        </div>


                        <div class="content">
                            <br/>
                                De acuerdo a la situacion climatica no se ve necesario activar los sistemas de riego.
                            <br/>
                            <br/>

                            <input type="button" class="mini" value="Activar Riego" onclick="irrigationDiv();"/>
                        </div>
                    </div>

                    <s:iterator value="controlBean.deviceList" var="device" id="device">
                        <s:if test="deviceTypeEnum.id == 29">

                            <div class="dialog" id="camera_<s:property value="code" />" style="display: none;position:absolute;top:30%;left:30%;background-color:rgba(255, 255, 255, 255);">
                                <div class="header"><span>[<s:property value='code' />] Camara IP "<s:property value='name' />"</span>
                                    <div><button type="button" onclick="cameraOff_<s:property value='code' />();"><i class="icon-cancel-2"></i></button></div>
                                </div>

                                <div class="content">
                                    <br/>
                                    <canvas id='camera_canvas_<s:property value='code' />' width='320px' height='240px' style='border:1px solid #d3d3d3'  >
                                    </canvas>
                                    <br/>

                                    <div class="toolbar ">
                                        <button type="button" onmousedown="Camera.cameraLeft('<s:property value='code' />')" onmouseup="Camera.cameraCommandOff('<s:property value='code' />');">
                                            <i class="icon-arrow-left" ></i>
                                        </button>
                                        <button type="button" onmousedown="Camera.cameraUp('<s:property value='code' />')" onmouseup="Camera.cameraCommandOff('<s:property value='code' />');">
                                            <i class="icon-arrow-up" ></i>
                                        </button>
                                        <button type="button" onmousedown="Camera.cameraDown('<s:property value='code' />')" onmouseup="Camera.cameraCommandOff('<s:property value='code' />');">
                                            <i class="icon-arrow-down" ></i>
                                        </button>
                                        <button type="button" onmousedown="Camera.cameraRight('<s:property value='code' />')" onmouseup="Camera.cameraCommandOff('<s:property value='code' />');">
                                            <i class="icon-arrow-right" ></i>
                                        </button>


                                        <button type="button" class="mini" onclick="Camera.viewCameraPosition('<s:property value='code' />', 1);" >1</button>
                                        <button type="button" class="mini" onclick="Camera.viewCameraPosition('<s:property value='code' />', 2);" >2</button>
                                        <button type="button" class="mini" onclick="Camera.viewCameraPosition('<s:property value='code' />', 3);" >3</button>
                                        <button type="button" class="mini" onclick="Camera.viewCameraPosition('<s:property value='code' />', 4);" >4</button>
                                        <button type="button" class="mini" onclick="" >-</button>

                                        <button type="button" onclick="cameraOff_<s:property value='code' />();">
                                            <i class="icon-cancel" ></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </s:if>
                    </s:iterator>


                    <div class="frames">
                        <s:iterator value="controlBean.level.mapList" var="map" status="st">
                            <div class="frame <s:if test="#st.index == 0">active</s:if>" id="planner<s:property value="id" />Frame">
                                <div class="span10" style="position:relative;">
                                    <img alt="<s:property value="info" />" id="planner<s:property value="id" />Img" src="<s:property value="path" />">

                                    <s:iterator value="coordinateList" >
                                        <abbr title="<s:property value="device.code"/>">
                                            <input type="hidden" name="<s:property value="map.id"/>_<s:property value="device.code"/>" id="<s:property value="map.id"/>_<s:property value="device.code"/>_Hidden" value="<s:property value="device.status"/>">

                                            <div style="display: none;"
                                                 <s:if test="device.alarmed">class="<s:include value="/jsp/common/deviceType.jsp"/>_alert"</s:if>
                                                 <s:else>class="<s:include value="/jsp/common/deviceType.jsp"/>_<s:property value="device.status"/>"</s:else>
                                                 status="<s:property value="device.status"/>"
                                                 sizex="<s:property value="x"/>"
                                                 sizey="<s:property value="y"/>"
                                                 id="<s:property value="map.id"/>_<s:property value="device.code"/>_Div"
                                                 onclick="changeStatusDevice('<s:include value="/jsp/common/deviceType.jsp"/>', <s:property value="device.id"/>, '<s:property value="device.code"/>', false);">
                                            </div>
                                        </abbr>
                                    </s:iterator>
                                </div>
                            </div>
                        </s:iterator>

                        <div class="frame" id="tableFrame">
                            <div class="span10">

                                <h2>Lista</h2>

                                <table class="striped">
                                    <thead>
                                    <tr>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <!--<th class="right">Ultima actualizaci&oacute;n</th>-->
                                        <th class="right">Nivel de senal</th>
                                        <th class="right">Nivel de ruido</th>
                                        <!--<th class="center">Estado</th>-->
                                        <th>R</th>
                                        <th>On/Off</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <s:iterator value="controlBean.deviceList" var="device" id="device">

                                        <tr>
                                            <td><s:property value="code" /></td>
                                            <td><s:property value="name" /></td>
                                            <!--<td class="right">00:20</td>-->
                                            <td class="right"><s:property value="signal" /></td>
                                            <td class="right"><s:property value="noize" /></td>
                                            <!--<td class="center">Activa</td>-->
                                            <td class="center" id="statusDevice_<s:property value="code" />" >
                                                <i class="icon-eye" style="cursor: pointer;" id="statusDeviceI_<s:property value="code" />" onclick="statusDevice('<s:include value="/jsp/common/deviceType2.jsp"/>', <s:property value="id" />, '<s:property value="code" />');" ></i>
                                            </td>
                                            <td>
                                                <label class="input-control switch" >
                                                    <input type="checkbox" name="controlBean.device" id="statusCheckbox_<s:property value="code" />" <s:if test="status" >checked="true" </s:if>  onchange="changeStatusDevice('<s:include value="/jsp/common/deviceType2.jsp"/>', <s:property value="id" />, '<s:property value="code" />', true);" <s:if test="deviceTypeEnum.generalDeviceTypeEnum.id == 5"> disabled </s:if><s:if test="deviceTypeEnum.generalDeviceTypeEnum.id == 6"> disabled </s:if><s:if test="deviceTypeEnum.generalDeviceTypeEnum.id == 7"> disabled </s:if> />
                                                    <span class="helper"></span>
                                                </label>
                                            </td>
                                        </tr>
                                    </s:iterator>
                                    </tbody>

                                    <tfoot></tfoot>
                                </table>

                            </div>
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
