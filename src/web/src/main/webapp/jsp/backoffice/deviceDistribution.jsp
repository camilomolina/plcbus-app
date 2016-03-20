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

    <script type="text/javascript">
        $(document).ready(function() {
            calculateDivPosition(null);
        });
    </script>
    <title>PLCBus</title>

    <script type="text/javascript">
        var MAP_ID = null;
        var MAP_ID_ARRAY = [];
        <s:iterator value="deviceDistributionBean.level.mapList" status="status" >
            <s:if test="#status.index == 0">
                MAP_ID = <s:property value="id"/>;
            </s:if>
            MAP_ID_ARRAY.push(<s:property value="id"/>);
        </s:iterator>

        function deleteCoordinate(id, deviceName) {
            $.Dialog({
                'title': 'Quitar',
                'content': 'Se eliminara la posicion del dispositivo "' + deviceName + "', del mapa",
                'buttons'    : {
                    'Ok'    : {
                        'action': function() {
                            $.ajax({
                                url: "/plcbus/backoffice/configuration/deleteCoordenate.do",
                                method: "POST",
                                data: "deviceDistributionBean.coordinateId=" + id,
                                dataType: "json",
                                success: function (json) {
                                    $("#homeControlId").submit();
                                }
                            });
                        }
                    },
                    'Cancel'     : {
                        'action': function() {}
                    }
                }
            });
        }

        function changeCoordinateClassOver(id, deviceTypeEnum) {
            $("#"+id).attr("class", deviceTypeEnum + "_m_del");
        }

        function changeCoordinateClassOut(id, deviceTypeEnum) {
            $("#"+id).attr("class", deviceTypeEnum + "_false");
        }

        function calculateDivPosition(mapId) {
            var w = 640;
            var h = 480;
            if (mapId == null) {
                mapId = MAP_ID;
            } else {
                MAP_ID = mapId;
            }

            <s:iterator value="deviceDistributionBean.level.mapList" status="status" >
                if (mapId == <s:property value="id" />) {
                    w = $("#planner<s:property value="id"/>Img").width();
                    h = $("#planner<s:property value="id"/>Img").height();
                }
            </s:iterator>

            var wResult = 0;
            var hResult = 0;

            <s:iterator value="deviceDistributionBean.level.mapList" var="map" >
                <s:iterator value="coordinateList" >
                    wResult = w / 100 * $("#<s:property value="map.id"/>_<s:property value="device.code"/>_Div").attr("sizex");
                    hResult = h / 100 * $("#<s:property value="map.id"/>_<s:property value="device.code"/>_Div").attr("sizey");
                    $("#<s:property value="map.id"/>_<s:property value="device.code"/>_Div").attr("style", "position:absolute;top:" + hResult + "px;left:" + wResult + "px;");
                </s:iterator>
            </s:iterator>
        }

        $(function(){
            $(window).resize(function(){
                calculateDivPosition(null);
            });

            <s:iterator value="deviceDistributionBean.level.mapList" >
                $("#planner<s:property value="id"/>Img").click(function(e) {
                    var pos = $(this).offset();

                    var w = $(this).width();
                    var h = $(this).height();

                    var posX = (e.pageX - pos.left);
                    var posY = (e.pageY - pos.top);

                    var percentageX = Number(posX * 100 / w);
                    var percentageY = Number(posY * 100 / h);

                    var errorMessage = [];
                    if ($("#deviceId").val() == -1) {
                        errorMessage.push("Debe seleccionar un dospositivo");
                        Generic.errorDialog(errorMessage);
                    } else {
                        $.Dialog({
                            'title': 'Agregar',
                            'content': 'Se ubicara el dispositivo "' + $("#deviceId").find("option:selected").text() + '" en los ejes x = ' + posX + ', y = ' + posY,
                            'buttons'    : {
                                'Ok'    : {
                                    'action': function() {
                                        $("#mapId").val(<s:property value="id"/>);

                                        var parameters = {
                                            "deviceDistributionBean.coordinate.map.id": <s:property value="id"/>
                                            , "deviceDistributionBean.coordinate.device.id": $("#deviceId").val()
                                            , "deviceDistributionBean.coordinate.x": percentageX.toFixed(0)
                                            , "deviceDistributionBean.coordinate.y": percentageY.toFixed(0)
                                            , "deviceDistributionBean.clone": $("#clone").val()
                                        };

                                        $.ajax({
                                            url: "/plcbus/backoffice/configuration/saveCoordenate.do",
                                            method: "POST",
                                            data: parameters,
                                            dataType: "json",
                                            success: function (json) {
                                                $("#homeControlId").submit();
                                            }
                                        });
                                    }
                                },
                                'Cancel'     : {
                                    'action': function() {}
                                }
                            }
                        });
                    }
                });
            </s:iterator>
        });

    </script>
