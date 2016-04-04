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
    <script type="text/javascript" src="/plcbus/js/backoffice/configuration.js"></script>

    <title>PLCBus</title>

</head>
<body class="metrouicss">
<form action="/plcbus/backoffice/configuration/start.do" id="homeId">
    <jsp:include page="/jsp/common/menu.jsp"/>

    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Configuracion
                    <small>opciones</small>
                </h1>
                <a href="/plcbus/frontoffice/home/start.do" class="back-button big page-back"></a>
            </div>
        </div>

        <div class="page-region">
            <div class="page-region-content">

                <div class="grid">
                    <div class="clearfix">
                        <h3>Propiedad</h3>
                        <div class="tile double bg-color-green" data-role="tile-slider" data-param-period="3000" onclick="Configuration.toProperty();">
                            <div class="tile-content">
                                <h2>Mail de administrador</h2>
                                <h5>Direccion propiedad</h5>
                                <p>Alias</p>
                            </div>
                            <div class="tile-content">
                                <h2><s:property value="configurationBean.property.mail" /></h2>
                                <h5><s:property value="configurationBean.property.address" /></h5>
                                <p><s:property value="configurationBean.property.name" /></p>
                            </div>
                            <div class="brand">
                                <div class="badge">1</div>
                                <img class="icon" src="/plcbus/images/metro/Mail128.png"/>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix">
                        <h3>Programaciones</h3>
                        <div class="tile double bg-color-green" onclick="Configuration.toProgramming();" >
                            <div class="tile-content">
                                <h5>Programacion</h5>
                                <p>Programacion de los dispositivos</p>
                            </div>
                            <div class="brand">
                                <div class="badge"><s:property value="configurationBean.programmingCount" /></div>
                                <img class="icon" src="/plcbus/images/metro/Calendar128.png"/>
                            </div>
                        </div>

                        <div class="tile double bg-color-blueDark" onclick="Configuration.toScheduledShutdown();">
                            <div class="tile-content">
                                <h5>Apagado Programado</h5>
                                <p>Apagado programado para ahorro de energia</p>
                            </div>
                            <div class="brand">
                                <div class="badge"><s:property value="configurationBean.scheduledShutdownCount" /></div>
                                <img class="icon" src="/plcbus/images/metro/Calendar128.png"/>
                            </div>
                        </div>

                        <div class="tile double bg-color-blueDark" onclick="Configuration.toMovementAction();">
                            <div class="tile-content">
                                <h5>Accion de Movimiento</h5>
                                <p>Programaciones para las acciones de los sensores</p>
                            </div>
                            <div class="brand">
                                <div class="badge"><s:property value="configurationBean.movementActionCount" /></div>
                                <img class="icon" src="/plcbus/images/metro/Calendar128.png"/>
                            </div>
                        </div>

                    </div>

                    <div class="clearfix">
                        <h3>Sincronizacion</h3>

                        <div class="tile double bg-color-orange" onclick="Configuration.toSynchronized();">
                            <div class="tile-content">
                                <h5>Sincronizacion</h5>
                                <p>Sincroniza los dispositivos</p>
                            </div>
                            <div class="brand">
                                <img class="icon" src="/plcbus/images/metro/Rss128.png"/>
                            </div>
                        </div>
                    </div>

                    <div class="clearfix">
                        <h3>Distribucion</h3>

                        <s:iterator value="#session.LEVEL_SESSION"  >
                            <div class="tile bg-color-orangeDark icon" onclick="location.href='/plcbus/backoffice/configuration/startLightDistribution.do?deviceDistributionBean.levelId=<s:property value='id' />';">
                                <div class="tile-content">
                                    <img alt="" src="/plcbus/images/metro/Video128.png"/>
                                </div>
                                <div class="brand">
                                    <span class="name"><s:property value="name" /></span>
                                </div>
                            </div>
                        </s:iterator>

                    </div>

                    <div class="clearfix">
                        <h3>Mantenedores</h3>
                        <div class="tile icon" onclick="Configuration.toDevice();">
                            <div class="tile-content">
                                <img src="/plcbus/images/metro/simple.png"/>
                            </div>
                            <div class="brand bg-color-red">
                                <p class="text">Dispositivos</p>
                            </div>
                        </div>

                        <div class="tile icon" onclick="Configuration.toSector();">
                            <div class="tile-content">
                                <img src="/plcbus/images/metro/grid.png"/>
                            </div>
                            <div class="brand bg-color-red">
                                <p class="text">Zonas</p>
                            </div>
                        </div>

                        <div class="tile icon" onclick="Configuration.toLevel();">
                            <div class="tile-content">
                                <img src="/plcbus/images/metro/responsive.png"/>
                            </div>
                            <div class="brand bg-color-red">
                                <p class="text">Niveles</p>
                            </div>
                        </div>

                        <div class="tile icon" onclick="Configuration.toMap();">
                            <div class="tile-content">
                                <img src="/plcbus/images/metro/Camera48.png"/>
                            </div>
                            <div class="brand bg-color-red">
                                <p class="text">Mapas</p>
                            </div>
                        </div>
                    </div>

                    <div class="clearfix">
                        <h3>Administradores</h3>
                        <div class="tile icon" onclick="Configuration.toCamera();">
                            <div class="tile-content">
                                <img src="/plcbus/images/metro/Camera48.png"/>
                            </div>
                            <div class="brand bg-color-red">
                                <p class="text">Camaras</p>
                            </div>
                        </div>

                        <div class="tile icon" onclick="Configuration.toIR();">
                            <div class="tile-content">
                                <img src="/plcbus/images/metro/grid.png"/>
                            </div>
                            <div class="brand bg-color-red">
                                <p class="text">IR</p>
                            </div>
                        </div>

                        <div class="tile icon" onclick="Configuration.toMagnetic();">
                            <div class="tile-content">
                                <img src="/plcbus/images/metro/grid.png"/>
                            </div>
                            <div class="brand bg-color-red">
                                <p class="text">Magneticos</p>
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
