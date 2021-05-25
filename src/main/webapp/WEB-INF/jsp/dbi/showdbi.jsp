<%--
  Created by IntelliJ IDEA.
  User: zhaojing
  Date: 2021/5/21
  Time: 14:40
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
    <title>Title</title>
    <!--引入layui的样式文件-->
    <link rel="stylesheet" href="static/lib/layui/css/layui.css">
    <!--引入的js文件-->
    <script src="static/lib/echarts/echarts.min.js"></script>
    <script src="static/lib/echarts/jquery-1.12.4.js"></script>
    <script src="static/lib/layui/layui.js"></script>
</head>
<body>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>客房销售记录分析</legend>
    </fieldset>
    <!--数据显示的容器 -->
    <div align="center" id="main" style="width: 1000px;height:600px;"></div>
</body>
<script src="static/js/dbi/showdbi.js"></script>
</html>
