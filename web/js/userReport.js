/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/********* Column chart *********/
Highcharts.chart('revenue-container', {
  chart: {
    type: 'column'
  },
  title: {
    text: 'Revenue by month'
  },
  xAxis: {
    categories: revenueByMonth.date,
    crosshair: true
  },
  yAxis: {
    min: 0,
    title: {
      text: 'Revenue (USD)'
    }
  },
  tooltip: {
    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
      '<td style="padding:0"><b>{point.y:.1f} USD</b></td></tr>',
    footerFormat: '</table>',
    shared: true,
    useHTML: true
  },
  plotOptions: {
    column: {
      pointPadding: 0.2,
      borderWidth: 0
    }
  },
  series: [{
    name: 'Revenue',
    data: revenueByMonth.revenue
  }]
});

/************ Pie chart ******************/
Highcharts.chart('category-container', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Items sold by category'
    },
    tooltip: {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    accessibility: {
      point: {
        valueSuffix: '%'
      }
    },
    plotOptions: {
        pie: {
            size: 250,
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
            }
        }
    },
    series: [{
      name: 'Category',
      colorByPoint: true,
      data: productsSoldByCategory
    }]
});

/******* Datatable *******/
const datatable = new simpleDatatables.DataTable("#revenue-products-table", {
    fixedHeight: true
});

console.log(datatable);

let newData = {
    headings: productsReportHeader,
    data: productsReport
};

datatable.insert(newData);

/******** Generate button *******/
const generateButtons = document.querySelectorAll(".generate-button");

for (let button of generateButtons) {
    button.addEventListener("click", () => {
        datatable.export({
            type: "csv",
            download: true
        })
    });
}

