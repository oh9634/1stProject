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
import com.tj.academy.dto.BoardDto;

public class BoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}

	private BoardDao() {
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
	public ArrayList<BoardDto> list(int startRow, int endRow) {
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				+ "    (SELECT fNo, (SELECT mName FROM FREEBOARD F, MEMBER M WHERE F.MID=M.MID AND FREEBOARD.fNo=fNo)|| "
				+ "        (SELECT aName FROM FREEBOARD F, ADMIN A WHERE A.AID=F.AID AND FREEBOARD.fNo=fNo) WRITER,"
				+ "    fTitle, fCategory, fContent, fFILENAME, fRdate, fHit,FGROUP, FSTEP, FINDENT,fImportance, fIp "
				+ "    from FREEBOARD ORDER BY fImportance DESC, fGroup DESC, fStep) A) "
				+ "    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int fNo = rs.getInt("fNo");
				String mName = rs.getString("writer");
				String fTitle = rs.getString("fTitle");
				String fCategory = rs.getString("fCategory");
				String fContent = rs.getString("fContent");
				String fFilename = rs.getString("fFilename");
				Date fRdate = rs.getDate("fRdate");
				int fHit = rs.getInt("fHit");
				int fGroup = rs.getInt("fGroup");
				int fStep = rs.getInt("fStep");
				int fIndent = rs.getInt("fIndent");
				int fImportance = rs.getInt("fImportance");
				String fIp = rs.getString("fIp");
				dtos.add(new BoardDto(fNo, null, mName, null, null, fTitle, fCategory, fContent, fFilename, fRdate,
						fHit, fGroup, fStep, fIndent, fImportance, fIp));
			}
		} catch (Exception e) {
			System.out.println("게시판 예외 : " + e.getMessage());
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
	public int getBoardTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM FREEBOARD";
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

	// 글쓰기 (원글) (mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep,
	// fIndent, fImportance, fIp) => 회원
	public int mWrite(String mId, String fTitle, String fCategory, String fContent, String fFilename, String fIp) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD (fNo, mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep, fIndent, fImportance, fIp) "
				+ "    VALUES (FREEBOARD_SEQ.NEXTVAL, ?,null,?,?,?, ?,FREEBOARD_SEQ.CURRVAL, 0, 0,0, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fCategory);
			pstmt.setString(4, fContent);
			pstmt.setString(5, fFilename);
			pstmt.setString(6, fIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "회원원글쓰기성공" : "회원원글쓰기실패");
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

	// 글쓰기 (원글) (mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep,
	// fIndent, fImportance, fIp) => 관리자
	public int aWrite(String aId, String fTitle, String fCategory, String fContent, String fFilename, String fIp) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD (fNo, mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep, fIndent, fImportance, fIp) "
				+ "    VALUES (FREEBOARD_SEQ.NEXTVAL, null,?,?,?,?, ?,FREEBOARD_SEQ.CURRVAL, 0, 0,1, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fCategory);
			pstmt.setString(4, fContent);
			pstmt.setString(5, fFilename);
			pstmt.setString(6, fIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "관리자공지쓰기성공" : "관리자공지쓰기실패");
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
	public void boardHitUp(int fNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET fHit = fHit + 1 WHERE fNo=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
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

	// fNo로 글 dto보기 : 글상세보기(hit수 올리기 호출)
	public BoardDto contentView(int fNo) {
		boardHitUp(fNo); // 글 상세보기시 자동적으로 hitup
		BoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT fNo, mId, aId, "
				+ "        (SELECT mName FROM FREEBOARD F, MEMBER M WHERE F.MID=M.MID AND FREEBOARD.fNo=fNo)|| "
				+ "        (SELECT aName FROM FREEBOARD F, ADMIN A WHERE A.AID=F.AID AND FREEBOARD.fNo=fNo) WRITER, "
				+ "    fTitle, fCategory, fContent, fFILENAME, fRdate, fHit, fGroup, fStep, fIndent, fImportance, fIp "
				+ "    from FREEBOARD WHERE fNo=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String mName = rs.getString("writer");
				String aId = rs.getString("aId");
				String fTitle = rs.getString("fTitle");
				String fCategory = rs.getString("fCategory");
				String fContent = rs.getString("fContent");
				String fFilename = rs.getString("fFilename");
				Date fRdate = rs.getDate("fRdate");
				int fHit = rs.getInt("fHit");
				int fGroup = rs.getInt("fGroup");
				int fStep = rs.getInt("fStep");
				int fIndent = rs.getInt("fIndent");
				int fImportance = rs.getInt("fImportance");
				String fIp = rs.getString("fIp");
				dto = new BoardDto(fNo, mId, mName, aId, null, fTitle, fCategory, fContent, fFilename, fRdate, fHit,
						fGroup, fStep, fIndent, fImportance, fIp);
			}
		} catch (Exception e) {
			System.out.println("DAO:" + e.getMessage());
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

	// fNo로 글 dto보기 : (답변글 쓰기 + 수정하기페이지)
	public BoardDto modifyView_replyView(int fNo) {
		BoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT fNo, mId, aId, "
				+ "        (SELECT mName FROM FREEBOARD F, MEMBER M WHERE F.MID=M.MID AND FREEBOARD.fNo=fNo)|| "
				+ "        (SELECT aName FROM FREEBOARD F, ADMIN A WHERE A.AID=F.AID AND FREEBOARD.fNo=fNo) WRITER, "
				+ "    fTitle, fCategory, fContent, fFILENAME, fRdate, fHit, fGroup, fStep, fIndent, fImportance, fIp "
				+ "    from FREEBOARD WHERE fNo=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String mName = rs.getString("writer");
				String aId = rs.getString("aId");
				String fTitle = rs.getString("fTitle");
				String fCategory = rs.getString("fCategory");
				String fContent = rs.getString("fContent");
				String fFilename = rs.getString("fFilename");
				Date fRdate = rs.getDate("fRdate");
				int fHit = rs.getInt("fHit");
				int fGroup = rs.getInt("fGroup");
				int fStep = rs.getInt("fStep");
				int fIndent = rs.getInt("fIndent");
				int fImportance = rs.getInt("fImportance");
				String fIp = rs.getString("fIp");
				dto = new BoardDto(fNo, mId, mName, aId, null, fTitle, fCategory, fContent, fFilename, fRdate, fHit,
						fGroup, fStep, fIndent, fImportance, fIp);
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

	// 글 수정하기(fTitle, fCategory, fContent, fFILENAME, fIp)
	public int modify(int fNo, String fTitle, String fCategory, String fContent, String fFileName, String fIp) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET fTitle=?, fCategory=?, fContent=?, fFILENAME=?, " + "    FIp=? WHERE fNo=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fTitle);
			pstmt.setString(2, fCategory);
			pstmt.setString(3, fContent);
			pstmt.setString(4, fFileName);
			pstmt.setString(5, fIp);
			pstmt.setInt(6, fNo);
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

	// 글 삭제하기
	public int delete(int fNo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM FREEBOARD WHERE fNo=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
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

	// 답변글 추가전 STEP a 수행
	public void preReplyStepA(int fGroup, int fStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET fStep = fStep+1 WHERE fGROUP=? AND fSTEP>?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fGroup);
			pstmt.setInt(2, fStep);
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

	// 답변글 쓰기 (fNo, mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep,
	// fIndent, fImportance, fIp) => 회원이 답글쓰기
	public int mReply(String mId, String fTitle, String fCategory, String fContent, String fFilename, int fGroup,
			int fStep, int fIndent, String fIp) {
		preReplyStepA(fGroup, fStep);
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD (fNo, mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep, fIndent, fImportance, fIp) "
				+ "    VALUES (FREEBOARD_SEQ.NEXTVAL, ?,null,?,?,?, ?,?, ?, ?,0, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fCategory);
			pstmt.setString(4, fContent);
			pstmt.setString(5, fFilename);
			pstmt.setInt(6, fGroup);
			pstmt.setInt(7, fStep + 1);
			pstmt.setInt(8, fIndent + 1);
			pstmt.setString(9, fIp);
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

	// 답변글 쓰기 (fNo, mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep,
	// fIndent, fImportance, fIp) => 관리자가 답글쓰기
	public int aReply(String aId, String fTitle, String fCategory, String fContent, String fFileName, int fGroup,
			int fStep, int fIndent, String fIp) {
		preReplyStepA(fGroup, fStep);
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD (fNo, mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep, fIndent, fImportance, fIp) "
				+ "    VALUES (FREEBOARD_SEQ.NEXTVAL, ?,null,?,?,?, ?,?, ?, ?,1, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fCategory);
			pstmt.setString(4, fContent);
			pstmt.setString(5, fFileName);
			pstmt.setInt(6, fGroup);
			pstmt.setInt(7, fStep + 1);
			pstmt.setInt(8, fIndent + 1);
			pstmt.setString(9, fIp);
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

	// fImportance로 공지사항가져오기
	public ArrayList<BoardDto> importanceList(int startRow, int endRow) {
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				+ "    (SELECT fNo, (SELECT mName FROM FREEBOARD F, MEMBER M WHERE F.MID=M.MID AND FREEBOARD.fNo=fNo)|| "
				+ "        (SELECT aName FROM FREEBOARD F, ADMIN A WHERE A.AID=F.AID AND FREEBOARD.fNo=fNo) WRITER, "
				+ "    fTitle, fCategory, fContent, fFILENAME, fRdate, fHit,FGROUP, FSTEP, FINDENT,fImportance, fIp "
				+ "    from FREEBOARD WHERE fImportance=1 ORDER BY fRDate DESC) A) " + "    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int fNo = rs.getInt("fNo");
				String mName = rs.getString("writer");
				String fTitle = rs.getString("fTitle");
				String fCategory = rs.getString("fCategory");
				String fContent = rs.getString("fContent");
				String fFilename = rs.getString("fFilename");
				Date fRdate = rs.getDate("fRdate");
				int fHit = rs.getInt("fHit");
				int fGroup = rs.getInt("fGroup");
				int fStep = rs.getInt("fStep");
				int fIndent = rs.getInt("fIndent");
				int fImportance = rs.getInt("fImportance");
				String fIp = rs.getString("fIp");
				dtos.add(new BoardDto(fNo, null, mName, null, null, fTitle, fCategory, fContent, fFilename, fRdate,
						fHit, fGroup, fStep, fIndent, fImportance, fIp));
			}
		} catch (Exception e) {
			System.out.println("게시판 예외 : " + e.getMessage());
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
	// 공지사항 글개수
	public int getImportanceTotCnt() {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM FREEBOARD WHERE fImportance=1";
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
}
