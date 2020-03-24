<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/bList.css" rel="stylesheet">
<style>
#content {
		width: 750px; height:600px;
		margin: 50px auto 0px;
}
#content .write_btn {
	margin-left:580px;
}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('tr').click(function() {
			var nNo = Number($(this).children(0).eq(0).text()); // 0번째 td안의 있는 text;
			if (!isNaN(nNo)) {
				location.href = '${conPath}/noticeContent_view.do?nNo='+ nNo+'&pageNum=${pageNum}';
					}
				});
			});
</script>
</head>
<body>
	<c:if test="${not empty noticeMsg}">
		<script>
			alert('${noticeMsg}');
		</script>
	</c:if>
	<c:if test="${not empty nDeleteMsg}">
		<script>
			alert('${nDeleteMsg}');
		</script>
	</c:if>
	<c:if test="${not empty nModifyMsg}">
		<script>
			alert('${nModifyMsg}');
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<table>
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>글제목</th>
				<th>조회수</th>
				<th>날짜</th>
			</tr>
			<c:if test="${totCnt==0 }">
				<tr>
					<td colspan="5">글이 없습니다</td>
				</tr>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${noticeList }" var="dto">
					<tr>
						<td class="board">${dto.nNo }</td>
						<td class="board">${dto.aName }</td>
						<td class="title">${dto.nTitle }</td>
						<td class="board">${dto.nHit }</td>
						<td class="board"><fmt:formatDate value="${dto.nRdate }" type="date"
								pattern="yy/MM/dd" /></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${not empty admin }">

			<input type="button" value="글쓰기"
				onclick="location.href='${conPath }/nWrite_view.do'" class="write_btn">

		</c:if>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/noticeList.do?pageNum=${startPage-1}"> 이전
				</a> ]
		</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
				[ <a href="${conPath }/noticeList.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/noticeList.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
		</div>
	</div>
	<jsp:include page="../main/side.jsp" />
	<jsp:include page="../main/footer.jsp" />
</body>
</html>