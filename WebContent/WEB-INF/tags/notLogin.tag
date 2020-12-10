<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%
HttpSession httpSession = request.getSession(false);
if(httpSession == null || httpSession.getAttribute("authUser") == null) {
%>
<jsp:doBody/>
<%
}
%>