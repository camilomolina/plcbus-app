$(document).ready(function () {
    Graphics.init();
});


var Graphics = {
    init: function () {

        $.ajax({
            url: "/plcbus/backoffice/statistics/matrixDeviceVsEvent.do",
            method: "POST",
//            data: "controlBean.deviceId=" + deviceId,
            dataType: "json",
            beforeSend: function () {

            },
            success: function (json) {
                var labels = [];
                var values = [];
                for (var i = 0; i < json.length; i++) {
                    values[i] = json[i][0];
                    labels[i] = json[i][1].name;
                }
                $('#eventsGraphic').highcharts({
                    title: {
                        text: 'Eventos por dispositivo',
                        x: -20 //center
                    },
                    subtitle: {
                        text: 'Grafico de los eventos ocurridos durante el mes',
                        x: -20
                    },
                    xAxis: {
                        categories: labels
                    },
                    yAxis: {
                        title: {
                            text: 'Cantidad'
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    tooltip: {
                        valueSuffix: ''
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                        borderWidth: 0
                    },
                    series: [{
                        name: 'Dispositivos',
                        data: values
                    }]
                });

            }
        });

        $.ajax({
            url: "/plcbus/backoffice/statistics/matrixConsumer.do",
            method: "POST",
            dataType: "json",
            beforeSend: function () {

            },
            success: function (json) {

                var labels = [];
                var values = [];
                var valuesM = [];
                for (var i = 0; i < json.length; i++) {
                    labels[i] = json[i].deviceName;
                    values[i] = json[i].cost;
                    valuesM[i] = json[i].hour;
                }

                $('#consumerGraphic').highcharts({
                    title: {
                        text: 'Consumo',
                        x: -20 //center
                    },
                    subtitle: {
                        text: 'Consumo del mes calculado segun costo promedio (88 pesos por kWh)',
                        x: -20
                    },
                    xAxis: {
                        categories: labels
                    },
                    yAxis: {
                        title: {
                            text: 'Pesos'
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    tooltip: {
                        valuePrefix: '$',
                        valueSuffix: '.-'
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                        borderWidth: 0
                    },
                    series: [{
                        name: 'CPL',
                        data: values
                    }]
                });

            }
        });


        $.ajax({
            url: "/plcbus/backoffice/statistics/matrixSensor.do",
            method: "POST",
            dataType: "json",
            beforeSend: function () {

            },
            success: function (json) {
                var series2 = [];
                for (var i = 0; i < json.length; i++) {
                    var values2 = [];
                    for (var j = 0; j < json[i].sensor4Day2List.length; j++) {
                        var m2 = [json[i].sensor4Day2List[j].day, json[i].sensor4Day2List[j].hour];

                        values2.push(m2);
                    }

                    var data2 = {name : json[i].deviceName
                        , data : values2
                    };

                    series2.push(data2)
                }

                $('#sensor2Graphic').highcharts({
                    chart: {
                        type: 'scatter',
                        zoomType: 'xy'
                    },
                    title: {
                        text: 'Sensores'
                    },
                    subtitle: {
                        text: 'Sensores de movimiento'
                    },
                    xAxis: {
                        title: {
                            enabled: true,
                            text: 'Dia del mes'
                        },
                        startOnTick: true,
                        endOnTick: true,
                        showLastLabel: true
                    },
                    yAxis: {
                        title: {
                            text: 'Hora del dia'
                        }
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'left',
                        verticalAlign: 'top',
                        x: 100,
                        y: 70,
                        floating: true,
                        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',
                        borderWidth: 1
                    },
                    plotOptions: {
                        scatter: {
                            marker: {
                                radius: 3,
                                states: {
                                    hover: {
                                        enabled: true,
                                        lineColor: 'rgb(100,100,100)'
                                    }
                                }
                            },
                            states: {
                                hover: {
                                    marker: {
                                        enabled: false
                                    }
                                }
                            },
                            tooltip: {
                                headerFormat: '<b>{series.name}</b><br>',
                                pointFormat: 'Dia {point.x} a las {point.y} horas'
                            }
                        }
                    },
                    series: series2
                });
            }
        });


        $.ajax({
            url: "/plcbus/backoffice/statistics/matrixSensorWeek.do",
            method: "POST",
            dataType: "json",
            beforeSend: function () {

            },
            success: function (json) {
                var series = [];
                for (var i = 0; i < json.length; i++) {
                    var values = [];
                    for (var j = 0; j < json[i].sensor4Day2List.length; j++) {
                        var m = [json[i].sensor4Day2List[j].day, json[i].sensor4Day2List[j].hour, json[i].sensor4Day2List[j].eventCount];

                        values.push(m);
                    }

                    var data = {name : json[i].deviceName
                        , data : values
                    };

                    series.push(data);
                }

                $('#sensorGraphic').highcharts({
                    chart: {
                        type: 'bubble',
                        zoomType: 'xy'
                    },

                    title: {
                        text: 'Sensores'
                    },

                    series: series
                });
            }
        });

    }

};


