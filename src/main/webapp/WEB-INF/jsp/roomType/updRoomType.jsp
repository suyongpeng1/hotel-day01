<%--
  Created by IntelliJ IDEA.
  User: XiaoSu
  Date: 2021/5/18
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>房型信息修改页面</title>
</head>
<body>
<!--房间类型修改的div-->
<div style="display: none;margin-top: 20px;" id="updRoomTypeDiv">
    <form class="layui-form layui-form-pane" lay-filter="updRoomTypeFromFilter" action="" style="margin-left: 50px;">
        <input type="hidden" name="id">
        <div class="layui-form-item">
            <label class="layui-form-label">类型名称：</label>
            <div class="layui-input-inline">
                <input type="text" name="roomTypeName" disabled autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">类型价格：</label>
            <div class="layui-input-inline">
                <input type="text" name="roomPrice" placeholder="请输入房间类型价格" lay-verify="required|number|roomPrice" autocomplete="off" class="layui-input"
                       step="1"  min="0" onkeyup="this.value= this.value.match(/\d+(\.\d{0,2})?/) ? this.value.match(/\d+(\.\d{0,2})?/)[0] : ''">
            </div>
        </div>
        <div class="layui-form-item" style="margin-left: 70px;margin-top: 30px;">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="demo4"><i class="layui-icon">&#xe609;</i>修改房间类型</button>
        </div>
    </form>
</div>
</body>
</html>
