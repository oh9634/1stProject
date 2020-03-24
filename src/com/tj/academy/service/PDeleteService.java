package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.dao.LectureDao;


public class PDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String lCode = request.getParameter("lCode");
		LectureDao lDao = LectureDao.getInstance();
		int result = lDao.lectureDelete(lCode);
		if(result == LectureDao.SUCCESS) {
			request.setAttribute("lDeleteMsg", "강의삭제에 성공하였습니다.");
		}else {
			request.setAttribute("lDeleteMsg", "강의삭제에 실패하였습니다.");
		}
	}

}
