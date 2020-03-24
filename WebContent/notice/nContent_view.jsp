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
		<form action="${conPath}/nModify_view.do" method="post">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="nNo" value="${nContent_view.nNo }">
		<table>
				 <caption>${nContent_view.nNo }글 상세보기</caption>
				 <tr><th>작성자</th>
				 		 <td class="style">${nContent_view.aName}(${nContent_view.aId})님</td>
				 </tr>
				 <tr><th>제목</th>
				 		 <td class="style">${nContent_view.nTitle }</td>
				 </tr>
				 <tr><th>본문</th>
				 		 <td class="style"><pre>${nContent_view.nContent}</pre></td>
				 </tr>
				 <tr><td colspan="2" class="btn_class">
				 			<c:if test="${not empty admin }">
				 				<input type="submit" value="수정" class="btn">
				 			</c:if>
				 			<c:if test="${not empty admin}">
				 				<input type="button" value="삭제" class="btn"
				 					onclick="location='${conPath}/nDelete.do?nNo=${nContent_view.nNo }&pageNum=${param.pageNum }'">
				 			</c:if>
				 			<input type="button" value="목록" class="btn"
				 	onclick="location='${conPath}/noticeList.do?pageNum=${param.pageNum }'">			 
		</table>
	</form>
	</div>
<jsp:include page="../main/side.jsp" />
<jsp:include page="../main/footer.jsp"/>
</body>
</html>