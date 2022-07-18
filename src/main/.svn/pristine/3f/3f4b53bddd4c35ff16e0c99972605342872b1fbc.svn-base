/**
* This is common function to make graph
* it includes following graphs
* only bar, only line,one line multiple bar, multiple bar multiple line
* 
*
* @method multiBarMultiLineGraphCommon
* @param {divId}(String) div id where to append graph
* @param {graphTitle}(String) graph title -- shown to top of graph
* @param {graphUnit}(String) unit that will shown on Y-axis
* @param {graphData}(Object) actual graphData
* @param {themeName}(String) theme name - if you pass theme name draggable option will not work.. your custom color array will be disabled
*        options -> azul, bee-inspired, blue, caravan, carp, cool, dark, dark-blue,dark-bold, dark-digerati, dark-fresh-cut, dark-mushroom,eduardo, forest, fresh-cut, fruit, gray, green, helianthus, infographic,inspired, jazz, london, macarons, macarons2, mint, red, red-velvet, roma,royal, sakura, shine, tech-blue,vintage
* @param {zoomOption}(String) actual graphData
* @param {draggable}(String) make graph draggable
		pass anything grather thn draggable will make it normal graph
* @param {toolboxType}(String) this will adde extra option > instant bar,line conversion
		options -> normal, advanced
*@param {graphType}(String) which type graph you want ?
		options -> 1) multiLineMultiBarGraph
				   2) onlyLine
				   3) onlyBar
				   4) singleLineMultiBar
				   5)
*@param {smoothLine}(String) key word "smoothLine" will make line graph smooth line else angled lines
*@param {graphData}(Object) actual graphData
* @return {Boolean} Returns true on success
*/
function makeCommonEchart(divId, graphTitle, graphUnit, graphData,
    themeName, zoomOption, draggable, toolboxType, graphType,smoothLine) {

	if(themeName!= "" || themeName !=null){
		var themeUsed = $('body').attr('data-ma-theme');
		if(themeUsed == "black" ){
			themeName = "dark";
		} else if(themeUsed == "green" ){
			themeName = "green";
		}else if(themeUsed == "blue" ){
			themeName = "tech-blue";
		}
	}
	
	
	
	console.log("themeUsed "+themeUsed);
	// remove old echart instance from div id where is graph rendering
	$("#"+divId).removeAttr("_echarts_instance_");

    // make graph draggable
    var isDraggable = false;
    if (draggable == "draggable") {
        isDraggable = true;
    }

 // make bara graph lines smooth
    var isSmoothLine = false;
    if (smoothLine == "smoothLine") {
    	isSmoothLine = true;
    }
    
    var dataLength = graphData.length;

    //graphData = graphData.slice(0, 5); for testing
    
    // chart toolbox
    var magicTypeShow = false;
    var dataViewShow = false;
    var restoreShow = false;

    if (toolboxType == "normal") {

    } else if (toolboxType == "advanced") {
        magicTypeShow = true;
        dataViewShow = true;
        restoreShow = true;
    }



    if (graphType == "multiLineMultiBarGraph") {
        graphData.length;
    }

    // convert y-axis unit name
    var graphUnitArr = graphUnit.split("/");
    if (graphUnitArr.length > 1) {
        graphUnit = graphUnitArr[0] + " /Day";
    }

    // array declaration 
    var dataSeries = new Array();
    var legendArray = new Array();
    var datesArray = new Array();

    // extract all dates from graphData response
    var firstRowArray = graphData[0];

    var totalXaxisData = firstRowArray.length;

    datesArray = firstRowArray.slice(1, totalXaxisData); // push x-axis data dates

    for (var i = 0; i < graphData.length; i++) {
        if (i > 0) {
            var type;

            var arrayElement = graphData[i]; // get array row
            var pName = arrayElement.slice(0, 1); // extract item name from single array row
            var itemName = pName[0]; // --> you get item Name Here

            var graphReading = arrayElement.slice(1, totalXaxisData); // extract graph data from single array row

            if (graphType == "multiLineMultiBarGraph") {
                //all first row will be line >>>> all second row will be bar >>>> soo..one line one bar.... in loop

                if (isEven(i)) {
                    type = "bar";
                } else {
                    type = "line";
                }
            } else if (graphType == "onlyLine") {
                // all row line >>>> soo..one line one bar.... in loop

                type = "line";
            } else if (graphType == "onlyBar") {
                // all row bar >>>> soo..one line one bar.... in loop

                type = "bar";
            } else if (graphType == "singleLineMultiBar") {
                // only first row will be line >>>> all second row will be bar >>>> soo..one line one bar.... in loop

                if (i == 1) {
                    type = "line";
                } else {
                    type = "bar";
                }
            }

            var obj1 = new Object();
            obj1.name = itemName; // push itemName in Object from here
            obj1.type = type; //push Graph Type in Object from here
            obj1.data = graphReading; //push Graph readings in Object from here
            obj1.smooth= isSmoothLine;
            dataSeries.push(obj1); // push whole object in data series from here

            legendArray.push(itemName); // push item names in legend Array
        }
    }


    var option = {
        title: {
            text: graphTitle,
            left: 'center',
            top: 0
        },

        tooltip: {

        },
        grid: {
            top: 100,
        },
        toolbox: {
            feature: {
                dataView: {
                    show: dataViewShow,
                    readOnly: false
                },
                magicType: {
                    show: magicTypeShow,
                    type: ['line', 'bar']
                },
                restore: {
                    show: restoreShow
                },
                saveAsImage: {
                    show: true
                }
            }
        },
        legend: [{
            type: 'scroll',
            selector: true,
            data: legendArray,
            backgroundColor: 'rgba(0,100,50,0.2)',
            pageButtonPosition: 'start',
            top: 30
        }],
        xAxis: [{
            type: 'category',
            data: datesArray,
            axisPointer: {
                type: 'shadow'
            }
        }],
        yAxis: [{
            type: 'value',
            name: graphUnit,
            axisLabel: {
                formatter: '{value} ' + graphUnit,
                textStyle: {
                    fontSize: 10
                }
            },

        }],
        dataZoom: [{
                show: true,
                start: 95,
                end: 90,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            },
            {
                type: 'inside',
                start: 95,
                end: 100
            },
            {
                show: true,
                yAxisIndex: 0,
                filterMode: 'empty',
                width: 30,
                height: '80%',
                showDataShadow: true,
                left: '93%',
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }
        ],

        series: dataSeries,
        animation: true
    };

    setTimeout(function() {
    if (themeName != "") {
      var myChart = echarts.init(document.getElementById(divId), themeName);
      myChart.setOption(option, true);
    } else {
      option.color = colorArray; // pushing custom colors array
      testHelper.createChart(echarts, document.getElementById(divId), option, {
        draggable: isDraggable
      });
    }

  }, 500);
    

}

