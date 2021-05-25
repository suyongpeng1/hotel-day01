layui.use(['jquery','layer', 'element','form','laypage'], function() {
    var $ = layui.jquery //引入jquery模块
        , layer = layui.layer //引用layer弹出层模块
        , element = layui.element //引用element面板模块
        , form = layui.form //引用form表单模块
        , laypage = layui.laypage; //引用分页组件

    var page = 1;//全局变量当前页码的初始值为1
    var limit = 3;//全局变量每一页的数据条数初始值为3
    var count; //全局变量总的数据条数

    //验证房型是否可删的判断
    var checkRoomsOfRoomTypeIf = false;

    //验证房型名称是否唯一验证
    var checkRoomTypeNameIf = false;

    //初始化首页类型的数据
    loadPageRoomType();

    //初始化加载分页查询
    loadPage();

    //全局变量-七牛云的云存储的空间位置
    var qnyName = "http://qt6nn5f1q.hn-bkt.clouddn.com/";



    //加载分页查询
    function loadPage(){
        //完整功能
        laypage.render({
            elem: 'test1'  //绑定是分页的容器id
            ,count: count //表示总条数
            ,limit: limit //每页显示的条数。
            ,limits: [3, 5, 8, 10, 20] //每页条数的选择项。
            ,curr : page //表示当前页
            ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            ,jump: function(obj, first){ //切换分页的回调
                //obj包含了当前分页的所有参数，比如：
                console.log("obj.curr:",obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                console.log("obj.limit:",obj.limit); //得到每页显示的条数

                //把当前选择的页，赋值给page变量，传递到后端去进行加载
                page = obj.curr;
                //把选择的每页显示记录数，赋值给limit，传递到后端去进行加载
                limit = obj.limit;

                //首次不执行 : 当第一次分页加载的时候，不执行 【重新加载客房类型的数据】，以后的每次操作，都需要加载。
                if(!first){
                    //重新加载客房类型的数据
                    loadPageRoomType();
                }
            }
        });
    }

    //添加功能的表单效验
    form.verify({
        //效验房型名称
        roomTypeName: function(value, item){ //value：表单的值、item：表单的DOM对象
            //向服务发送指令，效验房型名称
            checkRoomTypeName(value);
            if(!checkRoomTypeNameIf){
                return "客房类型重复！";
            }
        },
        //效验价格
        roomPrice: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(value < 120 || value > 8888){
                return "客房类型价格在 120 ~ 8888 之间！";
            }
        }
    });

    //监听添加按钮的表单提交操作
    form.on('submit(demo3)', function(data){
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        //向服务器发送指令，添加客房类型
        saveRoomType(data.field);
        layer.closeAll();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    //监听折叠
    // collapse(test) 对应的是jsp中的面板的 lay-filter="test" 的值
    element.on('collapse(test)', function(data){
        //判断
        if(data.show){
            //获取客房类型的id
            var roomTypeId = $(this).attr("roomTypeId");
            console.log("roomTypeId:",roomTypeId);
            //根据客房类型的id，去服务器查询该类型下所有的客房数据
            loadRoomsByRoomTypeId(roomTypeId);
        }
    });



    /************************自定义绑定函数****************************/
    //绑定面板容器对应的按钮
    $("#collapseDiv").on('click','button',function (){
        //拿到event
        var event = $(this).attr("event");
        //判断
        if(event == 'del'){ //点击了删除
            var roomTypeId = $(this).val();
            //1.先验证该客房类型下有没有客房数据
            checkRoomsOfRoomType(roomTypeId);
            if(checkRoomsOfRoomTypeIf){
                layer.confirm('真的要删除客房类型数据么', function(index){
                    //向服务端发送删除指令,删除对应的客房类型数据
                    delRoomTypeById(roomTypeId);
                    layer.close(index);
                });
            }else{
                layer.msg("该客房类型下有客房数据，不能删除！",{icon: 7,time:2000,anim: 3,shade:0.5});
            }
            //2.弹框提示是否删除
        }else{ //点击了修改
            //1.数据回显
            var roomTypeArr = $(this).val().split(",");
            //给表单赋值
            form.val("updRoomTypeFromFilter", { //formTest 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                "id": roomTypeArr[0]// "name": "value"
                ,"roomTypeName": roomTypeArr[1]
                ,"roomPrice": roomTypeArr[2]
            });
            //2.弹框
            layer.open({
                type:1,  //弹出类型
                title:"房型修改操作界面",  //弹框标题
                area:['380px','280px'],  //弹框款高度
                anim: 4,  //弹出的动画效果
                shade:0.5,  //阴影遮罩
                content:$("#updRoomTypeDiv")  //弹出的内容
            });

            //3.监听修改表单的提交按钮操作  - 作业
            //3.监听提交按钮，执行修改
            form.on('submit(demo4)', function (data) {
                var updJsonRoomType = data.field;
                updRoomType(updJsonRoomType); //执行修改
                layer.closeAll(); //关闭所有弹框
                return false; //阻止表单跳转提交
            });
        }
    })

    //绑定添加客房类型按钮的事件
    $("#saveRoomTypeBtn").click(function (){
        //1.要把之前填写的内容清空
        //jquery的方式去清空
        $("#saveRoomTypeDiv form").find("input").val("");
        //2.弹框
        layer.open({
            type:1,  //弹出类型
            title:"房型添加操作界面",  //弹框标题
            area:['380px','280px'],  //弹框款高度
            anim: 3,  //弹出的动画效果
            shade:0.5,  //阴影遮罩
            content:$("#saveRoomTypeDiv")  //弹出的内容
        });
    });

    /***************************自定义函数*****************************/
//加载第1页的房型数据，要得到总的数据条数（重要）
    function loadPageRoomType() {
//在$.post()前把ajax设置为同步请求：
        $.ajaxSettings.async = false;
        $.post(
            "roomType/loadDataByParams", //调用的是base系列的方法，只需要改mapper.xml文件
        {"page":page,"limit":limit},
        function (data){
            console.log(data);
//将数据总的条数赋值给全局变量
            count = data.count;
            var roomTypeStr = ``;
            $.each(data.data,function (i,roomType){
                roomTypeStr += `
      <div class="layui-colla-item"
      id="item${roomType.id}" style="margin-top: 10px;">
      <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" event="del" value="${roomType.id}" 
      style="float:right;">删除</button>
      <button type="button" class="layui-btn layui-btn-sm layui-btn-warm" event="upd"
       value="${roomType.id},${roomType.roomTypeName},${roomType.roomPrice}"
       style="float: right;">修改</button>
      <h2 class="layui-colla-title"
      roomTypeId="${roomType.id}">${roomType.roomTypeName} --
      ${roomType.roomPrice}元/天</h2>
      <div class="layui-colla-content">
      <p id="p${roomType.id}"></p>
      </div>
      </div>
      `;
            })
            //把拼接好的房型面板数据放到面板容器中
            $("#collapseDiv").html(roomTypeStr);
          //将面板渲染
            element.render("collapse")
        },"json"
    ).error(function (){
            layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
        })
    }

//先验证该客房类型下有没有客房数据
    function checkRoomsOfRoomType(roomTypeId){
        //在$.post()前把ajax设置为同步请求：
        $.ajaxSettings.async = false;
        $.post(
            "rooms/getCountByParams", //请求的url路径 ，使用base中方法
            {"roomTypeId":roomTypeId},
            function (data){
                console.log("data:",data);
                if(data == 0){
                    checkRoomsOfRoomTypeIf = true;
                }else{
                    checkRoomsOfRoomTypeIf = false;
                }
            },"json"
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }

    //向服务端发送删除指令,删除对应的客房类型数据
    function delRoomTypeById(roomTypeId){
        $.post(
            "roomType/delTById", //请求的url路径
            {"id":roomTypeId},
            function (data){
                if(data === 'success'){
                    //重新加载客房类型数据
                    loadPageRoomType();
                    //重新加载分页查询
                    loadPage();
                    layer.msg("客房类型删除成功！",{icon: 1,time:2000,anim: 1,shade:0.5});
                }else{
                    layer.msg("客房类型删除失败！",{icon: 2,time:2000,anim: 2,shade:0.5});
                }
            },"text" //text : 表示后端响应的是文本
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }

    //执行修改操作
    function updRoomType(updJsonRoomType){
        $.post(
            "roomType/updT", //调用的是base系列的方法，只需要改mapper.xml文件
            updJsonRoomType,
            function (data){
                if(data === 'success'){
                    loadPageRoomType(); //重新加载当前页
                    layer.msg("房型数据修改成功！",{icon: 1,time:2000,anim: 4,shade:0.5});
                }else{
                    layer.msg("房型数据修改失败！",{icon: 2,time:2000,anim: 4,shade:0.5});
                }
            },"text"
        ).error(function (){
            layer.msg("服务器异常！！！",{icon: 3,time:2000,anim: 6,shade:0.5});
        })
    }

    //向服务发送指令，效验房型名称
    //注意：如果查询出来结果是0 ，可以用 ，不是0：不能使用
    function checkRoomTypeName(roomTypeName){
        $.post(
            "roomType/getCountByParams", //请求的url路径
            {"roomTypeName":roomTypeName},
            function (data){
                console.log(data);
                if(data == 0){
                    checkRoomTypeNameIf = true;
                    layer.tips('没有重复的房型名称，验证通过','#roomTypeName', {tips: [2,'green'],time:2000});
                }else{
                    checkRoomTypeNameIf = false;
                    layer.tips('有重复的房型名称，验证不通过','#roomTypeName', {tips: [2,'red'],time:2000});
                }
            },"json" //text : 表示后端响应的是文本
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }


    //向服务器发送指令，添加客房类型
    function saveRoomType(saveJsonRoomType){
        $.post(
            "roomType/saveT", //请求的url路径
            saveJsonRoomType,
            function (data){
                if(data === 'success'){
                    //把当前页重置为1
                    page = 1;
                    //重新加载客房类型数据
                    loadPageRoomType();
                    //重新加载分页查询
                    loadPage();
                    layer.msg("客房类型添加成功！",{icon: 1,time:2000,anim: 1,shade:0.5});
                }else{
                    layer.msg("客房类型添加失败！",{icon: 2,time:2000,anim: 2,shade:0.5});
                }
            },"text" //text : 表示后端响应的是文本
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }

    //根据客房类型的id，去服务器查询该类型下所有的客房数据
    function loadRoomsByRoomTypeId(roomTypeId){
        $.post(
            "rooms/loadManyByParams", //请求的url路径
            {"roomTypeId":roomTypeId},
            function (data){
                console.log(data);
                //isEmptyObject: 判断json对象是否为空
                if(!$.isEmptyObject(data)){
                    //加载该客房类型下所有的客房数据
                    var rooms = `<ul class="site-doc-icon site-doc-anim">`;
                    $.each(data,function (i,room){
                        if(room.roomStatus == '0'){ //空闲
                            rooms += `<li style="background-color: #009688;">`;
                        }else if(room.roomStatus == '1'){ //已入住
                            rooms += `<li style="background-color: red;">`;
                        }else { //打扫
                            rooms += `<li style="background-color: blueviolet;">`;
                        }
                        rooms += `  <img class="layui-anim" id="demo1" src="${qnyName}/${room.roomPic}" 
                       width="135px" height="135px"/>
                              <div class="code">
                                 <span style="display: block;color: #0C0C0C;">${room.roomNum} - ${room.roomType.roomTypeName} - ${room.roomType.roomPrice}元/天</span>
                              </div>
                                </li>    
                                `;
                    })
                    rooms += `</ul>`;
                    $("#p"+roomTypeId).html(rooms);
                }else{
                    layer.msg("该客房类型下没有客房数据！",{icon: 7,time:2000,anim: 3,shade:0.5});
                }
            },"json" //text : 表示后端响应的是文本
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }


})