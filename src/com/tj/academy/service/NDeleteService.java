package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tj.academy.dao.NoticeDao;

public class NDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		NoticeDao nDao = NoticeDao.getInstance();
		int result = nDao.noticeDelete(nNo);
		if(result == NoticeDao.SUCCESS) {
			request.setAttribute("nDeleteMsg", "글삭제에 성공하였습니다.");
		}else {
			request.setAttribute("nDeleteMsg", "글삭제에 실패하였습니다.");
		}
	}

}
