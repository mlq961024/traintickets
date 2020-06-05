<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta http-equiv="Expires" content="0"> 
<title>后台管理</title>
<link href="css/adminlogin.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="login_box">
      <div class="login_l_img"><img src="images/login-img.png" /></div>
      <div class="login">
          <div class="login_logo"><a href="#"><img src="images/login_logo.png" /></a></div>
          <div class="login_name">
               <p>后台管理系统</p>
          </div>
          <form method="post" action="${pageContext.request.contextPath}/login?page=adminlogin">
              <input name="login-username" type="text"  value="用户名" onfocus="this.value=''" onblur="if(this.value==''){this.value='用户名'}">
              <span id="password_text" onclick="this.style.display='none';document.getElementById('password').style.display='block';document.getElementById('password').focus().select();" >密码</span>
<input name="login-password" type="password" id="password" style="display:none;" onblur="if(this.value==''){document.getElementById('password_text').style.display='block';this.style.display='none'};"/>
              <input value="登录" style="width:100%;" type="submit">
              <c:if test="${login_msg !=null }"> <div class="alert alert-warning alert-dismissible" role="alert" style="margin-bottom: 0px">
                  <button type="button" class="close" data-dismiss="alert" >
                      <span>&times;</span>
                  </button>
                  <strong>${login_msg}</strong>
              </div></c:if>
              <a href="index.jsp">返回主页</a>
          </form>
      </div>

</div>
</body>
</html>