function isEven(n) {
    return n % 2 == 0;
}

var colorArray = ['#FF6633', '#FFB399', '#FF33FF', '#FFFF99', '#00B3E6',
    '#E6B333', '#3366E6', '#999966', '#99FF99', '#B34D4D',
    '#80B300', '#809900', '#E6B3B3', '#6680B3', '#66991A',
    '#FF99E6', '#CCFF1A', '#FF1A66', '#E6331A', '#33FFCC',
    '#66994D', '#B366CC', '#4D8000', '#B33300', '#CC80CC',
    '#66664D', '#991AFF', '#E666FF', '#4DB3FF', '#1AB399',
    '#E666B3', '#33991A', '#CC9999', '#B3B31A', '#00E680',
    '#4D8066', '#809980', '#E6FF80', '#1AFF33', '#999933',
    '#FF3380', '#CCCC00', '#66E64D', '#4D80CC', '#9900B3',
    '#E64D66', '#4DB380', '#FF4D4D', '#99E6E6', '#6666FF'
];

var colorsList = ["#9966CC", "#7B3F00", "#DC143C", "#50c878", "#CC7722", "#531508", "#7F0099", "#F5597D", "#b30059", "#1ae728", "#f4460b", "#F9C905", "#F97805", "#05D4F9", "#09FA94", "#A299A7"];