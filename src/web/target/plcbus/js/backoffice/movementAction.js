$(document).ready(function () {
    MovementAction.init();
});

var ERROR_MESSAGE = [];
var MovementAction = {
    init: function () {
        $("#deviceId").attr("disabled", true);
    },
    save: function() {
        if (MovementAction.validate()) {
            var parameters = {
                "configurationBean.movementAction.device.id": $("#movementDeviceId").val()
                , "configurationBean.movementAction.reason": $("#reason").val()
                , "configurationBean.movementAction.movementTypeEnum": $("#movementTypeId").val()
                , "configurationBean.movementAction.timeSummary.startHourEnum": $("#startHourEnum").val()
                , "configurationBean.movementAction.timeSummary.startMinuteEnum": $("#startMinuteEnum").val()
                , "configurationBean.movementAction.timeSummary.endHourEnum": $("#endHourEnum").val()
                , "configurationBean.movementAction.timeSummary.endMinuteEnum": $("#endMinuteEnum").val()
                , "configurationBean.movementAction.active": $("#active").is(":checked")
            };

            $.ajax({
                url: "/plcbus/backoffice/configuration/saveMovementAction.do",
                method: "POST",
                data: parameters,
                dataType: "json",
                success: function (json) {
                    Generic.dialogOk("Se ha programado correctamente.");
                    MovementAction.clean();
                    MovementAction.refresh();
                }
            });
        } else {
            Generic.errorDialog(ERROR_MESSAGE);
        }
    },
    validate: function() {
        var forward = true;
        ERROR_MESSAGE = [];

        if ($("#movementDeviceId").val() == -1) {
            ERROR_MESSAGE.push("Debe seleccionar un dispositivo");
            forward = false;
        }

        if ($("#movementTypeId").val() == -1) {
            ERROR_MESSAGE.push("Debe seleccionar un tipo");
            forward = false;
        }

        if (!$("#haveDetail").val()) {
            ERROR_MESSAGE.push("Debe ingresar a lo menos una accion");
            forward = false;
        }

        if ($("#startHourEnum").val() == $("#endHourEnum").val() && $("#startMinuteEnum").val() == $("#endMinuteEnum").val()) {
            ERROR_MESSAGE.push("Debe indicar un rango de tiempo");
            forward = false;
        }

        return forward;
    },
    add: function() {
        if (MovementAction.validateAdd()) {
            var parameters = {
                "configurationBean.movementActionDetail.device.id": $("#deviceId").val()
                , "configurationBean.movementActionDetail.movementActionTypeEnum": $("#movementActionTypeId").val()
            };

            $.ajax({
                url: "/plcbus/backoffice/configuration/addMovementAction.do",
                method: "POST",
                data: parameters,
                dataType: "html",
                success: function (html) {
                    $("#movementActionDetailList").html(html);
                }
            });
        } else {
            Generic.errorDialog(ERROR_MESSAGE);
        }
    },
    validateAdd: function() {
        var forward = true;
        ERROR_MESSAGE = [];

        if($("#movementActionTypeId").val() == 1 || $("#movementActionTypeId").val() == 2) {
            if ($("#deviceId").val() == -1) {
                ERROR_MESSAGE.push("Debe seleccionar un dispositivo");
                forward = false;
            }
        }

        if ($("#movementActionTypeId").val() == -1) {
            ERROR_MESSAGE.push("Debe seleccionar una accion");
            forward = false;
        }

        return forward;
    },
    remove: function(index) {
        $.ajax({
            url: "/plcbus/backoffice/configuration/removeMovementAction.do",
            method: "POST",
            data: "configurationBean.movementActionDetailIndex=" + index,
            dataType: "html",
            success: function (html) {
                $("#movementActionDetailList").html(html);
            }
        });
    },
    delete: function(id, name) {
        $.Dialog({
            'title': 'Eliminar',
            'content': 'Esta seguro de eliminar la configuracion del sensor de para el dispositivo '+name+'?',
            'buttons'    : {
                'Ok'    : {
                    'action': function() {
                        $.ajax({
                            url: "/plcbus/backoffice/configuration/deleteMovementAction.do",
                            method: "POST",
                            data: "configurationBean.movementActionId="+id,
                            dataType: "json",
                            success: function (json) {
                                MovementAction.refresh();
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
            url: "/plcbus/backoffice/configuration/movementActionList.do",
            method: "POST",
            dataType: "html",
            success: function (html) {
                $("#movementActionListFrame").html(html);
            }
        });
    },
    get: function(id, name) {
        $.ajax({
            url: "/plcbus/backoffice/configuration/getMovementAction.do",
            method: "POST",
            data: "configurationBean.movementActionId="+id,
            dataType: "json",
            success: function (json) {
                $("#movementActionId").val(json.id);

            }
        });
    },
    clean: function() {
        $("#movementDeviceId").val("-1");
        $("#reason").val("");
        $("#deviceId").val("-1");
        $("#movementActionTypeId").val("-1");
        $("#movementTypeId").val("-1");
        $("#active").attr("checked", true);
        $("#movementActionDetailList").html("");
    },
    changeStatusMovementAction : function(id) {
        var movementActionHTMLId = "#statusCheckbox_" + id;
        var parameters = {
            "configurationBean.movementActionId" : id
            , "configurationBean.movementActionActive": $(movementActionHTMLId).is(':checked')
        };

        $.ajax({
            url: "/plcbus/backoffice/configuration/updateStatusMovementAction.do",
            method: "POST",
            data: parameters,
            dataType: "json"
        });
    },
    changeMovementActionType : function () {
        if($("#movementActionTypeId").val() == 1 || $("#movementActionTypeId").val() == 2) {
            $("#deviceId").attr("disabled", false);
        } else {
            $("#deviceId").val("-1");
            $("#deviceId").attr("disabled", true);
        }
    }
};

