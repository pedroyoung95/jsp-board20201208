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
<table border="1">
	<tr>
		<td colspan="4"><a href="write.do">[게시글쓰기]</a></td>
	</tr>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>조회수</td>
	</tr>
	<c:if test="${articlePage.hasNoArticles() }">
		<tr>
			<td colspan="4">게시글이 없습니다.</td>
		</tr>
	</c:if>
	<c:forEach var="article" items="${articlePage.content }">
		<tr>
			<td>${article.number }</td>
			<td>
				<a href="read.do?no=${article.number }&pageNo=${articlePage.currentPage }">
					<c:out value="${article.title }"></c:out>
				</a>
			</td>
			<td>${article.writer.name }</td>
			<td>${article.readCount }</td>
		</tr>
	</c:forEach>
	<c:if test="${articlePage.hasArticles() }">
		<tr>
			<td colspan="4">
				<c:if test="${articlePage.start }"></c:if>
			</td>
		</tr>
	</c:if>
</table>
</body>
</html>