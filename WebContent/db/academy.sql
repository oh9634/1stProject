----------------------------- member 테이블 -----------------------------
DROP TABLE MEMBER;
CREATE TABLE MEMBER(
    mId VARCHAR2(30) PRIMARY KEY,
    mPw VARCHAR2(30) NOT NULL,
    mName VARCHAR2(30) NOT NULL,
    mEmail VARCHAR2(30),
    mPhoto VARCHAR2(30) DEFAULT 'NOIMG.JPG',
    mBirth DATE,
    mAddress VARCHAR2(300),
    mRdate  DATE DEFAULT SYSDATE
);

-- 1. 아이디 중복검사
SELECT * FROM MEMBER WHERE mId='aaa';
-- 2. 회원가입 (mId, mPw, mName, mEmail, mPhoto, mBirth, mAddress)
INSERT INTO MEMBER (mId, mPw, mName, mEmail, mPhoto, mBirth, mAddress)
    VALUES ('aaa','1','홍길동','hong@naver.com','NOIMG.JPG','1990/12/12','종로');
-- 3. 로그인 (mId, mPw)
SELECT * FROM MEMBER WHERE mID='aaa' and mPW='1';
-- 4. 세션에 넣기 위해 mId로 member dto가져오기
SELECT * FROM MEMBER WHERE mId='aaa';
--회원정보수정(mPw, mNAME / mEMAIL, mPHOTO, mBIRTH, mADDRESS 수정 가능)
UPDATE MEMBER SET mPw = '111',
                    mName = 'HONG',
                    mEmail = 'yi@naver.com',
                    mPhoto = 'NOIMG.JPG',
                    mBirth = '1991/12/12',
                    mAddress = '서울'
        WHERE mId='aaa';
-- 회원목록(startRow ~ endRow)
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
                        (SELECT * FROM MEMBER ORDER BY mRDATE DESC) A)
        WHERE RN BETWEEN 1 AND 10;
-- 가입한 전체 회원 명수
SELECT COUNT(*) cnt FROM MEMBER;
commit;

----------------------------- admin 테이블 -----------------------------

DROP TABLE ADMIN;
CREATE TABLE ADMIN(
    aId VARCHAR2(30) PRIMARY KEY,
    aPw VARCHAR2(30) NOT NULL,
    aName VARCHAR2(30) NOT NULL
);
-- dummy data
INSERT INTO ADMIN VALUES ('admin','111','김관리');

--- DAO에 넣을 sql --- 

-- admin 로그인체크
SELECT * FROM ADMIN WHERE AID='admin' AND APW='111';
-- admin aid로 dto 가져오기
SELECT * FROM ADMIN WHERE AID='admin';
COMMIT;

----------------------------- book 테이블 -----------------------------
DROP TABLE BOOK;
CREATE TABLE BOOK(
    bCode VARCHAR2(5) PRIMARY KEY,
    bName VARCHAR2(50) NOT NULL,
    bWriter VARCHAR2(50) NOT NULL,
    bPrice NUMBER(9) NOT NULL
);
-- 1. 책 리스트 보기 (가격 저렴한 순)
SELECT * FROM (SELECT ROWNUM RN, B.* FROM 
                        (SELECT * FROM BOOK ORDER BY bPrice) B)
        WHERE RN BETWEEN 1 AND 10;
-- 2. 책 개수
SELECT COUNT(*) FROM BOOK;
-- 3. 책 검색(책이름, 저자, 책가격)
SELECT * FROM BOOK WHERE bName like '%' || 'a' || '%' and bWriter like '%' || 'wr' || '%' and bPrice = 9;
-- 4. 책 등록
INSERT INTO Book (bCode, bName, bWriter, bPrice)
    VALUES ('a', 'java','writer',9);
SELECT * FROM BOOK;
-- 5. 책 수정
UPDATE BOOK SET bName='책제목', bWriter='저자', bPrice=1000 WHERE bCode='a';
-- 6. 책 삭제
DELETE FROM BOOK WHERE bCode='a';
--7. 책 상세보기
SELECT * FROM BOOK WHERE bCode='b';
commit;
----------------------------- lecture 테이블 -----------------------------
DROP TABLE LECTURE;
CREATE TABLE LECTURE (
    lCode VARCHAR2(5) PRIMARY KEY,
    bCode VARCHAR2(5) REFERENCES BOOK(bCode), 
    lName VARCHAR(50) NOT NULL,
    lDate VARCHAR(50),
    STARTDATE DATE,
    ENDDATE DATE,
    lTeacherName VARCHAR(50),
    lPrice NUMBER(9),
    lStock NUMBER(9)
);

