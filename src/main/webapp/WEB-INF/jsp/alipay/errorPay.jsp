<%--
  Created by IntelliJ IDEA.
  User: xiaosu
  Date: 2021/5/13
  Time: 11:02
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
    <title>支付失败</title>
</head>
<body>
    <div align="center">
        <font color="red">很遗憾，支付失败！！</font>
    </div>
</body>
</html>
