layui.use(['jquery','layer', 'table','form','laydate'], function() {
    var $ = layui.jquery    //引入jquery模块
        , layer = layui.layer  //引用layer弹出层模块
        , table = layui.table  //引用table数据表格模块
        , form = layui.form  //引用form表单模块
        , laydate = layui.laydate;  //引用日期模块

    //日期时间范围选择
    laydate.render({
        elem: '#test3'
        ,type: 'datetime'
        ,format: 'yyyy/MM/dd HH:mm:ss'
        ,range: true //或 range: '~' 来自定义分割字符
    });

    var selJsonRoomSale = {}; //全局变量-消费记录查询条件对象

    //初始化加载消费记录分页查询
    loadPageRoomSale();

    //消费记录查询条件表单提交操作
    form.on('submit(demo1)', function(data){
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        selJsonRoomSale = {}; //每次提交表单，都清空查询条件
        //给selJsonOrders查询条件对象赋值
        //先处理日期
        if(data.field.queryTimes!=''){
            var arrTimes = data.field.queryTimes.split("-");
            //起始时间
            selJsonRoomSale['minDate'] = arrTimes[0];
            //结束时间
            selJsonRoomSale['maxDate'] = arrTimes[1];
        }
        selJsonRoomSale['roomNum'] = data.field.roomNum;
        console.log(selJsonRoomSale);
        //向服务器发送分页查询消费记录数据请求
        loadPageRoomSale();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    /***********************自定义函数************************/
    //加载消费记录分页查询
    function loadPageRoomSale(){
        //表格的分页加载，数据表格方法级渲染
        table.render({  //数据表格的数据渲染(此UI框架底层是进行异步加载)
            elem: '#demo'  //绑定容器  根据标签（数据容器）的id属性来
            , height: 412   //容器高度
            , limit: 3   //每一页显示的数据条数，默认值为10
            , limits: [2, 3, 5, 8, 10, 15, 20]   //进行每一页数据条数的选择
            , url: 'roomSale/loadDataByParams' //访问服务器端的数据接口(异步请求)，返回的json格式的数据
            , where:selJsonRoomSale
            , even: true  //每一行有渐变效果
            , page: true //开启分页,此时会自动的将当前页page和每一页数据条数limit的数值传回服务器端
            , cols: [[ //表头
                //加入复选框列
                {type: 'checkbox'}
                , {field: 'id', title: 'ID', align: 'center', width: 80, sort: true}
                , {field: 'roomNum', title: '房间编号', align: 'center', width: 140, sort: true}
                , {field: 'customerName', title: '客人姓名', align: 'center', width: 140, sort: true}
                , {field: 'startDate', title: '入住时间', align: 'center', width: 210}
                , {field: 'endDate', title: '退房时间', align: 'center', width: 210}
                , {field: 'roomPrice', title: '单价', align: 'center', width: 120, sort: true,style:'color: #c6612e;'}
                , {field: 'days', title: '天数', align: 'center', width: 120,style:'color: red;', sort: true}
                , {field: 'rentPrice', title: '住宿金额',align: 'center', width: 120, sort: true,style:'color: #2ec770;'}
                , {field: 'otherPrice', title: '其它消费',align: 'center', width: 120, sort: true,style:'color: #61c62e;'}
                , {field: 'salePrice', title: '支付金额',align: 'center', width: 120, sort: true,style:'color: #b72ec6;'}
                , {field: 'discountPrice', title: '优惠金额',align: 'center', width: 120, sort: true,style:'color: #2e61c6;'}
                , {title: '操作', align: 'center', toolbar: '#barDemo',fixed:'right', width: 160}
            ]],
            done: function (res, curr, count) {  //执行分页是的函数回调；res为分页时服务器端的整个Map集合数据  curr为当前页  count为总的数据条数

            }
        });
    }

});
