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
	height:650px;
	padding:30px;
	margin:0 auto;
	background-image: url('https://i.pinimg.com/236x/da/aa/53/daaa53fa0f7b90a2f4c577a633ea53a9.jpg');
}
#content_form table {
	width: 600px;
	margin:0px auto;
	margin-bottom:50px;
}
#content_form table caption {
	text-align : left;
	font-size: 1.5em;
	padding:22px;
	font-weight:bold;
	margin-bottom:10px;
}
.input_style {
	margin:3px 3px 3px 40px;
	border:2px solid black;
	font-size:1.1em;
	padding:5px 5px 5px 10px;
}
#groove {
	text-align:center;
	font-size : 1.2em;
	font-weight:bold;
	margin-left:35px;
}
#content_form table input[type='text'] {
	height:35px;
}
#content_form table .btn {
	background-image: url('/academy/img/mainBack.jpg');
	width:533px;
	margin-left:40px;
	margin-top:5px;
	height:50px;
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
	    $( "#datepicker2" ).datepicker({
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
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/lectureUpdate.do" method="post">
			<table>
				<caption>강의 등록</caption>
				<tr><td><input type="text" name="lCode" placeholder="강의코드"
								required="required" size="56" class="input_style"></td></tr>
				<tr><td><input type="text" name="lName" placeholder="강의이름"
								required="required" size="56" class="input_style"></td></tr>
				<tr><td><input type="text" name="lTeacherName" placeholder="강사이름"
								 size="56" class="input_style"></td></tr>
				<tr><td><input type="text" name="lPrice" placeholder="수강료"
								 size="56" class="input_style"></td></tr>
				<tr><td><input type="text" name="lDate" placeholder="강의시간"
								 size="56" class="input_style"></td></tr>
				<tr><td>
					<input type="text" name="startDate" id="datepicker" placeholder="기간(시작)"
								 size="21" class="input_style">
					<span id="groove">~</span>
					<input type="text" name="endDate" id="datepicker2" placeholder="기간(끝)"
								 size="21" class="input_style">
				</td></tr>
				<tr><td><input type="text" name="lNum" placeholder="정원"
								 size="56" class="input_style"></td></tr>
				<tr><td><input type="text" name="lStock" placeholder="여석"
								 size="56" class="input_style"></td></tr>
				<tr><td class="btn_class">
							<input type="submit" value="적용하기" class="btn">
				</td></tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>