-- 1.  강의 등록
INSERT INTO LECTURE ( lCode, bCode, lName, lDate, STARTDATE, ENDDATE, lTeacherName, lPrice, lStock)
    VALUES ('aaa','b','자바', '오전 9시','2020/03/23','2020/05/02','김사랑',110000,30);

-- 2. 강의 리스트 보기 (가격 저렴한 순)
SELECT * FROM (SELECT ROWNUM RN, L.* FROM (SELECT * FROM LECTURE ORDER BY lPrice) L)
        WHERE RN BETWEEN 1 AND 10;
-- 3. 강의 개수
SELECT COUNT(*) FROM LECTURE;
-- 4. 강의 검색(강의이름, 강사명, 강의가격)
SELECT * FROM LECTURE WHERE lName like '%' || '자' || '%' and lTeacherName like '%' || '김' || '%' and lPrice = 110000;
-- 5. 강의 수정
UPDATE LECTURE SET bCode='b', lName='오라클', lDate='오전 10시', STARTDATE='2020/04/23',
    ENDDATE='2020/05/23', lTeacherName='김사람', lPrice=99000, lStock=7
    WHERE lCode='aaa';
-- 6. 강의 삭제
DELETE FROM LECTURE WHERE lCode='aaa';
-- 7. 강의 상세보기
SELECT L.*, bName FROM LECTURE L, BOOK B WHERE L.bCode=B.bCode AND lCode='aaa';
commit;
----------------------------- orders 테이블 -----------------------------
DROP TABLE ORDERS;
DROP SEQUENCE ORDERS_SEQ;
CREATE SEQUENCE ORDERS_SEQ MAXVALUE 999999999 NOCACHE NOCYCLE;
CREATE TABLE ORDERS(
    oNo NUMBER(9) PRIMARY KEY,
    mId VARCHAR2(30) REFERENCES MEMBER(mId),
    lCode VARCHAR2(5) REFERENCES LECTURE(lCode),
    oTel VARCHAR(30),
    oDate DATE
);
-- 주문 등록
INSERT INTO ORDERS (oNo, mId, lCode, oTel, oDate)
    VALUES (ORDERS_SEQ.NEXTVAL, 'aaa','aaa',null,null);
----------------------------- freeboard 테이블 -----------------------------
DROP TABLE FREEBOARD;
DROP SEQUENCE FREEBOARD_SEQ;
CREATE SEQUENCE FREEBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE FREEBOARD (
    fNo NUMBER(6) PRIMARY KEY,
    mId VARCHAR2(30) REFERENCES MEMBER(mId),
    aId VARCHAR2(30) REFERENCES ADMIN(aId),
    fTitle VARCHAR2(100) NOT NULL,
    fCategory VARCHAR2(100),
    fContent VARCHAR2(4000),
    fFILENAME VARCHAR2(100),
    fRdate DATE,
    fHit NUMBER(6),
    fGroup NUMBER(6),
    fStep NUMBER(6),
    fIndent NUMBER(6),
    fImportance NUMBER(1) NOT NULL,
    fIp VARCHAR2(30)
);
-- 1. 글쓰기
INSERT INTO FREEBOARD (fNo, mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep, fIndent, fImportance, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'aaa',null,'title','수강후기',null, null,FREEBOARD_SEQ.CURRVAL, 0, 0,0, '192.168.10.151');
INSERT INTO FREEBOARD (fNo, mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep, fIndent, fImportance, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'aaa',null,'제목','도서후기',null, null,FREEBOARD_SEQ.CURRVAL, 0, 0,0, '192.168.10.151');
INSERT INTO FREEBOARD (fNo, mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep, fIndent, fImportance, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, null,'admin','title','공지',null, null,FREEBOARD_SEQ.CURRVAL, 0, 0,1, '192.168.10.151');
INSERT INTO FREEBOARD (fNo, mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep, fIndent, fImportance, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, null,'admin','공지2','공지','내용', '파일',FREEBOARD_SEQ.CURRVAL, 0, 0,1, '192.168.10.151');
-- 2. 글갯수
SELECT COUNT(*) FROM FREEBOARD;
-- 3. 글목록(startRow부터 endRow까지)
SELECT fNo, (SELECT mName FROM FREEBOARD F, MEMBER M WHERE F.MID=M.MID AND FREEBOARD.fNo=fNo)|| 
        (SELECT aName FROM FREEBOARD F, ADMIN A WHERE A.AID=F.AID AND FREEBOARD.fNo=fNo) WRITER,
    fTitle, fCategory, fContent, fFILENAME, fRdate, fHit, fIp
    from FREEBOARD ORDER BY fImportance DESC, fRdate DESC;
