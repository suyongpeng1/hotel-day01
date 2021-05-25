<%--
Created by IntelliJ IDEA.
User: xiaosu
Date: 2021/5/18
Time: 8:55
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() +
            ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1,
maximum-scale=1">
    <title>Title</title>
    <!--引入layui的样式文件-->
    <link rel="stylesheet" href="static/lib/layui/css/layui.css">
    <!--引入客房样式的css文件-->
    <link rel="stylesheet" href="static/css/back/showRooms.css">
    <!--引入layui的js文件-->
    <script src="static/lib/layui/layui.js"></script>

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top:
30px;">
    <legend>房屋类型管理</legend>
</fieldset>
<button id="saveRoomTypeBtn" type="button" class="layui-btn layui-btn-radius" style="margin-left: 30px;margin-bottom: 20px;"><i class="layui-icon">&#xe654;</i>添加房间类型</button>
<!--房型数据面板的容器-->
<div class="layui-collapse" lay-filter="test" id="collapseDiv"></div>
<!--分页标签容器-->
<div id="test1" style="float: right;margin-top: 15px;">
</div>
<%--引入客房修改页面的jsp--%>
<jsp:include page="updRoomType.jsp"/>
<%--引入客房类型添加页面jsp--%>
<jsp:include page="saveRoomType.jsp"/>
</body>
<script src="static/js/roomType/showRoomType.js"></script>
</html>