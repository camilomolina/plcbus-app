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
    <script type="text/javascript" src="/plcbus/js/frontoffice/home.js"></script>

    <title>PLCBus</title>

</head>
<body class="metrouicss">
<form action="/plcbus/frontoffice/home/start.do" id="homeId">
    <jsp:include page="/jsp/common/menu.jsp"/>

    <div class="page" id="page-index">
        <div class="page-region">
            <div class="page-region-content">

                <div class="grid">
                    <div class="row">
                        <div class="span4 bg-color-blue" onclick="Home.toControl();" style="cursor: pointer;">
                            <img src="/plcbus/images/metro/simple.png" class="place-right" style="margin: 10px;"/>
                            <h2 class="fg-color-white">&nbsp;Controles</h2>
                        </div>

                        <div class="span4 bg-color-green" onclick="Home.toStatistics();" style="cursor: pointer;">
                            <img src="/plcbus/images/metro/grid.png" class="place-right" style="margin: 10px;"/>
                            <h2 class="fg-color-white">&nbsp;Estadistica</h2>
                        </div>

                        <div class="span4 bg-color-yellow" onclick="Home.toConfiguration();" style="cursor: pointer;">
                            <img src="/plcbus/images/metro/responsive.png" class="place-right" style="margin: 10px;"/>
                            <h2 class="fg-color-white">&nbsp;Configuracion</h2>
                        </div>
                    </div>
                </div>

                <div class="grid">
                    <div class="row">
                        <div class="span4">
                            <div class="span4 padding30 text-center place-left bg-color-blueLight" id="sponsorBlock">
                                <p>Luces encendidas : <s:property value="homeBean.lightOn" /></p>
                                <p>Enchufes con corriente : <s:property value="homeBean.electricOutletOn" /></p>
                                <p>Riego encendido : <s:property value="homeBean.irrigationOn" /></p>
                                <p>Cortinas abiertas : <s:property value="homeBean.curtainOn" /></p>
                                <p>Reinicio PLCBus : <s:property value="homeBean.restatCount" /></p>
                            </div>
                        </div>
                        <div class="span8">
                            <div class="hero-unit">
                                <div id="carousel1" class="carousel" data-role="carousel" data-param-duration="300">
                                    <div class="slides">

                                        <div class="slide" id="slide1">
                                            <h2>Informacion</h2>

                                            <s:date name="%{new java.util.Date()}" format="dd/MM/yyyy HH:mm" />
                                            <br/>
                                            <s:property value="#session.WEATHER_SESSION.temp"/><i class="icon-Celsius"></i>
                                            <i class="<s:property value='#session.WEATHER_SESSION.weatherYahooEnum.icon'/>"></i>
                                            <s:property value="#session.WEATHER_SESSION.weatherYahooEnum.name"/>
                                            <br/>
                                            <br/>
                                            <s:iterator value="#session.WEATHER_SESSION.weatherDetail" >
                                                <s:date name="date" format="dd/MM/yyyy"/>
                                                <s:property value="low"/>-<s:property value="high"/><i class="icon-Celsius"></i>
                                                <i class="<s:property value='weatherYahooEnum.icon'/>"></i>
                                                <s:property value="weatherYahooEnum.name"/>
                                                <br/>
                                            </s:iterator>
                                        </div>

                                        <div class="slide" id="slide4">
                                            <h2 class="fg-color-darken">Alarmas</h2>
                                            <p class="bg-color-pink padding20 fg-color-white">
                                                Se detallan todas las alarmas que fueron lanzadas por el PLCBus, las cuales responden a reglas de negocio
                                                configuradas en el PCBus. Las alarmas mas comunes son uso continuo de un dispositivo determinado, cancelacion
                                                de riego por lluvia, uso innecesario de iluminacion.
                                            </p>
                                            <ul class="unstyled sprite-details">
                                                <s:iterator value="homeBean.alertedDevice"  >
                                                    <li><i class="icon-checkmark"></i><s:property value="code" /> - <s:property value="name" /></li>
                                                </s:iterator>
                                            </ul>
                                        </div>

                                        <div class="slide" id="slide5">
                                            <h2 class="fg-color-darken">Avisos</h2>
                                            <p class="bg-color-pink padding20 fg-color-white">
                                                Se detallan todos los avisos importantes que durante el dia fueron ocurriendo en el PLCBus
                                                como por ejemplo envios de SMS o envios de mail por dispositivos alarmados, tambien se detallan
                                                los eventos mas importantes como cancelacion de riego por lluvia
                                            </p>
                                            <ul class="unstyled sprite-details">
                                                <s:iterator value="homeBean.warningEvent"  >
                                                    <li><i class="icon-checkmark"></i><s:property value="date" /> - <s:property value="deviceCode" /> - <s:property value="deviceName" /> - <s:property value="eventTypeEnum.name" /> - </li>
                                                </s:iterator>
                                            </ul>
                                        </div>

                                        <div class="slide" id="slide6">
                                            <h2 class="fg-color-darken">Errores</h2>
                                            <p class="bg-color-pink padding20 fg-color-white">
                                                Se detallan los potenciales errores que detecta el PLCBus, como errores al
                                                envio de mail o saldo insuficiente para envio de SMS
                                            </p>
                                            <ul class="unstyled sprite-details">
                                                <s:iterator value="homeBean.errorEvent"  >
                                                    <li><i class="icon-checkmark"></i><s:property value="date" /> - <s:property value="deviceCode" /> - <s:property value="deviceName" /> - <s:property value="eventTypeEnum.name" /> - </li>
                                                </s:iterator>
                                            </ul>
                                        </div>

                                        <div class="slide" id="slide2">
                                            <h2>Queue de mensajes</h2>
                                            <p class="bg-color-blueDark padding20 fg-color-white">
                                                Mensajes en la cola del PLCBus que aun no han sido procesadas.
                                            </p>
                                            <ul class="unstyled sprite-details">
                                                <s:iterator value="homeBean.messageQueue"  >
                                                    <li><i class="icon-checkmark"></i><s:property value="message.dataMessage.homeUnit" /> - <s:property value="message.dataMessage.command" /> - <s:property value="message.dataMessage.rxtxSwitch" /></li>
                                                </s:iterator>
                                            </ul>
                                        </div>


                                        <span class="control left"><i class="icon-arrow-left-3"></i></span>
                                        <span class="control right"><i class="icon-arrow-right-3"></i></span>

                                    </div>
                                </div>
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
