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

    <link href="/plcbus/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">

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

    <script type="text/javascript" src="/plcbus/js/bootstrap-datetimepicker.min.js"></script>

    <script type="text/javascript" src="/plcbus/js/common/generic.js"></script>
    <script type="text/javascript" src="/plcbus/js/backoffice/scheduledShutdownDevice.js"></script>

    <title>PLCBus</title>

</head>
<body class="metrouicss">
<form action="/plcbus/backoffice/configuration/startDeviceOff.do" id="homeControlId">
    <jsp:include page="/jsp/common/menu.jsp"/>

    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Configuraci&oacute;n
                    <small>apagado automatico</small>
                </h1>
                <a href="/plcbus/backoffice/configuration/startConfiguration.do" class="back-button big page-back"></a>
            </div>
        </div>


        <div class="page-region">
            <div class="page-region-content">

                <div class="page-control" data-role="page-control">

                    <span class="menu-pull"></span>
                    <div class="menu-pull-bar"></div>

                    <ul>
                        <li class="active"><a href="#scheduledShutdownDeviceTable">Nueva configuracion de apagado</a></li>
                        <li><a href="#scheduledShutdownDevice">Dispositivos configurados</a></li>
                    </ul>

                    <div class="frames">
                        <div class="frame active" id="scheduledShutdownDeviceTable">
                            <div class="grid">
                                <div class="span8">
                                    <fieldset>
                                        <legend>Configuracion</legend>

                                        <div class="row">
                                            <!--<p class="tertiary-info-secondary-text">Se programara de forma diaria entre los horarios</p>-->
                                            <div class="span4">
                                                <p class="tertiary-info-secondary-text">Dispositivo</p>
                                                <div class="input-control select">
                                                    <s:select name="configurationBean.programmingSummary.deviceId" headerKey="-1" headerValue="Seleccione dispositivo" id="deviceId" list="configurationBean.deviceList" listKey="id" listValue="%{code + ' - ' + name}"/>
                                                </div>
                                            </div>
                                            <div class="span1"></div>
                                            <div class="span2">
                                                <p class="tertiary-info-secondary-text">Horario</p>
                                                <div class="span1">
                                                    <div class="input-control select">
                                                        <s:select name="configurationBean.programmingSummary.timeSummary.endHourEnum" id="endHourEnum" list="configurationBean.hourList" listKey="id" listValue="name"/>
                                                    </div>
                                                </div>

                                                <div class="span1">
                                                    <div class="input-control select">
                                                        <s:select name="configurationBean.programmingSummary.timeSummary.endMinuteEnum" id="endMinuteEnum" list="configurationBean.minuteList" listKey="id" listValue="name"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>

                                    <fieldset>
                                        <div class="row">
                                            <div class="span8">
                                                <input type="button" value="Cancelar" >
                                                <input type="button" value="Volver" class="bg-color-orange place-right">
                                                <input type="button" value="Guardar" class="standart default place-right" onclick="ScheduledShutdownDevice.save();" >
                                            </div>
                                        </div>
                                    </fieldset>

                                </div>
                            </div>
                        </div>
                        <div class="frame" id="scheduledShutdownDevice">
                            <div id="scheduledShutdownDeviceListFrame">
                                <jsp:include page="/jsp/backoffice/jspf/scheduledShutdownDeviceList.jsp" flush="true"/>
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
