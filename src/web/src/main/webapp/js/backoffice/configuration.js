$(document).ready(function () {
    Configuration.init();
});


var Configuration = {
    init: function () {

    },
    toProgramming: function() {
        location.href = "/plcbus/backoffice/configuration/startProgramming.do";
    },
    toScheduledShutdown: function() {
        location.href = "/plcbus/backoffice/configuration/startScheduledShutdownDevice.do";
    },
    toMovementAction: function() {
        location.href = "/plcbus/backoffice/configuration/startMovementAction.do";
    },
    toSynchronized: function() {
        location.href = "/plcbus/backoffice/configuration/startSynchronized.do";
    },
    toProperty: function() {
        location.href = "/plcbus/backoffice/configuration/startPropertyData.do";
    },
    toDevice: function() {
        location.href = "/plcbus/backoffice/maintainer/startDevice.do";
    },
    toMap: function() {
        location.href = "/plcbus/backoffice/maintainer/startMap.do";
    },
    toSector: function() {
        location.href = "/plcbus/backoffice/maintainer/startSector.do";
    },
    toLevel: function() {
        location.href = "/plcbus/backoffice/maintainer/startLevel.do";
    },
    toCamera: function() {
        location.href = "/plcbus/backoffice/administration/startCamera.do";
    },
    toIR: function() {
        //location.href = "/plcbus/backoffice/administration/startCamera.do";
    },
    toMagnetic: function() {
        //location.href = "/plcbus/backoffice/administration/startCamera.do";
    }


};


