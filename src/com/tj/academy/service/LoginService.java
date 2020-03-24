package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tj.academy.dao.MemberDao;
import com.tj.academy.dto.MemberDto;


public class LoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.loginCheck(mId, mPw);
		if(result==MemberDao.LOGIN_SUCCESS) {
			HttpSession session = request.getSession();
			MemberDto member = mDao.getMember(mId);
			session.setAttribute("member", member);
		} else {
			request.setAttribute("LoginErrorMsg", "아이디와 비번을 확인하세요");
		}
	}

}