</head>
<body class="metrouicss" >
<form action="/plcbus/backoffice/configuration/startLightDistribution.do" id="homeControlId">
    <s:hidden name="deviceDistributionBean.levelId" id="levelId"/>
    <s:hidden name="deviceDistributionBean.mapId" id="mapId" />

    <jsp:include page="/jsp/common/menu.jsp"/>


    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Distribucion
                    <small><s:property value="deviceDistributionBean.level.name"/></small>
                </h1>
                <a href="/plcbus/backoffice/configuration/startConfiguration.do" class="back-button big page-back"></a>
            </div>
        </div>


        <div class="page-region">
            <div class="page-region-content">

                <div class="page-control" data-role="page-control">

                    <span class="menu-pull"></span>
                    <div class="menu-pull-bar"></div>

                    <div class="row">
                        <pre>Ubicacion de cada dispositivo en los mapas, indica el dispositivo y presiona la ubicacion sobre el mapa</pre>
                    </div>

                    <div class="row">
                        <div class="span4">
                            <p class="tertiary-info-secondary-text">Dispositivo</p>
                            <div class="input-control select">
                                <s:select name="deviceDistributionBean.coordinate.device.Id" headerKey="-1" headerValue="Seleccione dispositivo" id="deviceId" list="deviceDistributionBean.deviceList" listKey="id" listValue="%{code + ' - ' + name}"/>
                            </div>
                        </div>
                        <div class="span1">
                            <p class="tertiary-info-secondary-text">Clonar</p>
                            <label class="input-control switch" >
                                <input type="checkbox" name="deviceDistributionBean.clone" id="clone" disabled />
                                <span class="helper"></span>
                            </label>
                        </div>
                    </div>

                    <ul>
                        <s:iterator value="deviceDistributionBean.level.mapList" >
                            <s:if test="id == deviceDistributionBean.mapId">
                                <li onclick="calculateDivPosition(<s:property value="id" />);" class="active"><a href="#planner<s:property value="id" />Frame"><s:property value="name" /></a></li>
                            </s:if>
                            <s:else>
                                <li onclick="calculateDivPosition(<s:property value="id" />);"><a href="#planner<s:property value="id" />Frame"><s:property value="name" /></a></li>
                            </s:else>
                        </s:iterator>
                    </ul>

                    <div class="frames">
                        <s:iterator value="deviceDistributionBean.level.mapList" var="map" >
                            <div class="frame" id="planner<s:property value="id" />Frame">
                                <div class="span10" style="position:relative;">
                                    <img alt="<s:property value="info" />" id="planner<s:property value="id" />Img" src="<s:property value="path" />">

                                    <s:iterator value="coordinateList" >
                                        <abbr title="<s:property value="device.code"/>">
                                            <div class="<s:include value="/jsp/common/deviceType.jsp"/>_false"
                                                 sizex="<s:property value="x"/>"
                                                 sizey="<s:property value="y"/>"
                                                 onmouseover="changeCoordinateClassOver('<s:property value="map.id"/>_<s:property value="device.code"/>_Div', '<s:include value="/jsp/common/deviceType.jsp"/>')"
                                                 onmouseout="changeCoordinateClassOut('<s:property value="map.id"/>_<s:property value="device.code"/>_Div', '<s:include value="/jsp/common/deviceType.jsp"/>')"
                                                 onclick="deleteCoordinate(<s:property value="id" />, '<s:property value="device.code"/>');"
                                                 id="<s:property value="map.id"/>_<s:property value="device.code"/>_Div">
                                            </div>
                                        </abbr>
                                    </s:iterator>
                                </div>
                            </div>
                        </s:iterator>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <jsp:include page="/jsp/common/footer.jsp"/>

</form>
</body>
</html>
