<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/footer.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Nanum+Pen+Script&display=swap"
	rel="stylesheet">
</head>
<body>
	<div id="footer_add">
		<ul>
			<li><img src="${conPath }/img/로고.PNG" alt="로고" /></li>
			<li><img src="${conPath }/img/address.png" alt="회사주소" /></li>
		</ul>
	</div>
	<div id="footer_sns">
		<ul>
			<li><a href="https://www.facebook.com" target="_black" ><img src="${conPath }/img/sns1.PNG" alt="SNS" /></a></li>
			<li><a href="https://www.instagram.com" target="_black"><img src="${conPath }/img/sns2.PNG" alt="SNS" /></a></li>
			<li><a href="https://post.naver.com" target="_black"><img src="${conPath }/img/sns3.PNG" alt="SNS" /></a></li>
			<li><a href="https://section.blog.naver.com" target="_black"><img src="${conPath }/img/sns4.PNG" alt="SNS" /></a></li>
			<li><a href="https://www.youtube.com" target="_black"><img src="${conPath }/img/sns5.PNG" alt="SNS" /></a></li>
			<li id="admin_style"><a href="${conPath }/aLoginView.do">☞ 관리자모드</a></li>
		</ul>
	</div>
</body>
</html>