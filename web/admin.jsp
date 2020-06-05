<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>
    <link rel="stylesheet"type="text/css" href="css/pintuer.css">
    <link rel="stylesheet" type="text/css" href="css/admin.css">
    <script src="js/jquery.min.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1><img src="images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
    </div>
    <div class="head-l"><a class="button button-little bg-green" href="index.jsp" target="_blank">
        <span class="icon-home"></span>
        前台首页</a> &nbsp;
        &nbsp;&nbsp;
        <a class="button button-little bg-red" href="${pageContext.request.contextPath}/outLoginServlet">
            <span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
    <h2><span class="icon-user"></span>用户管理</h2>
    <ul style="display:block">
        <li><a href="changeinform.jsp" target="right"><span class="icon-leaf"></span>个人管理</a></li>
        <li><a href="${pageContext.request.contextPath}/findUserByPageServlet" target="right"><span class="icon-leaf"></span>注册用户管理</a></li>
        <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?page=power" target="right"><span class="icon-leaf"></span>权限修改</a></li>
    </ul>
    <h2><span class="icon-pencil-square-o"></span>车次管理</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/findstaionServlet" target="right"><span class="icon-leaf"></span>站点管理</a></li>
        <li><a href="${pageContext.request.contextPath}/findtrainServlet" target="right"><span class="icon-leaf"></span>车次修改</a></li>
        <li><a href="${pageContext.request.contextPath}/delatetrainServlet" target="right"><span class="icon-leaf"></span>删除车次</a></li>
        <li><a href="${pageContext.request.contextPath}/AddtrainServlet" target="right"><span class="icon-leaf"></span>添加车次</a></li>
    </ul>
    <a href="${pageContext.request.contextPath}/ticketServlet" target="right"><h2><span class="icon-adjust"></span>车票管理</h2></a>
</div>
<script type="text/javascript">
    $(function(){
        $(".leftnav h2").click(function(){
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        })
        $(".leftnav ul li a").click(function(){
            $("#a_leader_txt").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        })
    });
</script>
<ul class="bread">
    <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li>
    <li><a id="a_leader_txt">网站信息</a></li>
    <li><b>超级管理员：</b><span style="color:red;">${user.id}</span>
</ul>
<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="main.html" name="right" width="100%" height="100%"></iframe>
</div>
</body>
</html>