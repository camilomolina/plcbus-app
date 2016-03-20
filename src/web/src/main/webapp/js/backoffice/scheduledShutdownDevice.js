$(document).ready(function () {
    ScheduledShutdownDevice.init();
});

var ERROR_MESSAGE = [];
var ScheduledShutdownDevice = {
    init: function () {

    },
    save: function() {
        if (ScheduledShutdownDevice.validate()) {
            var parameters = {
                "configurationBean.programmingSummary.timeSummary.endHourEnum": $("#endHourEnum").val()
                , "configurationBean.programmingSummary.timeSummary.endMinuteEnum": $("#endMinuteEnum").val()
                , "configurationBean.programmingSummary.deviceId": $("#deviceId").val()
            };

            $.ajax({
                url: "/plcbus/backoffice/configuration/saveScheduledShutdownDevice.do",
                method: "POST",
                data: parameters,
                dataType: "json",
                success: function (json) {
                    Generic.dialogOk("Se ha programado correctamente.");
                    ScheduledShutdownDevice.clean();
                    ScheduledShutdownDevice.refresh();
                }
            });
        } else {
            Generic.errorDialog(ERROR_MESSAGE);
        }
    },
    validate: function() {
        var forward = true;
        ERROR_MESSAGE = [];

        if ($("#deviceId").val() == -1) {
            ERROR_MESSAGE.push("Debe seleccionar un dispositivo");
            forward = false;
        }

        return forward;
    },
    delete: function(id, name) {
        $.Dialog({
            'title': 'Eliminar',
            'content': 'Esta seguro de eliminar la programación para el dispositivo '+name+'?',
            'buttons'    : {
                'Ok'    : {
                    'action': function() {
                        $.ajax({
                            url: "/plcbus/backoffice/configuration/deleteScheduledShutdownDevice.do",
                            method: "POST",
                            data: "configurationBean.scheduledShutdownDeviceId="+id,
                            dataType: "json",
                            success: function (json) {
                                ScheduledShutdownDevice.refresh();
                            }
                        });
                    }
                },
                'Cancel'     : {
                    'action': function() {}
                }
            }
        });
    },
    refresh: function() {
        $.ajax({
            url: "/plcbus/backoffice/configuration/scheduledShutdownDeviceList.do",
            method: "POST",
            dataType: "html",
            success: function (html) {
                $("#scheduledShutdownDeviceListFrame").html(html);
            }
        });
    },
    clean: function() {
        $("#endHourEnum").val(0);
        $("#endMinuteEnum").val(0);
        $("#deviceId").val(-1);
    }
};

