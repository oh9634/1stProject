package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.dao.BoardDao;
import com.tj.academy.dto.BoardDto;


public class ContentViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fNo = Integer.parseInt(request.getParameter("fNo"));
		BoardDao boardDao = BoardDao.getInstance();
		BoardDto dto = boardDao.contentView(fNo);
		request.setAttribute("content_view", dto);
	}

}
