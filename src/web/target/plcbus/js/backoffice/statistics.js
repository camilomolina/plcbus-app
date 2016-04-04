$(document).ready(function () {
    Statistics.init();
});

var Statistics = {
    init: function () {
        $.ajax({
            url: "/plcbus/backoffice/statistics/consumption.do",
            method: "POST",
            dataType: "json",
            beforeSend: function () {

            },
            success: function (json) {
                var gaugeOptions = {
                    chart: {
                        type: 'solidgauge'
                    },
                    title: null,
                    pane: {
                        center: ['50%', '85%'],
                        size: '140%',
                        startAngle: -90,
                        endAngle: 90,
                        background: {
                            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
                            innerRadius: '60%',
                            outerRadius: '100%',
                            shape: 'arc'
                        }
                    },
                    tooltip: {
                        enabled: false
                    },

                    // the value axis
                    yAxis: {
                        stops: [
                            [0.1, '#55BF3B'], // green
                            [0.5, '#DDDF0D'], // yellow
                            [0.9, '#DF5353'] // red
                        ],
                        lineWidth: 0,
                        minorTickInterval: null,
                        tickPixelInterval: 400,
                        tickWidth: 0,
                        title: {
                            y: -70
                        },
                        labels: {
                            y: 16
                        }
                    },

                    plotOptions: {
                        solidgauge: {
                            dataLabels: {
                                y: 5,
                                borderWidth: 0,
                                useHTML: true
                            }
                        }
                    }
                };

                $('#consumptionGraphic').highcharts(Highcharts.merge(gaugeOptions, {
                    yAxis: {
                        min: 0,
                        max: 100,
                        title: {
                            text: 'Conumo del mes'
                        }
                    },

                    credits: {
                        enabled: false
                    },

                    series: [{
                        name: 'Consumo',
                        data: [json],
                        dataLabels: {
                            format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                                ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                                '<span style="font-size:12px;color:silver">%</span></div>'
                        },
                        tooltip: {
                            valueSuffix: ' %'
                        }
                    }]
                }));
            }
        });

/*
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
                    title: null,
                    series: series
                });
            }
        });
        */
    },
    toReport: function () {
        location.href = "/plcbus/backoffice/statistics/startReport.do";
    },
    toGraphics: function () {
        location.href = "/plcbus/backoffice/statistics/startGraphics.do";
    }

};


