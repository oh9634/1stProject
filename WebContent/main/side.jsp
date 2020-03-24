<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#side_banner {
	border-radius: 50px;
	border: 2px solid black;
	padding: 20px;
	position: fixed;
	top: 225px;
	right: 70px;
	z-index: 99999;
	background-image: url('/academy/img/bannerBack.jpg');
}

#side_banner div:nth-child(2) div, #side_banner div:nth-child(3) div,
	#side_banner div:nth-child(4) div {
	border: 2px solid gray;
	background-color: black;
	width:125px;
	height:80px;
	padding:1px;
	margin-top: 2px;
	margin-bottom: 18px;
	text-align: center;
}
#side_banner div img{
	border: 2px solid black;
}
#side_banner div h2 {
	color: gainsboro;
	font-size: 1.9em;
	text-align: center;
	font-family: 'Typo_DecoSolidSlash';
}

#side_banner div:first-child, #side_banner div:last-child {
	text-align: center;
	padding: 10px;
}

#side_banner div:last-child {
	margin-top: 10px;
}

#side_banner div:last-child a {
	font-weight: bold;
	color: white;
	font-size: 1.1em;
}
</style>
</head>
<body>
	<div id="side_banner">
		<div>
			<h2>인기 강좌</h2>
			<hr />
			<br />
		</div>
		<div>
			<a href="${conPath }/lectureList.do?pageNum=1">
				<div>
					<img src="${conPath }/img/탑1.jpg" />
				</div>
			</a>
		</div>
		<div>
			<a href="${conPath }/lectureList.do?pageNum=2">
				<div>
					<img src="${conPath }/img/탑2.jpg" />
				</div>
			</a>
		</div>
		<div>
			<a href="#header_top">▲ TOP </a>
		</div>
	</div>
</body>
</html>