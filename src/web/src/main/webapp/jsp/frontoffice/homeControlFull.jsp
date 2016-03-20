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
    <script type="text/javascript" src="/plcbus/js/frontoffice/homeControl.js"></script>

    <title>PLCBus</title>

    <style>
        .light_off {
            background: url('/plcbus/images/icon/light_icon.gif') no-repeat;width: 34px;height: 34px;position:relative;background-position: 0px 0;cursor: pointer;
        }
        .light_on {
            background: url('/plcbus/images/icon/light_icon.gif') no-repeat;width: 34px;height: 34px;position:relative;background-position: -40px 0;cursor: pointer;
        }

    </style>

    <script type="text/javascript">
        function changeLigth(id) {
            if ($("#" + id + "_Hidden").val() == 'on') {
                $("#" + id + "_Hidden").val("off");
                $("#" + id + "_Div").attr('class', 'light_off');
            } else {
                $("#" + id + "_Hidden").val("on");
                $("#" + id + "_Div").attr('class', 'light_on');
            }
        }
    </script>
</head>
<body class="metrouicss">
<form action="/plcbus/frontoffice/home/start.do" id="homeControlId">
    <jsp:include page="/jsp/common/menu.jsp"/>


    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Controles
                    <small>todos los niveles</small>
                </h1>
                <a href="/plcbus/" class="back-button big page-back"></a>
            </div>
        </div>

        <div class="page-region">
            <div class="page-region-content">

                <div class="frame active" id="tableFrame">
                    <div class="span10">

                        <h2>Lista</h2>
                        <pre class="prettyprint linenums">Tabla completa de todos los dispositivos activos, se incluyen todos los niveles y sectores</pre>

                        <table class="striped">
                            <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Codigo</th>
                                <!--<th class="right">Ultima actualizaci&oacute;n</th>-->
                                <th class="right">Nivel de senal</th>
                                <th class="right">Nivel de ruido</th>
                                <!--<th class="center">Estado</th>-->
                                <th>R</th>
                                <th>On/Off</th>
                            </tr>
                            </thead>

                            <tbody>

                            <s:iterator value="controlBean.deviceList">
                                <tr>
                                    <td><s:property value="info" /></td>
                                    <td><s:property value="name" /></td>
                                    <!--<td class="right">00:20</td>-->
                                    <td class="right"><s:property value="signal" /></td>
                                    <td class="right"><s:property value="noize" /></td>
                                    <!--<td class="center">Activa</td>-->
                                    <td class="center" id="statusDevice_<s:property value="name" />" >
                                    <i class="icon-eye" style="cursor: pointer;" id="statusDeviceI_<s:property value="name" />" onclick="Control.statusDevice('<s:property value="name" />', <s:property value="id" />);" ></i>
                                    </td>
                                    <td>
                                        <label class="input-control switch" >
                                            <input type="checkbox" name="controlBean.device" id="statusCheckbox_<s:property value="name" />" <s:if test="status" >checked="true" </s:if>  onchange="Control.changeStatusDevice('<s:property value="name" />', <s:property value="id" />);" />
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

    <jsp:include page="/jsp/common/footer.jsp"/>

</form>
</body>
</html>
