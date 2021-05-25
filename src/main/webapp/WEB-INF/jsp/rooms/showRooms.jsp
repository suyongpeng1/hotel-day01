<%--
  Created by IntelliJ IDEA.
  User: xiaosu
  Date: 2021/5/14
  Time: 11:45
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
    <title>客房管理页面</title>
    <!--引入layui的样式文件-->
    <link rel="stylesheet" href="static/lib/layui/css/layui.css">
    <link rel="stylesheet" href="static/css/back/showRooms.css">
    <!--引入layui的js文件-->
    <script src="static/lib/layui/layui.js"></script>
</head>
<body>
    <p><button type="button" id="saveRoomsUI" class="layui-btn layui-btn-warm layui-btn-lg"><i class="layui-icon">&#xe654;</i>添加</button></p>
    <div id="LAY_preview">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>空闲客房信息</legend>
        </fieldset>
        <%--展示空闲客房的信息--%>
        <ul class="site-doc-icon site-doc-anim"></ul>

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>已入住客房信息</legend>
        </fieldset>
        <%--展示已入住客房的信息--%>
        <ul class="site-doc-icon site-doc-anim"></ul>

        <fieldset class="layui-elem-field layui-field-title">
            <legend>打扫的客房信息</legend>
        </fieldset>
        <%--展示打扫客房的信息--%>
        <ul class="site-doc-icon site-doc-anim"></ul>
    </div>
    <%--引入客房信息添加页面--%>
    <jsp:include page="saveRooms.jsp"/>
</body>
<script src="static/js/rooms/showRooms.js"></script>
</html>
