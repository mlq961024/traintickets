<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>注册</title>
    <link rel="stylesheet" type="text/css"href="css/normalize.css">
    <link rel="stylesheet" type="text/css"href="css/login.css">
    <link rel="stylesheet" type="text/css"href="css/sign-up-login.css">
    <link rel="stylesheet"type="text/css" href="css/inputEffect.css" />
    <link rel="stylesheet"type="text/css" href="css/tooltips.css" />
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
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
        <!-- 注册页面 -->
        <div class="login sign-up-htm">
            <form action="${pageContext.request.contextPath}/register" method="post" class="container offset1 loginform" id="registerform" style="max-width: 820px;top: 120px;">
                <div class="pad input-container "style="padding:25px 5px 0px 5px">
                    <section class="content" style="align: left">
                        <span class="input input--hideo">
                            <input class="input__field input__field--hideo" type="text" id="register-username"
                                   name="useid" autocomplete="off" placeholder="请输入用户名" maxlength="8"/>
                            <label class="input__label input__label--hideo" for="register-username">
                                <i class="fa fa-fw fa-user icon icon--hideo"></i>
                                <span class="input__label-content input__label-content--hideo"></span>
                            </label>
                        </span>
                        <span class="input input--hideo" >
                            <div class="input__field input__field--hideo" style="text-align: left">
                                <input type="radio" id="man" name="sex" value="男" checked/>男&nbsp;&nbsp;
                                <input type="radio" id="woman" name="sex" value="女" />女
                            </div>
                            <label class="input__label input__label--hideo" for="man">
                                <i class="fa fa-times fa- icon icon--hideo"></i>
                                <span class="input__label-content input__label-content--hideo"></span>
                            </label>
                            </span>
                        <span class="input input--hideo">
                                <input class="input__field input__field--hideo" type="password" id="register-password" name="password" placeholder="请输入密码" maxlength="15"/>
                                <label class="input__label input__label--hideo" for="register-password">
                                    <i class="fa fa-fw fa-lock icon icon--hideo"></i>
                                    <span class="input__label-content input__label-content--hideo"></span>
                                </label>
                            </span>
                        <span class="input input--hideo">
                                <input class="input__field input__field--hideo" type="password" id="register-repassword" placeholder="请确认密码" maxlength="15"/>
                                <label class="input__label input__label--hideo" for="register-repassword">
                                    <i class="fa fa-fw fa-lock icon icon--hideo"></i>
                                    <span class="input__label-content input__label-content--hideo"></span>
                                </label>
                            </span>
                        <span class="input input--hideo">
                                <input class="input__field input__field--hideo" type="text" id="psq"name="psq" placeholder="密保问题" maxlength="20"/>
                                <label class="input__label input__label--hideo" for="psq">
                                    <i class="fa fa-fw fa-question icon icon--hideo"></i>
                                    <span class="input__label-content input__label-content--hideo"></span>
                                </label>
                            </span>
                        <span class="input input--hideo">
                                <input class="input__field input__field--hideo" type="text" id="answer"name="answer" placeholder="密保答案" maxlength="20"/>
                                <label class="input__label input__label--hideo" for="answer">
                                    <i class="fa fa-fw fa-key icon icon--hideo"></i>
                                    <span class="input__label-content input__label-content--hideo"></span>
                                </label>
                            </span>
                        <span class="input input--hideo" >
                                <input class="input__field input__field--hideo" type="date" id="brithday"name="brithday" />
                                <label class="input__label input__label--hideo" for="brithday">
                                    <i class="fa fa-calendar icon icon--hideo"></i>
                                    <span class="input__label-content input__label-content--hideo"></span>
                                </label>
                            </span>
                        <span class="input input--hideo" style="width: 26%">
                                <input class="input__field input__field--hideo" type="text" id="checkcode" name="checkcode" placeholder="验证码" maxlength="20"/>
                                <label class="input__label input__label--hideo" for="checkcode">
                                    <i class="fa fa-fw fa-check-circle icon icon--hideo"></i>
                                    <span class="input__label-content input__label-content--hideo"></span>
                                </label>
                            </span>
                        <span class="input input--hideo" style="width: 100px">
                               <a onclick="refreshCode()"> <img id="checkimg" src="${pageContext.request.contextPath}/Checkcode"></a>
                            </span>

                    </section>
                </div>
                <div class="form-actions">
                    <a class="btn pull-left btn-link text-muted" href="login.jsp">返回登录</a>
                    <input class="btn btn-primary" type="reset"  value="重置"
                           style="color:white;"/>
                    <input class="btn btn-primary" type="submit"  value="注册"
                           style="color:white;"/>
                </div>
                <c:if test="${regist_msg !=null }"> <div class="alert alert-warning alert-dismissible" role="alert" style="margin-bottom: 0px">
                    <button type="button" class="close" data-dismiss="alert" >
                        <span>&times;</span>
                    </button>
                    <strong>${regist_msg}</strong>
                </div></c:if>

            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    //切换验证码
    function refreshCode(){
        //1.获取验证码图片对象
        var vcode = document.getElementById("checkimg");

        //2.设置其src属性，加时间戳
        vcode.src = "${pageContext.request.contextPath}/Checkcode?time="+new Date().getTime();
    }
    //验证

</script>
<script type="text/javascript">
    function checkcodef() {
        var coder = $("#checkcode").val();
        var flag = true ;

        if(coder == ""){
            $.pt({
                target: $("#checkcode"),
                position: 'r',
                align: 't',
                width: 'auto',
                height: 'auto',
                content:"验证码不能为空"
            });
            flag=false;


        }
        return flag;
    }

</script>
<script src="js/loginregist.js"></script>
</body>
</html>