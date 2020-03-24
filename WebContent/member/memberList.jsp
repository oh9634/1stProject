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
<link href="${conPath }/css/mList.css" rel="stylesheet">
<style>
#mId {
	font-size:1.3em;
}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	function listDetail(mId){
		location.href = '${conPath}/listDetail.do?mId='+mId+'&pageNum=${pageNum}';
	}
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<c:if test="${not empty adminMsg }">
		<script>
			alert('${adminMsg}');
		</script>
	</c:if>
	<c:if test="${not empty adminLoginError }">
		<script>
			alert('${adminLoginError}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty deleteMsg }">
		<script>
			alert('${deleteMsg}');
		</script>
	</c:if>
	<div id="content_form">
	<table>
		<caption>전체회원보기</caption>
		<tr>
			<c:forEach var="dto" items="${mAllView }">
				<td onclick="listDetail('${dto.mId }')">
					<p id="mId">${dto.mId }</p>
					<p>(${dto.mName })</p><br>
					<p><img src="${conPath }/memberPhotoUp/${dto.mPhoto}" alt="사진"></p>
				</td>
			</c:forEach>
		</tr>
	</table>
	<div class="paging">
		<a href="${conPath }/list.do?pageNum=1">&lt;&lt;</a>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${startPage>BLOCKSIZE }">
			<a href="${conPath }/list.do?pageNum=${startPage-1}">&lt;</a>
		</c:if>
		<c:if test="${startPage<=BLOCKSIZE }">
			&lt;
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				[ <b>${i }</b> ]
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/list.do?pageNum=${i}">${i }</a> ]
			</c:if>
		</c:forEach>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${endPage < pageCnt }">
			<a href="${conPath }/list.do?pageNum=${endPage+1}">&gt;</a>
		</c:if>
		<c:if test="${endPage == pageCnt }">
			&gt;
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<a href="${conPath }/list.do?pageNum=${pageCnt}">&gt;&gt;</a>
	</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>