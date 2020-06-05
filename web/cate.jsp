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
    window.onload = function(){
      //给删除选中按钮添加单击事件
      document.getElementById("delSelected").onclick = function(){
        if(confirm("您确定要删除选中条目吗？")){
          var flag = false;
          //判断是否有选中条目
          var cbs = document.getElementsByName("uid");
          for (var i = 0; i < cbs.length; i++) {
            if(cbs[i].checked){
              //有一个条目选中了
              flag = true;
              break;
            }
          }

          if(flag){//有条目被选中
            //表单提交
            document.getElementById("form").submit();
          }

        }

      }
      //1.获取第一个cb
      document.getElementById("firstCb").onclick = function(){
        //2.获取下边列表中所有的cb
        var cbs = document.getElementsByName("uid");
        //3.遍历
        for (var i = 0; i < cbs.length; i++) {
          //4.设置这些cbs[i]的checked状态 = firstCb.checked
          cbs[i].checked = this.checked;
        }
      }
    }
  </script>
  <script >
    function deleteUser(id){
      //用户安全提示
      if(confirm("您确定要删除吗？")){
        //访问路径
        location.href="${pageContext.request.contextPath}/delUserServlet?id="+id;
      }
    }
  </script>
</head>
<body>
<div>
  <div style="float: left;">
    <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
      <div class="form-group">
        <label for="exampleInputName2">用户名</label>
        <input type="text" name="id" value="${username}" class="form-control" id="exampleInputName2" >
      </div>
      <button type="submit" >查询</button>
    </form>
  </div>
  <div style="float: right;margin-right: 100px">
    <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
  </div>
</div>

<div class="panel admin-panel">
  <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
  <table class="table table-hover text-center">
    <tr>
      <th><input type="checkbox" id="firstCb"></th>
      <th>序号</th>
      <th>ID</th>
      <th >性别</th>
      <th >出生日期</th>
      <th >操作</th>
    </tr>
    <c:forEach items="${pb.list}" var="userc" varStatus="s">
      <tr>
        <td><input type="checkbox" name="uid" value="${userc.id}"></td>
        <td>${s.count}</td>
        <td>${userc.id}</td>
        <td>${userc.sex}</td>
        <td>${userc.birthday}</td>
        <td>
          <a class="button border-main" href="${pageContext.request.contextPath}/changeUserServlet?id=${userc.id}">
            <span class="icon-edit">
            </span> 修改</a>
          <a class="button border-red" href="javascript:deleteUser('${userc.id}');">
            <span class="icon-trash-o"></span> 删除</a>
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

              <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage - 1}&rows=5&id=${username}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
          </c:if>
          <c:forEach begin="1" end="${pb.totalPage}" var="i" >


            <c:if test="${pb.currentPage == i}">
              <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&id=${username}">${i}</a></li>
            </c:if>
            <c:if test="${pb.currentPage != i}">
              <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5&id=${username}">${i}</a></li>
            </c:if>

          </c:forEach>
          <c:if test="${pb.currentPage != pb.totalPage}">
            <li>
              <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage + 1}&rows=5&id=${username}" aria-label="Next">
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