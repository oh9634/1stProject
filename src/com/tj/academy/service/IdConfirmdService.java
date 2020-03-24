package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.dao.MemberDao;


public class IdConfirmdService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.mIdConfirm(mId);
		if(result==MemberDao.EXISTENT) {
			request.setAttribute("idResult", "중복된 아이디입니다.");
		} else if(result==MemberDao.NONEXISTENT) {
			request.setAttribute("idResult", "사용가능한 아이디입니다.");
		}
	}

}
