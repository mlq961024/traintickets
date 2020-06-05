<%--
  Created by IntelliJ IDEA.
  User: 莫立庆
  Date: 2020/2/15
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>登陆</title>
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/sign-up-login.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/inputEffect.css" />
    <link rel="stylesheet" href="css/tooltips.css" />
    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
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

            background-color: #00BDDC;

            background-image: url(images/snow.jpg);

            background-size: 100% 100%;

        }

        .snow-container { position: fixed; top: 0; left: 0; width: 100%; height: 100%; pointer-events: none; z-index: 100001; }

    </style>


</head>
<body>

<!-- 雪花背景 -->
<div class="snow-container"></div>
<!-- 登录控件 -->

<div id="login">
    <div class="container">
        <!-- 登录页面 -->
        <div class="login sign-in-htm">
            <form class="container offset1 loginform" id="loginform" method="post" action="${pageContext.request.contextPath}/login">
                <div class="pad input-container">
                    <section class="content">
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="login-username"
                                       autocomplete="off" placeholder="请输入用户名" tabindex="1" maxlength="15"  name="login-username"/>
								<label class="input__label input__label--hideo" for="login-username">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="login-password" name="login-password" placeholder="请输入密码" tabindex="2" maxlength="15"/>
								<label class="input__label input__label--hideo" for="login-password">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                    </section>
                </div>
                <div class="form-actions">
                    <a tabindex="4" class="btn pull-left btn-link text-muted" href="lostpass.jsp">忘记密码?</a>
                    <a tabindex="5" class="btn btn-link text-muted" href="regist.jsp">注册</a>
                    <a tabindex="4" class="btn pull-left btn-link text-muted" href="index.jsp">返回首页</a>
                    <input class="btn btn-primary" type="submit" tabindex="3" value="登录"
                           style="color:white;"/>
                </div>

<%--                <% request.getAttribute("login_msg"); %>--%>
                <c:if test="${login_msg !=null }"> <div class="alert alert-warning alert-dismissible" role="alert" style="margin-bottom: 0px">
                    <button type="button" class="close" data-dismiss="alert" >
                        <span>&times;</span>
                    </button>
                    <strong>${login_msg}</strong>
                </div></c:if>

            </form>

        </div>
    </div>
</div>

<script src="js/loginregist.js"></script>
</body>
</html>
