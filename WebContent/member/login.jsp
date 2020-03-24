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
<link href="${conPath }/css/login.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty joinResult }">
		<script>
			alert('${joinResult}');
		</script>
	</c:if>	
	<div id="content_form">
	<fieldset>
	<legend><img src="${conPath }/img/logo.png" alt="logo" /></legend>
	<form action="${conPath }/login.do" method="post">
			<table>
				<tr>
					<th><label for="mId">ID </label></th>
					<td><input type="text" name="mId" value="${mId }" id="mId" 
						required="required" autofocus="autofocus" class="input_style"></td>
					<td rowspan="2"><input type="submit" value="LOGIN"></td>
				</tr>
				<tr>
					<th><label for="mPw">PW </label></th>
					<td><input type="password" name="mPw" id="mPw" required="required" class="input_style"></td>
				</tr>
			</table>
			<p id="login_join"><u>아직 회원이 아니신가요?</u> <a href="${conPath}/joinView.do">회원가입</a></p>
		</form>
		</fieldset>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>