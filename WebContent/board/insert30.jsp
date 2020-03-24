<%@page import="com.tj.academy.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%
		for(int i=0 ; i<60 ; i++){
			BoardDao dao = BoardDao.getInstance();
			if(i%4==0){
				dao.mWrite("abd", "제목"+i, "강의후기", i+"번째 본문", null, "192.168.10.151");			
			}else if(i%4==1){
				dao.mWrite("ddd", "제목입니다."+i, "도서후기",i+"번째 본문", null, "192.168.10.151");		
			}else if(i%4==2){
				dao.mWrite("bbb", i+"번째 제목", "스펙공유",i+"번째 본문", null, "192.168.10.151");		
			}else if(i%4==3){
				dao.mWrite("ccc", "제목"+i, "자격증",i+"번째 본문", null, "192.168.10.151");		
			}		
		}
		response.sendRedirect("../boardList.do");
	%>
</body>
</html>