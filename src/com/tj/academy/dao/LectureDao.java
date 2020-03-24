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

import com.tj.academy.dto.LectureDto;

public class LectureDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static LectureDao instance = new LectureDao();
	public static LectureDao getInstance() {
		return instance;
	}
	private LectureDao() {}
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
	// 강의 등록 ( lCode, lName, lDate, STARTDATE, ENDDATE, lTeacherName, lPrice, lNum, lStock)
	public int lectureUp(String lCode, String lName, String lDate, Date startDate, Date endDate, String lTeacherName, int lPrice, int lNum, int lStock) {
		int result = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO LECTURE (lCode, lName, lDate, STARTDATE, ENDDATE, lTeacherName, lPrice, lNum, lStock) " + 
				"    VALUES (?,?, ?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lCode);
			pstmt.setString(2, lName);
			pstmt.setString(3, lDate);
			pstmt.setDate(4, startDate);
			pstmt.setDate(5, endDate);
			pstmt.setString(6, lTeacherName);
			pstmt.setInt(7, lPrice);
			pstmt.setInt(8, lNum);
			pstmt.setInt(9, lStock);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "강의등록성공":"강의등록실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 강의 리스트 보기 (재고 없는 순) startRow ~ endRow
	public ArrayList<LectureDto> lectureList(int startRow, int endRow) {
		ArrayList<LectureDto> dtos = new ArrayList<LectureDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, L.* FROM (SELECT * FROM LECTURE WHERE STARTDATE>SYSDATE ORDER BY lStock) L) " + 
				"        WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String lCode = rs.getString("lCode");
				String lName = rs.getString("lName");
				String lDate = rs.getString("lDate");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				String lTeacherName = rs.getString("lTeacherName");
				int lPrice = rs.getInt("lPrice");
				int lNum = rs.getInt("lNum");
				int lStock = rs.getInt("lStock");
				dtos.add(new LectureDto(lCode, lName, lDate, startDate, endDate, lTeacherName, lPrice, lNum, lStock));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
	// 강의 개수
	public int getLectureTotCnt() {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM LECTURE WHERE STARTDATE>SYSDATE";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				totCnt = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
	// 강의 개수 (이름검색)
		public int getLectureNameTotCnt(String schWord) {
			int totCnt = 0;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT COUNT(*) FROM LECTURE WHERE STARTDATE>SYSDATE AND lName like '%' || ? || '%'";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, schWord);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					totCnt = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs   != null) rs.close();
					if(pstmt!= null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return totCnt;
		}
	// 강의 검색(강의이름)
		public ArrayList<LectureDto> lectureName(String schWord, int startRow, int endRow) {
			ArrayList<LectureDto> dtos = new ArrayList<LectureDto>();
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM (SELECT ROWNUM RN, L.* FROM (SELECT * FROM LECTURE WHERE STARTDATE>SYSDATE AND lName like '%' || ? || '%' ORDER BY lStock) L) " + 
					"        WHERE RN BETWEEN ? AND ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, schWord);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String lCode = rs.getString("lCode");
					String lName = rs.getString("lName");
					String lDate = rs.getString("lDate");
					Date startDate = rs.getDate("startDate");
					Date endDate = rs.getDate("endDate");
					String lTeacherName = rs.getString("lTeacherName");
					int lPrice = rs.getInt("lPrice");
					int lNum = rs.getInt("lNum");
					int lStock = rs.getInt("lStock");
					dtos.add(new LectureDto(lCode, lName, lDate, startDate, endDate, lTeacherName, lPrice, lNum, lStock));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs   != null) rs.close();
					if(pstmt!= null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return dtos;
		}
	// 강의 삭제
	public int lectureDelete(String lCode) {
		int result = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM LECTURE WHERE lCode=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lCode);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	// 재고 수량 줄이기
	public void stockDown(String lCode) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE LECTURE SET lStock = lStock-1 WHERE lCode=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lCode);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	// lCode로 dto 가져오기
	public LectureDto buyView(String lCode) {
		LectureDto          dto   = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM LECTURE WHERE lCode=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lCode);;
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String lName = rs.getString("lName");
				String lDate = rs.getString("lDate");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				String lTeacherName = rs.getString("lTeacherName");
				int lPrice = rs.getInt("lPrice");
				int lNum = rs.getInt("lNum");
				int lStock = rs.getInt("lStock");
				dto = new LectureDto(lCode, lName, lDate, startDate, endDate, lTeacherName, lPrice, lNum, lStock);
			}
		} catch (Exception e) {
			System.out.println("DAO:"+e.getMessage());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
}
