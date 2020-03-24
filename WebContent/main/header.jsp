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
<link href="${conPath }/css/header.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Nanum+Pen+Script&display=swap"
	rel="stylesheet">
</head>
<body>
	<div id="header_top">
		<div class="adver">
			<img src="${conPath }/img/crown.png" /> 국내 프로그래밍 교육사이트 1위
		</div>
		<c:if test="${empty member and empty admin}">
			<%-- 로그인 전 화면 --%>
			<div class="gnb">
				<ul>
					<li><a href="${conPath }/boardList.do">자유게시판</a></li>
					<li>|</li>
					<li><a href="${conPath }/lectureList.do">강의목록</a></li>
					<li>|</li>
					<li><a href="${conPath }/joinView.do">회원가입</a></li>
					<li>|</li>
					<li><a href="${conPath }/loginView.do">로그인</a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${not empty member and empty admin}">
			<%-- 사용자 모드 로그인 화면--%>
			<div class="gnb">
				<ul>
					<li><a href="${conPath }/logout.do">로그아웃</a></li>
					<li>|</li>
					<li><a href="${conPath }/orderList.do?mId=${member.mId}">내강의실</a></li>
					<li>|</li>
					<li><a href="${conPath }/modifyView.do">정보수정</a></li>
					<li>|</li>
					<li><a>${member.mName }님 &nbsp; ▶</a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${empty member and not empty admin}">
			<%-- 관리자 모드 로그인 화면--%>
			<div class="gnb">
				<ul>
					<li><a href="${conPath }/logout.do">로그아웃</a></li>
					<li>|</li>
					<li><a href="${conPath }/list.do">고객센터</a></li>
					<li>|</li>
					<li><a href="${conPath }/lectureUpdate_view.do">강의등록</a></li>
					<li>|</li>
					<li><a>${admin.aName }님 &nbsp; ▶</a></li>
				</ul>
			</div>
		</c:if>
	</div>
	<div id="header_info">
		<div class="star">
			<img src="${conPath }/img/별.png" />
		</div>
		<div class="logo">
			<a href="${conPath }/main_view.do"><img src="${conPath }/img/logo.png"
				alt="logo" /></a>
		</div>
		<div class="search">
			<form action="${conPath }/lectureSearch.do" method="post">
				<input type="text" name="schWord" placeholder=" 강의 검색 " /> <input
					type="submit" class="btn_search" value=" " />
			</form>
		</div>
		<div class="star">
			<img src="${conPath }/img/별2.png" />
		</div>
	</div>
	<div id="header_banner">
		<div class="banner_menu">
			<ul>
				<li><img src="${conPath }/img/menu.png" /></li>
				<li><a href="${conPath }/itInfo.do">IT바다 소개</a></li>
				<li><a href="${conPath }/lectureList.do">강의 목록</a></li>
				<li><a href="${conPath }/boardList.do">자유게시판</a></li>
				<li><a href="${conPath }/noticeList.do">구인공고</a></li>
			</ul>
		</div>
	</div>
	<div id="event">
		<p class="move">
			<img src="${conPath }/img/hot.png" class="hot" />
		</p>
	</div>
</body>
</html>