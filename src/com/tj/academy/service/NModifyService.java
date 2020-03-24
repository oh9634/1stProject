package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.dao.NoticeDao;

public class NModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		NoticeDao nDao = NoticeDao.getInstance();
		int result = nDao.noticeModify(nNo, nTitle, nContent);
		if(result == NoticeDao.SUCCESS) {
			request.setAttribute("nModifyMsg", "글수정에 성공하였습니다.");
		}else {
			request.setAttribute("nModifyMsg", "글수정에 실패하였습니다.");
		}
	}

}
