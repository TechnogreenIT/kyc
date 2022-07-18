function chartGraph(title, y_axis_lebel, divId, queryObject) {
    var listlabel = new Array();
    var config = {
        type: 'bar',
        data: {
            labels: [],
            datasets: []
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: title,
                fontSize: 20,
            },
            animation: {
                duration: 2600, // general animation time
                easing: 'easeInQuad'
            },
            hover: {
                animationDuration: 0, // duration of animations when hovering an item
            },
            responsiveAnimationDuration: 0, // animation duration after a resize
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Days'
                    },
                    scalePositionLeft: true,
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: y_axis_lebel
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            pan: {
                enabled: true,
                mode: "xy",
                speed: 10,
                threshold: 10
            },
            zoom: {
                enabled: true,
                drag: false,
                mode: "xy",
                limits: {
                    max: 10,
                    min: 0.5
                }
            }
        },

    };

    var ctx = document.getElementById(divId).getContext("2d");
    //drawChart
    window.myLine = new Chart(ctx, config);
    for (var i = 0; i < queryObject.length; i++) {
        var cube = queryObject[i];
        if (i == 0) {
            for (var j = 1; j < cube.length; j++) {
                var array_element = cube[j];
                listlabel.push(array_element);
            }
        }
        if (i > 0) {
            var colorCode = "";
            var linecolorIndex = i - 1;
            var barcolorIndex = i - 2;
            var typee = "";
            var mydata = new Array();
            if (isEven(i)) {
                typee = "bar";
                colorCode = colorsList[barcolorIndex];
            } else {
                typee = "line";
                colorCode = colorsList[linecolorIndex];
            }
            var newDataset = {
                type: typee,
                label: cube[0],
                backgroundColor: colorCode,
                borderColor: colorCode,
                borderWidth: 1,
                data: [],
                fill: false,
                pointRadius: 1,
                pointHoverRadius: 3,
                borderWidth: 1
            };
            for (var p = 1; p < cube.length; p++) {
                mydata.push(cube[p]);
            }
            for (var index = 0; index < mydata.length; ++index) {
                newDataset.data.push(mydata[index]);
            }
            config.data.datasets.push(newDataset);
        }
    }
    for (var i = 0; i < listlabel.length; i++) {
        config.data.labels.push(listlabel[i]);
    }
    window.myLine.update();
}

function chartGraphForGraphs(title, y_axis_lebel, divId, queryObject) {
    var listlabel = new Array();
    var config = {
        type: 'bar',
        data: {
            labels: [],
            datasets: []
        },
        options: {

            responsive: true,
            title: {
                display: true,
                text: title,
                fontSize: 20,
            },
            animation: {
                duration: 2600, // general animation time
                easing: 'easeInQuad'
            },
            hover: {
                animationDuration: 0, // duration of animations when hovering an item
            },
            responsiveAnimationDuration: 0, // animation duration after a resize
            /*hover: {
              mode: 'nearest',
              intersect: true
            },*/
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Days'
                    },
                    scalePositionLeft: true,
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: y_axis_lebel
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            pan: {
                enabled: true,
                mode: "xy",
                speed: 10,
                threshold: 10
            },
            zoom: {
                enabled: true,
                drag: false,
                mode: "xy",
                limits: {
                    max: 10,
                    min: 0.5
                }
            }
        }
    };

    var ctx = document.getElementById(divId).getContext("2d");
    //clear Chart
    if (window.myLine != undefined) {
        window.myLine.destroy();
    }
    //drawChart
    window.myLine = new Chart(ctx, config);
    for (var i = 0; i < queryObject.length; i++) {
        var cube = queryObject[i];
        if (i == 0) {
            for (var j = 1; j < cube.length; j++) {
                var array_element = cube[j];
                listlabel.push(array_element);
            }
        }
        if (i > 0) {
            var colorCode = "";
            var linecolorIndex = i - 1;
            var barcolorIndex = i - 2;
            var typee = "";
            var mydata = new Array();
            if (isEven(i)) {
                typee = "bar";
                colorCode = colorsList[barcolorIndex];
            } else {
                typee = "line";
                colorCode = colorsList[linecolorIndex];
            }
            var newDataset = {
                type: typee,
                label: cube[0],
                backgroundColor: colorCode,
                borderColor: colorCode,
                borderWidth: 1,
                data: [],
                fill: false,
                pointRadius: 1,
                pointHoverRadius: 3,
                borderWidth: 1
            };
            for (var p = 1; p < cube.length; p++) {
                mydata.push(cube[p]);
            }
            for (var index = 0; index < mydata.length; ++index) {
                newDataset.data.push(mydata[index]);
            }
            config.data.datasets.push(newDataset);
        }
    }
    for (var i = 0; i < listlabel.length; i++) {
        config.data.labels.push(listlabel[i]);
    }
    window.myLine.update();
}

