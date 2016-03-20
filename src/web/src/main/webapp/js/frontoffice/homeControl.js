var Control = {
    changeStatusDevice: function (device, deviceId) {
        var deviceHTMLId = "#statusCheckbox_" + device;
        if ($(deviceHTMLId).is(':checked')) {
            Control.onDevice(deviceId);
        } else {
            Control.offDevice(deviceId);
        }
    },
    onDevice: function (deviceId, dialog) {
        //enciende dispositivo

        $.ajax({
            url: "/plcbus/frontoffice/control/on.do",
            method: "POST",
            data: "controlBean.deviceId=" + deviceId,
            dataType: "json",
            beforeSend: function () {
                if (dialog) {
                    $.Dialog({
                        'title': 'On',
                        'content': '<i class="icon-lightning"></i> .. Enviando comando de encendido<br/><br/><div style="text-align: center"><img src="/plcbus/images/metro/preloader-w8-line-black.gif" alt="sendCommand" /></div><br/>',
                        'buttons': {
                        }
                    });
                }
            },
            success: function (json) {
                $.Dialog.close();
            }
        });
    },
    offDevice: function (deviceId, dialog) {

        $.ajax({
            url: "/plcbus/frontoffice/control/off.do",
            method: "POST",
            data: "controlBean.deviceId=" + deviceId,
            dataType: "json",
            beforeSend: function () {
                if (dialog) {
                    $.Dialog({
                        'title': 'Off',
                        'content': '<i class="icon-lightning"></i> .. Enviando comando de apagado<br/><br/><div style="text-align: center"><img src="/plcbus/images/metro/preloader-w8-line-black.gif" alt="sendCommand" /></div><br/>',
                        'buttons': {
                        }
                    });
                }
            },
            success: function (json) {
                $.Dialog.close();
            }
        });
    },
    /*
    statusDevice: function (device, deviceId) {
        var deviceHTMLId = "#statusDevice_" + device;
        var deviceIHTMLId = "#statusDeviceI_" + device;
        var deviceCheckHTMLId = "#statusCheckbox_" + device;
        var deviceCheckHTMLId2 = "statusCheckbox_" + device;

        $(deviceIHTMLId).attr('class', 'icon-loading');
        $(deviceIHTMLId).attr('disabled', true);

        $.ajax({
            url: "/plcbus/frontoffice/control/status.do",
            method: "POST",
            data: "controlBean.deviceId=" + deviceId,
            dataType: "json",
            success: function (json) {
                //$(deviceCheckHTMLId).attr('checked', json.statusResponse);
                document.getElementById(deviceCheckHTMLId2).checked = json.statusResponse;
                $(deviceIHTMLId).attr('class', 'icon-eye');
                $(deviceIHTMLId).attr('disabled', false);
            }
        });
    },
    */
    dimmerDevice: function (deviceId, delay, dimmer) {
        //enciende dispositivo

        var delayNumber = Number(delay);
        var dimmerNumber = Number(dimmer);

        var parameters = {
            "controlBean.deviceId": deviceId
            , "controlBean.deplay": delayNumber.toFixed(0)
            , "controlBean.dimmer": dimmerNumber.toFixed(0)
        };

        $.ajax({
            url: "/plcbus/frontoffice/control/dimmer.do",
            method: "POST",
            data: parameters,
            dataType: "json",
            beforeSend: function () {
                $.Dialog({
                    'title': 'Dimmer Set',
                    'content': '<i class="icon-lightning"></i> .. Enviando comando dimmer<br/><br/><div style="text-align: center"><img src="/plcbus/images/metro/preloader-w8-line-black.gif" alt="sendCommand" /></div><br/>',
                    'buttons': {
                    }
                });
            },
            success: function (json) {
                $.Dialog.close();
            }
        });
    }
};


