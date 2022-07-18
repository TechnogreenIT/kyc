var colorsList =["#9696CC","#7B3F00","#14d8dc","#50c878","#CC7722","#531508","#7F0099","#f559e5","#00adb2","#1ae728","#f4f40a","#151c17","#f9ec05","#05D4F9","#09FA94","#A299A7","#ff4500","#34AABB"];

function graphSingleLineMultiBar(title, y_axis_lebel, divId, queryObject) {
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
                //waterLineColor = colorsList[i];
                waterLineColor = getRandomColor();
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
function printCanvas(e)  
{  
	var div=e;
    var dataUrl = document.getElementById(div).toDataURL(); //attempt to save base64 string to server using this var  
    var windowContent = '<!DOCTYPE html>';
    windowContent += '<html>'
    windowContent += '<head><title>KYC GRAPH</title></head>';
    windowContent += '<body>'
    windowContent += '<img src="' + dataUrl + '" style="margin-top:200px">';
    windowContent += '</body>';
    windowContent += '</html>';
    var src="canvasPrinting";
    var printWin = window.open(src, "newWin", "width="+screen.availWidth+",height="+screen.availHeight)
    printWin.document.open();
    printWin.document.write(windowContent);
    printWin.document.addEventListener('load', function() {
        printWin.focus();
        printWin.print();
        printWin.document.close();
        printWin.close();            
    }, true);
}

function getRandomColor() {
	var letters = '0123456789ABCDEF';
	var color = '#';
	for (var i = 0; i < 6; i++) {
		color += letters[Math.floor(Math.random() * 16)];
	}
	return color;
}

function isEven(n) {
    return n % 2 == 0;
}