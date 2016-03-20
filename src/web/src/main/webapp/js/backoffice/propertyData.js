$(document).ready(function () {
    PropertyData.init();
});

var ERROR_MESSAGE = [];
var PropertyData = {
    init: function () {

    },
    save: function() {
        if (PropertyData.validate()) {
            var parameters = {
                "configurationBean.property.id": $("#propertyId").val()
                , "configurationBean.property.address": $("#address").val()
                , "configurationBean.property.phone": $("#phone").val()
                , "configurationBean.property.mail": $("#mail").val()
                , "configurationBean.property.name": $("#name").val()
            };

            $.ajax({
                url: "/plcbus/backoffice/configuration/savePropertyData.do",
                method: "POST",
                data: parameters,
                dataType: "json",
                success: function (json) {
                    Generic.dialogOk("Se ha guardado correctamente");
                }
            });
        } else {
            Generic.errorDialog(ERROR_MESSAGE);
        }
    },
    validate: function() {
        var forward = true;
        ERROR_MESSAGE = [];

        /*
        if ($("#phone").val().length != 8) {
            ERROR_MESSAGE.push("");
            forward = false;
        }
        if ($("#mail").val() == -1) {
            ERROR_MESSAGE.push("Debe seleccionar un dispositivo");
            forward = false;
        }
        */
        return forward;
    }
};