-- 4. FHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE FREEBOARD SET fHit = fHit + 1 WHERE fNo=2;
-- 5. fNo로 글 dto보기
SELECT fNo, (SELECT mName FROM FREEBOARD F, MEMBER M WHERE F.MID=M.MID AND FREEBOARD.fNo=fNo)|| 
        (SELECT aName FROM FREEBOARD F, ADMIN A WHERE A.AID=F.AID AND FREEBOARD.fNo=fNo) WRITER,
    fTitle, fCategory, fContent, fFILENAME, fRdate, fHit, fGroup, fStep, fIndent, fImportance, fIp
    from FREEBOARD WHERE fNo=3;
-- 6. 글 수정하기(fTitle, fCategory, fContent, fFILENAME, fIp)
UPDATE FREEBOARD SET fTitle='바뀜', fCategory='바뀜', fContent='바뀜', fFILENAME='바뀜.jpg',
    FIp='192.168.20.44', fRDATE=SYSDATE WHERE fNo=4;
-- 글 삭제하기(bId로 삭제하기)
DELETE FROM FREEBOARD WHERE fNo=1;
--    답변글 추가전 STEP a 수행
UPDATE FREEBOARD SET fStep = fStep+1 WHERE fGROUP=0 AND fSTEP>0;
-- 답변글 쓰기
INSERT INTO FREEBOARD (fNo, mId, aId, fTitle, fCategory, fContent, fFILENAME, fGroup, fStep, fIndent, fImportance, fIp)
    VALUES (FREEBOARD_SEQ.NEXTVAL, 'aaa',null,'reply','수강후기',null, null,FREEBOARD_SEQ.CURRVAL, 3, 1,0, '192.168.10.151');
commit;
----------------------------- notice 테이블 -----------------------------
DROP TABLE NOTICE;
DROP SEQUENCE NOTICE_SEQ;
CREATE SEQUENCE NOTICE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE NOTICE(
    nNo NUMBER(6) PRIMARY KEY,
    aId VARCHAR2(30) REFERENCES ADMIN(aId),
    nTitle VARCHAR2(100) NOT NULL,
    nContent VARCHAR2(4000),
    nRdate    DATE DEFAULT SYSDATE,
    nHit     NUMBER(6) DEFAULT 0
    );

-- 더미데이터(원글)
INSERT INTO NOTICE (nNo, aId, nTitle, nContent, nRdate)
    VALUES (NOTICE_SEQ.NEXTVAL, 'admin','title','content',null);
SELECT * FROM NOTICE;

---- DAO에 넣을 sql -----
-- 글목록(startRow부터 endRow까지)
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
    (SELECT N.*,ANAME FROM NOTICE N, ADMIN A WHERE N.AID=A.AID ORDER BY NNO DESC) A) 
    WHERE RN BETWEEN 2 AND 5;
-- 글갯수
SELECT COUNT(*) FROM NOTICE;
-- 글쓰기
INSERT INTO NOTICE (nNo, aId, nTitle, nContent, nRdate)
    VALUES (NOTICE_SEQ.NEXTVAL, 'admin','title',null,null);
-- FHit 하나 올리기(1번글 조회수 하나 올리기)
UPDATE NOTICE SET nHit = nHit + 1 WHERE nNo=2;
-- nNo로 글 dto보기
SELECT N.*, ANAME FROM NOTICE N, ADMIN A WHERE N.AID=A.AID AND NNO=2;
-- 글 수정하기(nTitle, nContent)
UPDATE NOTICE SET nTitle='바뀐제목', nRDATE=SYSDATE WHERE nno=4;
-- 글 삭제하기(nNo로 삭제하기)
DELETE FROM NOTICE WHERE nNo=1;

commit;