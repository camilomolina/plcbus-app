<%@include file="/jsp/taglibs.jspf" %>

<html lang="es">
<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="/plcbus/js/jquery-1.9.0.min.js"></script>

    <script>
        $(document).ready(function () {
            $("#start").submit();
        });
    </script>
</head>

<s:form action="/frontoffice/home/start.do" method="post" id="start">
</s:form>
</html>



