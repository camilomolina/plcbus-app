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

    <title>PLCBus</title>
    <script type="text/javascript">
        function checkPW() {
            return $("#pass").val() == '11042.';
        }
    </script>
</head>
<body class="metrouicss">
<!--<form action="j_spring_security_check" method="post" id="loginId">-->
<form action="/plcbus/frontoffice/home/start.do" method="post" id="loginId" onsubmit="return checkPW();">


    <div class="page" id="page-index">
        <div class="page-region">
            <div class="page-region-content">

                <div class="grid">
                    <div class="span4">

                        <fieldset>
                            <legend>Login</legend>

                            <div class="row">
                                <div class="span4">
                                    <p class="tertiary-info-secondary-text">User</p>
                                    <!--
                                    <div class="input-control text">
                                        <input type="text" name="j_username" id="user" >
                                        <button type="button" class="btn-clear" />
                                    </div>
                                    -->

                                    <div class="input-control select">
                                        <select id="user" name="j_username">
                                            <option value="monitor" selected>Monitor</option>
                                            <option value="camilo">Camilo</option>
                                            <option value="3">Tania</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="span4">
                                    <p class="tertiary-info-secondary-text">Pass</p>
                                    <div class="input-control password" id="pworddiv">
                                        <input type="password" id="pass" name="j_password"/>
                                        <button class="btn-reveal"></button>
                                    </div>
                                </div>
                            </div>

                        </fieldset>

                    </div>
                </div>



            </div>
        </div>
    </div>

</form>
</body>
</html>
