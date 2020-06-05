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
    <script src="js/snow.js"></script>
    <script src="js/jquery.pure.tooltips.js"></script>
    <script src="js/spop.min.js"></script>

</head>
<body>
<div class="container">

    <div >
        <h3 style="text-align: center">站点添加</h3>
        <form class="form-inline" action="${pageContext.request.contextPath}/addstationServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">省份：</label>
                <select id="exampleInputName2" name="pro">
                    <option value="0">
                        --请选择省份--
                    </option>
                    <c:forEach items="${allpro}" var="city" >
                        <option value="${city}">
                                ${city}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="exampleInputName3">城市：</label>
                <input type="text" name="city" class="form-control" id="exampleInputName3" >
            </div>

            <button type="submit" class="btn btn-default">添加</button>
            <c:if test="${addstation_msg!=null }"> <div class="alert alert-warning alert-dismissible" role="alert" style="margin-bottom: 0px">
                <button type="button" class="close" data-dismiss="alert" >
                    <span>&times;</span>
                </button>
                <strong>${addstation_msg}</strong>
            </div></c:if>
        </form>

    </div>

    <div>
        <h3 style="text-align: center">站点查询</h3>

        <form class="form-inline" action="${pageContext.request.contextPath}/findstaionServlet" method="post" id="staionform">
            <div class="form-group">
                <label for="exampleInputName1">省份：</label>
                <select id="exampleInputName1" name="proo">
                    <option value="0">
                        --请选择省份--
                    </option>
                    <c:forEach items="${allpro}" var="city" >
                        <option value="${city}">
                                ${city}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <form id="form">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>编号</th>
                <th>城市</th>
                <th>省份</th>
            </tr>
            <c:forEach items="${pb.list}" var="station" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${station.city}</td>
                    <td>${station.province}</td>
                   </tr>

            </c:forEach>
            <c:if test="${pb.totalCount==0}">
                <tr>
                    <td colspan="3" style="text-align: center">对不起，未查到，请重新查询！</td>
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

                            <a href="${pageContext.request.contextPath}/findstaionServlet?currentPage=${pb.currentPage - 1}&rows=5" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${pb.totalPage}" var="i" >


                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a href="${pageContext.request.contextPath}/findstaionServlet?currentPage=${i}&rows=5">${i}</a></li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li><a href="${pageContext.request.contextPath}/findstaionServlet?currentPage=${i}&rows=5">${i}</a></li>
                        </c:if>

                    </c:forEach>
                    <c:if test="${pb.currentPage != pb.totalPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findstaionServlet?currentPage=${pb.currentPage + 1}&rows=5" aria-label="Next">
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
</body>
</html>
