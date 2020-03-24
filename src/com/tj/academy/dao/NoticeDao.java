package com.tj.academy.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.academy.dto.NoticeDto;

public class NoticeDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static NoticeDao instance = new NoticeDao();

	public static NoticeDao getInstance() {
		return instance;
	}

	private NoticeDao() {
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	// 글목록(startRow부터 endRow까지)
	public ArrayList<NoticeDto> noticeList(int startRow, int endRow) {
		ArrayList<NoticeDto> dtos = new ArrayList<NoticeDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				+ "    (SELECT N.*,ANAME FROM NOTICE N, ADMIN A WHERE N.AID=A.AID ORDER BY NNO DESC) A) "
				+ "    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int nNo = rs.getInt("nNo");
				String aId = rs.getString("aId");
				String aName = rs.getString("aName");
				String nTitle = rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				Date nRdate = rs.getDate("nRdate");
				int nHit = rs.getInt("nHit");
				dtos.add(new NoticeDto(nNo, aId, aName, nTitle, nContent, nRdate, nHit));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}

	// 글갯수
	public int getNoticeTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM NOTICE";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totCnt = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}

	// 글쓰기(원글)
	public int noticeWrite(String aId, String nTitle, String nContent) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO NOTICE (nNo, aId, nTitle, nContent) " + "    VALUES (NOTICE_SEQ.NEXTVAL, ?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, nTitle);
			pstmt.setString(3, nContent);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글쓰기성공" : "글쓰기실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// FHit 하나 올리기(1번글 조회수 하나 올리기)
	public void noticeHitUp(int nNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICE SET nHit = nHit + 1 WHERE nNo=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// FId로 글 dto보기 : 글상세보기(hit수 올리기 호출)
	public NoticeDto noticeContentView(int nNo) {
		noticeHitUp(nNo); // 글 상세보기시 자동적으로 hitup
		NoticeDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT N.*, ANAME FROM NOTICE N, ADMIN A WHERE N.AID=A.AID AND NNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String aName = rs.getString("aName");
				String nTitle = rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				Date nRdate = rs.getDate("nRdate");
				int nHit = rs.getInt("nHit");
				dto = new NoticeDto(nNo, aId, aName, nTitle, nContent, nRdate, nHit);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}

	// nNo로 글 dto보기 : (답변글 쓰기 + 수정하기페이지)
	public NoticeDto noticeModifyView(int nNo) {
		NoticeDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT N.*, ANAME FROM NOTICE N, ADMIN A WHERE N.AID=A.AID AND NNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String aName = rs.getString("aName");
				String nTitle = rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				Date nRdate = rs.getDate("nRdate");
				int nHit = rs.getInt("nHit");
				dto = new NoticeDto(nNo, aId, aName, nTitle, nContent, nRdate, nHit);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}

	// 글 수정하기(nTitle, nContent)
	public int noticeModify(int nNo, String nTitle, String nContent) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICE SET nTitle=?,nContent=?, nRDATE=SYSDATE WHERE nno=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nTitle);
			pstmt.setString(2, nContent);
			pstmt.setInt(3, nNo);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글수정성공" : "글수정실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// 글 삭제하기(nNo로 삭제하기)
	public int noticeDelete(int nNo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM NOTICE WHERE nNo=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
