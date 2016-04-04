$(document).ready(function () {
    Programming.init();
});

var ERROR_MESSAGE = [];
var Programming = {
    init: function () {
        $("#dayDiv").hide();
        $("#temperatureDiv").hide();
        Programming.checkRangeDiv();
    },
    checkTemperatureDiv: function () {
        if ($("#temperature").is(":checked")) {
            $("#temperatureDiv").show();
        } else {
            $("#temperatureDiv").hide();
        }
    },
    checkDayDiv: function () {
        $("input[name='configurationBean.programmingSummary.programmingTypeEnum']:checked").each(function () {
            if (parseInt($(this).val()) == 1) {
                $("#dayDiv").hide();
            } else if (parseInt($(this).val()) == 2) {
                $("#dayDiv").show();
            } else if (parseInt($(this).val()) == 3) {
                //
            } else if (parseInt($(this).val()) == 4) {
                //
            }
        });
    },
    checkRangeDiv : function () {
        $("input[name='configurationBean.programmingSummary.rangeTypeEnum']:checked").each(function () {
            if (parseInt($(this).val()) == 1) {
                $("#temperatureMinDiv").hide();
                $("#temperatureMaxDiv").show();
                $("#temperatureMinDiv").attr("class","span0");
                $("#temperatureMaxDiv").attr("class","span2");
            } else if (parseInt($(this).val()) == 2) {
                $("#temperatureMinDiv").show();
                $("#temperatureMaxDiv").hide();
                $("#temperatureMinDiv").attr("class","span2");
                $("#temperatureMaxDiv").attr("class","span0");
            } else if (parseInt($(this).val()) == 3) {
                $("#temperatureMinDiv").show();
                $("#temperatureMaxDiv").show();
                $("#temperatureMinDiv").attr("class","span1");
                $("#temperatureMaxDiv").attr("class","span1");
            }
        });
    },
    save: function() {
        if (Programming.validate()) {
            var programmingTypeEnum = $("input[name='configurationBean.programmingSummary.programmingTypeEnum']:checked").val();
            var day2List = new Array();

            var dayList = [];
            var days = "";
            $("input[name='configurationBean.programmingSummary.dayLists']:checked").each(function () {
                dayList.push(parseInt($(this).val()));
                day2List.push($(this).val());
                days = days + ($(this).val()) + ",";
            });

            var rangeTypeEnum = $("input[name='configurationBean.programmingSummary.rangeTypeEnum']:checked").val();

            var parameters = {
                "configurationBean.programmingSummary.programmingTypeEnum": programmingTypeEnum
                , "configurationBean.programmingSummary.reason": $("#reason").val()
                , "configurationBean.programmingSummary.timeSummary.startHourEnum": $("#startHourEnum").val()
                , "configurationBean.programmingSummary.timeSummary.startMinuteEnum": $("#startMinuteEnum").val()
                , "configurationBean.programmingSummary.timeSummary.endHourEnum": $("#endHourEnum").val()
                , "configurationBean.programmingSummary.timeSummary.endMinuteEnum": $("#endMinuteEnum").val()
                , "configurationBean.programmingSummary.deviceId": $("#deviceId").val()
                , "configurationBean.programmingSummary.dayList": dayList
                , "configurationBean.programmingSummary.dayIdArray": day2List
                , "configurationBean.programmingSummary.days": days
                , "configurationBean.programmingSummary.active": $("#active").is(":checked")

                , "configurationBean.programmingSummary.temperature": $("#temperature").is(":checked")
                , "configurationBean.programmingSummary.rangeTypeEnum": rangeTypeEnum
                , "configurationBean.programmingSummary.temperatureSummary.min": $("#min").val()
                , "configurationBean.programmingSummary.temperatureSummary.max": $("#max").val()
            };

            $.ajax({
                url: "/plcbus/backoffice/configuration/saveProgramming.do",
                method: "POST",
                data: parameters,
                dataType: "json",
                success: function (json) {
                    Generic.dialogOk("Se ha programado correctamente.");
                    Programming.clean();
                    Programming.refresh();
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

        $("input[name='configurationBean.programmingSummary.programmingTypeEnum']:checked").each(function () {
            if (parseInt($(this).val()) == 2) {
                if ($("input[name='configurationBean.programmingSummary.dayLists']:checked").length == 0) {
                    ERROR_MESSAGE.push("Debe seleccionar al menos un dia");
                    forward = false;
                }
            }
        });

        if ($("#startHourEnum").val() == $("#endHourEnum").val() && $("#startMinuteEnum").val() == $("#endMinuteEnum").val()) {
            ERROR_MESSAGE.push("Debe indicar un rango de tiempo");
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
                            url: "/plcbus/backoffice/configuration/deleteProgramming.do",
                            method: "POST",
                            data: "configurationBean.programmingId="+id,
                            dataType: "json",
                            success: function (json) {
                                Programming.refresh();
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
            url: "/plcbus/backoffice/configuration/programmingList.do",
            method: "POST",
            dataType: "html",
            success: function (html) {
                $("#programmingListFrame").html(html);
            }
        });
    },
    get: function(id, name) {
        $.ajax({
            url: "/plcbus/backoffice/configuration/getProgramming.do",
            method: "POST",
            data: "configurationBean.programmingId="+id,
            dataType: "json",
            success: function (json) {
                $("#programmingId").val(json.id);

                $("#startHourEnum4UpdateCalendar").val(json.timeSummary.startHourEnum.id);
                $("#startMinuteEnum4UpdateCalendar").val(json.timeSummary.startMinuteEnum.id);
                $("#endHourEnum4UpdateCalendar").val(json.timeSummary.endHourEnum.id);
                $("#endMinuteEnum4UpdateCalendar").val(json.timeSummary.endMinuteEnum.id);

                $("#updateCalendarSpan").show();
            }
        });
    },
    updateCalendar: function() {
        var parameters = {
            "configurationBean.programmingId" : $("#programmingId").val()
            , "configurationBean.timeSummary.startHourEnum": $("#startHourEnum4UpdateCalendar").val()
            , "configurationBean.timeSummary.startMinuteEnum": $("#startMinuteEnum4UpdateCalendar").val()
            , "configurationBean.timeSummary.endHourEnum": $("#endHourEnum4UpdateCalendar").val()
            , "configurationBean.timeSummary.endMinuteEnum": $("#endMinuteEnum4UpdateCalendar").val()
        };

        $.ajax({
            url: "/plcbus/backoffice/configuration/updateCalendarProgramming.do",
            method: "POST",
            data: parameters,
            dataType: "json",
            success: function (json) {
                Programming.refresh();
                $("#updateCalendarSpan").hide();
            }
        });
    },
    clean: function() {
        $("#reason").val("");
        $("#startHourEnum").val(0);
        $("#startMinuteEnum").val(0);
        $("#endHourEnum").val(0);
        $("#endMinuteEnum").val(0);
        $("#deviceId").val(-1);
        $("#active").attr("checked", true);
        $("input[name='configurationBean.programmingSummary.dayLists']").each(function () {
            $(this).attr("checked", false);
        });
        $("input[name='configurationBean.programmingSummary.programmingTypeEnum']").each(function () {
            if (parseInt($(this).val()) == 1) {
                $(this).attr("checked", true);
            } else {
                $(this).attr("checked", false);
            }
        });

        $("#dayDiv").hide();
        $("#temperatureDiv").hide();
        Programming.checkRangeDiv();
    },
    changeStatusProgramming : function(id) {
        var programmingHTMLId = "#statusCheckbox_" + id;
        var parameters = {
            "configurationBean.programmingId" : id
            , "configurationBean.programmingActive": $(programmingHTMLId).is(':checked')
        };

        $.ajax({
            url: "/plcbus/backoffice/configuration/updateStatusProgramming.do",
            method: "POST",
            data: parameters,
            dataType: "json"
        });


    }
};

