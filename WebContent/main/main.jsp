<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IT BADA</title>
<link href="${conPath }/css/main.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Baloo+2&display=swap"
	rel="stylesheet">
<script>
	$(document).ready(function() {
		$('.slider').bxSlider();
	});
</script>
</head>
<body>
	<c:if test="${not empty LoginErrorMsg}">
		<script>
			alert('${LoginErrorMsg}');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="content">
		<div id="main_menu">
			<input type="checkbox" id="chkbox" checked="checked" /> <label
				for="chkbox"><em></em></label>
			<div class="sidebar">
				<div id="adver">
					<img src="${conPath }/img/광고배너1.png" alt="광고" /> <img
						src="${conPath }/img/광고배너2.PNG" alt="광고" />
				</div>
			</div>
		</div>
		<div id="main">
			<div class="main_back">
				<div id="main_top">
					<video controls="controls" loop="loop" width="900" height="320"
						autoplay="autoplay">
						<source src="${conPath}/vod/동영상.mp4">
					</video>
				</div>
				<div id="main_bottom1">
					<h2>
						공지사항 <a href="${conPath }/boardList.do">MORE &gt;</a>
					</h2>
					<hr />
					<table>
						<c:if test="${totCnt==0 }">
							<tr>
								<td>글이 없습니다</td>
							</tr>
						</c:if>
						<c:if test="${totCnt!=0 }">
							<c:forEach items="${importanceList }" var="dto">
								<tr>
									<td class="board"><a href="${conPath }/content_view.do?fNo=${dto.fNo}" id="impoTitle">${dto.fTitle }</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					<div class="paging">
						<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/main_view.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }">
							<c:if test="${i == pageNum }">
								<b> [ ${i } ] </b>
							</c:if>
							<c:if test="${i != pageNum }">
				[ <a href="${conPath }/main_view.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
						</c:forEach>
						<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/main_view.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
					</div>
				</div>
				<div id="main_bottom2">
					<div class="slider">
						<div>
							<img src="${conPath }/img/03_1.png" />
						</div>
						<div>
							<img src="${conPath }/img/04_1.png" />
						</div>
						<div>
							<img src="${conPath }/img/05_1.png" />
						</div>
						<div>
							<img src="${conPath }/img/06_1.png" />
						</div>
					</div>
				</div>
				<div id="main_bottom3">
					<h2>3월 상세일정</h2>
					<hr />
					<ul>
						<li>03/03(화)<br /> <span>웹개발과정 개강</span></li>
						<li>03/21(토)<br /> <span>상반기 취업박람회</span></li>
						<li>03/25(수)<br /> <span>유튜브 크리에이터반 개강</span></li>
						<li>03/27(금)<br /> <span>네트워크보안과정 종강</span></li>
					</ul>
				</div>
			</div>
			<div id="side_banner">
				<div>
					<h2>인기 강좌</h2>
					<hr />
					<br />
				</div>
				<div>
					<a href="${conPath }/lectureList.do?pageNum=1">
						<div>
							<img src="${conPath }/img/탑1.jpg" />
						</div>
					</a>
				</div>
				<div>
					<a href="${conPath }/lectureList.do?pageNum=2">
						<div>
							<img src="${conPath }/img/탑2.jpg" />
						</div>
					</a>
				</div>
				<div>
					<a href="#header_top">▲ TOP </a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>