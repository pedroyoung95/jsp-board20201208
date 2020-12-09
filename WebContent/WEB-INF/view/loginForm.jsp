<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<form action="login.do" method="post">
	<c:if test="${errors.idOrPwNotMatch }">아이디와 암호가 일치하지 않습니다.</c:if>
	<p>
		아이디 : <br /><input type="text" name="id" value="${param.id }" />
		<c:if test="${errors.id }">ID를 입력하세요</c:if>
	</p>
	<p>
		암호 : <br /><input type="password" name="password" id="" />
		<c:if test="${errors.password }">암호를 입력하세요</c:if>
	</p>		
	<input type="submit" value="로그인" />
	</form>
</div>
</body>
</html>