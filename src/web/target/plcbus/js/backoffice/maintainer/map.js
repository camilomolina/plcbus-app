$(document).ready(function () {
    Map.init();
});

var ERROR_MESSAGE = [];
var Map = {
    init: function () {
        
    },
    save: function() {
        if (Map.validate()) {
            var parameters = {
                "maintainerBean.map.id": $("#sectorId").val()
                , "maintainerBean.map.name": $("#name").val()
                , "maintainerBean.map.info": $("#info").val()
                , "maintainerBean.map.desc": $("#desc").val()
                , "maintainerBean.map.path": $("#path").val()
                , "maintainerBean.map.image": $("#image").val()
                , "maintainerBean.map.level.id": $("#levelId").val()
                , "maintainerBean.map.active": $("#active").is(":checked")
            };

            $.ajax({
                url: "/plcbus/backoffice/maintainer/saveMap.do",
                method: "POST",
                data: parameters,
                dataType: "json",
                contentType: 'multipart/form-data',
                success: function (json) {
                    Generic.dialogOk("Se agrego el mapa correctamente.");
                    Sector.clean();
                    Sector.refresh();
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
    edit: function(id) {
        $.ajax({
            url: "/plcbus/backoffice/maintainer/editSector.do",
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
            'content': 'Esta seguro de eliminar el mapa  '+name+'?',
            'buttons'    : {
                'Ok'    : {
                    'action': function() {
                        $.ajax({
                            url: "/plcbus/backoffice/maintainer/deleteSector.do",
                            method: "POST",
                            data: "maintainerBean.sectorId="+id,
                            dataType: "json",
                            success: function (json) {
                                Sector.refresh();
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

