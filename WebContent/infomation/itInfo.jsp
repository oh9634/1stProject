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
<link href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap" rel="stylesheet">
<style>
	#content_top {
		background-image: url('${conPath}/img/mainBack.jpg');
		background-position : center;
		height:470px;
	}
	#content_top h1 {
		color:white;
		width:1000px;
		padding:80px 100px 30px 100px;
		margin:0 auto;
		font-size:2.5em;
		font-family: 'Poor Story', cursive;
	}
	#content_top h1 span:nth-child(1) {
		color:red;
	}
	#content_top h1 span:last-child {
		color:orange;
	}
	#content_top .pStyle {
		width:150px;
		margin-left:450px;
		border-top: 4px solid white;
	}
	#content_top p:last-child {
		color:white;
		margin:40px 0 0 450px;
	}
	#content_bottom {
		width: 850px;
		height:460px;
		margin:0 auto;
		padding:40px 50px 0 50px;
		overflow: hidden;
	}
	#bottom_top, #bottom_bottom {
		float:left;
		height:450px;
		width:420px;
	}
	#bottom_top h2 {
		margin:20px;
	}
	#bottom_top .pStyle{
		width:50px;
		margin-left:20px;
		border-top: 3px solid black;
	}
	#bottom_top .pInfo{
		margin-left:20px;
		padding:2px;
	}
	#bottom_top p img {
		width: 380px;
		margin-left:20px;
		margin-top:10px;
		border:1px solid black;
	}
	#bottom_bottom p{
		margin:30px;
	}
	#bottom_bottom p img {
		width:330px;
		border:1px solid black;
	}
	#content {
		border-bottom: 1px solid black;
	}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp" />
	<div id="content">
		<div id="content_top">
		<h1>IT교육 산업 핵심 리더 <span>IT BADA</span>와<br> 대한민국 대표 온라인 고등 교육 <span>메가스터디</span>가 하나가 되었습니다!</h1>
		<p class="pStyle">&nbsp;</p>
		<p>
			IT BADA는 다양한 컴퓨터 교육 서비스로 매년 수많은 취준생을 취업의 길로 이끌고 있으며<br>
			메가스터디는 최고의 고등 교육 컨텐츠를 제공하여 수많은 학생의 대학 진학에 힘쓰고 있습니다.<br><br>

			IT BADA는 웹디자인, 편집디자인, 건축, 인테리어디자인, 모바일 및 웹퍼블리셔 과정, 전산세무회계 등<br>
			컴퓨터 분야의 전문화된 교육으로 운영하며 각 교육과정은 10년 이상의 실무경력을 가진 전문강사진으로 구성하여<br>
			실무를 대비한 커리큘럼과 트렌드 및 미래 흐름까지 파악할 수 있는 대한민국 최고의 실무 인재 양성과정으로 인정받고 있습니다.
		</p>
		</div>
		<div id="content_bottom">
			<div id="bottom_top">
				<h2>종로 캠퍼스</h2>
				<p class="pStyle">&nbsp;</p>
				<p class="pInfo"><b>서울시 종로구 수표로 105 육의전 빌딩 8,9</b></p>
				<p class="pInfo">[1호선,3호선,5호선]종로3가역</p>
				<p class="pInfo"><br>Tel.02-766-8367 &nbsp; Fax.02-766-8370</p>
				<p><img src="${conPath }/img/지도.png" alt="지도사진" /></p>
			</div>
			<div id="bottom_bottom">
			<p><img src="${conPath }/img/정보.jpg" alt="학원사진" /></p>
			<p><img src="${conPath }/img/정보2.jpg" alt="학원사진" /></p>
			</div>
		</div>
	</div>
<jsp:include page="../main/side.jsp" />
<jsp:include page="../main/footer.jsp" />
</body>
</html>