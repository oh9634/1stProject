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
	<div id="content_form">
	<fieldset>
	<legend><img src="${conPath }/img/logo.png" alt="logo" /></legend>
	<form action="${conPath }/aLogin.do" method="post">	
			<table>
				<tr>
					<th><label for="aId">ID</label></th>
					<td><input type="text" name="aId" value="${aId }" id="aId" 
						required="required" autofocus="autofocus" class="input_style"></td>
					<td rowspan="2"><input type="submit" value="LOGIN"></td>
				</tr>
				<tr>
					<th><label for="aPw">PW</label></th>
					<td><input type="password" name="aPw" id="aPw" required="required" class="input_style"></td>
				</tr>
			</table>
			<p><u>관리자가 아니신가요?</u> <a href="${conPath}/loginView.do">로그인</a></p>
		</form>
		</fieldset>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>