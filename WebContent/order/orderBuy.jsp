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
	width:770px;
	height:410px;
	padding:50px;
	margin:55px auto;
	background-image: url('/academy/productUp/배경24.jpg');
	background-position: center;
}
caption {
	color : white;
	font-size : 2.3em;
	text-align: center;
	width:300px;
	margin:0 auto 40px auto;	
}
#content_form table {
	width: 660px;
	margin:0 auto;
}
#content_form table div {
	opacity: 0.9;
	padding:40px;
	margin:10px;
	background-color: gainsboro;
	float: left;
	height:190px;
}
.pStyle {
	border-bottom:1px dashed olive;
	font-weight : bold;
	text-align:left;
	padding:8px;
	color:black;	
}
.infoStyle {
	font-size: 1.2em;
	font-weight : bold;
	text-align:left;
	padding:8px;
	color:black;
	margin-bottom: 15px;	
}
.infoStyle input {
	margin-top:10px;
	height:25px;
}
#div_top {
	width: 265px;
}
#div_bottom {
	opacity : 0.7;
	margin-left:75px;	
}
.btn {
	height:40px;
	margin-left:10px;
	width:630px;
	font-weight: bold;
}
.btn:hover {
	cursor:pointer;
}
</style>
</head>
<body>
<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/orderBuy.do" method="post">
			<input type="hidden" name="lCode" value="${buyInfo.lCode }">
			<table>
				<caption>구매정보 입력</caption>
				<tr>
					<td><div id="div_top">
					<p class="pStyle" >[ ${buyInfo.lName } ]</p>
					<p class="pStyle">(${buyInfo.lTeacherName } / ${buyInfo.lDate }반)</p>
					<p class="pStyle"><fmt:formatNumber value="${buyInfo.lPrice }" groupingUsed="true" type="currency"/></p>
					<p class="pStyle">${buyInfo.startDate }<br> &nbsp; &nbsp; &nbsp; &nbsp;~ <br>${buyInfo.endDate }</p>
					</div></td>
					<td><div id="div_bottom">
					<p class="infoStyle">주문자 아이디<br>
					<input type="text" name="mId" value="${member.mId }" readonly="readonly"></p>
					<p class="infoStyle">주문자 전화번호<br>
					<input type="text" name="oTel"></p>
					<p>* 정확히 입력해주세요 </p>
					</div></td>
				</tr>
				<tr><td class="btn_class" colspan="2">
							<input type="submit" value="구매하기" class="btn">
				</td></tr>
			</table>
		</form>
	</div>
<jsp:include page="../main/side.jsp" />
<jsp:include page="../main/footer.jsp" />
</body>
</html>