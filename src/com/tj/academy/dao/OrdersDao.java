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
import com.tj.academy.dto.OrdersDto;

public class OrdersDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static OrdersDao instance = new OrdersDao();

	public static OrdersDao getInstance() {
		return instance;
	}
	private OrdersDao() {
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
	
	// 구매하기 (oNo, mId, lCode, oTel, oDate)
	public int lectureBuy(String mId, String lCode, String oTel) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ORDERS (oNo, mId, lCode, oTel, oDate) " + 
				"    VALUES (ORDERS_SEQ.NEXTVAL, ?,?,?,SYSDATE)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, lCode);
			pstmt.setString(3, oTel);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "구매성공" : "구매실패");
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
	// 구매목록 (최신구매순)
	public ArrayList<OrdersDto> lectureList(String mId, int startRow, int endRow) {
		ArrayList<OrdersDto> dtos = new ArrayList<OrdersDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " + 
				"    (SELECT O.*,LNAME,LPRICE,LTEACHERNAME,LDATE,STARTDATE,ENDDATE FROM ORDERS O, LECTURE L " + 
				"    WHERE O.LCODE=L.LCODE AND mId=? ORDER BY oNo DESC) A) " + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int oNo = rs.getInt("oNo");
				String lCode = rs.getString("lCode");
				String oTel = rs.getString("oTel");
				Date oDate = rs.getDate("oDate");
				String lName = rs.getString("lName");
				String lTeacherName = rs.getString("lTeacherName");
				String lDate = rs.getString("lDate");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				dtos.add(new OrdersDto(oNo, mId, lCode, oTel, oDate, lName, lTeacherName, lDate, startDate, endDate));
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
	// 총 구매갯수
	public int getOrderTotCnt(String mId) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM ORDERS O, LECTURE L WHERE O.LCODE=L.LCODE " + 
				"    AND mId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
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
	// 수강중인 강의 목록
	public ArrayList<OrdersDto> nowList(String mId, int startRow, int endRow) {
		ArrayList<OrdersDto> dtos = new ArrayList<OrdersDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " + 
				"    (SELECT O.*,LNAME,LPRICE,LTEACHERNAME,LDATE,STARTDATE,ENDDATE FROM ORDERS O, LECTURE L " + 
				"    WHERE O.LCODE=L.LCODE AND enddate > SYSDATE AND STARTDATE<SYSDATE AND mId=? ORDER BY oNo DESC) A) " + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int oNo = rs.getInt("oNo");
				String lCode = rs.getString("lCode");
				String oTel = rs.getString("oTel");
				Date oDate = rs.getDate("oDate");
				String lName = rs.getString("lName");
				String lTeacherName = rs.getString("lTeacherName");
				String lDate = rs.getString("lDate");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				dtos.add(new OrdersDto(oNo, mId, lCode, oTel, oDate, lName, lTeacherName, lDate, startDate, endDate));
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
	// 수강한 강의 목록
	public ArrayList<OrdersDto> pastList(String mId, int startRow, int endRow) {
		ArrayList<OrdersDto> dtos = new ArrayList<OrdersDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " + 
				"    (SELECT O.*,LNAME,LPRICE,LTEACHERNAME,LDATE,STARTDATE,ENDDATE FROM ORDERS O, LECTURE L " + 
				"    WHERE O.LCODE=L.LCODE AND enddate < SYSDATE AND mId=? ORDER BY oNo DESC) A) " + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int oNo = rs.getInt("oNo");
				String lCode = rs.getString("lCode");
				String oTel = rs.getString("oTel");
				Date oDate = rs.getDate("oDate");
				String lName = rs.getString("lName");
				String lTeacherName = rs.getString("lTeacherName");
				String lDate = rs.getString("lDate");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				dtos.add(new OrdersDto(oNo, mId, lCode, oTel, oDate, lName, lTeacherName, lDate, startDate, endDate));
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
	// 수강중인 강의 개수
	public int getNowTotCnt(String mId) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM ORDERS O, LECTURE L WHERE O.LCODE=L.LCODE " + 
				"    AND enddate > SYSDATE AND STARTDATE<SYSDATE AND mId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
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
	// 수강한 강의 개수
	public int getPastTotCnt(String mId) {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM ORDERS O, LECTURE L WHERE O.LCODE=L.LCODE AND enddate < SYSDATE AND mId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
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
}
