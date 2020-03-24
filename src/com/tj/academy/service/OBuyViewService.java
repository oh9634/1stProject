package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.dao.LectureDao;
import com.tj.academy.dao.NoticeDao;
import com.tj.academy.dto.LectureDto;
import com.tj.academy.dto.NoticeDto;

public class OBuyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String lCode = request.getParameter("lCode");
		LectureDao lDao = LectureDao.getInstance();
		LectureDto dto = lDao.buyView(lCode);
		request.setAttribute("buyInfo", dto);
		
	}

}
