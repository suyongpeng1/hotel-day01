//jquery的入口函数
$(function (){
    // 基于准备好的dom容器，初始化echarts实例，myChart为此容器中的echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    //static/json/data1.json为静态的json数据
    //在项目中此时的json数据应为访问服务器端动态获取到的数据
    $.get('roomSale/loadRoomSale').done(function (data) {
        console.log("data:",data);
        //把json字符串转换为json数组对象
        var data = JSON.parse(data);
        console.log("data:",data);
        myChart.setOption({
            title: {
                text: '客房销售记录分析'
            },
            tooltip: {  //节点的显示效果
                trigger: 'axis'
            },
            toolbox: {  //表示工具条显示
                show: true,
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    dataView: {readOnly: false},
                    magicType: {type: ['line', 'bar']},
                    restore: {},
                    saveAsImage: {}
                }
            },
            legend: {  ////数据标记
                data: data.legend
            },
            xAxis: {  //横轴数据
                data: data.xAxis
            },
            yAxis: {},  //纵轴数据
            series: [{
                name: '客房销售',
                type: 'bar',
                data: data.series
            }]
        });
    });

})
