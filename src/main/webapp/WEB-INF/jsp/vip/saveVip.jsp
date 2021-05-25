<%--
  Created by IntelliJ IDEA.
  User: zhaojing
  Date: 2021/5/14
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>vip添加页面</title>
    <!--引入layui的样式文件-->
    <link rel="stylesheet" href="static/lib/layui/css/layui.css">
    <!--引入layui的js文件-->
    <script src="static/lib/layui/layui.js"></script>
    <style type="text/css">
        .layui-form-item{
            margin-top: 32px;
        }

    </style>
</head>
<body>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>VIP会员信息添加</legend>
    </fieldset>
    <form class="layui-form layui-form-pane" action="">
        <%--当前日期--%>
        <input type="hidden" id="createDate" name="createDate"/>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证号：</label>
            <div class="layui-input-inline" style="width: 300px;">
                <!--identity为身份证号验证-->
                <input type="text" name="idcard" id="idcard" lay-verify="required|identity|checkIdcard" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客人姓名：</label>
                <div class="layui-input-inline" style="width: 300px;">
                    <input type="text" name="customerName" lay-verify="required" placeholder="请输入客人姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">会员卡号：</label>
            <div class="layui-input-inline" style="width: 300px;">
                <input type="text"  name="vipNum" id="vipNum" placeholder="自动生成会员卡号" autocomplete="off" class="layui-input" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系方式：</label>
            <div class="layui-input-inline" style="width: 300px;">
                <input type="text" id="phone" name="phone" placeholder="请输入联系方式" lay-verify="required|phone|checkPhone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">会员类型：</label>
            <div class="layui-input-inline" style="width: 300px;">
                <select name="vipRate" lay-filter="vipRate" lay-verify="required">
                    <option value="">---请选择会员类型---</option>
                    <option value="0.9">普通会员</option>
                    <option value="0.8">超级会员</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">选择性别：</label>
                <div class="layui-input-block">
                    <input type="radio" name="gender" value="1" title="男" checked="checked"/>
                    <input type="radio" name="gender" value="0" title="女"/>
                    <input type="radio" name="gender" value="2" title="未识别" disabled="">
                </div>
            </div>
        </div>
        <div class="layui-form-item" style="margin-left: 100px;margin-top: 40px;">
            <button class="layui-btn" lay-submit="" lay-filter="demo2">确认添加</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
</body>
<script src="static/js/vip/saveVip.js"></script>
<%--引入公共的js--%>
<script src="static/js/common/common.js"></script>
</html>
