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
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<u:navbar></u:navbar>

<div class="container">
	<div class="jumbotron">
	  <h1 class="display-4">Guest님, 안녕하세요!</h1>
	  <p class="lead">새로운 커뮤니티로 들어오세요!</p>
	  <i class="fas fa-check-circle"></i>
		<i class="fas fa-user-clock"></i>
		<br />
		<button type="button" class="btn btn-primary">접속상태</button>
	  <hr class="my-4">
	  <p>회원가입부터 시작하세요!</p>
	  <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath }/join.do" role="button">회원가입</a>
	</div>
</div>
</body>
</html>