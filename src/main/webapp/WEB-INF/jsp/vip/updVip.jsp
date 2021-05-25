<%--
  Created by IntelliJ IDEA.
  User: zhaojing
  Date: 2021/5/14
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改会员界面</title>
</head>
<body>
    <div align="center" style="display: none;margin-top: 20px;" id="updVipDiv">
        <form class="layui-form layui-form-pane" action="" lay-filter="updVipForm" style="margin-left: 50px;">
            <%--隐藏域，用于传递到后端去修改会员信息的id--%>
            <input type="hidden" name="id" id="vip_id"/>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone" id="phone" lay-verify="required|phone|checkPhone" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">会员类型：</label>
                <div class="layui-input-inline">
                    <select name="vipRate" id="vipRate" lay-verify="required">
                        <option value="0.9">普通会员</option>
                        <option value="0.8">超级会员</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item" style="margin-left: 70px;">
                <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="demo3"><i class="layui-icon">&#xe605;</i>修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </form>
    </div>
</body>
</html>
