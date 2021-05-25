<%--
  Created by IntelliJ IDEA.
  User: xiaosu
  Date: 2021/5/11
  Time: 8:57
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
    <style type="text/css">
        .layui-table td{
            height: 60px;
        }
    </style>
    <!--引入layui的js文件-->
    <script src="static/lib/layui/layui.js"></script>
</head>
<body>
<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>订单信息显示</legend>
    </fieldset>
    <div align="center">
        <!--查询的表单-->
        <form class="layui-form" action="" lay-filter="example" style="margin-top: 20px;">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">订单编号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="orderNum" autocomplete="off" class="layui-input" placeholder="请输入订单编号">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">时间范围</label>
                    <div class="layui-input-inline" style="width: 420px;">
                        <input type="text" class="layui-input" id="test3" placeholder="选则时间范围" name="queryTimes">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">订单状态</label>
                    <div class="layui-input-block">
                        <select name="orderStatus">
                            <option value="" selected>--全部--</option>
                            <option value="1">已支付</option>
                            <option value="0">未支付</option>
                        </select>
                    </div>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <button class="layui-btn" lay-submit="" lay-filter="demo1"><i class="layui-icon">&#xe615;</i>查询</button>
                    </div>
                </div>
            </div>
        </form>
        <!--订单信息数据显示的容器-->
        <table id="demo" lay-filter="test"></table>
    </div>
</div>
</body>
<!--引入layui的js文件-->
<script src="static/js/orders/showOrders.js"></script>
<%--开启头部工具栏--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container" style="float: left;">
        <button class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delBatchOrders">批量删除</button>
    </div>
</script>
<!--表格操作模板-->
<script type="text/html" id="barDemo">
    {{#  if(d.orderStatus == 1){ }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
    {{#  } else { }}
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="payUI"><i class="layui-icon">&#xe65e;</i>支付</a>
    {{#  } }}
</script>
<script type="text/html" id="isVipTpl">
    {{#  if(d.inRoomInfo.isVip == 1){ }}
    <font color="green">是</font>
    {{#  } else { }}
    <font color="red">否</font>
    {{#  } }}
</script>
<script type="text/html" id="orderStatusTpl">
    {{#  if(d.orderStatus == 1){ }}
    <font color="green">已结算</font>
    {{#  } else { }}
    <font color="red">未结算</font>
    {{#  } }}
</script>
</html>
