<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: 莫立庆
  Date: 2020/2/20
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html >

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="applicable-device" content="pc,mobile" />
    <title>飞猫铁路购票</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet" />
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script src="js/snow.js"></script>
    <script src="js/jquery.pure.tooltips.js"></script>
    <script src="js/spop.min.js"></script>
    <style type="text/css">
        html{width: 100%; height: 100%;}

        body{
            background-color: #93defe;
            border-radius: 5px;
            background-repeat: no-repeat;
            background-size: 100% 100%;
        }
        #formstyle{
            padding-top: 30px;
            text-align: center;
            font-size: 20px;
        }
        #formstyle input{
            width: 200px;
            height: 26px;

        }

        .info select{ border:1px #993300 solid; background:#FFFFFF;}
        .snow-container { position: fixed; top: 0; left: 0; width: 100%; height: 120%; pointer-events: none; z-index: 100001; }
    </style>
</head>
<body>
<!-- 雪花背景 -->
<div class="snow-container"></div>
<!--导航栏-->
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
                            <c:if test="${user!=null}">
                            <li><a href="${pageContext.request.contextPath}/myTicketServlet">退票、改签</a></li>
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
                            <li><a href="${pageContext.request.contextPath}/myrider.jsp">我的乘车人</a></li>
                            <li><a href="${pageContext.request.contextPath}/changeinform.jsp">修改资料</a></li>
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
<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="position: absolute">
    <div class="carousel-inner" role="listbox">
        <div class="item active ">
            <img src="images/banner07.jpg" alt="...">

        </div>
        <div class="item">
            <img src="images/banner19.jpg" alt="...">

        </div>
        <div class="item">
            <img src="images/banner20.jpg" alt="...">
        </div>
    </div>


</div>
<div class="simpomeboder">
    <form name="form1" action="${pageContext.request.contextPath}/selectTicketServlet" method="post" id="formstyle">
<%--       二级联动--%>
<%--        <select id="s_province" name="s_province"></select>--%>
<%--        <select id="s_city" name="s_city" ></select>--%>
<%--        <script class="resources library" src="js/citys.js" type="text/javascript"></script>--%>
<%--        <script type="text/javascript">_init_area();</script>--%>
        <div style="margin-bottom: 15px">快速查询</div>

        <div class="form-group">
            <label for="date">日期 :</label>
            <input type="date"  id="date" name="date">
        </div>
        <div class="form-group">
            <label for="start">出发地:</label>
            <input type="text"  id="start" name="start">
        </div>
        <div class="form-group">
            <label for="end">到达地:</label>
            <input type="text" id="end" name="end">
        </div>

        <button type="submit" class="btn btn-default">查询</button>
    </form>
</div>
//内容广告
<div class="newsdiv">
    <table class="newschang">
        <tr>
            <a ><td id="news1">最新发布</td></a>
            <a ><td id="news2" style="color: #757575;background-color:#FFFFFF;">常见问题</td></a>
        </tr>
    </table>
</div>
<div style="margin-top: 90px">
    <ul id="newschang1" >
        <li class="lileft">
            <ul>
                <li>铁路部门再次调整免费退票措施</li>
                <li>铁路部门再出免费退票措施</li>
                <li>铁路部门再出免费退票措施</li>
                <li>铁路部门再次调整免费退票措施</li>
            </ul>
        </li>
        <li class="lileft">
            <ul>
                <li>铁路部门再次调整免费退票措施</li>
                <li>铁路部门再出免费退票措施</li>
                <li>1111</li>
                <li>铁路部门再次调整免费退票措施</li>
            </ul>
        </li>
    </ul>
    <ul id="newschang2" >
    <li class="lileft">
        <ul>
            <li>铁路部门再次调整免费退票措施</li>
            <li>铁路部门再出免费退票措施</li>
            <li>铁路部门再出免费退票措施</li>
            <li>铁路部门再次调整免费退票措施</li>
        </ul>
    </li>
    <li class="lileft">
        <ul>
            <li>铁路部门再次调整免费退票措施</li>
            <li>铁路部门再出免费退票措施</li>
            <li>222</li>
            <li>铁路部门再次调整免费退票措施</li>
        </ul>
    </li>
</ul>
</div>
<div class="footer-div">
    <p>
        <a href="#">公司简介</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="#">新闻中心</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="#">产品中心</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        <a href="#">联系我们</a>
    </p>
    <p class="copyright_p">© 2019-2020 飞猫购票</p>
</div>
<script type="text/javascript">

    function time() {
        var data = new Date();
        var time = document.getElementById("time");
        time.innerHTML="现在是北京时间:"+ data.toLocaleString();
    }
    setInterval(time,1000);
    setTimeout(time,0);

    $("#news1").click(function () {
        $("#newschang1").css('display','block');
        $("#newschang2").css('display','none');
        $("#news1").css({'background-color':'#3a95ed','color':'#ffffff'});
        $("#news2").css({'background-color':'#ffffff','color':'#757575'});
    });
    $("#news2").click(function () {
        $("#newschang2").css('display','block');
        $("#newschang1").css('display','none');
        $("#news2").css({'background-color':'#3a95ed','color':'#ffffff'});
        $("#news1").css({'background-color':'#ffffff','color':'#757575'});
    });
</script>

</body>
</html>

