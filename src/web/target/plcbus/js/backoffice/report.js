$(document).ready(function () {
    Report.init();
});


var Report = {
    init: function () {
    },
    deviceXls: function() {
        location.href = "/plcbus/backoffice/statistics/deviceXls.do";
    },
    sectorXls: function() {
        location.href = "/plcbus/backoffice/statistics/sectorXls.do";
    },
    levelXls: function() {
        location.href = "/plcbus/backoffice/statistics/levelXls.do";
    },
    eventXls: function() {
        location.href = "/plcbus/backoffice/statistics/eventXls.do";
    },
    sensorXls: function() {
        location.href = "/plcbus/backoffice/statistics/sensorXls.do";
    }

};


