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
    <script type="text/javascript" src="/plcbus/js/backoffice/programming.js"></script>

    <title>PLCBus</title>

</head>
<body class="metrouicss">
<form action="/plcbus/backoffice/configuration/startProgramming.do" id="homeControlId">
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

        <input type="hidden" id="programmingId" name="configurationBean.programmingId" />

        <div class="page-region">
            <div class="page-region-content">

                <div class="page-control" data-role="page-control">

                    <div class="dialog" id="updateCalendarSpan" style="display: none;position:absolute;top:30%;left:30%;background-color:rgba(255, 255, 255, 255);">
                        <div class="header"><span>Actualizar calendario</span>
                            <div><button type="button" onclick="$('#updateCalendarSpan').hide();"><i class="icon-cancel-2"></i></button></div>
                        </div>


                        <div class="content">

                            <div class="span2">
                                <p class="tertiary-info-secondary-text">Desde</p>
                                <div class="span1">
                                    <div class="input-control select">
                                        <s:select name="configurationBean.timeSummary.startHourEnum" id="startHourEnum4UpdateCalendar" list="configurationBean.hourList" listKey="id" listValue="name"/>
                                    </div>
                                </div>

                                <div class="span1">
                                    <div class="input-control select">
                                        <s:select name="configurationBean.timeSummary.startMinuteEnum" id="startMinuteEnum4UpdateCalendar" list="configurationBean.minuteList" listKey="id" listValue="name"/>
                                    </div>
                                </div>
                            </div>

                            <div class="span2">
                                <p class="tertiary-info-secondary-text">Hasta</p>
                                <div class="span1">
                                    <div class="input-control select">
                                        <s:select name="configurationBean.timeSummary.endHourEnum" id="endHourEnum4UpdateCalendar" list="configurationBean.hourList" listKey="id" listValue="name"/>
                                    </div>
                                </div>
                                <div class="span1">
                                    <div class="input-control select">
                                        <s:select name="configurationBean.timeSummary.endMinuteEnum" id="endMinuteEnum4UpdateCalendar" list="configurationBean.minuteList" listKey="id" listValue="name"/>
                                    </div>
                                </div>
                            </div>

                            <input type="button" value="Volver" class="bg-color-orange place-right" onclick="$('#updateCalendarSpan').hide();">
                            <input type="button" value="Guardar" class="standart default place-right" onclick="Programming.updateCalendar();" >


                            <br/>
                            <br/>
                        </div>
                    </div>


                    <span class="menu-pull"></span>
                    <div class="menu-pull-bar"></div>

                    <ul>
                        <li class="active"><a href="#programmongTable">Nueva programacion</a></li>
                        <li><a href="#programming">Programaciones activas</a></li>
                    </ul>

                    <div class="frames">
                        <div class="frame active" id="programmongTable">
                            <div class="grid">
                                <div class="span8">
                                    <fieldset>
                                        <legend>Programaci&oacute;n</legend>

                                        <div class="row">
                                            <div class="span7">
                                                <label class="input-control radio" onclick="Programming.checkDayDiv();">
                                                    <input type="radio" value="1" name="configurationBean.programmingSummary.programmingTypeEnum" checked>
                                                    <span class="helper">Diaria</span>
                                                </label>
                                                <label class="input-control radio" onclick="Programming.checkDayDiv();">
                                                    <input type="radio" value="2" name="configurationBean.programmingSummary.programmingTypeEnum" >
                                                    <span class="helper">Semanal</span>
                                                </label>
                                                <label class="input-control radio" onclick="Programming.checkDayDiv();">
                                                    <input type="radio" value="3" name="configurationBean.programmingSummary.programmingTypeEnum" disabled>
                                                    <span class="helper">Mensual</span>
                                                </label>
                                                <label class="input-control radio" onclick="Programming.checkDayDiv();">
                                                    <input type="radio" value="4" name="configurationBean.programmingSummary.programmingTypeEnum" disabled>
                                                    <span class="helper">Anual</span>
                                                </label>
                                            </div>
                                            <div class="span1">
                                                <p class="tertiary-info-secondary-text">Activo</p>
                                                <label class="input-control switch" >
                                                    <input type="checkbox" name="configurationBean.programmingSummary.active" id="active" checked="true" />
                                                    <span class="helper"></span>
                                                </label>
                                            </div>
                                        </div>

                                        <br/>

                                        <div class="row">
                                            <!--<p class="tertiary-info-secondary-text">Se programara de forma diaria entre los horarios</p>-->
                                            <div class="span4">
                                                <p class="tertiary-info-secondary-text">Dispositivo</p>
                                                <div class="input-control select">
                                                    <s:select name="configurationBean.programmingSummary.deviceId" headerKey="-1" headerValue="Seleccione dispositivo" id="deviceId" list="configurationBean.deviceList" listKey="id" listValue="%{code + ' - ' + name}"/>
                                                </div>
                                            </div>
                                            <div class="span2">
                                                <p class="tertiary-info-secondary-text">Desde</p>
                                                <div class="span1">
                                                    <div class="input-control select">
                                                        <s:select name="configurationBean.programmingSummary.timeSummary.startHourEnum" id="startHourEnum" list="configurationBean.hourList" listKey="id" listValue="name"/>
                                                    </div>
                                                </div>

                                                <div class="span1">
                                                    <div class="input-control select">
                                                        <s:select name="configurationBean.programmingSummary.timeSummary.startMinuteEnum" id="startMinuteEnum" list="configurationBean.minuteList" listKey="id" listValue="name"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="span2">
                                                <p class="tertiary-info-secondary-text">Hasta</p>
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
                                        <div class="row" id="dayDiv">
                                            <div class="span8">
                                                <p class="tertiary-info-secondary-text">Dias</p>
                                                <s:iterator value="configurationBean.dayList">
                                                    <label class="input-control checkbox" onclick="">
                                                        <input type="checkbox" name="configurationBean.programmingSummary.dayLists" value="<s:property value="id"/>"/>
                                                        <span class="helper"><s:property value="name"/></span>
                                                    </label>
                                                </s:iterator>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="span8">
                                                <p class="tertiary-info-secondary-text">Motivo</p>
                                                <div class="input-control textarea">
                                                    <textarea name="configurationBean.programmingSummary.reason" id="reason" placeholder="Escriba el motivo de la programacion..."></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>

                                    <fieldset>
                                        <div class="row">
                                            <div class="span8">
                                                <input type="button" value="Cancelar" >
                                                <input type="button" value="Volver" class="bg-color-orange place-right">
                                                <input type="button" value="Guardar" class="standart default place-right" onclick="Programming.save();" >
                                            </div>
                                        </div>
                                    </fieldset>

                                </div>
                            </div>
                        </div>
                        <div class="frame" id="programming">
                            <div id="programmingListFrame">
                                <jsp:include page="/jsp/backoffice/jspf/programmingList.jsp" flush="true"/>
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
