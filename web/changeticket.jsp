<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>请选择车票</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet" />
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script src="js/snow.js"></script>
    <script src="js/jquery.pure.tooltips.js"></script>
    <script src="js/spop.min.js"></script>

    <style type="text/css">
        html{width: 100%; height: 100%;}

        body{
            border-radius: 5px;

            background-repeat: no-repeat;


            background-size: 100% 100%;

        }

        .snow-container { position: fixed; top: 0; left: 0; width: 100%; height: 100%; pointer-events: none; z-index: 100001; }

    </style>
    <script>
        function changeticket(start,end,date,startt,endt,trainid) {
            if(confirm("确认改签吗")){
                //访问路径
                location.href="${pageContext.request.contextPath}/changeTicket?start="+start+"&end="+end+"&date="+date+"&startt="+startt+"&endt="+endt+"&trainid="+trainid;
            }
        }
    </script>
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
                            <li><a href="login.jsp">普通登陆</a></li>
                            <li><a href="#">售票员登陆</a></li>
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
        <c:if test="${user!=null}">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">票务大厅</a>
                        <ul class="dropdown-menu">
                            <li><a href="#">购票</a></li>
                            <li><a href="#">退票</a></li>
                            <li><a href="#">改签</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人中心</a>
                        <ul class="dropdown-menu drop1" style="text-align: center">
                            <li>${user.id}</li>
                            <li>账户余额：${user.balance}元</li>
                            <li><a href="#">我的车票</a></li>
                            <li><a href="#">我的通知</a></li>
                            <li><a>我的乘车人</a></li>
                            <li><a>充值</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </c:if>

    </div><!-- /.container-fluid -->
</nav>
<div class="container">
    <h3 style="text-align: center">车票改签</h3>
    <table border="1" class="table table-bordered table-hover">
            <tr><td colspan="11" style="text-align: left">您要改签的车票：</td></tr>
            <tr class="success">
                <th>票号</th>
                <th>车次</th>
                <th>乘车人</th>
                <th>乘车人身份识别码</th>
                <%--                <th>购票人</th>--%>
                <th>开始站点</th>
                <th>结束站点</th>
                <th>日期</th>
                <th>开始时间</th>
                <th>到达时间</th>
                <th>座位号</th>
                <th>价格</th>
            </tr>

                <tr>
                    <td>${oldticket.id}</td>
                    <td>${oldticket.trainid}</td>
                    <td>${oldticket.name}</td>
                    <td>${oldticket.riderid}</td>
                        <%--                    <td>${ticket.userid}</td>--%>
                    <td>${oldticket.start}</td>
                    <td>${oldticket.end}</td>
                    <td>${oldticket.date}</td>
                    <td>${oldticket.startt}</td>
                    <td>${oldticket.endt}</td>
                    <td>${oldticket.sate}</td>
                    <td>${oldticket.price}</td>
                </tr>
        </table>
    <br>
    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/selectTicketServlet?page=changeticket" method="post">
            <div class="form-group">
                <label for="exampleInputName1">日期</label>
                <input type="date" name="date" value="${date}" class="form-control" id="exampleInputName1" >
            </div>
            <div class="form-group">
                <label for="exampleInputName2">出发地</label>
                <input type="text" name="start" value="${start}" class="form-control" id="exampleInputName2" >
            </div>
            <div class="form-group">
                <label for="exampleInputName3">到达地</label>
                <input type="text" name="end" value="${end}" class="form-control" id="exampleInputName3" >
            </div>

            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <form id="form">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>编号</th>
                <th>车次</th>
                <th>开始站点</th>
                <th>结束站点</th>
                <th>日期</th>
                <th>开始时间</th>
                <th>到达时间</th>
                <th>价格</th>
                <th>数量</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pb.list}" var="ticket" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${ticket.id}</td>
                    <td>${ticket.start}</td>
                    <td>${ticket.end}</td>
                    <td>${ticket.date}</td>
                    <td>${ticket.startt}</td>
                    <td>${ticket.endt}</td>
                    <td>${ticket.price}</td>
                    <td>${ticket.num}</td>
                    <td>
                        <a class="btn btn-default btn-sm" href="javascript:changeticket('${ticket.start}','${ticket.end}','${ticket.date}','${ticket.startt}','${ticket.endt}','${ticket.id}');" >改签</a></td>
                </tr>

            </c:forEach>
            <c:if test="${pb.totalCount==0}">
                <tr>
                    <td colspan="10" style="text-align: center">对不起，未查到车票，请重新查询！</td>
                </tr>
            </c:if>


        </table>
    </form>
    <c:if test="${pb.totalCount!=0}">
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${pb.currentPage != 1}">
                        <li>

                            <a href="${pageContext.request.contextPath}/selectTicketServlet?page='changeticket'&currentPage=${pb.currentPage - 1}&rows=5&start=${ticket.start}&end=${ticket.end}&date=${ticket.date}&page=changeticket" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${pb.totalPage}" var="i" >


                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a href="${pageContext.request.contextPath}/selectTicketServlet?page='changeticket'&currentPage=${i}&rows=5&start=${ticket.start}&end=${ticket.end}&date=${ticket.date}&page=changeticket">${i}</a></li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li><a href="${pageContext.request.contextPath}/selectTicketServlet?page='changeticket'&currentPage=${i}&rows=5&start=${ticket.start}&end=${ticket.end}&date=${ticket.date}"&page=changeticket>${i}</a></li>
                        </c:if>

                    </c:forEach>
                    <c:if test="${pb.currentPage != pb.totalPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/selectTicketServlet?page='changeticket'&currentPage=${pb.currentPage + 1}&rows=5&start=${ticket.start}&end=${ticket.end}&date=${ticket.date}&page=changeticket" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <span style="font-size: 25px;margin-left: 5px;">
                    共${pb.totalCount}条记录，共${pb.totalPage}页
                </span>

                </ul>
            </nav>
        </div>
    </c:if>
    <c:if test="${changeticket_msg !=null }"> <div class="alert alert-warning alert-dismissible" role="alert" style="margin-bottom: 0px">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span>
        </button>
        <strong>${changeticket_msg !=null}</strong>
    </div></c:if>



</div>

<script>
    function time() {
        var data = new Date();
        var time = document.getElementById("time");
        time.innerHTML="现在是北京时间:"+ data.toLocaleString();
    }
    setInterval(time,1000);
    setTimeout(time,0);

</script>
</body>
</html>
