package com.tj.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.dao.LectureDao;
import com.tj.academy.dao.OrdersDao;

public class OBuyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String lCode = request.getParameter("lCode");
		String oTel = request.getParameter("oTel");
		String mId = request.getParameter("mId");
		LectureDao lDao = LectureDao.getInstance();
		OrdersDao oDao = OrdersDao.getInstance();
		lDao.stockDown(lCode);
		oDao.lectureBuy(mId, lCode, oTel);
		request.setAttribute("mId", mId);
	}

}
