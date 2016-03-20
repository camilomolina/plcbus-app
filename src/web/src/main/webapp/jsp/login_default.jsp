<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/plcbus/css/modern.css"/>
    <link rel="stylesheet" href="/plcbus/css/theme-dark.css"/>

    <script src="/plcbus/js/jquery-1.9.0.min.js"></script>
    <script src="/plcbus/js/jquery-ui-1.10.3.custom.min.js"></script>

    <script type="text/javascript">
        function checkPW() {
            return ($("#pw").val() == "11042.");
        }
    </script>
</head>
<body class="metrouicss" >
<form action="/plcbus/frontoffice/home/start.do" onsubmit="return checkPW();" >

    <div class="page-region">
        <div class="page-region-content">

            <div class="grid">
                <div class="span4">
                    <fieldset>
                        <legend>Login</legend>

                        <div class="row">
                            <div class="span4">
                                <p class="tertiary-info-secondary-text">User</p>
                                <div class="input-control select">
                                    <select id="user" name="user">
                                        <option value="1" selected>Monitor</option>
                                        <option value="2">Camilo</option>
                                        <option value="3">Tania</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="span4">
                                <p class="tertiary-info-secondary-text">Pass</p>
                                <div class="input-control password" id="pworddiv">
                                    <input type="password" id="pw"/>
                                    <button class="btn-reveal">o</button>
                                </div>
                            </div>
                        </div>

                    </fieldset>

                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>

