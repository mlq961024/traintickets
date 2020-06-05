<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" type="text/css" href="css/pintuer.css">
    <link rel="stylesheet" type="text/css" href="css/admin.css">
    <%--  <link href="css/bootstrap-theme.min.css">--%>
    <script src="js/jquery.min.js"></script>
    <script src="js/pintuer.js"></script>
    <script>

    </script>
</head>
<body>
<div>
    <div style="float: left;">
        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet?page='power'" method="post">
            <div class="form-group">
                <label for="exampleInputName2">用户名</label>
                <input type="text" name="id" value="${username}" class="form-control" id="exampleInputName2" >
            </div>
            <button type="submit" >查询</button>
        </form>
    </div>
</div>

<div class="panel admin-panel">
    <form >
        <table class="table table-hover text-center">
            <tr>
                <th>序号</th>
                <th>ID</th>
                <th >用户身份</th>
                <th >用户权限</th>
                <th >修改为</th>
            </tr>
            <c:forEach items="${pb.list}" var="userc" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${userc.id}</td>

                    <c:if test="${userc.power==0}">
                        <td>普通用户</td>
                    </c:if>
                    <c:if test="${userc.power==1}">
                        <td>售票员</td>
                    </c:if>
                    <c:if test="${userc.power==2}">
                        <td>管理员</td>
                    </c:if>

                    <td>${userc.power}</td>
                    <td>
                        <a class="button border-main" href="${pageContext.request.contextPath}/changepowerServlet?ids=${userc.id}&power=0">
            <span class="icon-edit">
            </span> 普通用户</a>
                        <a class="button border-main" href="${pageContext.request.contextPath}/changepowerServlet?ids=${userc.id}&power=1">
            <span class="icon-edit">
            </span>售票员</a>
                        <a class="button border-main" href="${pageContext.request.contextPath}/changepowerServlet?ids=${userc.id}&power=2">
            <span class="icon-edit">
            </span> 管理员</a>
                    </td>
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

                            <a href="${pageContext.request.contextPath}/findUserByPageServlet?page='power'&currentPage=${pb.currentPage - 1}&rows=5&id=${username}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${pb.totalPage}" var="i" >


                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?page='power'&currentPage=${i}&rows=5&id=${username}">${i}</a></li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?page='power'&currentPage=${i}&rows=5&id=${username}">${i}</a></li>
                        </c:if>

                    </c:forEach>
                    <c:if test="${pb.currentPage != pb.totalPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findUserByPageServlet?page='power'&currentPage=${pb.currentPage + 1}&rows=5&id=${username}" aria-label="Next">
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