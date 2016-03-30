<%@include file="/jsp/taglibs.jspf" %>

<div class="page">
    <div class="nav-bar">
        <div class="nav-bar-inner padding10">
            <span class="pull-menu"></span>

            <a href="/plcbus/frontoffice/home/start.do">
                <span class="element brand">
                    <img class="place-left" src="/plcbus/images/metro/logo32.png" style="height: 20px"/>
                    PLCBus <small>0.1v</small>
                </span>
            </a>

            <div class="divider"></div>

            <ul class="menu">
                <li><a href="/plcbus/frontoffice/home/start.do">Home</a></li>
                <li data-role="dropdown">
                    <a href="#">Controles</a>
                    <ul class="dropdown-menu">
                        <li><a href="/plcbus/frontoffice/control/startTableControl.do">Tabla Completa</a></li>
                        <li class="divider"></li>
                        <s:iterator value="#session.LEVEL_SESSION"  >
                            <li><a href="/plcbus/frontoffice/control/startControl.do?controlBean.levelId=<s:property value='id' />"><s:property value="name" /></a></li>
                        </s:iterator>
                    </ul>
                </li>
                <li data-role="dropdown">
                    <a href="#">Estadistica</a>
                    <ul class="dropdown-menu">
                        <li><a href="/plcbus/backoffice/statistics/startGraphics.do">Graficos</a></li>
                        <li><a href="/plcbus/backoffice/statistics/startReport.do">Reportes</a></li>
                    </ul>
                </li>
                <li data-role="dropdown"><a href="#">Configuraci&oacute;n</a>
                    <ul class="dropdown-menu">
                        <li><a href="/plcbus/backoffice/configuration/startPropertyData.do">Propiedad</a></li>
                        <li class="divider"></li>
                        <li><a href="/plcbus/backoffice/configuration/startProgramming.do">Programaci&oacute;n</a></li>
                        <li><a href="/plcbus/backoffice/configuration/startScheduledShutdownDevice.do">Apagado Programado</a></li>
                        <li><a href="/plcbus/backoffice/configuration/startMovementAction.do">Sensor de movimiento</a></li>
                        <!--<li><a href="/plcbus/backoffice/configuration/startTemperatureProgramming.do">Programaci&oacute;n por Temperatura</a></li>-->
                        <li><a href="/plcbus/backoffice/configuration/startSynchronized.do">Sinronizaci&oacute;n</a></li>
                        <li class="divider"></li>
                        <li><a>Distribucion</a></li>
                        <s:iterator value="#session.LEVEL_SESSION"  >
                            <li><a href="/plcbus/backoffice/configuration/startLightDistribution.do?deviceDistributionBean.levelId=<s:property value='id' />"><s:property value="name" /></a></li>
                        </s:iterator>
                        <li class="divider"></li>
                        <li><a href="/plcbus/backoffice/maintainer/startDevice.do">Dispositivos</a></li>
                        <li><a href="/plcbus/backoffice/maintainer/startMap.do">Mapas</a></li>
                        <li><a href="/plcbus/backoffice/maintainer/startSector.do">Zonas</a></li>
                        <li><a href="/plcbus/backoffice/maintainer/startLevel.do">Niveles</a></li>
                        <li class="divider"></li>
                        <li><a href="/plcbus/backoffice/maintainer/start.do">Usuarios</a></li>
                        <li><a href="/plcbus/backoffice/maintainer/startPassChange.do">Cambio de clave</a></li>
                    </ul>
                </li>
                <li data-role="dropdown"><a href="#">Administraci&oacute;n</a>
                    <ul class="dropdown-menu">
                        <li><a href="/plcbus/backoffice/administration/startCamera.do">Camara</a></li>
                        <li><a href="">IR</a></li>
                        <li><a href="">Magneticos</a></li>
                    </ul>
                </li>
                <li data-role="dropdown"><a href="#">Ayuda</a>
                    <ul class="dropdown-menu">
                        <li><a href="/plcbus/jsp/frontoffice/use.jsp">Uso</a></li>
                        <li><a href="/plcbus/jsp/frontoffice/contact.jsp">Contactar</a></li>
                        <li><a href="/plcbus/jsp/frontoffice/update.jsp">Actualizaciones</a></li>
                        <li><a href="/plcbus/jsp/frontoffice/version.jsp">Versi&oacute;n</a></li>
                        <li class="divider"></li>
                        <li><a href="/plcbus/jsp/frontoffice/about.jsp">Acerca</a></li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</div>