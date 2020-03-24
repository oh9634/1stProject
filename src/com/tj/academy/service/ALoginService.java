package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.academy.dao.AdminDao;
import com.tj.academy.dto.AdminDto;


public class ALoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aId = request.getParameter("aId");
		String aPw = request.getParameter("aPw");
		AdminDao aDao = AdminDao.getInstance();
		int result = aDao.aLoginCheck(aId, aPw);
		if(result == AdminDao.LOGIN_SUCCESS) {
			AdminDto admin = aDao.getAdmin(aId);
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			request.setAttribute("adminMsg", "관리자 계정으로 들어오셨습니다");
		}else {
			request.setAttribute("adminLoginError", "관리자 계정으로 로그인이 실패되었습니다");
		}
	}

}
