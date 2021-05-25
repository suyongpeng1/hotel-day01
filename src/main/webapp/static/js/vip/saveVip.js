 layui.use(['jquery','layer', 'table','form','laydate'], function() {
    var $ = layui.jquery    //引入jquery模块
        , layer = layui.layer  //引用layer弹出层模块
        , table = layui.table  //引用table数据表格模块
        , form = layui.form  //引用form表单模块
        , laydate = layui.laydate;  //引用日期模块

    //全局变量-验证身份证号
    var checkIdcardIf = false;

    //全局变量-验证手机号
    var checkPhoneIf = false;

    //自定义验证
    form.verify({
        checkIdcard: function(value, item){ //value：表单的值、item：表单的DOM对象
            //去服务器验证身份证号是否唯一
            checkIdcard(value);
            if(!checkIdcardIf){
                return "身份证号不可用！";
            }
        },
        checkPhone: function(value, item){ //value：表单的值、item：表单的DOM对象
            //去服务器验证手机号是否唯一
            checkPhone(value);
            if(!checkPhoneIf){
                return "手机号不可用！";
            }
        }
    });

    //下拉框的监听
    form.on('select(vipRate)', function(data){
        console.log(data.value); //得到被选中的值
        /*得到日期的字符串格式*/
        var nowDateStr = getNowDate(new Date());
        //给隐藏域的日期赋值
        $("#createDate").val(nowDateStr);
        //判断会员折扣
        if(data.value == '0.8'){
            //生成卡号   日期数字 + 后缀 01:超级会员 /  02: 普通会员
            $("#vipNum").val(dateReplace(nowDateStr) + "01");
        }else{
            $("#vipNum").val(dateReplace(nowDateStr) + "02");
        }
    });

    //监听添加会员按钮，提交表单
    form.on('submit(demo2)', function(data){
        console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
        //向服务器发送添加请求
        saveVip(data.field);
        layer.closeAll();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });


    /**************************自定义函数***********************/
    //去服务器验证身份证号是否唯一
    function checkIdcard(idcard){
        //在$.post()前把ajax设置为同步请求：
        $.ajaxSettings.async = false;
        $.post(
            "vip/getCountByParams", //请求的url路径 ，使用base中方法
            {"idcard":idcard}, //数据
            function (data){
                console.log("data:",data);
                if(data == 0){
                    //身份证号可用
                    checkIdcardIf = true;
                    //tipsMore: true允许多个吸附框的弹出
                    layer.tips('此身份证号可以使用','#idcard', {tips: [2,'green'],time:2000,tipsMore: true});
                }else{
                    checkIdcardIf = false;
                    layer.tips('此身份证号不可以使用','#idcard', {tips: [2,'red'],time:2000,tipsMore: true});
                }
            },"json"
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }

    //去服务器验证手机号是否唯一
    function checkPhone(phone){
        //在$.post()前把ajax设置为同步请求：
        $.ajaxSettings.async = false;
        $.post(
            "vip/getCountByParams", //请求的url路径 ，使用base中方法
            {"phone":phone}, //数据
            function (data){
                console.log("data:",data);
                if(data == 0){
                    //手机号可用
                    checkPhoneIf = true;
                    layer.tips('此手机号可以使用','#phone', {tips: [2,'green'],time:2000,tipsMore: true});
                }else{
                    checkPhoneIf = false;
                    layer.tips('此手机号不可以使用','#phone', {tips: [2,'red'],time:2000,tipsMore: true});
                }
            },"json"
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }

    //向服务器发送添加请求
    function saveVip(saveJsonVip){
        $.post(
            "vip/saveT", //请求的url路径
            saveJsonVip,
            function (data){
                if(data === 'success'){
                    layer.msg("会员信息添加成功！",{icon: 1,time:2000,anim: 1,shade:0.5});
                     //2秒之后跳转到会员显示页面
                    setTimeout("window.location.href='model/toShowVip'",2000);
                }else{
                    layer.msg("会员信息添加失败！",{icon: 2,time:2000,anim: 2,shade:0.5});
                }
            },"text" //text : 表示后端响应的是文本
        ).error(function (){
            layer.msg("数据请求异常！",{icon: 7,time:2000,anim: 3,shade:0.5});
        })
    }

});
