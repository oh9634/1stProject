package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.academy.dao.NoticeDao;
import com.tj.academy.dto.AdminDto;

public class NWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		String aId = ((AdminDto)httpSession.getAttribute("admin")).getaId();
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");
		NoticeDao nDao = NoticeDao.getInstance();
		int result;
		result = nDao.noticeWrite(aId, nTitle, nContent);
		if(result == NoticeDao.SUCCESS) { 
			request.setAttribute("noticeMsg", "글쓰기에 성공하였습니다.");
		}else {
			request.setAttribute("noticeMsg", "글쓰기에 실패하였습니다.");
		}
	}

}
