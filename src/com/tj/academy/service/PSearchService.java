package com.tj.academy.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.academy.dao.LectureDao;
import com.tj.academy.dto.LectureDto;

public class PSearchService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1";
		String schWord = request.getParameter("schWord");
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 1, BLOCKSIZE=5;
		int startRow = (currentPage-1)*PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		// dao의 list(startRow, endRow) 실행결과를 request.setAttribute
		LectureDao lDao = LectureDao.getInstance();
		ArrayList<LectureDto> searchLectures = lDao.lectureName(schWord, startRow, endRow);
		request.setAttribute("searchLectures", searchLectures);
		// totCnt, pageCnt, startPage, endPage, BLOCKSIZE, pageNum
		//    => request.setAttribute
		int totCnt = lDao.getLectureNameTotCnt(schWord);
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE +1;
		int endPage   = startPage + BLOCKSIZE -1 ;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("schWord", schWord);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("pageNum", currentPage);
	}

}
