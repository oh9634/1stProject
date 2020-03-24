package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.dao.BoardDao;


public class DeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fNo = Integer.parseInt(request.getParameter("fNo"));
		BoardDao boardDao = BoardDao.getInstance();
		int result = boardDao.delete(fNo);
	}

}
