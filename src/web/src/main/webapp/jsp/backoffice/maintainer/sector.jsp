<%@include file="/jsp/taglibs.jspf" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="target-densitydpi=sector-dpi, width=sector-width, initial-scale=1.0, maximum-scale=1">
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
    <script type="text/javascript" src="/plcbus/js/backoffice/maintainer/sector.js"></script>

    <title>PLCBus</title>

</head>
<body class="metrouicss">
<form action="/plcbus/backoffice/maintainer/startSector.do" id="homeControlId">
    <jsp:include page="/jsp/common/menu.jsp"/>

    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Configuraci&oacute;n
                    <small>zonas</small>
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
                        <li id="maintainerLi" class="active"><a href="#maintainerFrame"><i class="icon-equalizer"></i>Mantenedor</a></li>
                        <li id="sectorListLi" ><a href="#sectorListFrame">Lista</a></li>
                        <li class="place-right"><a href="#helpFrame"><i class="icon-help-2 nrm"></i></a></li>
                    </ul>

                    <div class="frames">
                        <div class="frame active" id="maintainerFrame">
                            <div class="grid">
                                <div class="span9">

                                    <h2>Mantenedor de zonas</h2>

                                    <fieldset>
                                        <legend>Datos de las zona</legend>
                                        <input type="hidden" name="maintainerBean.sector.id" id="sectorId" >

                                        <div class="row">
                                            <div class="span8">
                                                <p class="tertiary-info-secondary-text">Nombre</p>
                                                <div class="input-control text">
                                                    <input type="text" name="maintainerBean.sector.name" id="name" autofocus="" placeholder="Nombre">
                                                    <button type="button" class="btn-clear" />
                                                </div>
                                            </div>

                                        </div>

                                        <div class="row">
                                            <div class="span7">
                                                <p class="tertiary-info-secondary-text">Nivel</p>
                                                <div class="input-control select">
                                                    <s:select name="maintainerBean.sector.level.id" headerKey="-1" headerValue="Seleccione nivel" id="levelId" list="maintainerBean.levelList" listKey="id" listValue="name"/>
                                                </div>
                                            </div>
                                            <div class="span1">
                                                <p class="tertiary-info-secondary-text">Activo</p>
                                                <label class="input-control switch" >
                                                    <input type="checkbox" name="maintainerBean.sector.active" id="active" checked="true" />
                                                    <span class="helper"></span>
                                                </label>
                                            </div>
                                        </div>
                                    </fieldset>

                                    <fieldset>
                                        <div class="row">
                                            <div class="span8">
                                                <input type="button" value="Cancelar" >
                                                <input type="button" value="Volver" class="bg-color-orange place-right">
                                                <input type="button" value="Guardar" class="standart default place-right" onclick="Sector.save();">
                                            </div>
                                        </div>
                                    </fieldset>


                                </div>
                            </div>
                        </div>
                        <div class="frame" id="sectorListFrame">
                            <jsp:include page="/jsp/backoffice/maintainer/jspf/sectorList.jsp" flush="true"/>
                        </div>
                        <div class="frame" id="helpFrame">
                            <h2>Ayuda</h2>
                            <p>Zonas
                            </p>
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
