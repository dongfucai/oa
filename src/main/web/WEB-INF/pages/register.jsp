<%--
  Created by IntelliJ IDEA.
  User: dongfucai
  Date: 2018/4/15
  Time: 下午9:46
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path=request.getContextPath();
%>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title></title>
</head>
<body>

<h1>注册</h1>
<form id="loginForm"  action="<%=path%>/register" method="post">
    用户名：<input type="text" name="loginName"/><br>
    密  码：<input type="text" name="password"/><br>
    密  码:<input type="text"   name="password2"/> <br>
    <input type="submit" value="注册"/>
</form>
</body>
</html>