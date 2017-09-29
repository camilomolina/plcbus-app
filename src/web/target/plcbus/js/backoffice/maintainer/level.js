$(document).ready(function () {
    Level.init();
});

var ERROR_MESSAGE = [];
var Level = {
    init: function () {
        
    },
    save: function() {
        if (Level.validate()) {
            var parameters = {
                "maintainerBean.level.id": $("#levelId").val()
                , "maintainerBean.level.property.id": $("#propertyId").val()
                , "maintainerBean.level.name": $("#name").val()
                , "maintainerBean.level.active": $("#active").is(":checked")
            };

            $.ajax({
                url: "/plcbus/backoffice/maintainer/saveLevel.do",
                method: "POST",
                data: parameters,
                dataType: "json",
                success: function (json) {
                    if (json == "unique_error") {
                        Generic.errorDialogMsg("Nombre de nivel ya esta en uso, debe seleccionar otro nombre de nivel");
                    } else {
                        Generic.dialogOk("Se agrego el nivel correctamente.");
                        Level.clean();
                        Level.refresh();
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
        if ($("#propertyId").val() == null ||  $("#propertyId").val().trim() == "") {
            ERROR_MESSAGE.push("Debe tener una propiedad ingresada");
            forward = false;
        }

        return forward;
    },
    get: function(id) {
        $.ajax({
            url: "/plcbus/backoffice/maintainer/getLevel.do",
            method: "POST",
            data: "maintainerBean.levelId="+id,
            dataType: "json",
            success: function (json) {
                $("#levelId").val(json.id);
                $("#name").val(json.name);
                $("#active").prop("checked", json.active);

                $("#maintainerLi").attr("class", "active");
                $("#maintainerFrame").attr("style", "frame active");
                $("#levelListLi").attr("class", "");
                $("#levelListFrame").attr("style", "frame");
            }
        });
    },
    delete: function(id, name) {
        $.Dialog({
            'title': 'Eliminar',
            'content': 'Esta seguro de eliminar el nivel '+name+'?',
            'buttons'    : {
                'Ok'    : {
                    'action': function() {
                        $.ajax({
                            url: "/plcbus/backoffice/maintainer/deleteLevel.do",
                            method: "POST",
                            data: "maintainerBean.levelId="+id,
                            dataType: "json",
                            success: function (json) {
                                if (json == "foreignkey_error") {
                                    $("#dialogBox").children(".header").html("Advertencia");
                                    $("#dialogBox").children(".content").html("Este nivel tiene relaciones activas, por ejemplo zonas asociadas a este nivel.");
                                } else {
                                    Level.clean();
                                    Level.refresh();
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
            url: "/plcbus/backoffice/maintainer/levelList.do",
            method: "POST",
            dataType: "html",
            success: function (html) {
                $("#levelListFrame").html(html);
            }
        });
    },
    configMap: function(levelId) {
        location.href = "/plcbus/backoffice/maintainer/configMap.do?maintainerBean.levelId="+levelId;
    },
    clean: function() {
        $("#levelId").val("");
        $("#name").val("");
        $("#active").attr("checked", true);
    }
};

