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
<link href="${conPath }/css/orderList.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/header.jsp" />
	<div id="content">
		<table>
		<caption>구매목록 <span id="totCnt">| 총 ${totCnt }개</span></caption>
		<c:if test="${totCnt!=0 }">
		<tr><td>
			<input type="button" value="수강중인 강의 " onclick="location='${conPath }/orderNowList.do?mId=${mId}'" class="lecture_btn">&nbsp;
			<input type="button" value="수강이력" onclick="location='${conPath }/orderPastList.do?mId=${mId}'" class="lecture_btn">
		</td></tr>
		</c:if>
		</table>		
		<table id="second_table">
		<tr id="tr_th"><th>강의명</th><th>강사명</th><th>수강반</th><th>수강기간</th><th>주문전화번호</th><th>주문일자</th></tr>
		<c:if test="${totCnt==0 }">
			<tr><td colspan="6" class="td_content1">구매내역이 없습니다</td></tr>
		</c:if>
		<c:if test="${totCnt!=0 }">
			<c:forEach items="${orderList }" var="dto">
				<tr>
					<td class="td_content">${dto.lName }</td>
					<td class="td_content">${dto.lTeacherName }</td>
					<td class="td_content">(${dto.lDate })반</td>
					<td class="td_content">${dto.startDate } ~ ${dto.endDate }</td>
					<td class="td_content">${dto.oTel }</td>
					<td class="td_content"><fmt:formatDate value="${dto.oDate }" type="date"
								pattern="yy/MM/dd" /></td>
				</tr>
			</c:forEach>			
		</c:if>
	</table>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/orderList.do?pageNum=${startPage-1}&mId=${mId}"> 이전 </a> ]
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				<b> [ ${i } ] </b>
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/orderList.do?pageNum=${i}&mId=${mId}"> ${i } </a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/orderList.do?pageNum=${endPage+1}&mId=${mId}"> 다음 </a> ]
		</c:if>
	</div>
	</div>
<jsp:include page="../main/side.jsp" />
	<jsp:include page="../main/footer.jsp" />	
</body>
</html>