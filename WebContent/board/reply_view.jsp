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
	height:680px;
	padding:30px;
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
	padding:22px;
	font-weight:bold;
}
.input_style {
	margin-left:40px;
	border:2px solid black;
	font-size:1.1em;
	padding:5px;
}
#fCategory {
	margin-left:40px;
	font-size:1.1em;
	width:706px;
	height:40px;
	border:2px solid black;
	outline:black;
}
#content_form table input[type='text'] {
	height:35px;
}
#content_form table input[type='file'] {
	margin-left:40px;
	padding:10px;
	font-size: 1.1em;
	font-weight: bold;
}
#content_form table .btn {
	background-image: url('/academy/img/mainBack.jpg');
	width:700px;
	margin-left:40px;
	height:40px;
	color:white;
	border-radius:3px;
	border:3px solid black;
	text-align : center;
	font-size:1.1em;
	font-weight: bold;
}
#content_form table .btn:hover {
	cursor:pointer;
}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/reply.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="fGroup" value="${reply_view.fGroup }">
			<input type="hidden" name="fStep" value="${reply_view.fStep }">
			<input type="hidden" name="fIndent" value="${reply_view.fIndent }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<caption>${reply_view.fNo }번글의 답변쓰기</caption>
				<tr><td><input type="text" name="fTitle" placeholder=" 답변글 제목"
								required="required" size="76" class="input_style"></td></tr>
				<tr><td>
					<select name="fCategory" id="fCategory">
                        <option>강의후기</option>
                        <option>도서후기</option>
                        <option>자격증</option>
                        <option>스펙공유</option>
                   </select>
				</td></tr>
				<tr><td><textarea name="fContent" rows="20" placeholder=" 본문"
								cols="75" class="input_style"></textarea></td></tr>
				<tr><td><input type="file" name="filName"></td></tr>
				<tr><td class="btn_class">
							<input type="submit" value="답변쓰기" class="btn">
				</td></tr>
			</table>
		</form>
	</div>
<jsp:include page="../main/side.jsp" />
<jsp:include page="../main/footer.jsp"/>
</body>
</html>