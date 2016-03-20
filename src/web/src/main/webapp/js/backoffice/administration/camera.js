var Camera = {
    cameraDecoderControl: function (code, commandCode) {
        var f = eval("hostCamera_" +code);
        $.ajax({
            url: "http://" + f + "/decoder_control.cgi?user=admin&command=" + commandCode,
            method: "POST",
            dataType: 'jsonp'
        });
    },
    cameraControl: function (code, paramCode, commandCode) {
        var f = eval("hostCamera_" +code);
        $.ajax({
            url: "http://" + f + "/camera_control.cgi?user=admin&param=" + paramCode + "&value=" + commandCode,
            method: "POST",
            dataType: 'jsonp'
        });
    },
    cameraCommandOff: function (code) {
        Camera.cameraDecoderControl(code, 1);
    },
    cameraUp: function (code) {
        Camera.cameraDecoderControl(code, 0);
    },
    cameraUpRight: function (code) {
        Camera.cameraDecoderControl(code, 91);
    },
    cameraRight: function (code) {
        Camera.cameraDecoderControl(code, 6);
    },
    cameraDownRight: function (code) {
        Camera.cameraDecoderControl(code, 93);
    },
    cameraDown: function (code) {
        Camera.cameraDecoderControl(code, 2);
    },
    cameraDownLeft: function (code) {
        Camera.cameraDecoderControl(code, 92);
    },
    cameraLeft: function (code) {
        Camera.cameraDecoderControl(code, 4);
    },
    cameraUpLeft: function (code) {
        Camera.cameraDecoderControl(code, 90);
    },
    cameraBrightness: function (code) {
        var value = 48; // 48, 96, 128, 168, 210, 255
        Camera.cameraControl(code, 1, value);
    },
    cameraContrast: function (code) {
        var value = 0; // 0-6
        Camera.cameraControl(code, 2, value);
    },
    cameraView: function (code, viewNumber) {
        var value = 0; // 31, 33, 35, 37
        if (viewNumber == 1) {
            value = 31;
        } else if (viewNumber == 2) {
            value = 33;
        } else if (viewNumber == 3) {
            value = 35;
        } else if (viewNumber == 4) {
            value = 37;
        }
        Camera.cameraDecoderControl(code, 4);
    },
    getParameterCamera : function (code) {
        var f = eval("hostCamera_" +code);
        $.ajax({
            url: "http://" + f + "/get_camera_params.cgi?user=admin&callback=myCallbackFunction",
            method: "GET",
            dataType: 'jsonp',

            jsonp: 'myCallbackFunction',
            success: function (jsonp) {
                alert(jsonp);
                alert(jsonp.resolution);
                alert(jsonp.brightness);
                alert(jsonp.contrast);
                alert(jsonp.mode);
                alert(jsonp.flip);
                alert(jsonp.fps);
            }
        });

    },
    saveCameraPosition : function (code, position) {
        var command = 0;
        if (position == 1) {
            command = 30;
        } else if (position == 2) {
            command = 32;
        } else if (position == 3) {
            command = 34;
        } else if (position == 4) {
            command = 36;
        }
        Camera.cameraDecoderControl(code, command);
    },
    viewCameraPosition : function (code, position) {
        var command = 0;
        if (position == 1) {
            command = 31;
        } else if (position == 2) {
            command = 33;
        } else if (position == 3) {
            command = 35;
        } else if (position == 4) {
            command = 37;
        }
        Camera.cameraDecoderControl(code, command);
    },
    flipCamera: function (code, flipHorizontallyId, flipVerticallyId) {
        var command = 0;
        if ($("#"+flipHorizontallyId).is(":checked")) {
            if ($("#"+flipVerticallyId).is(":checked")) {
                Camera.cameraControl(code, 5, 3);
            } else {
                Camera.cameraControl(code, 5, 2);
            }
        } else {
            if ($("#"+flipVerticallyId).is(":checked")) {
                Camera.cameraControl(code, 5, 1);
            } else {
                Camera.cameraControl(code, 5, 0);
            }
        }
    },
    getCamera : function (code) {
        $.ajax({
            url: "/plcbus/backoffice/administration/getCamera.do",
            method: "POST",
            data: "administrationBean.deviceCode="+code,
            dataType: "json",
            success: function (json) {
                $("#deviceId").val(json.id);
                $("#name").val(json.name);
                $('#code').val(json.code);
                $("#deviceTypeEnum").val(json.deviceTypeEnum.name);

                $("#active").prop("checked", json.active);

                $("#ip").val(json.camera.ip);
                $("#port").val(json.camera.port);
                $("#nameCamera").val(json.camera.name);
                $("#cameraTypeEnumId").val(json.camera.cameraTypeEnum.id);
            }
        });

    },
    setFunctionCamera : function (code) {
        //
        $("#up").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#up").attr("onmousedown", Camera.cameraUp(code));
        $("#up").attr("onmouseup", Camera.cameraCommandOff(code));

        $("#up-right").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#up-right").attr("onmousedown", Camera.cameraUpRight(code));
        $("#up-right").attr("onmouseup", Camera.cameraCommandOff(code));

        $("#right").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#right").attr("onmousedown", Camera.cameraRight(code));
        $("#right").attr("onmouseup", Camera.cameraCommandOff(code));

        $("#down-right").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#down-right").attr("onmousedown", Camera.cameraDownRight(code));
        $("#down-right").attr("onmouseup", Camera.cameraCommandOff(code));

        $("#down").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#down").attr("onmousedown", Camera.cameraDown(code));
        $("#down").attr("onmouseup", Camera.cameraCommandOff(code));

        $("#down-left").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#down-left").attr("onmousedown", Camera.cameraDownLeft(code));
        $("#down-left").attr("onmouseup", Camera.cameraCommandOff(code));

        $("#left").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#left").attr("onmousedown", Camera.cameraLeft(code));
        $("#left").attr("onmouseup", Camera.cameraCommandOff(code));

        $("#up-left").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#up-left").attr("onmousedown", Camera.cameraUpLeft(code));
        $("#up-left").attr("onmouseup", Camera.cameraCommandOff(code));

        $("#up-left").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#up-left").attr("onmousedown", Camera.cameraUpLeft(code));
        $("#up-left").attr("onmouseup", Camera.cameraCommandOff(code));

        $("#save-1").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#save-1").attr("onclick", Camera.saveCameraPosition(code, 1));
        $("#save-2").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#save-2").attr("onclick", Camera.saveCameraPosition(code, 2));
        $("#save-3").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#save-3").attr("onclick", Camera.saveCameraPosition(code, 3));
        $("#save-4").attr("style", "color: green; font-size: 20px;cursor:pointer;");
        $("#save-4").attr("onclick", Camera.saveCameraPosition(code, 4));
        $("#view-1").attr("style", "color: blue; font-size: 20px;cursor:pointer;");
        $("#view-1").attr("onclick", Camera.viewCameraPosition(code, 1));
        $("#view-2").attr("style", "color: blue; font-size: 20px;cursor:pointer;");
        $("#view-2").attr("onclick", Camera.viewCameraPosition(code, 2));
        $("#view-3").attr("style", "color: blue; font-size: 20px;cursor:pointer;");
        $("#view-3").attr("onclick", Camera.viewCameraPosition(code, 3));
        $("#view-4").attr("style", "color: blue; font-size: 20px;cursor:pointer;");
        $("#view-4").attr("onclick", Camera.viewCameraPosition(code, 4));

        $("#flipHorizontally").attr("onchange", Camera.flipCamera(code, 'flipHorizontally', 'flipVertically'));
        $("#flipVertically").attr("onchange", Camera.flipCamera(code, 'flipHorizontally', 'flipVertically'));
    }
};

function myCallbackFunction(jsonp){
    alert(jsonp);
}


