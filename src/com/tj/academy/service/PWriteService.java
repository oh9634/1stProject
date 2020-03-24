package com.tj.academy.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.academy.dao.LectureDao;


public class PWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		String lCode = request.getParameter("lCode");
		String lName = request.getParameter("lName");
		String lDate = request.getParameter("lDate");
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");
		String lTeacherName = request.getParameter("lTeacherName");
		int lPrice = Integer.parseInt(request.getParameter("lPrice"));
		int lNum = Integer.parseInt(request.getParameter("lNum"));
		int lStock = Integer.parseInt(request.getParameter("lStock"));
		Date startDate = null;
		Date endDate = null;
		if(!startDateStr.equals("")) {
			startDate = Date.valueOf(startDateStr);
		}
		if(!endDateStr.equals("")) {
			endDate = Date.valueOf(endDateStr);
		}
		LectureDao lDao = LectureDao.getInstance();
		int result;
		result = lDao.lectureUp(lCode, lName, lDate, startDate, endDate, lTeacherName, lPrice, lNum, lStock);
		if(result == LectureDao.SUCCESS) { 
			request.setAttribute("lectureMsg", "업로드에 성공하였습니다.");
		}else {
			request.setAttribute("lectureMsg", "업로드에 실패하였습니다.");
		}
	}

}
