<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/modify.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$( function() {
	    $( "#datepicker" ).datepicker({
	    	dateFormat : 'yy-mm-dd',
	    	monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    	showMonthAfterYear : true,
	    	yearSuffix : "년",
	    	showOtherMonths : true,
	    	dayNamesMin : ['일','월','화','수','목','금','토']
	    	
	    });
	  } );
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<fieldset>
			<legend><img src="${conPath }/img/logo.png" alt="logo" id="logo"/></legend>
	<form action="${conPath }/modify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="dbmPhoto" value="${member.mPhoto }">
		<table>
			<tr><th>ID</th>
					<td><input type="text" name="mId" value="${member.mId }"
									readonly="readonly">
					</td>
					<td rowspan="4">
						<img src="${conPath }/memberPhotoUp/${member.mPhoto}" width="100">
					</td>
			</tr>
			<tr><th>PASSWORD</th>
					<td><input type="password" name="mPw" value="${member.mPw }"
									required="required">
					</td>
			</tr>
			<tr><th>NAME</th>
					<td><input type="text" name="mName" value="${member.mName }"
									required="required">
					</td>
			</tr>
			<tr><th>EMAIL</th>
					<td><input type="text" name="mEmail" value="${member.mEmail }">
					</td>
			</tr>
			<tr><th>PHOTO</th>
					<td colspan="2"><input type="file" name="mPhoto"></td>
			</tr>
			<tr><th>BIRTH</th>
					<td colspan="2">
						<input type="text" name="mBirth" value="${member.mBirth }" id="datepicker">
					</td>
			</tr>
			<tr><th>ADDRESS</th>
					<td colspan="2">
						<input type="text" name="mAddress" value="${member.mAddress }">
					</td>
			</tr>
			<tr><td colspan="3">
						<input type="submit" value="정보수정"  class="btn">
						<input type="reset" value="취소"  class="btn">
						<input type="reset" value="이전" onclick="history.go(-1)"  class="btn">
					</td>
			</tr>
		</table>
	</form>
	</fieldset>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>