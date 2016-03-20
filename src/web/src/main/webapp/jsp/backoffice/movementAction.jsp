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
    <script type="text/javascript" src="/plcbus/js/backoffice/movementAction.js"></script>

    <title>PLCBus</title>

</head>
<body class="metrouicss">
<form action="/plcbus/backoffice/configuration/startMovementAction.do" id="homeControlId">
    <jsp:include page="/jsp/common/menu.jsp"/>

    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Configuraci&oacute;n
                    <small>programaci&oacute;n</small>
                </h1>
                <a href="/plcbus/backoffice/configuration/startConfiguration.do" class="back-button big page-back"></a>
            </div>
        </div>

        <input type="hidden" id="movementActionId" name="configurationBean.movementActionId" />

        <div class="page-region">
            <div class="page-region-content">

                <div class="page-control" data-role="page-control">

                    <span class="menu-pull"></span>
                    <div class="menu-pull-bar"></div>

                    <ul>
                        <li class="active"><a href="#movementActionTable">Nueva accion de movimiento</a></li>
                        <li><a href="#movementAction">Acciones Movimiento activas</a></li>
                    </ul>

                    <div class="frames">
                        <div class="frame active" id="movementActionTable">
                            <div class="grid">

                                <fieldset>
                                    <legend>Accion Movimiento</legend>


                                    <div class="row">
                                        <div class="span4">
                                            <p class="tertiary-info-secondary-text">Dispositivo Movimiento</p>
                                            <div class="input-control select">
                                                <s:select name="configurationBean.movementAction.device.id" headerKey="-1" headerValue="Seleccione dispositivo" id="movementDeviceId" list="configurationBean.deviceMovementList" listKey="id" listValue="%{code + ' - ' + name}"/>
                                            </div>
                                        </div>
                                        <div class="span3">
                                            <p class="tertiary-info-secondary-text">Tipo</p>
                                            <div class="span3">
                                                <div class="input-control select">
                                                    <s:select name="configurationBean.movementAction.movementTypeEnum" headerKey="-1" headerValue="Seleccione tipo" id="movementTypeId" list="configurationBean.movementTypeList" listKey="id" listValue="name"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="span1">
                                            <p class="tertiary-info-secondary-text">Activo</p>
                                            <label class="input-control switch" >
                                                <input type="checkbox" name="configurationBean.movementAction.active" id="active" checked="true" />
                                                <span class="helper"></span>
                                            </label>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="span4">
                                            <p class="tertiary-info-secondary-text">Motivo</p>
                                            <div class="input-control textarea">
                                                <textarea name="configurationBean.movementAction.reason" id="reason" placeholder="Escriba el motivo de la accion del sensor..."></textarea>
                                            </div>
                                        </div>

                                        <div class="span2">
                                            <p class="tertiary-info-secondary-text">Desde</p>
                                            <div class="span1">
                                                <div class="input-control select">
                                                    <s:select name="configurationBean.movementAction.timeSummary.startHourEnum" id="startHourEnum" list="configurationBean.hourList" listKey="id" listValue="name"/>
                                                </div>
                                            </div>

                                            <div class="span1">
                                                <div class="input-control select">
                                                    <s:select name="configurationBean.movementAction.timeSummary.startMinuteEnum" id="startMinuteEnum" list="configurationBean.minuteList" listKey="id" listValue="name"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="span2">
                                            <p class="tertiary-info-secondary-text">Hasta</p>
                                            <div class="span1">
                                                <div class="input-control select">
                                                    <s:select name="configurationBean.movementAction.timeSummary.endHourEnum" id="endHourEnum" list="configurationBean.hourList" listKey="id" listValue="name"/>
                                                </div>
                                            </div>

                                            <div class="span1">
                                                <div class="input-control select">
                                                    <s:select name="configurationBean.movementAction.timeSummary.endMinuteEnum" id="endMinuteEnum" list="configurationBean.minuteList" listKey="id" listValue="name"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="span4">
                                            <p class="tertiary-info-secondary-text">Dispositivo</p>
                                            <div class="input-control select">
                                                <s:select name="configurationBean.movementActionDetail.device.id" headerKey="-1" headerValue="Seleccione dispositivo" id="deviceId" list="configurationBean.deviceList" listKey="id" listValue="%{code + ' - ' + name}" />
                                            </div>
                                        </div>
                                        <div class="span4">
                                            <p class="tertiary-info-secondary-text">Accion</p>
                                            <div class="span3">
                                                <div class="input-control select">
                                                    <s:select name="configurationBean.movementActionTypeList" headerKey="-1" headerValue="Seleccione accion" id="movementActionTypeId"  list="configurationBean.movementActionTypeList" listKey="id" listValue="name" onclick="MovementAction.changeMovementActionType();" />
                                                </div>
                                            </div>
                                            <div class="span1">
                                                <input type="button" value="+" class="standart default place-right mini" onclick="MovementAction.add();">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div id="movementActionDetailList">
                                            <jsp:include page="/jsp/backoffice/jspf/movementActionDetailList.jsp" flush="true"/>
                                        </div>
                                    </div>

                                </fieldset>

                                <fieldset>
                                    <div class="row">
                                        <div class="span8">
                                            <input type="button" value="Cancelar" >
                                            <input type="button" value="Volver" class="bg-color-orange place-right">
                                            <input type="button" value="Guardar" class="standart default place-right" onclick="MovementAction.save();" >
                                        </div>
                                    </div>
                                </fieldset>

                            </div>
                        </div>
                        <div class="frame" id="movementAction">
                            <div id="movementActionListFrame">
                                <jsp:include page="/jsp/backoffice/jspf/movementActionList.jsp" flush="true"/>
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
