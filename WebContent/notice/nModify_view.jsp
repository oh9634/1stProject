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
	font-size: 1.4em;
	padding:20px;
	font-weight:bold;
}
#content_form table input[type='text'], #content_form textarea {
	margin-left:40px;
	border:2px solid black;
	font-size:1.1em;
}
#content_form table input[type='text'] {
	height:40px;
}
#content_form table .btn {
	background-image: url('/academy/img/mainBack.jpg');
	width:700px;
	margin-left:40px;
	height:35px;
	color:white;
	border-radius:3px;
	border:3px solid black;
	text-align : center;
	font-size:1.1em;
}
#content_form table .btn:hover {
	cursor:pointer;
}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/nModify.do" method="post">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="nNo" value="${nModify_view.nNo }">
			<table>
				<caption>${nModify_view.nNo }번 글 수정</caption>
				<tr><td><input type="text" name="nTitle" id="nTitle"
								required="required" size="76" value="${nModify_view.nTitle }"></td></tr>
				<tr><td><textarea name="nContent" id="nContent" rows="20" placeholder=" 본문"
								cols="75">${nModify_view.nContent }</textarea></td></tr>
				<tr><td class="btn_class">
							<input type="submit" value="적용하기" class="btn">
				</td></tr>
				
			</table>
		</form>
	</div>
<jsp:include page="../main/side.jsp" />
<jsp:include page="../main/footer.jsp"/>
</body>
</html>