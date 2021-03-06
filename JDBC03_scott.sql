SELECT USER
FROM DUAL;
--==>> SCOTT

--○ 실습 테이블 생성
CREATE TABLE TBL_SCORE
( SID   NUMBER
, NAME  VARCHAR2(30)
, KOR   NUMBER(3)
, ENG   NUMBER(3)
, MAT   NUMBER(3)
);
--==>> Table TBL_SCORE이(가) 생성되었습니다.


--○ 제약조건 추가(SID 컬럼에 PK 제약조건 추가)
ALTER TABLE TBL_SCORE
ADD CONSTRAINT SCORE_SID_PK PRIMARY KEY (SID);
--==>> Table TBL_SCORE이(가) 변경되었습니다.

--○ 제약조건 추가(KOR, ENG, MAT 컬럼에 CK 제약조건 추가)
ALTER TABLE TBL_SCORE
ADD ( CONSTRAINT SCORE_KOR_CK CHECK (KOR BETWEEN 0 AND 100)
    , CONSTRAINT SCORE_ENG_CK CHECK (ENG BETWEEN 0 AND 100)
    , CONSTRAINT SCORE_MAT_CK CHECK (MAT BETWEEN 0 AND 100) );
--==> Table TBL_SCORE이(가) 변경되었습니다.

--○ 시퀀스 생성
CREATE SEQUENCE SCORESEQ
NOCACHE;
--==>> Sequence SCORESEQ이(가) 생성되었습니다.

--○ 입력(INSERT) 쿼리문 구성
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL(), '김정규', 80, 70, 75)
;
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL(), '나주영', 100, 90, 80)
;
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL(), '임나래', 80, 85, 80)
;



--○ 인원 수 확인 쿼리문 구성
SELECT COUNT(*) AS COUNT
FROM TBL_SCORE;
--> 한 줄 구성
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;

--○ 전체 리스트 조회 쿼리문 구성
SELECT *
FROM TBL_SCORE
ORDER BY 1;
--> 한 줄로 바꾸기
SELECT * FROM TBL_SCORE ORDER BY 1;
