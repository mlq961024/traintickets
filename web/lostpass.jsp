<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>忘记密码</title>
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

        #fun{
            display: none;
        }
        #questionimg{
            border-radius: 5px;
        }
    </style>


</head>
<body>

<!-- 雪花背景 -->
<div class="snow-container"></div>
<!-- 登录控件 -->
<div id="login">
    <div class="container">
        <!-- 忘记密码页面 -->
        <div class="login sign-out-htm ">
            <form action="${pageContext.request.contextPath}/CheckAnwser" method="post" class="container offset1 loginform"id="lostform">
                <div class="pad input-container">
                    <section class="content">
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="forget-username" name="useid" autocomplete="off" placeholder="请输入用户名"/>
								<label class="input__label input__label--hideo" for="forget-username">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span id="fun">
                            <img id="questionimg" src="images/question.jpg">
                        </span>

                            <div class="input__field input__field--hideo " id="forget-psq" name="psq" align="left">
                                密保问题
                            </div>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="forget-answer" name="answer" autocomplete="off" placeholder="请输入密保答案"/>
								<label class="input__label input__label--hideo" for="forget-answer">
									<i class="fa fa-fw fa-key icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                    </section>
                </div>
                <div class="form-actions">
                    <a class="btn pull-left btn-link text-muted" href="login.jsp">返回登录</a>
                    <input class="btn btn-primary" type="submit"  value="获取密码"
                           style="color:white;"/>
                    <input class="btn btn-primary" type="button" onClick="refreshCode()" value="获取密保问题"
                           style="color:white;"/>
                </div>
                <c:if test="${lost_msg !=null }"> <div class="alert alert-warning alert-dismissible" role="alert" style="margin-bottom: 0px">
                    <button type="button" class="close" data-dismiss="alert" >
                        <span>&times;</span>
                    </button>
                    <strong>${lost_msg}</strong>
                </div></c:if>

            </form>
        </div>

    </div>
</div>
<script src="js/loginregist.js"></script>
<script type="text/javascript">
//密保问题
    function refreshCode(){
        var name =  $("#forget-username").val();
        if(name!=null){
            var qstimg = document.getElementById("questionimg");
            var fun = $("#fun");
            $("#forget-psq").css('display','none');
            fun.css('display','block');
            qstimg.src ="${pageContext.request.contextPath}/Checkquestion?question="+name;
        }
    }

</script>

</body>
</html>