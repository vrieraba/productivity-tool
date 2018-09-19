const COLOR = Object.freeze({"GRIS":"#d6d6c2", "AZUL":"#3e95cd", "AMARILLO":"#FFF333", "VERDE":"#AFFF33", "NARANJA":"#FFA833", "ROJO":"#FF4F33", "LILA":"#F633FF"});
const CHART_TYPE = Object.freeze({"BAR":"bar", "LINE":"line"});
const CHART_SIZE = Object.freeze({"BIG":"big", "SMALL":"small"});

function ChartDataEntry(name, values) {
    this.name = name;
    this.values = values;
}

function ChartDataSet(name, values, borderColor, backgroundColor, fill) {
    this.label = name;
    this.data = values;
    this.borderColor = borderColor;
    this.backgroundColor = backgroundColor;
    this.fill = fill;
}

function addChart(chartType, chartAreaId, chartId, title, yAxisLabels, dataEntries, size) {
    if (size == "big") {
        $("#" + chartAreaId).append("<div class='chart_container'><canvas id='" + chartId + "' width='1000' height='250'></canvas></div>")
    } else {
        $("#" + chartAreaId).append("<div class='chart_container_small'><canvas id='" + chartId + "' width='500' height='250'></canvas></div>")
    }

    var dataSets = [];
    var availableColors = Object.values(COLOR);
    for (let i = 0; i < dataEntries.length; i++) {
        let dataEntry = dataEntries[i];
        dataSets.push(new ChartDataSet(dataEntry.name, dataEntry.values, availableColors[i], availableColors[i], false));
    }

    var ctx1 = document.getElementById(chartId);
    var myChart = new Chart(ctx1, {
        type: chartType,
        data: {
            labels: yAxisLabels,
            datasets: dataSets
        },
        options: {
            title: {
                display: true,
                text: title
            },
            legend: {
                position: 'right',
                labels : {
                    boxWidth: 12
                }
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true
                    }
                }]
            }
        }
    });
}

function cleanCharts() {
    $(".charts_area").empty();
}