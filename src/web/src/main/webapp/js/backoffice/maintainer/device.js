$(document).ready(function () {
    Device.init();
});

var ERROR_MESSAGE = [];
var Device = {
    init: function () {
        $("#cameraDiv").hide();
    },
    save: function() {
        if (Device.validate()) {
            var code;
            if ($("#deviceTypeEnumId").val() == 29) {
                code = $("#otherCode").val();
            } else {
                code = $("#code").val();
            }

            var parameters = {
                "maintainerBean.device.id": $("#deviceId").val()
                , "maintainerBean.device.name": $("#name").val()
                , "maintainerBean.device.code": code
                , "maintainerBean.device.desc": $("#desc").val()
                , "maintainerBean.device.deviceTypeEnum": $("#deviceTypeEnumId").val()
                , "maintainerBean.device.sector.id": $("#sectorId").val()
                , "maintainerBean.device.active": $("#active").is(":checked")
            };
            if ($("#cameraTypeEnumId").val() != -1) {
                var parameters = {
                    "maintainerBean.device.id": $("#deviceId").val()
                    , "maintainerBean.device.name": $("#name").val()
                    , "maintainerBean.device.code": code
                    , "maintainerBean.device.desc": $("#desc").val()
                    , "maintainerBean.device.deviceTypeEnum": $("#deviceTypeEnumId").val()
                    , "maintainerBean.device.sector.id": $("#sectorId").val()
                    , "maintainerBean.device.active": $("#active").is(":checked")

                    , "maintainerBean.device.camera.id": $("#cameraId").val()
                    , "maintainerBean.device.camera.ip": $("#ip").val()
                    , "maintainerBean.device.camera.port": $("#port").val()
                    , "maintainerBean.device.camera.cameraTypeEnum": $("#cameraTypeEnumId").val()
                };
            }

            $.ajax({
                url: "/plcbus/backoffice/maintainer/saveDevice.do",
                method: "POST",
                data: parameters,
                dataType: "json",
                success: function (json) {
                    if (json == "unique_error") {
                        Generic.errorDialogMsg("Codigo de dispositivo ya esta en uso, debe seleccionar otro codigo de dispositivo");
                    } else {
                        if ($("#deviceTypeEnumId").val() == 29) {
                            $("#otherCode option:selected").remove();
                        } else {
                            $("#code option:selected").remove();
                        }

                        //$("select#code option[value='" + $("#code").val() + "']").remove();

                        Generic.dialogOk("Se agrego el dispositivo correctamente.");
                        Device.clean();
                        Device.refresh();
                    }
                }
            });
        } else {
            Generic.errorDialog(ERROR_MESSAGE);
        }
    },
    validate: function() {
        var forward = true;
        ERROR_MESSAGE = [];

        if ($("#deviceTypeEnumId").val() == 29) {
            if ($("#otherCode").val() == -1) {
                ERROR_MESSAGE.push("Debe seleccionar un codigo de dispositivo");
                forward = false;
            }
        } else {
            if ($("#code").val() == -1) {
                ERROR_MESSAGE.push("Debe seleccionar un codigo de dispositivo");
                forward = false;
            }
        }
        if ($("#deviceTypeEnumId").val() == -1) {
            ERROR_MESSAGE.push("Debe seleccionar un tipo de dispositivo");
            forward = false;
        } else {
            if ($("#deviceTypeEnumId").val() == 29) {
                if ($("#ip").val().trim() == "") {
                    ERROR_MESSAGE.push("Debe indicar una IP para la camara");
                    forward = false;
                }
                if ($("#port").val().trim() == "") {
                    ERROR_MESSAGE.push("Debe indicar una puerto para la camara");
                    forward = false;
                }
                if ($("#cameraTypeEnumId").val() == -1) {
                    ERROR_MESSAGE.push("Debe indicar un tipo de camara");
                    forward = false;
                }
            }
        }
        if ($("#sectorId").val() == -1) {
            ERROR_MESSAGE.push("Debe seleccionar un sector");
            forward = false;
        }

        return forward;
    },
    get: function(id) {
        $.ajax({
            url: "/plcbus/backoffice/maintainer/getDevice.do",
            method: "POST",
            data: "maintainerBean.deviceId="+id,
            dataType: "json",
            success: function (json) {
                $("#deviceId").val(json.id);
                $("#name").val(json.name);
                $("#desc").val(json.desc);
                $("#deviceTypeEnumId").val(json.deviceTypeEnum.id);
                $("#sectorId").val(json.sector.id);
                $("#active").prop("checked", json.active);

                if (json.camera != null) {
                    $("#cameraId").val(json.camera.id);
                    $("#ip").val(json.camera.ip);
                    $("#port").val(json.camera.port);
                    $("#cameraTypeEnumId").val(json.camera.cameraTypeEnum.id);
                }

                $("#maintainerLi").attr("class", "active");
                $("#maintainerFrame").attr("style", "frame active");
                $("#deviceListLi").attr("class", "");
                $("#deviceListFrame").attr("style", "frame");

                if (json.deviceTypeEnum.id == 29) {
                    $('#otherCode').append(new Option(json.code, json.code, true, true));
                } else {
                    $('#code').append(new Option(json.code, json.code, true, true));
                }
                Device.changeDeviceType();
            }
        });
    },
    delete: function(id, name) {
        $.Dialog({
            'title': 'Eliminar',
            'content': 'Esta seguro de eliminar el dispositivo '+name+'?',
            'buttons'    : {
                'Ok'    : {
                    'action': function() {
                        $.ajax({
                            url: "/plcbus/backoffice/maintainer/deleteDevice.do",
                            method: "POST",
                            data: "maintainerBean.deviceId="+id,
                            dataType: "json",
                            success: function (json) {
                                if (json == "foreignkey_error") {
                                    $("#dialogBox").children(".header").html("Advertencia");
                                    $("#dialogBox").children(".content").html("Este dispositivo tiene relaciones activas, por ejemplo coordenadas.");
                                } else {
                                    Device.clean();
                                    Device.refresh();
                                }
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
            url: "/plcbus/backoffice/maintainer/deviceList.do",
            method: "POST",
            dataType: "html",
            success: function (html) {
                $("#deviceListFrame").html(html);
            }
        });
    },
    clean: function() {
        $("#deviceId").val("");
        $("#name").val("");
        $("#code").val(-1);
        $("#desc").val("");
        $("#deviceTypeEnumId").val(-1);
        $("#sectorId").val(-1);
        $("#active").attr("checked", true);
        $("#cameraId").val("");
        $("#ip").val("");
        $("#port").val("");
        $("#cameraTypeEnumId").val(-1);

        Device.changeDeviceType();
    },
    changeDeviceType : function() {
        if ($("#deviceTypeEnumId").val() == 29) {
            $("#code").attr("style","display:none;");
            $("#otherCode").attr("style","display:;");
            $("#cameraDiv").show();
        } else {
            $("#code").attr("style","display:;");
            $("#otherCode").attr("style","display:none;");
            $("#cameraDiv").hide();
        }

    }
};

