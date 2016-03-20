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
    <script type="text/javascript" src="/plcbus/js/backoffice/propertyData.js"></script>

    <title>PLCBus</title>

</head>
<body class="metrouicss">
<form action="/plcbus/backoffice/configuration/startPropertyData.do" id="homeControlId">
    <jsp:include page="/jsp/common/menu.jsp"/>

    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Configuraci&oacute;n
                    <small>propiedad</small>
                </h1>
                <a href="/plcbus/backoffice/configuration/startConfiguration.do" class="back-button big page-back"></a>
            </div>
        </div>


        <div class="page-region">
            <div class="page-region-content">

                <div class="grid">
                    <div class="span8">
                        <fieldset>
                            <legend>Datos de la propiedad</legend>
                            <input type="hidden" name="configurationBean.property.id" id="propertyId" value="<s:property value="configurationBean.property.id"/>">

                            <div class="row">
                                <div class="span8">
                                    <p class="tertiary-info-secondary-text">Alias</p>
                                    <div class="input-control text">
                                        <input type="text" name="configurationBean.property.name" value="<s:property value="configurationBean.property.name"/>" id="name" autofocus="" placeholder="Alias propiedad">
                                        <button type="button" class="btn-clear" />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="span8">
                                    <p class="tertiary-info-secondary-text">Direccion</p>
                                    <div class="input-control text">
                                        <input type="text" name="configurationBean.property.address" value="<s:property value="configurationBean.property.address"/>" id="address" autofocus="" placeholder="Direccion">
                                        <button type="button" class="btn-clear" />
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="span5">
                                    <p class="tertiary-info-secondary-text">Mail de administracion</p>
                                    <div class="input-control text">
                                        <input type="text" name="configurationBean.property.mail" id="mail" value="<s:property value="configurationBean.property.mail"/>" autofocus="" placeholder="mail@dominio.com">
                                        <button type="button" class="btn-clear" />
                                    </div>
                                </div>
                                <div class="span3">
                                    <p class="tertiary-info-secondary-text">Celular</p>
                                    <div class="input-control text span2" >
                                        <input type="text" name="configurationBean.property.phone" maxlength="8" id="phone" value="<s:property value="configurationBean.property.phone"/>" autofocus="" placeholder="943229XX">
                                        <button type="button" class="btn-clear" />
                                    </div>
                                </div>
                            </div>

                        </fieldset>

                        <fieldset>
                            <div class="row">
                                <div class="span8">
                                    <input type="button" value="Cancelar" >
                                    <input type="button" value="Volver" class="bg-color-orange place-right">
                                    <input type="button" value="Guardar" class="standart default place-right" onclick="PropertyData.save();">
                                </div>
                            </div>
                        </fieldset>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/jsp/common/footer.jsp"/>

</form>
</body>
</html>
