$(document).ready(function () {
    Sector.init();
});

var ERROR_MESSAGE = [];
var Sector = {
    init: function () {
        
    },
    save: function() {
        if (Sector.validate()) {
            var parameters = {
                "maintainerBean.sector.id": $("#sectorId").val()
                , "maintainerBean.sector.name": $("#name").val()
                , "maintainerBean.sector.level.id": $("#levelId").val()
                , "maintainerBean.sector.active": $("#active").is(":checked")
            };

            $.ajax({
                url: "/plcbus/backoffice/maintainer/saveSector.do",
                method: "POST",
                data: parameters,
                dataType: "json",
                success: function (json) {
                    if (json == "unique_error") {
                        Generic.errorDialogMsg("El nombre de zona ya esta en uso, debe seleccionar otro nombre de zona");
                    } else {
                        Generic.dialogOk("Se agrego la zona correctamente.");
                        Sector.clean();
                        Sector.refresh();
                        Sector.refresh();
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

        if ($("#name").val().trim() == "") {
            ERROR_MESSAGE.push("Debe indicar un nombre");
            forward = false;
        }
        if ($("#levelId").val() == -1) {
            ERROR_MESSAGE.push("Debe seleccionar un nivel");
            forward = false;
        }

        return forward;
    },
    get: function(id) {
        $.ajax({
            url: "/plcbus/backoffice/maintainer/getSector.do",
            method: "POST",
            data: "maintainerBean.sectorId="+id,
            dataType: "json",
            success: function (json) {
                $("#sectorId").val(json.id);
                $("#name").val(json.name);
                $("#levelId").val(json.level.id);
                $("#active").prop("checked", json.active);

                $("#maintainerLi").attr("class", "active");
                $("#maintainerFrame").attr("style", "frame active");
                $("#sectorListLi").attr("class", "");
                $("#sectorListFrame").attr("style", "frame");
            }
        });
    },
    delete: function(id, name) {
        $.Dialog({
            'title': 'Eliminar',
            'content': 'Esta seguro de eliminar la zona '+name+'?',
            'buttons'    : {
                'Ok'    : {
                    'action': function() {
                        $.ajax({
                            url: "/plcbus/backoffice/maintainer/deleteSector.do",
                            method: "POST",
                            data: "maintainerBean.sectorId="+id,
                            dataType: "json",
                            success: function (json) {
                                if (json == "foreignkey_error") {
                                    $("#dialogBox").children(".header").html("Advertencia");
                                    $("#dialogBox").children(".content").html("Esta zona tiene relaciones activas, por ejemplo dispositivos asociados a esta zona.");
                                } else {
                                    Sector.clean();
                                    Sector.refresh();
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
            url: "/plcbus/backoffice/maintainer/sectorList.do",
            method: "POST",
            dataType: "html",
            success: function (html) {
                $("#sectorListFrame").html(html);
            }
        });
    },
    clean: function() {
        $("#sectorId").val("");
        $("#name").val("");
        $("#levelId").val(-1);
        $("#active").attr("checked", true);
    }
};

