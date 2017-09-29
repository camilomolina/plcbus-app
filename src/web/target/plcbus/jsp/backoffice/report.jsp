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
    <script type="text/javascript" src="/plcbus/js/Chart.min.js"></script>

    <script src="/plcbus/js/highcharts/highcharts.js"></script>
    <script src="/plcbus/js/highcharts/modules/exporting.js"></script>

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
    <script type="text/javascript" src="/plcbus/js/backoffice/report.js"></script>

    <title>PLCBus</title>

</head>
<body class="metrouicss">
<form action="/plcbus/frontoffice/home/start.do" id="homeControlId">
    <jsp:include page="/jsp/common/menu.jsp"/>

    <div class="page secondary">
        <div class="page-header">
            <div class="page-header-content">
                <h1>Reportes
                    <small>plcbus</small>
                </h1>
                <a href="/plcbus/backoffice/statistics/startStatistics.do" class="back-button big page-back"></a>
            </div>
        </div>


        <div class="page-region">
            <div class="page-region-content">
                <div class="grid">
                    <div class="row">
                        <div class="span5">
                            <ul class="listview iconic">
                                <li onclick="Report.deviceXls();">
                                    <div class="icon">
                                        <img src="/plcbus/images/metro/excel2013icon.png"/>
                                    </div>
                                    <div class="data">
                                        <h4>Dispositivos</h4>
                                    </div>
                                </li>
                                <li onclick="Report.sectorXls();">
                                    <div class="icon">
                                        <img src="/plcbus/images/metro/excel2013icon.png"/>
                                    </div>
                                    <div class="data">
                                        <h4>Zonas</h4>
                                    </div>
                                </li>
                                <li onclick="Report.levelXls();">
                                    <div class="icon">
                                        <img src="/plcbus/images/metro/excel2013icon.png"/>
                                    </div>
                                    <div class="data">
                                        <h4>Niveles</h4>
                                    </div>
                                </li>
                            </ul>
                        </div>

                        <div class="span5">
                            <ul class="listview iconic">
                                <li onclick="Report.eventXls();">
                                    <div class="icon">
                                        <img src="/plcbus/images/metro/excel2013icon.png"/>
                                    </div>
                                    <div class="data">
                                        <h4>Eventos</h4>
                                    </div>
                                </li>
                                <li>
                                    <div class="icon">
                                        <img src="/plcbus/images/metro/word2013icon.png"/>
                                    </div>
                                    <div class="data">
                                        <h4>Eventos</h4>
                                    </div>
                                </li>
                                <li onclick="Report.sensorXls()();">
                                    <div class="icon">
                                        <img src="/plcbus/images/metro/onenote2013icon.png"/>
                                    </div>
                                    <div class="data">
                                        <h4>Otro</h4>
                                    </div>
                                </li>
                            </ul>
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
