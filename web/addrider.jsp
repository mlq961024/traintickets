<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>购票</title>
    <link rel="stylesheet"type="text/css" href="css/normalize.css">
    <link rel="stylesheet"type="text/css" href="css/login.css">
    <link rel="stylesheet" type="text/css"href="css/sign-up-login.css">
    <link rel="stylesheet"type="text/css" href="css/inputEffect.css" />
    <link rel="stylesheet"type="text/css" href="css/tooltips.css" />
    <!-- 1. 导入CSS的全局样式 -->
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/snow.js"></script>
    <script src="js/jquery.pure.tooltips.js"></script>
    <script src="js/spop.min.js"></script>

    <style type="text/css">
        html{width: 100%; height: 100%;}

        body{

            background-repeat: no-repeat;


            background-image: url(images/bg01.jpg);

            background-size: 100% 100%;

        }

        .snow-container { position: fixed; top: 0; left: 0; width: 100%; height: 100%; pointer-events: none; z-index: 100001; }

        span {
            color: #e74c3c;
        }
        tr{

            width: 550px;
        }
    </style>


</head>
<body>

<!-- 雪花背景 -->
<div class="snow-container"></div>
<div style="background-color: #9196A1;color: #FFFFFF;height: 25px;padding-top: 3px" >
    <div>
        <div id="time" >
        </div>
        <div id="loginstyle01">
            <c:if test="${user != null}">
                欢迎登陆飞猫网${user.id}!
            </c:if>
            <c:if test="${user == null}">
                <ul class="nav navbar-nav navbar-right" style="height: 25px;margin-right: 15px">
                    <li class="dropdown loginstyle" >
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">登陆</a>
                        <ul class="dropdown-menu">
                            <li><a href="login.jsp">登陆</a></li>
                            <li><a href="adminlogin.jsp">管理员登陆</a></li>
                        </ul>
                    </li>
                    <li class="loginstyle"><a href="regist.jsp">注册</a></li>
                </ul>

            </c:if></div>

    </div>


</div>
<nav class="navbar navbar-default" style="margin-bottom: 0px">
    <div class="container-fluid" >
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">主页</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">票务大厅</a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/selectTicketServlet">查询车票</a></li>
                        <c:if test="${user!=null&&user.power==0}">
                            <li><a href="${pageContext.request.contextPath}/myTicketServlet">退票、改签</a></li>
                        </c:if>
                        <c:if test="${user!=null&&user.power==1}">
                            <li><a href="${pageContext.request.contextPath}/sellticket.jsp">退票、改签</a></li>
                        </c:if>
                    </ul>
                </li>
            </ul>
            <c:if test="${user!=null&&user.power==0}">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人中心</a>
                        <ul class="dropdown-menu drop1" style="text-align: center">
                            <li>${user.id}</li>
                            <li>账户余额：${user.balance}元</li>
                            <li><a href="${pageContext.request.contextPath}/myTicketServlet">我的车票</a></li>
                            <li><a href="#">我的通知</a></li>
                            <li><a href="/myrider.jsp">我的乘车人</a></li>
                            <li><a href="/changeinform.jsp">修改资料</a></li>
                            <li><a href="${pageContext.request.contextPath}/addmoney.jsp">充值</a></li>
                            <li><a href="${pageContext.request.contextPath}/outLoginServlet">退出登陆</a></li>
                        </ul>
                    </li>
                </ul>
            </c:if>
            <c:if test="${user!=null&&user.power==1}">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人中心</a>
                        <ul class="dropdown-menu drop1" style="text-align: center">
                            <li>${user.id}</li>
                            <li><a href="#">我的通知</a></li>
                            <li><a href="${pageContext.request.contextPath}/addmoney.jsp">充值</a></li>
                            <li><a href="${pageContext.request.contextPath}/changeinform.jsp">修改资料</a></li>
                            <li><a href="${pageContext.request.contextPath}/outLoginServlet">退出登陆</a></li>
                        </ul>
                    </li>
                </ul>
            </c:if>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div id="login">

    <div class="container" style="width: 550px;height: 400px ;background-color: #5cb85c;margin-top: 30px;border-radius: 6px">
        <form action="${pageContext.request.contextPath}/addRiderServlet" method="post" class="container offset1"  id="buytickform" style="width: 540px">
            <table style="text-align: center;border: #FFFFFF solid 3px;margin:80px 26px;">
                <tr>
                    <td colspan="4"><h3>请添加乘车人</h3></td>
                </tr>
                <tr>
                   <td ><label>乘车人身份识别号：</label>
                    <td><input name="riderid" placeholder="请输入身份识别号"></td>
                   </td>
                    <td ><label>乘车人真实姓名：</label>
                    <td><input name="ridername" placeholder="请输入真实姓名"></td>
                    </td>
                </tr>
                <tr>
                    <td ><label>添加联系人账户：</label></td><td>
                    <input type="text" readonly="readonly" value="${user.id}" name="usreid" style="width: 100px" ></td>
                    <td colspan="2" class="form-actions">
                        <input class="btn btn-primary" type="reset"  value="重置"
                               style="color:white;"/>
                        <input class="btn btn-primary" type="submit"  value="添加"
                               style="color:white;"/>

                    </td>
                </tr>

            </table>
            <c:if test="${addrider_msg !=null }">  <tr><div class="alert alert-warning alert-dismissible" role="alert" style="margin-bottom: 0px">
                <button type="button" class="close" data-dismiss="alert" >
                    <span>&times;</span>
                </button>
                <strong>${addrider_msg}</strong>
            </div></tr></c:if>
        </form>
    </div>
</div>

<script src="js/loginregist.js"></script>
</body>
</html>