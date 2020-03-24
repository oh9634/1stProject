package com.tj.academy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.service.ALoginService;
import com.tj.academy.service.AWriteService;
import com.tj.academy.service.BListService;
import com.tj.academy.service.BModifyService;
import com.tj.academy.service.BModifyViewService;
import com.tj.academy.service.BReplyService;
import com.tj.academy.service.BReplyViewService;
import com.tj.academy.service.ContentViewService;
import com.tj.academy.service.DeleteService;
import com.tj.academy.service.IdConfirmdService;
import com.tj.academy.service.JoinService;
import com.tj.academy.service.LoginService;
import com.tj.academy.service.LogoutService;
import com.tj.academy.service.MAllViewService;
import com.tj.academy.service.MDeleteService;
import com.tj.academy.service.MDetailViewService;
import com.tj.academy.service.MWriteService;
import com.tj.academy.service.MainService;
import com.tj.academy.service.ModifyService;
import com.tj.academy.service.NContentViewService;
import com.tj.academy.service.NDeleteService;
import com.tj.academy.service.NListService;
import com.tj.academy.service.NModifyService;
import com.tj.academy.service.NModifyViewService;
import com.tj.academy.service.NWriteService;
import com.tj.academy.service.OBuyService;
import com.tj.academy.service.OBuyViewService;
import com.tj.academy.service.OListService;
import com.tj.academy.service.ONowListService;
import com.tj.academy.service.OPastListService;
import com.tj.academy.service.PDeleteService;
import com.tj.academy.service.PListService;
import com.tj.academy.service.PSearchService;
import com.tj.academy.service.PWriteService;
import com.tj.academy.service.Service;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int write_view = 0;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		if(command.equals("/main_view.do")) { // 메인페이지가기
			service = new MainService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} else if(command.equals("/loginView.do")) { // 로그인페이지가기
			viewPage = "member/login.jsp";
		} else if(command.equals("/login.do")) { // 로그인하기
			service = new LoginService();
			service.execute(request, response);
			viewPage = "main_view.do";
		} else if(command.equals("/logout.do")) { // 로그아웃하기
			service = new LogoutService();
			service.execute(request, response);
			viewPage = "main_view.do";
		} else if(command.equals("/aLoginView.do")) { // ADMIN 로그인페이지가기
			viewPage = "admin/aLogin.jsp";
		} else if(command.equals("/aLogin.do")) { // ADMIN 로그인
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "list.do";
		} else if(command.equals("/joinView.do")) { // 회원가입페이지가기
			viewPage = "member/join.jsp";
		} else if(command.equals("/idConfirm.do")) { // 회원가입 아이디 확인
			service = new IdConfirmdService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		} else if(command.equals("/join.do")) { // 회원가입하기
			service = new JoinService();
			service.execute(request, response);
			viewPage = "member/login.jsp";
		} else if (command.equals("/list.do")) { // 전체회원보기
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "member/memberList.jsp";
		} else if (command.equals("/listDetail.do")) { // 회원상세보기
			service = new MDetailViewService();
			service.execute(request, response);
			viewPage = "member/memberDetail.jsp";
		} else if (command.equals("/delete.do")) { // 회원탈퇴
			service = new MDeleteService();
			service.execute(request, response);
			viewPage = "list.do";
		} else if (command.equals("/modifyView.do")) { // 정보수정페이지 가기
			viewPage = "member/modify.jsp";
		} else if (command.equals("/modify.do")) { // 정보수정하기
			service = new ModifyService();
			service.execute(request, response);
			viewPage = "main_view.do";
		} else if (command.equals("/noticeList.do")) { // 구인공고 페이지 가기
			service = new NListService();
			service.execute(request, response);
			viewPage = "notice/noticeList.jsp";
		} else if (command.equals("/nWrite_view.do")) { // 구인공고 글쓰기 페이지 가기
			write_view = 1;
			viewPage = "notice/nWrite_view.jsp";
		} else if (command.equals("/nWrite.do")) { // 구인공고 글쓰기
			if(write_view == 1) {
				service = new NWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "noticeList.do";
		} else if(command.equals("/noticeContent_view.do")) { // 구인공고 글 상세보기
			service = new NContentViewService();
			service.execute(request, response);
			viewPage = "notice/nContent_view.jsp";
		} else if(command.equals("/nDelete.do")) { // 구인공고 글 삭제하기
			service = new NDeleteService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		} else if(command.equals("/nModify_view.do")) { // 구인공고 글 수정페이지로 가기
			service = new NModifyViewService();
			service.execute(request, response);
			viewPage = "notice/nModify_view.jsp";
		} else if(command.equals("/nModify.do")) { // 구인공고 글 수정하기
			service = new NModifyService();
			service.execute(request, response);
			viewPage = "noticeList.do";
		} else if (command.equals("/boardList.do")) { // 자유게시판 페이지 가기
			service = new BListService();
			service.execute(request, response);
			viewPage = "board/boardList.jsp";
		} else if (command.equals("/mWrite_view.do")) { // 자유게시판 회원글쓰기 페이지 가기
			write_view = 1;
			viewPage = "board/mWrite_view.jsp";
		} else if (command.equals("/mWrite.do")) { // 자유게시판 관리자글쓰기
			if(write_view == 1) {
				service = new MWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "boardList.do";
		} else if (command.equals("/aWrite_view.do")) { // 자유게시판 관리자글쓰기 페이지 가기
			write_view = 1;
			viewPage = "board/aWrite_view.jsp";
		} else if (command.equals("/aWrite.do")) { // 자유게시판 관리자글쓰기
			if(write_view == 1) {
				service = new AWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "boardList.do";
		} else if(command.equals("/content_view.do")) { // 자유게시판 글 상세보기
			service = new ContentViewService();
			service.execute(request, response);
			viewPage = "board/content_view.jsp";
		} else if(command.equals("/bDelete.do")) { // 자유게시판 글 삭제하기
			service = new DeleteService();
			service.execute(request, response);
			viewPage = "boardList.do";
		} else if(command.equals("/bModify_view.do")) { // 자유게시판 수정페이지로 가기
			service = new BModifyViewService();
			service.execute(request, response);
			viewPage = "board/bModify_view.jsp";
		} else if(command.equals("/bModify.do")) { // 자유게시판 글 수정하기
			service = new BModifyService();
			service.execute(request, response);
			viewPage = "boardList.do";
		} else if(command.equals("/reply_view.do")) { // 답변글 쓰기 페이지 가기
			service = new BReplyViewService();
			service.execute(request, response);
			viewPage = "board/reply_view.jsp";
		} else if(command.equals("/reply.do")) { // 답변글 쓰기
			service = new BReplyService();
			service.execute(request, response);
			viewPage = "boardList.do";
		} else if (command.equals("/lectureList.do")) { // 상품보기
			service = new PListService();
			service.execute(request, response);
			viewPage = "product/lectureList.jsp";
		} else if (command.equals("/lectureUpdate_view.do")) { // 강의등록 페이지 가기
			write_view = 1;
			viewPage = "product/lectureUp.jsp";
		} else if (command.equals("/lectureUpdate.do")) { // 강의 등록
			if(write_view == 1) {
				service = new PWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "lectureList.do";
		} else if(command.equals("/lectureDelete.do")) { // 강의 삭제하기
			service = new PDeleteService();
			service.execute(request, response);
			viewPage = "lectureList.do";
		} else if(command.equals("/lectureSearch.do")) { // 강의 검색하기
			service = new PSearchService();
			service.execute(request, response);
			viewPage = "lectureList2.do";
		} else if (command.equals("/lectureList2.do")) { // 검색상품보기
			service = new PSearchService();
			service.execute(request, response);
			viewPage = "product/lectureList2.jsp";
		} else if (command.equals("/orderList.do")) { // 내 강의실 가기
			service = new OListService();
			service.execute(request, response);
			viewPage = "order/orderList.jsp";
		} else if (command.equals("/orderNowList.do")) { // 수강중인 강의목록 가기
			service = new ONowListService();
			service.execute(request, response);
			viewPage = "order/orderNowList.jsp";
		} else if (command.equals("/orderPastList.do")) { // 수강했던 강의목록 가기
			service = new OPastListService();
			service.execute(request, response);
			viewPage = "order/orderPastList.jsp";
		} else if (command.equals("/orderBuyView.do")) { // 구매페이지 가기
			service = new OBuyViewService();
			service.execute(request, response);
			viewPage = "order/orderBuy.jsp";
		} else if (command.equals("/orderBuy.do")) { // 구매하기
			service = new OBuyService();
			service.execute(request, response);
			viewPage = "orderList.do";
		} else if(command.equals("/itInfo.do")) { // 연혁 페이지가기
			viewPage = "infomation/itInfo.jsp";
		} else if(command.equals("/teacherInfo.do")) { // 강사소개 페이지가기
			viewPage = "infomation/teacherInfo.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
