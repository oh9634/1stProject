package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.dao.MemberDao;

public class MDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.deleteMember(mId, mPw);
		if(result == MemberDao.SUCCESS) {
			request.setAttribute("deleteMsg", "회원정보가 삭제되었습니다.");
		}else {
			request.setAttribute("deleteMsg", "회원삭제에 실패했습니다.");
		}
	}

}
