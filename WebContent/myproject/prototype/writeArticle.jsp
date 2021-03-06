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
<title>게시글 작성</title>
</head>
<body>
<u:navbar></u:navbar>

<div class="container">
	<div class="row">
		<div class="col-3"></div>
		
		<div class="col-6">
			<h1>게시글 등록</h1>
			<form action="write.do" method="post">
				<div class="form-group">
					<label for="input1-title">제목</label>
					<input type="text" id="input1-title" name="title" value="${param.title }" class="form-control"/>
					
						<small class="form-text text-danger">제목을 입력하세요.</small>
					
				</div>
				<div class="form-group">
					<label for="input2-content">내용</label>					
					<textarea name="content" id="input2-content" cols="30" rows="10" class="form-control">${param.content }</textarea>
				</div>
				<input type="submit" class="btn btn-primary" value="새 글 등록" />	
			</form>
		</div>
		
		<div class="col-3"></div>
	</div>
</div>
</body>
</html>