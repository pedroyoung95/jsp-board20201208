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
<title>글 목록</title>
<style>
.d-flex .num-col {
  width: 10%;
}

.d-flex .title-col {
  width: 60%;
}

.d-flex .writer-col {
  width: 15%;
}

.d-flex .read-col {
  width: 15%;
}
</style>
</head>
<body>
<u:navbar></u:navbar>

 <div class="container">
    <div class="row">
      <div class="col-3"></div>
      <div class="col-6">
        <h1>게시글 목록</h1>

        <div class="list-container">
          <div class="list-header d-flex">
            <div class="list-header-cell num-col">번호</div>
            <div class="list-header-cell title-col">제목</div>
            <div class="list-header-cell read-col">조회수</div>
            <div class="list-header-cell writer-col">작성자</div>
          </div>

          <c:forEach var="article" items="${articlePage.content }">
            <div class="list-body d-flex">
              <div class="list-body-cell num-col">${article.number }</div>
              <div class="list-body-cell title-col">${article.title }</div>
              <div class="list-body-cell read-col">${article.readCount }</div>
              <div class="list-body-cell writer-col">${article.writer.name }</div>
            </div>
          </c:forEach>


        </div>
        
        <nav aria-label="Page navigation example">
          <ul class="pagination">
            <c:if test="${articlePage.startPage > 5}">
              <li class="page-item"><a class="page-link" href="${root }/article/list.do?pageNo=${articlePage.startPage - 5 }">Previous</a></li>
            </c:if>
            
            <c:forEach begin="${articlePage.startPage }" end="${articlePage.endPage }" var="pNo">
              <li class="page-item"><a class="page-link" href="${root }/article/list.do?pageNo=${pNo}">${pNo }</a></li>
            
            </c:forEach>
            <c:if test="${articlePage.endPage < articlePage.totalPages }">
              <li class="page-item"><a class="page-link" href="${root }/article/list.do?pageNo=${articlePage.startPage + 5 }">Next</a></li>
            </c:if>
          </ul>
        </nav>
      </div>
    </div>
  </div>

</body>
</html>