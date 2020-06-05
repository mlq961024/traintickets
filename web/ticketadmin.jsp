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
    <title>车票</title>

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
    <script src="js/jquery.pure.tooltips.js"></script>

    <style type="text/css">
        html{width: 100%; height: 100%;}

        body{
            border-radius: 5px;

            background-repeat: no-repeat;


            background-size: 100% 100%;

        }
        a{
            cursor: pointer;
        }

    </style>
    <script>
        function delticket(id) {
            if(confirm("确定删除吗？")){
                //访问路径
                location.href="${pageContext.request.contextPath}/delTicketServlet?id="+id+"&currentPage="+${pb.currentPage};
            }
        }

    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">车票</h3>
    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/ticketServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName1">状态：</label>
                <select id="exampleInputName1" name="usering">
                    <option value="8">
                        --请选择状态--
                    </option>
                    <option value="0">
                        未使用
                    </option>
                    <option value="1">
                        已使用
                    </option>
                </select>
            </div>

            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <form id="form">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>序号</th>
                <th>票号</th>
                <th>车次</th>
                <th>乘车人</th>
                <th>乘车人身份识别码</th>
                <th>购票人</th>
                <th>开始站点</th>
                <th>结束站点</th>
                <th>日期</th>
                <th>开始时间</th>
                <th>到达时间</th>
                <th>座位号</th>
                <th>价格</th>
                <th>状态</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pb.list}" var="ticket" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${ticket.id}</td>
                    <td>${ticket.trainid}</td>
                    <td>${ticket.name}</td>
                    <td>${ticket.riderid}</td>
                    <td>${ticket.userid}</td>
                    <td>${ticket.start}</td>
                    <td>${ticket.end}</td>
                    <td>${ticket.date}</td>
                    <td>${ticket.startt}</td>
                    <td>${ticket.endt}</td>
                    <td>${ticket.sate}</td>
                    <td>${ticket.price}</td>
                    <c:if test="${ticket.usering==0}"><td><span style="color: #0a789b">未使用</span></td>
                    </c:if>
                    <c:if test="${ticket.usering==1}"><td></td><span style="color: red">已使用</span></td>
                    </c:if>
                    <td>
                        <a class="btn btn-default btn-sm" href="javascript:delticket('${ticket.id}');" >删除</a></td>
                </tr>

            </c:forEach>
        </table>
    </form>
    <c:if test="${pb.totalCount!=0}">
        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${pb.currentPage != 1}">
                        <li>

                            <a href="${pageContext.request.contextPath}/ticketServlet?currentPage=${pb.currentPage - 1}&rows=5"aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${pb.totalPage}" var="i" >


                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a href="${pageContext.request.contextPath}/ticketServlet?currentPage=${i}&rows=5">${i}</a></li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li><a href="${pageContext.request.contextPath}/ticketServlet?currentPage=${i}">${i}</a></li>
                        </c:if>

                    </c:forEach>
                    <c:if test="${pb.currentPage != pb.totalPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/ticketServlet?currentPage=${pb.currentPage + 1}&rows=5" aria-label="Next">
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
    <c:if test="${brackticket_msg !=null }"> <div class="alert alert-warning alert-dismissible" role="alert" style="margin-bottom: 0px">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span>
        </button>
        <strong>${brackticket_msg !=null}</strong>
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
