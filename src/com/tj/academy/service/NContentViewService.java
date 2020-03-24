package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.dao.MemberDao;
import com.tj.academy.dao.NoticeDao;
import com.tj.academy.dto.MemberDto;
import com.tj.academy.dto.NoticeDto;

public class NContentViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		NoticeDao nDao = NoticeDao.getInstance();
		NoticeDto dto = nDao.noticeContentView(nNo);
		request.setAttribute("nContent_view", dto);
	}

}
