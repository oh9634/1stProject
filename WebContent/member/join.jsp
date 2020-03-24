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
<link href="${conPath }/css/join.css" rel="stylesheet">
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
	$(document).ready(function(){
		$('#mId').keyup(function(){
			var mId= $('#mId').val();
			$.ajax({
				url : '${conPath}/idConfirm.do',
				data : 'mId='+mId,
				dataType : 'html',
				success : function(data) {
					$('#showId').html(data);
				}
			});
		});
	});
</script>
</head>
<body>
	<div id="content_form">
		<fieldset>
			<legend><img src="${conPath }/img/logo.png" alt="logo" /></legend>
			<form action="${conPath }/join.do" method="post" enctype="multipart/form-data">
				<table>
					<tr><th>ID *</th>
							<td><input type="text" name="mId" id="mId" required="required">
							<div id="showId"></div></td>
					</tr>
					<tr><th>PASSWORD *</th>
							<td><input type="password" name="mPw" id="mPw" required="required"></td>
					</tr>
					<tr><th>NAME *</th>
							<td><input type="text" name="mName" required="required"></td>
					</tr>
					<tr><th>EMAIL</th><td><input type="text" name="mEmail"></td></tr>
					<tr><th>PHOTO</th><td><input type="file" name="mPhoto"></td></tr>
					<tr><th>BIRTH</th><td><input type="text" name="mBirth" id="datepicker"></td></tr>
					<tr><th>ADDRESS</th><td><input type="text" name="mAddress"></td></tr>
					<tr><td colspan="2">
								<input type="submit" value="회원가입" class="btn_class">
								<input type="reset" value="이전" onclick="history.go(-1)"  class="btn_class">
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>