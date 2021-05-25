layui.use(['jquery','layer', 'table','form','laydate'], function() {
    var $ = layui.jquery //引入jquery模块
        , layer = layui.layer //引用layer弹出层模块
        , table = layui.table //引用table数据表格模块
        , form = layui.form //引用form表单模块
        , laydate = layui.laydate; //引用日期模块


    var selJsonOrders = {}; //全局变量-订单查询条件对象

    var currentPage = 1; //初始化分页查询，默认第1页

    //初始化加载订单分页查询数据
    loadPageOrders();

    //日期时间范围选择
    laydate.render({
        elem: '#test3'
        ,type: 'datetime'
        ,format: 'yyyy/MM/dd HH:mm:ss'
        ,range: true //或 range: '~' 来自定义分割字符
    });

    //查询订单的表单提交
    form.on('submit(demo1)', function(data){
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        selJsonOrders = {}; //每次提交表单，都清空查询条件
        //给selJsonOrders查询条件对象赋值
        //先处理日期
        if(data.field.queryTimes!=''){
            var arrTimes = data.field.queryTimes.split("-");
            //起始时间
            selJsonOrders['minDate'] = arrTimes[0];
            //结束时间
            selJsonOrders['maxDate'] = arrTimes[1];
        }
        selJsonOrders['orderNum'] = data.field.orderNum;
        selJsonOrders['orderStatus'] = data.field.orderStatus;
        console.log(selJsonOrders);
        //向服务器发送分页查询订单数据请求
        loadPageOrders();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //工具条事件
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前从后端服务器返回的data数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        console.log(data);

        if(layEvent === 'del'){ //删除
            layer.confirm('真的删除订单数据么？', function(index){
                //向服务端发送订单删除指令（更新flag字段）
                updOrdersFlag(data.id,obj);
                layer.close(index);
            });
        } else if(layEvent === 'payUI'){ //支付
            //跳转到支付宝的支付页面，同时传递参数orderNum,orderMoney
            layer.confirm('真的需要支付订单么？', function(index){
                //打开一个新窗口，进行支付
                window.open("model/toOrdersPay?orderNum=" + data.orderNum + "&orderMoney=" + data.orderMoney);
                layer.close(index);
            });
        }
    });

    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'delBatchOrders':
                var data = checkStatus.data;
                console.log(data);
                //判断是否选中
                if(data.length != 0){
                    //如果选中了，拼接好ids，传递到后端服务器
                    var idsStr = ""; //需要传递到后端的ids字符串 结构： 178,189,167
                    var checkDelIf = true; //定义是否能够删除的提示
                    //let: 表示es6的新语法，表示变量
                    for (let i = 0; i < data.length; i++) {
                        //判断订单的状态，如果没有支付，那么必须提示！返回
                        if(data[i].orderStatus == '0'){
                            checkDelIf = false; //如果不能删除，那么设置为false
                            break;
                        }else {
                            //拼接ids
                            idsStr += data[i].id + ",";
                        }
                    }
                    //判断，如果不能删除，需要提示
                    if(checkDelIf){
                        //截取字符串
                        idsStr = idsStr.substring(0,idsStr.length-1);
                        console.log("idsStr:",idsStr);
                        layer.confirm('真的批量删除订单数据么？', function(index){
                            //向服务器发送批量删除请求(修改flag的状态)
                            updBatchOrdersFlag(idsStr);
                            layer.close(index);
                        });
                    }else{
                        layer.msg("亲, 你有未支付的订单！",{icon: 2,time:2000,anim: 2,shade:0.5});
                    }
                }else{
                    layer.msg("请选中需要批量删除的订单！",{icon: 2,time:2000,anim: 2,shade:0.5});
                }
                break;
            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        };
    });


    /************************自定义layui函数****************************/
    //订单分页查询数据
    function loadPageOrders(){
        //表格的分页加载，数据表格方法级渲染
        table.render({  //数据表格的数据渲染(此UI框架底层是进行异步加载)
            elem: '#demo'  //绑定容器  根据标签（数据容器）的id属性来
            , height: 412   //容器高度
            , limit: 3   //每一页显示的数据条数，默认值为10
            , limits: [2, 3, 5, 8, 10, 15, 20]   //进行每一页数据条数的选择
            , url: 'orders/loadDataByParams' //访问服务器端的数据接口(异步请求)，返回的json格式的数据
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            , where:selJsonOrders
            , even: true  //每一行有渐变效果
            , page: true //开启分页,此时会自动的将当前页page和每一页数据条数limit的数值传回服务器端
            , cols: [[ //表头
                //加入复选框列
                {type: 'checkbox'}
                , {field: 'id', title: 'ID', align: 'center', width: 80, sort: true}
                , {field: 'orderNum', title: '订单编号' , align: 'center', width: 180}
                , {field: 'customerName', title: '客人姓名', align: 'center', width: 140, sort: true,templet: '<div>{{d.inRoomInfo.customerName}}</div>'}
                , {field: 'idcard', title: '身份证号', align: 'center', width: 210,templet: '<div>{{d.inRoomInfo.idcard}}</div>'}
                , {field: 'isVip', title: 'vip', align: 'center', width: 100,templet: '#isVipTpl'}
                , {field: 'phone', title: '手机号', align: 'center', width: 180, sort: true,templet: '<div>{{d.inRoomInfo.phone}}</div>'}
                , {field: 'createDate', title: '下单时间', align: 'center', width: 240, sort: true}
                , {field: 'orderMoney', title: '总价',align: 'center', width: 140, sort: true}
                , {field: 'remark', title: '备注',align: 'center', width: 280, sort: true}
                , {field: 'orderStatus', title: '状态',align: 'center', width: 120, sort: true,templet:'#orderStatusTpl'}
                , {title: '操作', align: 'center', toolbar: '#barDemo',fixed:'right', width: 180}
            ]],
            done: function (res, curr, count) {  //执行分页是的函数回调；res为分页时服务器端的整个Map集合数据  curr为当前页  count为总的数据条数
                //每一次分页加载时调用图片放大镜函数
                currentPage = curr;  //将分页时的当前页赋值给次全局变量
            }
        });
    }


    /***************************自定义函数*****************************/
    //向服务端发送订单删除指令（更新flag字段）
    function updOrdersFlag(id,obj){
        $.post(
            "orders/updT", //请求的url路径
            {"id":id,"flag":"0"},
            function (data){
                if(data === 'success'){
                    layer.msg("订单信息删除成功！",{icon: 1,time:2000,anim: 1,shade:0.5});
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                }else{
                    layer.msg("订单信息删除失败！",{icon: 2,time:2000,anim: 2,shade:0.5});
                }
            },"text" //text : 表示后端响应的是文本
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }

    //向服务器发送批量删除请求(修改flag的状态)
    function updBatchOrdersFlag(idsStr){
        $.post(
            "orders/updBatchTByIds", //请求的url路径
            {"ids":idsStr, "flag":"0"},
            function (data){
                if(data === 'success'){
                    layer.msg("订单信息批量删除成功！",{icon: 1,time:2000,anim: 1,shade:0.5});
                    //重新加载数据，加载当前页的数据
                    table.reload('demo', {  //"demo"为容器id的值
                        page: {
                            curr: currentPage //重新从第 当前 页开始
                        }
                    }); //只重载数据，异步加载表格数据
                }else{
                    layer.msg("订单信息批量删除失败！",{icon: 2,time:2000,anim: 2,shade:0.5});
                }
            },"text" //text : 表示后端响应的是文本
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }

});
