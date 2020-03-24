package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.dao.MemberDao;
import com.tj.academy.dto.MemberDto;

public class MDetailViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		MemberDao mDao = MemberDao.getInstance();
		MemberDto dto = mDao.getMember(mId);
		request.setAttribute("detail_view", dto);
	}

}
