SELECT USER
FROM DUAL;
--==>> SCOTT

SELECT *
FROM TBL_MEMBER;
--==>> 
/*
1	전훈의	010-1111-1111
2	유진석	010-2222-2222
3	최보라	010-3333-3333
*/


--○ 데이터 입력 쿼리문 구성
INSERT INTO TBL_MEMBER(SID, NAME, TEL)
VALUES(MEMBERSEQ.NEXTVAL, '이지혜', '010-1212-1212');
--> 한 줄 구성
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '이지혜', '010-1212-1212')
;
--==>> 1 행 이(가) 삽입되었습니다.

COMMIT;
--==>> 커밋 완료.

-- Statement 작업 객체 사용
SELECT *
FROM TBL_MEMBER;
--==>>
/*
5	이지혜	010-1212-1212
1	전훈의	010-1111-1111
2	유진석	010-2222-2222
3	최보라	010-3333-3333
4	이지혜	010-1212-1212
*/

-- Statement 작업 객체 사용(SQL 위치 상단으로 변경 후)
SELECT *
FROM TBL_MEMBER;
--==>>
/*
5	이지혜	010-1212-1212
6	이기승	010-9090-9090
1	전훈의	010-1111-1111
2	유진석	010-2222-2222
3	최보라	010-3333-3333
4	이지혜	010-1212-1212
*/

-- PreparedStatement 작업 객체 사용
SELECT *
FROM TBL_MEMBER;
--==>>
/*
5	이지혜	010-1212-1212
6	이기승	010-9090-9090
7	윤희진	010-7777-7777
1	전훈의	010-1111-1111
2	유진석	010-2222-2222
3	최보라	010-3333-3333
4	이지혜	010-1212-1212
*/

-- PreparedStatement 작업 객체 사용(사용자 입력값 받아서 입력)
SELECT *
FROM TBL_MEMBER;
--==>>
/*
5	이지혜	010-1212-1212
6	이기승	010-9090-9090
7	윤희진	010-7777-7777
8	조수연	010-8888-8888
9	조목연	010-9999-9999
10	조금연	010-9876-9876
1	전훈의	010-1111-1111
2	유진석	010-2222-2222
3	최보라	010-3333-3333
4	이지혜	010-1212-1212
*/


--○ 데이터 전체 조회 쿼리문 구성
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--> 한 줄 구성
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;