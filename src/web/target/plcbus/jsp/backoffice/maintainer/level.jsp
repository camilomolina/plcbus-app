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
    <script type="text/javascript" src="/plcbus/js/backoffice/maintainer/level.js"></script>

    <title>PLCBus</title>

</head>
<body class="metrouicss">
<form action="/plcbus/backoffice/maintainer/startLevel.do" id="homeControlId">
    <jsp:include page="/jsp/common/menu.jsp"/>

    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Configuraci&oacute;n
                    <small>niveles</small>
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
                        <li id="levelListLi" ><a href="#levelListFrame">Lista</a></li>
                        <li class="place-right"><a href="#helpFrame"><i class="icon-help-2 nrm"></i></a></li>
                    </ul>

                    <div class="frames">
                        <div class="frame active" id="maintainerFrame">
                            <div class="grid">
                                <div class="span9">

                                    <h2>Mantenedor de niveles</h2>

                                    <fieldset>
                                        <legend>Datos del nivel</legend>
                                        <input type="hidden" name="maintainerBean.level.property.id" id="propertyId" value="<s:property value="maintainerBean.property.id"/>">

                                        <div class="row">
                                            <div class="span8">
                                                <p class="tertiary-info-secondary-text">Propiedad</p>
                                                <div class="input-control text">
                                                    <input type="text" name="maintainerBean.property.name" value="<s:property value="maintainerBean.property.name"/>" disabled>
                                                </div>
                                            </div>
                                        </div>

                                        <input type="hidden" name="maintainerBean.level.id" id="levelId" >

                                        <div class="row">
                                            <div class="span7">
                                                <p class="tertiary-info-secondary-text">Nombre</p>
                                                <div class="input-control text">
                                                    <input type="text" name="maintainerBean.level.name" id="name" autofocus="" placeholder="Nombre">
                                                    <button type="button" class="btn-clear" />
                                                </div>
                                            </div>
                                            <div class="span1">
                                                <p class="tertiary-info-secondary-text">Activo</p>
                                                <label class="input-control switch" >
                                                    <input type="checkbox" name="maintainerBean.level.active" id="active" checked="true" />
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
                                                <input type="button" value="Guardar" class="standart default place-right" onclick="Level.save();">
                                            </div>
                                        </div>
                                    </fieldset>


                                </div>
                            </div>
                        </div>
                        <div class="frame" id="levelListFrame">
                            <jsp:include page="/jsp/backoffice/maintainer/jspf/levelList.jsp" flush="true"/>
                        </div>
                        <div class="frame" id="helpFrame">
                            <h2>Ayuda</h2>
                            <p>Esta session permite crear, eliminar o actualizar los niveles de la propiedad.
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
