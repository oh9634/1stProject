<%@page import="com.tj.academy.dao.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		for(int i=0 ; i<110 ; i++){
			NoticeDao dao = NoticeDao.getInstance();
			if(i%4==0){
				dao.noticeWrite("admin", "[중요]구인공고"+i, i+"번째 본문");			
			}else{
				dao.noticeWrite("admin", "구인공고"+i, i+"번째 본문");		
			}			
		}
		response.sendRedirect("../noticeList.do");
	%>
</body>
</html>