function chartgraphSingleLine(title, y_axis_lebel, divId, queryObject) {
    var listlabel = new Array();
    var config = {
        type: 'bar',
        data: {
            labels: [],
            datasets: []
        },
        options: {

            responsive: true,
            title: {
                display: true,
                text: title,
                fontSize: 20
            },
            animation: {
                duration: 2600, // general animation time
                easing: 'easeInQuad'
            },
            hover: {
                animationDuration: 0, // duration of animations when hovering an item
            },
            responsiveAnimationDuration: 0, // animation duration after a resize
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Days'
                    },
                    scalePositionLeft: true,
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: y_axis_lebel
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            pan: {
                enabled: true,
                mode: "xy",
                speed: 10,
                threshold: 10
            },
            zoom: {
                enabled: true,
                drag: false,
                mode: "xy",
                limits: {
                    max: 10,
                    min: 0.5
                }
            }
        }
    };

    var ctx = document.getElementById(divId).getContext("2d");
    //clear Chart
    if (window.myLine != undefined) {
        window.myLine.destroy();
    }
    //drawChart
    window.myLine = new Chart(ctx, config);
    for (var i = 0; i < queryObject.length; i++) {
        var cube = queryObject[i];
        if (i == 0) {
            for (var j = 1; j < cube.length; j++) {
                var array_element = cube[j];
                listlabel.push(array_element);
            }
        }
        if (i > 0) {
            var waterLineColor = "";
            var typee = "";
            var mydata = new Array();
            if (i == 1) {
                typee = "line";
                waterLineColor = "#0000ff";
            } else {
                waterLineColor = colorsList[i];
                typee = "bar";
            }

            var newDataset = {
                type: typee,
                label: cube[0],
                borderWidth: 1,
                bezierCurve: false,
                backgroundColor: waterLineColor,
                borderColor: waterLineColor,
                borderWidth: 1,
                data: [],
                fill: false,
                pointRadius: 1,
                pointHoverRadius: 3,
                borderWidth: 1
            };
            for (var p = 1; p < cube.length; p++) {
                mydata.push(cube[p]);
            }
            for (var index = 0; index < mydata.length; ++index) {
                newDataset.data.push(mydata[index]);
            }
            config.data.datasets.push(newDataset);
        }

    }
    for (var i = 0; i < listlabel.length; i++) {
        config.data.labels.push(listlabel[i]);
    }
    window.myLine.update();
}

//for mansgement

