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

    <link href="/plcbus/css/plcbus.css" rel="stylesheet" type="text/css">

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
</head>
<body class="metrouicss" >
<form action="/plcbus/frontoffice/home/start.do" id="homeControlId" >
    <jsp:include page="/jsp/common/menu.jsp"/>


    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Controles
                    <small>niveles</small>
                </h1>
                <a href="/plcbus/frontoffice/home/start.do" class="back-button big page-back"></a>
            </div>
        </div>

        <div class="page-region">
            <div class="page-region-content">

                <div class="page-control" data-role="page-control">
                    <div class="row">
                        <s:iterator value="#session.LEVEL_SESSION"  >
                            <div class="span8">
                                <div class="span8 padding30 text-center place-left bg-color-blueLight" id="levelBlock<s:property value='id' />" style="cursor: pointer;" onclick="location.href='/plcbus/frontoffice/control/startControl.do?controlBean.levelId=<s:property value='id' />';">
                                    <br />
                                    <h2 class="fg-color-red"><s:property value="name" /></h2>
                                    <br />
                                    <a href="/plcbus/frontoffice/control/startControl.do?controlBean.levelId=<s:property value='id' />"><h1><i class="icon-arrow-right-3 fg-color-red"></i></h1></a>
                                </div>
                            </div>
                        </s:iterator>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/jsp/common/footer.jsp"/>

</form>
</body>
</html>
