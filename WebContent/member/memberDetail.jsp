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
<link href="${conPath }/css/mList.css" rel="stylesheet">
<style>
.btn {
	width:130px;
	height:35px;
	margin:15px;
	background-color: black;
	color:white;
	font-size: 1.1em;
	font-weight: bold;
	text-align: center;
	cursor:pointer;
}
a:hover {
	color:black;
}
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
<div id="content_form">
	<form action="${conPath}/delete.do?mId=${detail_view.mId }&mPw=${detail_view.mPw}&pageNum=${param.pageNum }" method="post">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="mId" value="${detail_view.mId }">
		<input type="hidden" name="mPw" value="${detail_view.mPw }">
		<table>
				 <caption>${detail_view.mId }님의 회원정보</caption>
				 <tr><th>비밀번호</th>
				 		 <td>${detail_view.mPw}</td>
				 </tr>
				 <tr><th>이름</th>
				 		 <td>${detail_view.mName}</td>
				 </tr>
				 <tr><th>이메일</th>
				 		 <td>${detail_view.mEmail}</td>
				 </tr>
				 <tr><th>사진</th>
						 <td>
						 	<c:if test="${not empty detail_view.mPhoto }">
						 		<a href="${conPath }/memberPhotoUp/${detail_view.mPhoto}" target="_blank">${detail_view.mPhoto}</a>
						 	</c:if>
						 	<c:if test="${empty detail_view.mPhoto }">
						 		사진없음
						 	</c:if>
						</td>
				 </tr>
				 <tr><th>생년월일</th>
				 		 <td>${detail_view.mBirth}</td>
				 </tr>
				 <tr><th>주소</th>
				 		 <td>${detail_view.mAddress}</td>
				 </tr>
				 <tr><td colspan="2">
				 			<c:if test="${not empty admin}">
				 				<input type="submit" value="삭제" class="btn">
				 			</c:if>
				 			<input type="button" value="목록" class="btn"
				 	onclick="location='${conPath}/list.do?pageNum=${param.pageNum }'">			 
		</table>
	</form>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>