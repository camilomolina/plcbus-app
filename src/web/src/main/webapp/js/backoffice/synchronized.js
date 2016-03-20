$(document).ready(function () {
    Synchronized.init();
});


var Synchronized = {
    init: function () {
        setInterval(function() {
            Synchronized.refresh();
        }, 1000);
    },
    forceButton: function(synchronizedInCurse, synchronizedTimeOut) {
        if (synchronizedInCurse) {
            if (synchronizedTimeOut) {
                Generic.warningDialog("Existe una sincronizacion en curso que se tardo mas de lo habitual, no se puede forzar la sincronizacion hasta que los procesos batch del plcbus rectifiquen la sincronizacion");
            } else {
                Generic.warningDialog("Existe una sincronizacion en curso, no es necesario forzar la sincronizacion");
            }
        } else {
            $("#forceButton").attr("onclick","Synchronized.forceButton(true);");
            $.ajax({
                url: "/plcbus/backoffice/configuration/forceSynchronized.do",
                method: "POST",
                dataType: "json",
                success: function (json) {
                    Synchronized.refresh();
                }
            });
        }
    },
    refresh: function() {
        $.ajax({
            url: "/plcbus/backoffice/configuration/synchronizedList.do",
            method: "POST",
            dataType: "html",
            success: function (html) {
                $("#synchronizedListFrame").html(html);
            }
        });
    }
};