function chartgraphSingleLine1(title, y_axis_lebel, divId, queryObject) {
    var listlabel = new Array();
    var config = {
        type: 'bar',
        data: {
            labels: [],
            datasets: []
        },
        options: {

            responsive: true,
            title: {
                display: true,
                text: title,
                fontSize: 20
            },
            animation: {
                duration: 2600, // general animation time
                easing: 'easeInQuad'
            },
            hover: {
                animationDuration: 0, // duration of animations when hovering an item
            },
            responsiveAnimationDuration: 0, // animation duration after a resize
            scales: {
                xAxes: [{
                    display: true,
                    stacked: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Days'
                    },
                    scalePositionLeft: true,
                }],
                yAxes: [{
                    display: true,
                    stacked: true,
                    scaleLabel: {
                        display: true,
                        labelString: y_axis_lebel
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            pan: {
                enabled: true,
                mode: "xy",
                speed: 10,
                threshold: 10
            },
            zoom: {
                enabled: true,
                drag: false,
                mode: "xy",
                limits: {
                    max: 10,
                    min: 0.5
                }
            }
        }
    };

    var ctx = document.getElementById(divId).getContext("2d");
    //clear Chart

    //drawChart
    window.myLine = new Chart(ctx, config);
    for (var i = 0; i < queryObject.length; i++) {
        var cube = queryObject[i];
        if (i == 0) {
            for (var j = 1; j < cube.length; j++) {
                var array_element = cube[j];
                listlabel.push(array_element);
            }
        }
        if (i > 0) {
            var waterLineColor = "";
            var typee = "";
            var mydata = new Array();
            if (i == 1) {
                typee = "line";
                waterLineColor = "#0000ff";
            } else {
                waterLineColor = colorsList[i];
                typee = "bar";
            }

            var newDataset = {
                type: typee,
                label: cube[0],
                borderWidth: 1,
                bezierCurve: false,
                backgroundColor: waterLineColor,
                borderColor: waterLineColor,
                borderWidth: 1,
                data: [],
                fill: false,
                pointRadius: 2,
                pointHoverRadius: 3,
                borderWidth: 1
            };
            for (var p = 1; p < cube.length; p++) {
                mydata.push(cube[p]);
            }
            for (var index = 0; index < mydata.length; ++index) {
                newDataset.data.push(mydata[index]);
            }
            config.data.datasets.push(newDataset);
        }

    }
    for (var i = 0; i < listlabel.length; i++) {
        config.data.labels.push(listlabel[i]);
    }
    window.myLine.update();
}

//single_bar
function chartBarGraph(title, y_axis_lebel, divId, queryObject) {
    var listlabel = new Array();

    var config = {
        type: 'bar',
        data: {
            labels: [],
            datasets: []
        },
        options: {
            
            responsive: true,
            title: {
                display: true,
                text: title,
                fontSize: 20,
            },
            animation: {
                duration: 2600, // general animation time
                easing: 'easeInQuad'
            },
            hover: {
                animationDuration: 0, // duration of animations when hovering
                // an item
            },
            responsiveAnimationDuration: 0, // animation duration after a
            // resize
            hover: {
                mode: 'label'
            },
            scales: {
                xAxes: [{
                    display: true,
                    zooming: true,
                    stacked: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Days'
                    },
                    scalePositionLeft: true,
                }],
                yAxes: [{
                    display: true,
                    zooming: true,
                    stacked: true,
                    scaleLabel: {
                        display: true,
                        labelString: y_axis_lebel
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            pan: {
                enabled: true,
                mode: "xy",
                speed: 10,
                threshold: 10
            },
            zoom: {
                enabled: true,
                drag: false,
                mode: "xy",
                limits: {
                    max: 10,
                    min: 0.5
                }
            }
        }
    };

    if (myLine) {
        myLine.destroy();
    }
    var ctx = document.getElementById(divId).getContext("2d");
    myLine = new Chart(ctx, config);
    var colorCode = "";
    for (var i = 0; i < queryObject.length; i++) {
        var cube = queryObject[i];
        if (i == 0) {
            for (var j = 1; j < cube.length; j++) {
                var array_element = cube[j];
                listlabel.push(array_element);
            }
        }
        if (i > 0) {
            var typee = "bar";
            var mydata = new Array();
            var barcolorIndex = i - 1;
            colorCode = colorsList[barcolorIndex];

            /*var colorName = colorNames[config.data.datasets.length
            		% colorNames.length];
            var newColor = window.chartColors[colorName];*/
            var newDataset = {
                type: typee,
                label: cube[0],
                backgroundColor: colorCode,
                borderColor: colorCode,
                data: [],
                fill: false,
                pointRadius: 1,
                pointHoverRadius: 3,
                borderWidth: 1
                // and not lineWidth
            };
            for (var p = 1; p < cube.length; p++) {
                mydata.push(cube[p]);
            }
            for (var index = 0; index < mydata.length; ++index) {
                var temp = mydata[index].toFixed(2);
                newDataset.data.push(temp);
            }
            config.data.datasets.push(newDataset);
        }
    }
    for (var i = 0; i < listlabel.length; i++) {
        config.data.labels.push(listlabel[i]);
    }
    myLine.update();
}

function getPrevDate(date) {
    var c = new Date(date);
    c.setDate(c.getDate() - 30);
    x = c.getMonth() + 1;
    y = c.getDate();
    if (x < 10) {
        x = "0" + x;
    }
    if (y < 10) {
        y = "0" + y;
    }
    prev_date = c.getFullYear() + '-' + x + '-' + y;
    return prev_date;
}

//single_Line
function drawChartJs(title, y_axis_lebel, divId, queryObject) {

    $('#newDate').val(today_date);
    var prevDate = getPrevDate(today_date);
    $('#prevDate').val(prevDate);

    var listlabel = new Array();

    var config = {
        type: 'line',
        data: {
            labels: [],
            datasets: []
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: title,
                fontSize: 20,
            },
            animation: {
                duration: 2600, // general animation time
                easing: 'easeInQuad'
            },
            hover: {
                animationDuration: 0, // duration of animations when hovering
                // an item
            },
            responsiveAnimationDuration: 0, // animation duration after a
            // resize
            hover: {
                mode: 'label'
            },
            scales: {
                xAxes: [{
                    display: true,
                    zooming: true,
                    stacked: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Days'
                    },
                    scalePositionLeft: true,
                }],
                yAxes: [{
                    display: true,
                    zooming: true,
                    stacked: true,
                    scaleLabel: {
                        display: true,
                        labelString: y_axis_lebel
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    };

    if (myLine) {
        myLine.destroy();
    }
    var ctx = document.getElementById(divId).getContext("2d");
    myLine = new Chart(ctx, config);
    var colorCode = "";
    for (var i = 0; i < queryObject.length; i++) {
        var cube = queryObject[i];
        if (i == 0) {
            for (var j = 1; j < cube.length; j++) {
                var array_element = cube[j];
                listlabel.push(array_element);
            }
        }
        if (i > 0) {
            var typee = "line";
            var mydata = new Array();
            var linecolorIndex = i - 1;
            colorCode = colorsList[linecolorIndex];

            /*var colorName = colorNames[config.data.datasets.length
            		% colorNames.length];
            var newColor = window.chartColors[colorName];*/
            var newDataset = {
                type: typee,
                label: cube[0],
                backgroundColor: colorCode,
                borderColor: colorCode,
                data: [],
                fill: false,
                pointRadius: 1,
                pointHoverRadius: 3,
                borderWidth: 1
                // and not lineWidth
            };
            for (var p = 1; p < cube.length; p++) {
                mydata.push(cube[p]);
            }
            for (var index = 0; index < mydata.length; ++index) {
                var temp = mydata[index].toFixed(2);
                newDataset.data.push(temp);
            }
            config.data.datasets.push(newDataset);
        }
    }
    for (var i = 0; i < listlabel.length; i++) {
        config.data.labels.push(listlabel[i]);
    }
    myLine.update();

    statisticalAnalysis('source');
    dataQualityPerformanceStat('source');
    dailyDataStat('source');
}

//single_Line
function drawLineChartChartJs(title, y_axis_lebel, divId, queryObject) {
    var listlabel = new Array();
    var ctx = document.getElementById(divId).getContext("2d");

    var config = {
        type: 'line',
        data: {
            labels: [],
            datasets: []
        },
        options: {
            
            responsive: true,
            title: {
                display: true,
                text: title,
                fontSize: 20,
            },
            animation: {
                duration: 2600, // general animation time
                easing: 'easeInQuad'
            },
            hover: {
                animationDuration: 0, // duration of animations when hovering
                // an item
            },
            responsiveAnimationDuration: 0, // animation duration after a
            // resize
            scales: {
                xAxes: [{
                    display: true,
                    zooming: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Days'
                    },
                    scalePositionLeft: true,
                }],
                yAxes: [{
                    display: true,
                    zooming: true,
                    scaleLabel: {
                        display: true,
                        labelString: y_axis_lebel
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    };

    myLine = new Chart(ctx, config);

    if (myLine) {
        myLine.destroy();
    }
    var colorCode = "";
    for (var i = 0; i < queryObject.length; i++) {
        var cube = queryObject[i];
        if (i == 0) {
            for (var j = 1; j < cube.length; j++) {
                var array_element = cube[j];
                listlabel.push(array_element);
            }
        }
        if (i > 0) {
            var typee = "line";
            var mydata = new Array();
            var linecolorIndex = i - 1;
            colorCode = colorsList[linecolorIndex];

            /*var colorName = colorNames[config.data.datasets.length
            		% colorNames.length];
            var newColor = window.chartColors[colorName];*/
            var newDataset = {
                type: typee,
                label: cube[0],
                backgroundColor: colorCode,
                borderColor: colorCode,
                data: [],
                fill: false,
                pointRadius: 1,
                pointHoverRadius: 3,
                borderWidth: 1
                // and not lineWidth
            };
            for (var p = 1; p < cube.length; p++) {
                mydata.push(cube[p]);
            }
            for (var index = 0; index < mydata.length; ++index) {
                var temp = mydata[index].toFixed(2);
                newDataset.data.push(temp);
            }
            config.data.datasets.push(newDataset);
        }
    }
    for (var i = 0; i < listlabel.length; i++) {
        config.data.labels.push(listlabel[i]);
    }
    myLine.update();
}

//graph tab
function lineOnlyGraph(title, y_axis_lebel, divId, queryObject) {
    var listlabel = new Array();
    var config = {
        type: 'line',
        data: {
            labels: [],
            datasets: []
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: title,
                fontSize: 20
            },
            animation: {
                duration: 2600, // general animation time
                easing: 'easeInQuad'
            },
            hover: {
                animationDuration: 0, // duration of animations when hovering
                // an item
            },
            responsiveAnimationDuration: 0, // animation duration after a
            // resize
            pan: {
                enabled: true,
                mode: "x",
                speed: 10,
                threshold: 10
            },
            zoom: {
                enabled: true,
                drag: false,
                mode: "xy",
                limits: {
                    max: 10,
                    min: 0.5
                }
            },
            /*
             * hover: { mode: 'nearest', intersect: true },
             */
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Days'
                    },
                    scalePositionLeft: true,
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: y_axis_lebel
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    };
    var ctx = document.getElementById(divId).getContext("2d");
    //clear Chart
    if (window.myLine != undefined) {
        window.myLine.destroy();
    }
    //drawChart
    window.myLine = new Chart(ctx, config);

    for (var i = 0; i < queryObject.length; i++) {
        var cube = queryObject[i];
        if (i == 0) {
            for (var j = 1; j < cube.length; j++) {
                var array_element = cube[j];
                listlabel.push(array_element);
            }
        }
        if (i > 0) {
            var waterLineColor = "";
            var typee = "";
            var mydata = new Array();
            waterLineColor = colorsList[i];
            typee = "line";

            var newDataset = {
                type: typee,
                label: cube[0],
                borderWidth: 1,
                bezierCurve: false,
                backgroundColor: waterLineColor,
                borderColor: waterLineColor,
                borderWidth: 1,
                data: [],
                fill: false,
                pointRadius: 1,
                pointHoverRadius: 3,
                borderWidth: 1
            };
            for (var p = 1; p < cube.length; p++) {
                mydata.push(cube[p]);
            }
            for (var index = 0; index < mydata.length; ++index) {
                newDataset.data.push(mydata[index]);
            }
            config.data.datasets.push(newDataset);
        }

    }
    for (var i = 0; i < listlabel.length; i++) {
        config.data.labels.push(listlabel[i]);
    }
    window.myLine.update();
}


function chartgraphSingleLine1(title, y_axis_lebel, divId, queryObject) {
    var listlabel = new Array();
    var config = {
        type: 'bar',
        data: {
            labels: [],
            datasets: []
        },
        options: {

            responsive: true,
            title: {
                display: true,
                text: title,
                fontSize: 20
            },
            animation: {
                duration: 2600, // general animation time
                easing: 'easeInQuad'
            },
            hover: {
                animationDuration: 0, // duration of animations when hovering an item
            },
            responsiveAnimationDuration: 0, // animation duration after a resize
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Days'
                    },
                    scalePositionLeft: true,
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: y_axis_lebel
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            pan: {
                enabled: true,
                mode: "xy",
                speed: 10,
                threshold: 10
            },
            zoom: {
                enabled: true,
                drag: false,
                mode: "xy",
                limits: {
                    max: 10,
                    min: 0.5
                }
            }
        }
    };

    var ctx = document.getElementById(divId).getContext("2d");
    //clear Chart

    //drawChart
    window.myLine = new Chart(ctx, config);
    for (var i = 0; i < queryObject.length; i++) {
        var cube = queryObject[i];
        if (i == 0) {
            for (var j = 1; j < cube.length; j++) {
                var array_element = cube[j];
                listlabel.push(array_element);
            }
        }
        if (i > 0) {
            var waterLineColor = "";
            var typee = "";
            var mydata = new Array();
            if (i == 1) {
                typee = "line";
                waterLineColor = "#0000ff";
            } else {
                waterLineColor = colorsList[i];
                typee = "bar";
            }

            var newDataset = {
                type: typee,
                label: cube[0],
                borderWidth: 1,
                bezierCurve: false,
                backgroundColor: waterLineColor,
                borderColor: waterLineColor,
                borderWidth: 1,
                data: [],
                fill: false,
                pointRadius: 2,
                pointHoverRadius: 3,
                borderWidth: 1
            };
            for (var p = 1; p < cube.length; p++) {
                mydata.push(cube[p]);
            }
            for (var index = 0; index < mydata.length; ++index) {
                newDataset.data.push(mydata[index]);
            }
            config.data.datasets.push(newDataset);
        }

    }
    for (var i = 0; i < listlabel.length; i++) {
        config.data.labels.push(listlabel[i]);
    }
    window.myLine.update();
}

function isEven(n) {
    return n % 2 == 0;
}

var colorsList = ["#9966CC", "#7B3F00", "#DC143C", "#50c878", "#CC7722",
    "#531508", "#7F0099", "#F5597D", "#b30059", "#1ae728", "#f4460b",
    "#F9C905", "#F97805", "#05D4F9", "#09FA94", "#A299A7","#d06317",
    "#50086d","#a27fa7","#18b656","#d14e97","#404723","#033514",
    "#f4289d","#a41422","#3b94ad","#85602d"];