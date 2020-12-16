<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
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
<u:navbar></u:navbar>

<div class="container">
	<div class="container mx-auto">
		<h1>로그인하기</h1>
		<form action="login.do" method="post">
			<p>
			아이디 : <br /><input type="text"  name="id" value="${param.id }"/>
			</p>
			<p>
			비밀번호 : <br /><input type="password" name="password" id="" /> 
			</p>
			<input type="submit" class="btn btn-primary" value="로그인" />
		</form>	
		<br />
		<form action="join.do" method="post">
			<input type="submit" class="btn btn-primary" value="회원 가입" />
		</form>
	</div>
</div>
</body>
</html>