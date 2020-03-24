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
<link href="${conPath }/css/bList.css" rel="stylesheet">
<style>
#content {
		width: 950px;
		margin: 50px auto 0px;
}
#content .write_btn {
	margin-left:740px;
}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('tr').click(function() {
			var fNo = Number($(this).children(0).eq(0).text()); // 0번째 td안의 있는 text;
			if (!isNaN(fNo)) {
				location.href = '${conPath}/content_view.do?fNo='+ fNo+'&pageNum=${pageNum}';
					}
				});
			});
</script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<div id="content">
	<table>
		<tr><th>글번호</th><th>작성자</th><th>글제목</th><th>글분류</th><th>조회수</th>
				<th>날짜</th><th>IP</th></tr>
		<c:if test="${totCnt==0 }">
			<tr><td colspan="6">글이 없습니다</td></tr>
		</c:if>
		<c:if test="${totCnt!=0 }">
			<c:forEach items="${list }" var="dto">
				<tr><td class="board">${dto.fNo }</td>
						<td class="board">${dto.mName }</td>
						<td class="title">
							<c:forEach var="i" begin="1" end="${dto.fIndent }">
								<c:if test="${i==dto.fIndent }">└</c:if>
								<c:if test="${i!=dto.fIndent }"> &nbsp; &nbsp; </c:if>
							</c:forEach>
							<c:if test="${dto.fImportance==0 }">
							${dto.fTitle }
							</c:if>
							<c:if test="${dto.fImportance==1 }">
							<span><img src="${conPath }/boardUp/공지사항.png" alt="공지사항" width="22"/>&nbsp; ${dto.fTitle }</span>
							</c:if>
							<c:if test="${dto.fHit>10 }">
							<img src="${conPath }/boardUp/best.png" alt="베스트 글" width="20" id="bestIcon"/>
							</c:if>
						</td>
						<td class="board">${dto.fCategory }</td>
						<td class="board">${dto.fHit }</td>
						<td class="board"><fmt:formatDate value="${dto.fRdate }" 
											type="date" pattern="yy/MM/dd" /></td>
						<td class="board">${dto.fIp }</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<c:if test="${not empty admin }">

			<input type="button" value="글쓰기"
				onclick="location.href='${conPath }/aWrite_view.do'" class="write_btn">

	</c:if>
	<c:if test="${not empty member }">

			<input type="button" value="글쓰기"
				onclick="location.href='${conPath }/mWrite_view.do'" class="write_btn">

	</c:if>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/boardList.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				<b> [ ${i } ] </b>
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/boardList.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/boardList.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
	</div>
	</div>
<jsp:include page="../main/side.jsp" />
<jsp:include page="../main/footer.jsp"/>
</body>
</html>