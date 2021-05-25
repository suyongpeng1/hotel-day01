<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" href="static/lib/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="static/css/back/login.css" />
</head>

<body >
	<div class="qiqiu1 qiqiu">
		<img src="http://qt6nn5f1q.hn-bkt.clouddn.com/q2.png" />
		<div class="text">苏氏集团</div>
	</div>
	<div class="qiqiu2 qiqiu">
		<img src="http://qt6nn5f1q.hn-bkt.clouddn.com/q3.png" />
		<div class="text">苏氏集团</div>
	</div>
	<div class="qiqiu3 qiqiu">
		<img src="http://qt6nn5f1q.hn-bkt.clouddn.com/q4.png" />
		<div class="text">苏氏集团</div>
	</div>
	<div class="qiqiu4 qiqiu">
		<img src="http://qt6nn5f1q.hn-bkt.clouddn.com/q5.png" />
		<div class="text">苏氏集团</div>
	</div>
	<div class="qiqiu5 qiqiu">
		<img src="http://qt6nn5f1q.hn-bkt.clouddn.com/q6.png" />
		<div class="text">苏氏集团</div>
	</div>

	<div class="qiqiu6 qiqiu">
		<img src="http://qt6nn5f1q.hn-bkt.clouddn.com/q2.png" />
		<div class="text">苏氏集团</div>
	</div>


	<div class="login" style="height: 300px;">
		<h1>用户后台登录</h1>
		<!--拦截后的提示-->
		<input type="hidden" id="loginUIMsg" value="${loginUIMsg }">
		<form class="layui-form" action="" lay-filter="example">
			<div class="layui-form-item">
				<input id="userName" class="layui-input" name="username" placeholder="用户名"
					lay-verify="required|userName" type="text" autocomplete="off">
			</div>
			<div class="layui-form-item">
				<input id="pwd" class="layui-input" name="pwd" placeholder="密码"
					lay-verify="required|pwd" type="password" autocomplete="off">
			</div>
			<div class="layui-form-item">
			    <input id="yzm" class="layui-input" placeholder="验证码(不区分大小写)"
				  type="text" autocomplete="off" style="margin-bottom: 10px;" lay-verify="required|verifyCheck">
				<p align="center"><img src="user/getVerifyCode" onclick=flushCheckCode(this) alt="点击刷新验证码" style="cursor: hand" /></p>
		    </div>
			<button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
		</form>
	</div>

	<script type="text/javascript">
		function flushCheckCode(obj) {
			//注意：这里的 ? 后面的new Date() ，目的是让每次请求路径的参数都不一样，这样才能刷新验证码
			obj.src = (obj.src + '?' + new Date())
		}
	</script>

<script src="static/lib/layui/layui.js"></script>
<script src="static/js/login/login.js"></script>
</body>
</html>
