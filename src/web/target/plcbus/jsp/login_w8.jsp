<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/plcbus/css/modern.css"/>
    <link rel="stylesheet" href="/plcbus/css/theme-dark.css"/>

    <script src="/plcbus/js/jquery-1.9.0.min.js"></script>
    <script src="/plcbus/js/jquery-ui-1.10.3.custom.min.js"></script>

    <style type="text/css">
        .metrouicss {
            background-color: #4A0093;
        }

        #pwordbox {
            position: absolute;
            height: 100px;
            width: 100px;
            left: 50%;
            top: 35%;
            transform: translate(-50%, -50%);
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
        }

        #imgcont {
            position: absolute;
            left: -175%;
            top: 50%;
            transform: translate(-50%, -10%);
            -webkit-transform: translate(-50%, -10%);
            -moz-transform: translate(-50%, -10%);
            -ms-transform: translate(-50%, -10%);
        }

        #pboxcont {
            position: absolute;
            left: 0%;
            top: 100%;
            /*
            transform: translate(-50%, -10%);
            -webkit-transform: translate(-50%, -10%);
            -moz-transform: translate(-50%, -10%);
            -ms-transform: translate(-50%, -10%);
            */
        }

        #pwordhint {
            color: #E86C19;
            font-size: 17.5px;
        }
    </style>
    <script type="text/javascript">
        function checkPW() {
            return ($("#pw").val() == "11042.");
        }
    </script>
</head>
<body class="metrouicss" >
<form action="/plcbus/frontoffice/home/start.do" onsubmit="return checkPW();" >
    <div id="pwordbox">
        <div id="imgcont">
            <img alt="win8" src="/plcbus/images/metro/WIN8.png"/>
        </div>
        <div id="fieldcont">
            <div id="pboxcont">
                <h1 style="color:white;">&lt;Monitor&gt;</h1>

                <div class="input-control password span4" id="pworddiv">
                    <input type="password" id="pw"/>
                    <button class="btn-reveal">o</button>
                </div>
            </div>
        </div>
</form>
</body>
</html>

