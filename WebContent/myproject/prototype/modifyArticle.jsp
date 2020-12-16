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
<title>게시글 수정</title>
</head>
<body>
<u:navbar></u:navbar>

<div class="container">
	<div class="row">
		<div class="col-3"></div>
		
		<div class="col-6">
			<h1>게시물 수정</h1>
			<form action="delete.do" method="post">			  
				<div class="form-group">	
					<label for="input1-id">번호</label>
			      	<input type="text" id="input1-id" name="no" class="form-control" value=1 readonly/>     	
			    </div>
			    <div class="form-group">	
					<label for="input2-title">제목</label>
					<input type="text" id="input2-title" name="title" value="제목" class="form-control"/>		    	
			    </div>
			    <div class="form-group">	
					<label for="input3-body">내용</label>
					<textarea name="content" id="input3-body" cols="30" rows="10" class="form-control">내용</textarea>		    	
			    </div>
			  <input type="submit" class="btn btn-primary" value="게시물 수정" />
			</form>
		</div>
		
		<div class="col-3"></div>
	</div>
</div>

</body>
</html>