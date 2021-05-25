layui.use(['jquery','layer', 'table','form','upload'], function() {
    var $ = layui.jquery    //引入jquery模块
        , layer = layui.layer  //引用layer弹出层模块
        , table = layui.table  //引用table数据表格模块
        , form = layui.form  //引用form表单模块
        , upload = layui.upload //文件上传组件
        , element = layui.element; //引入常用元素操作的组件

    //初始化所有的客房数据
    loadAllRooms();

    //初始化加载所有的客房类型的数据
    loadAllRoomType();

    //房屋显示的ul容器数组
    //arrUl ： 是一个数组，里面有3个ul
    //arrul[0]:空闲 arrul[1]：已入住  arrul[2]：打扫
    var arrUl = $("#LAY_preview").find("ul");


    //全局变量-验证房间号的唯一性
    var checkRoomNumIf = false;

    //全局变量-七牛云的云存储的空间位置
    var qnyName = "http://qt6nn5f1q.hn-bkt.clouddn.com/";

    //验证房间号的唯一性
    form.verify({
        roomNum: function(value, item){ //value：表单的值、item：表单的DOM对象
            //向服务器发送请求，验证房间号的唯一性
            checkRoomNum(value);
            if(!checkRoomNumIf){
                return "当前房间号不可用！";
            }
        }
    });


    //监听添加表单提交按钮操作
    form.on('submit(demo3)', function(data){
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        //添加客房状态和显示状态
        var saveJsonRoom = data.field;
        saveJsonRoom['roomStatus'] = '0';
        saveJsonRoom['flag'] = '1';
        //向服务器发送添加请求
        saveRoom(saveJsonRoom);
        layer.closeAll();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });


    //常规使用 - 普通图片上传
    var uploadInst = upload.render({
        elem: '#test1'
        ,url: 'rooms/uploadRoomPic' //改成您自己的上传接口
        ,data: {"path":"D:\\img"} //传递到后端的参数，表示上传路径
        ,field: "myFile"  //表示文件上传的对象
        ,auto: false //取消自动上传
        ,bindAction: '#test9'  //yyyyyyyyy绑定开始上传文件按钮，实现手动上传
        ,size: 1024  //设置文件最大可允许上传的大小，单位 KB。不支持ie8/9
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo1').attr('src', result); //图片链接（base64）
            });

            element.progress('demo', '0%'); //进度条复位
            layer.msg('上传中', {icon: 16, time: 0});
        }
        ,done: function(res){  //上传之后的回调函数
            console.log("res:",res);
            //如果上传失败
            if(res.code > 0){
                return layer.msg("上传失败！",{icon: 7,time:2000,anim: 3,shade:0.5});
            }else{ //上传成功
                console.log("newFileName",res.newFileName);
                //把回显的上传图片的名称赋值给隐藏域的图片元素的值。
                $("#roomPicId").val(res.newFileName);
                return layer.msg("上传成功！",{icon: 1,time:2000,anim: 4,shade:0.5});
            }
            //上传成功的一些操作
            //……
            $('#demoText').html(''); //置空上传失败的状态
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
        //进度条
        ,progress: function(n, index, e){
            element.progress('demo', n + '%'); //可配合 layui 进度条元素使用
            if(n == 100){
                layer.msg('上传完毕', {icon: 1});
            }
        }
    });
    /*************************自定义函数************************/
    //初始化所有的客房数据
    function loadAllRooms(){
        $.post(
            "rooms/loadAllT", //请求的url路径 ，使用base中方法
            function (data){
                console.log("data:",data);
                var roomStatus0 = ""; //空闲客房状态数据标签字符串
                var roomStatus1 = "";//已入住客房状态数据标签字符串
                var roomStatus2 = "";//打扫客房状态数据标签字符串
                //对data进行遍历
                $.each(data, function (i,room){
                    if(room.roomStatus == '0'){  //空闲
                        roomStatus0 += `
                           <li style="background-color: #009688;">
                                <img class="layui-anim" src="${qnyName}${room.roomPic}" width="135" height="135">
                                   <div class="code">
                                        <span style="display: block; color: #0C0C0C;">
                                          ${room.roomNum} - ${room.roomType.roomTypeName} - ${room.roomType.roomPrice} 元/天
                                        </span>
                                        <button type="button" value="del" roomid="${room.id}" class="layui-btn layui-btn-danger layui-btn-xs">删除</button>
                                   </div>
                           </li>
                         `;
                    }else if(room.roomStatus == '1'){ //已入住
                        roomStatus1 += `
                            <li style="background-color: red;">
                                <img class="layui-anim" src="${qnyName}${room.roomPic}" width="135" height="135">
                                    <div class="code">
                                        <span style="display: block; color: #0C0C0C;">
                                        ${room.roomNum} - ${room.roomType.roomTypeName} - ${room.roomType.roomPrice} 元/天
                                        </span>
                                    </div>
                            </li>
                         `;
                    }else{ //打扫
                        roomStatus2 += `
                            <li style="background-color: blueviolet;">
                                <img class="layui-anim" src="${qnyName}${room.roomPic}" width="135" height="135">
                                <div class="code">
                                    <span style="display: block; color: #0C0C0C;">
                                    ${room.roomNum} - ${room.roomType.roomTypeName} - ${room.roomType.roomPrice} 元/天
                                    </span>
                                    <button type="button" value="del" roomid="${room.id}" class="layui-btn layui-btn-danger layui-btn-xs">删除</button>
                                    <button type="button" value="upd" roomid="${room.id}" class="layui-btn layui-btn-normal layui-btn-xs">空闲</button>
                                </div>
                            </li>
                        `;
                    }
                })
                //分别将三种状态的客房标签数据填充到对应的ul列表中
                $(arrUl[0]).html(roomStatus0);
                $(arrUl[1]).html(roomStatus1);
                $(arrUl[2]).html(roomStatus2);
                //调用放大镜效果
                hoverOpenImg();

            },"json"
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }


    /**********************自定义函数****************************/
    //图片放大镜
    function hoverOpenImg(){
        var img_show = null; // tips提示
        $('img').hover(function(){
            var img = "<img class='img_msg' src='"+$(this).attr('src')+"' style='width:580px;' />";
            img_show = layer.tips(img, this,{
                tips:[2, 'rgba(41,41,41,.5)']
                ,area: ['600px']
                ,time: -1  //永久显示
                ,anim: 3
            });
        },function(){
            layer.close(img_show);
        });
        $('img').attr('style','max-width:270px');
    }

    //向服务端发送修改客房的显示状态
    function updRoomsFlag(roomid, flag){
        $.post(
            "rooms/updT", //请求的url路径
            {"id":roomid,"flag":flag},
            function (data){
                if(data === 'success'){
                    //重新加载客房数据
                    loadAllRooms();
                    layer.msg("空闲客房删除成功！",{icon: 1,time:2000,anim: 1,shade:0.5});
                }else{
                    layer.msg("空闲客房删除失败！",{icon: 2,time:2000,anim: 2,shade:0.5});
                }
            },"text" //text : 表示后端响应的是文本
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }


    //向服务端发送修改客房的状态  ： 2 -> 0
    function updRoomsStatus(roomid,roomStatus){
        $.post(
            "rooms/updT", //请求的url路径
            {"id":roomid,"roomStatus":roomStatus},
            function (data){
                if(data === 'success'){
                    //重新加载客房数据
                    loadAllRooms();
                    layer.msg("客房状态修改成功！",{icon: 1,time:2000,anim: 1,shade:0.5});
                }else{
                    layer.msg("客房状态修改失败！",{icon: 2,time:2000,anim: 2,shade:0.5});
                }
            },"text" //text : 表示后端响应的是文本
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }


    /******************************自定义绑定函数*********************/
    //将空闲的客房的显示状态由1(显示)----->0(不显示)
    $("ul").eq(0).on('click','button',function (){
        //获取客房的id
        var roomid = $(this).attr("roomid");
        layer.confirm('真的要删除空闲的客房么', function(index){
            //向服务端发送修改客房的显示状态
            updRoomsFlag(roomid,'0');
            layer.close(index);
        });
    })

//删除按钮 - 将打扫的客房的显示状态由1(显示)----->0(不显示)
    //空闲按钮 - 将打扫的客房的客房状态由 2(打扫) ----> 0（空闲）
    $("ul").eq(2).on('click','button',function (){
        //判断点击了哪个按钮
        var event = $(this).val();
        //获取客房的id
        var roomid = $(this).attr("roomid");
        if(event == 'del'){ //点击了删除
            layer.confirm('真的要删除打扫状态的客房么', function(index){
                //向服务端发送修改客房的显示状态
                updRoomsFlag(roomid,'0');
                layer.close(index);
            });
        }else{ //点击了修改
            layer.confirm('真的要把打扫状态的客房置为空闲吗', function(index){
                //向服务端发送修改客房的状态  ： 2 -> 0
                updRoomsStatus(roomid,'0');
                layer.close(index);
            });
        }
    })


    //绑定添加客房信息的按钮
    $("#saveRoomsUI").click(function (){
        //2.把之前填写过的内容清空
        $("form").eq(0).find("input").val("");
        element.progress('demo', '0%'); //进度条复位
        //3.默认回显图片
        //回显页面上默认显示的图片
        $("#demo1").attr("src","/img/fm1.jpg");
        //回显添加到数据库的默认的图片
        $("#roomPicId").val("fm1.jpg");
        //1.弹框
        layer.open({
            type:1,  //弹出类型
            title:"客房添加操作界面",  //弹框标题
            area:['400px','500px'],  //弹框款高度
            anim: 2,  //弹出的动画效果
            shade:0.5,  //阴影遮罩
            content:$("#saveRoomsDiv")  //弹出的内容
        });
    })

    //加载所有的客房类型的数据
    function loadAllRoomType(){
        $.post(
            "roomType/loadAllT", //请求的url路径 ，使用base中方法
            function (data){
                console.log("data:",data);
                //拼接客房类型数据的下拉框数据
                //通过模板字符串完成
                var roomTypeStr = `<option value="">---请选择客房类型---</option>`;
                $.each(data,function (i,roomType){
                    roomTypeStr += `<option value="${roomType.id}">${roomType.roomTypeName} - ${roomType.roomPrice}</option>`;
                })
                //把拼接好的下拉框数据，放入到对应的下拉框元素中
                $("#selRoomType").html(roomTypeStr);
                //下拉框的渲染
                form.render("select");
            },"json"
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }


    //向服务器发送请求，验证房间号的唯一性
    function checkRoomNum(roomNum){
        //在$.post()前把ajax设置为同步请求：
        $.ajaxSettings.async = false;
        $.post(
            "rooms/getCountByParams", //请求的url路径 ，使用base中方法
            {"roomNum":roomNum},
            function (data){
                console.log("data:",data);
                if(data == 0){
                    //可用
                    checkRoomNumIf = true;
                    layer.tips('此房间号可以使用','#roomNum', {tips: [2,'green'],time:2000,tipsMore: true});
                }else{
                    //不可用
                    checkRoomNumIf = false;
                    layer.tips('此房间号不可以使用','#roomNum', {tips: [2,'red'],time:2000,tipsMore: true});
                }
            },"json"
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }


    //向服务器发送添加请求
    function saveRoom(saveJsonRoom){
        $.post(
            "rooms/saveT", //请求的url路径
            saveJsonRoom,
            function (data){
                if(data === 'success'){
                    //重新加载客房数据
                    loadAllRooms();
                    layer.msg("客房信息添加成功！",{icon: 1,time:2000,anim: 1,shade:0.5});
                }else{
                    layer.msg("客房信息添加失败！",{icon: 2,time:2000,anim: 2,shade:0.5});
                }
            },"text" //text : 表示后端响应的是文本
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }


});
