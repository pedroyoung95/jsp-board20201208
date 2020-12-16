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
<title>게시글 보기</title>
</head>
<body>
<u:navbar></u:navbar>

<div class="container">
	<div class="row">		
		<div class="col-6">
			<h1>게시글 보기</h1>
				<div class="form-group">	
					<label for="input1-id">번호</label>
			      	<input type="text" id="input1-id" name="no" class="form-control" value=1 readonly/>     	
			    </div>
			    <div class="form-group">	
					<label for="input2-title">제목</label>
					<input type="text" id="input2-title" name="title" value="제목" class="form-control" readonly/>		    	
			    </div>
			    <div class="form-group">	
					<label for="input3-body">내용</label>
					<textarea name="content" id="input3-body" cols="30" rows="10" class="form-control" readonly>내용</textarea>		    	
			    </div>
			<br />
			<a href="#" class="btn btn-primary">목록</a>
			<a href="#" class="btn btn-primary">게시글 수정</a>
			<a href="#" class="btn btn-primary">게시글 삭제</a>
		</div>
		
		<div class="col-6">
			<h1>댓글</h1>			
			<div class="form-group">					
				<input name="reply" id="input4-reply" class="form-control" value="댓글"></input>
				<br />
				<a href="#" class="btn btn-secondary">댓글등록</a>
				<a href="#" class="btn btn-secondary">댓글수정</a> 	
				<a href="#" class="btn btn-secondary">댓글삭제</a>	    		    	
			</div>			
		</div>
	</div>
</div>
</body>
</html>