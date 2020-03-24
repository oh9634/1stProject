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
<link href="${conPath }/css/lectureList.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<table>
			<tr>
			<c:forEach var="dto" items="${lectureView }">			
				<td>
					<p id="lName">
						${dto.lName }<br>
						<span id="lInfo">(${dto.lTeacherName })</span>
					</p>
					<div class="lContentId">
					<p class="lContent">* 기간 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${dto.startDate }" type="date" pattern="yy/MM/dd" /> ~ 
					<fmt:formatDate value="${dto.endDate }" type="date" pattern="yy/MM/dd" /></p>
					<p class="lContent">* 수강료 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${dto.lPrice }" groupingUsed="true" type="currency"/></p>
					<p class="lContent">* 수업시간 &nbsp;&nbsp;&nbsp; ${dto.lDate }</p>
					<p class="lContent">* 모집인원 &nbsp;&nbsp;&nbsp; ${dto.lStock }명 / ${dto.lNum }명</p>
					</div>
					<div class="lContentId">
					<p class="lContent2">* 훈련대상</p>
					<p class="lContent2">&nbsp;&nbsp;- 취업준비생&nbsp;&nbsp;&nbsp;매월 최대 40만원지원</p>
					<p class="lContent2">&nbsp;&nbsp;- 직장인 &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;최대 85% 국비지원</p>
					<p class="lContent2">&nbsp;&nbsp;- 일반/학생 &nbsp;&nbsp;&nbsp; 최대 40% 할인</p>
					</div>
					<c:if test="${not empty member}">
					<input type="button" value="구매하기" class="btn"
				 					onclick="location='${conPath}/orderBuyView.do?lCode=${dto.lCode }&pageNum=${param.pageNum }'">
				 	</c:if>
				 	<c:if test="${not empty admin }">
					<input type="button" value="삭제" class="btn"
				 					onclick="location='${conPath}/lectureDelete.do?lCode=${dto.lCode }&pageNum=${param.pageNum }'">
				 	</c:if>
				</td>		
			</c:forEach>
			</tr>	
	</table>
	<br>
		<div class="paging">
		<a href="${conPath }/lectureList.do?pageNum=1">&lt;&lt;</a>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${startPage>BLOCKSIZE }">
			<a href="${conPath }/lectureList.do?pageNum=${startPage-1}">&lt;</a>
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
				[ <a href="${conPath }/lectureList.do?pageNum=${i}">${i }</a> ]
			</c:if>
		</c:forEach>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${endPage < pageCnt }">
			<a href="${conPath }/lectureList.do?pageNum=${endPage+1}">&gt;</a>
		</c:if>
		<c:if test="${endPage == pageCnt }">
			&gt;
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<a href="${conPath }/lectureList.do?pageNum=${pageCnt}">&gt;&gt;</a>
	</div>
	</div>
<jsp:include page="../main/side.jsp" />
<jsp:include page="../main/footer.jsp" />
</body>
</html>