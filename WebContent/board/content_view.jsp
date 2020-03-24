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
<style>
	#content_form {
	width:800px;
	height:580px;
	padding:50px;
	margin:0 auto;
	background-image: url('https://i.pinimg.com/236x/da/aa/53/daaa53fa0f7b90a2f4c577a633ea53a9.jpg');
}
#content_form table {
	width: 700px;
	margin:0px auto;
	margin-bottom:50px;
}
#content_form table caption {
	text-align : left;
	font-size: 1.5em;
	padding:20px;
	font-weight:bold;
}
#content_form table th {
	background-image: url('/academy/img/mainBack.jpg');
	border:1px solid black;
	width:170px;
	height:37px;
	color:white;
}
#content_form table .style {
	text-align : center;
	background-color: white;
	border:1px solid black;
}
.btn_class {
	height:	37px;
	border:none;
	text-align : center;
}
.btn {
	width: 15%;
	height:25px;
	font-weight:bold;
	margin: 20px 10px;
}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath}/bModify_view.do" method="post">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="fNo" value="${content_view.fNo }">
		<table>
				 <caption>${content_view.fNo }글 상세보기</caption>
				 <tr><th>작성자</th>
				 		 <td class="style">${content_view.mName}님</td>
				 </tr>
				 <tr><th>제목</th>
				 		 <td class="style">${content_view.fTitle }</td>
				 </tr>
				 <tr><th>카테고리</th>
				 		 <td class="style">${content_view.fCategory }</td>
				 </tr>
				 <tr><th>본문</th>
				 		 <td class="style"><pre>${content_view.fContent}</pre></td>
				 </tr>
				 <tr><th>첨부파일</th>
				 	<td class="style">
						 	<c:if test="${not empty content_view.fFilename }">
						 		<a href="${conPath }/boardUp/${content_view.fFilename}" target="_blank">${content_view.fFilename}</a>
						 	</c:if>
						 	<c:if test="${empty content_view.fFilename }">
						 		첨부파일없음
						 	</c:if>
						</td>
				 </tr>
				 <tr><td colspan="2" class="btn_class">
				 			<c:if test="${(member.mId eq content_view.mId and not empty member) or not empty admin}">
				 				<input type="submit" value="수정" class="btn">
				 				<input type="button" value="삭제" class="btn"
				 					onclick="location='${conPath}/bDelete.do?fNo=${content_view.fNo }&pageNum=${param.pageNum }'">
				 			</c:if>
				 			<c:if test="${not empty member }">
				 				<input type="button" value="답변" class="btn"
				 				onclick="location='${conPath}/reply_view.do?fNo=${content_view.fNo}&pageNum=${param.pageNum}'">
				 			</c:if>
				 			<input type="button" value="목록" class="btn"
				 	onclick="location='${conPath}/boardList.do?pageNum=${param.pageNum }'">			 
		</table>
	</form>
	</div>
<jsp:include page="../main/side.jsp" />
<jsp:include page="../main/footer.jsp"/>
</body>
</html>