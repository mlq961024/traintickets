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
    <title>删除车次</title>

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
    </style>

</head>
<body>
<div class="container">
    <h3 style="text-align: center">车次信息</h3>

    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/delatetrainServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">日期：</label>
                <select id="exampleInputName2" name="date">
                    <option value="0">
                        --请选择日期--
                    </option>
                    <c:forEach items="${dates}" var="dat">
                        <option value="${dat}" <c:if test="${date==dat}">selected</c:if> >
                                ${dat}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <form id="form">
        <table border="1" class="table table-bordered table-hover" >
            <tr class="success">
                <th>车次</th>
                <th>日期</th>
                <th>途径站点</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pb.list}" var="trainShow" >
                <tr>
                    <td>${trainShow.trainid}</td>
                    <td>${trainShow.date}</td>
                    <td>${trainShow.station}</td>
                        <td><a class="button border-red" href="javascript:deltrain('${trainShow.trainid}');" >
                            <span class="icon-trash-o"></span> 删除整个车次</a>&nbsp;<a class="button border-red" href="javascript:delonetrain('${trainShow.trainid}','${trainShow.date}');" >
                            <span class="icon-trash-o"></span> 删除当天车次</a></td>
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

                            <a href="${pageContext.request.contextPath}/delatetrainServlet?currentPage=${pb.currentPage - 1}&rows=5&date=${date}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${pb.totalPage}" var="i" >


                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a href="${pageContext.request.contextPath}/delatetrainServlet?currentPage=${i}&rows=5&date=${date}">${i}</a></li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li><a href="${pageContext.request.contextPath}/delatetrainServlet?currentPage=${i}&rows=5&date=${date}">${i}</a></li>
                        </c:if>

                    </c:forEach>
                    <c:if test="${pb.currentPage != pb.totalPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/delatetrainServlet?currentPage=${pb.currentPage + 1}&rows=5&date=${date}" aria-label="Next">
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

</div>
<script>

    //删除方法
    function deltrain(obj){
        if (confirm("确定删除吗？")){
            location.href="${pageContext.request.contextPath}/delonetrain?id="+id;
        }
    }
    function delonetrain(obj,ob){
        if (confirm("确定删除吗？")){
            location.href="${pageContext.request.contextPath}/deltrain?id="+id+"&date="+ob;
        }
    }


</script>
</body>
</html>


