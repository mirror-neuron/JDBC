/*=======================
    ScoreMain.java
========================*/

/*
○ 성적 처리 → 데이터베이스 연동(데이터베이스 연결 및 액션 처리)
            	ScoreDTO 클래스 활용(속성만 존재하는 클래스, getter / setter 구성)
            	ScoreDAO 클래스 활용(데이터베이스 액션 처리)
            	Process 클래스 활용(단위 기능 구성)
            
여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
총점, 평균, 석차 등을 계산하여 출력하는 프로그램 구현.
※ 서브 메뉴 구성 → Process 클래스 활용.

실행 예)

a

7번 학생 성적 입력(이름 국어 영어 수학) : 이원영 50 60 70
8번 학생 성적 입력(이름 국어 영어 수학) : 김선아 80 80 80
9번 학생 성적 입력(이름 국어 영어 수학) : .

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 2

전체 인원 : 8명
번호 이름 국어 영어 수학 총점 평균 석차
 1
 2
 3
 4
 5               ...
 6
 7
 8
 
====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : -1

프로그램이 종료되었습니다.

*/


package com.test;

import java.util.Scanner;

public class ScoreMain
{
	public static void main(String[] args)
	{
		ScoreProcess prc = new ScoreProcess();
		Scanner sc = new Scanner(System.in);
		
		do
		{
			System.out.println();
			System.out.println("====[ 성적 처리 ]====");
			System.out.println("1. 성적 입력");
			System.out.println("2. 성적 전체 출력");
			System.out.println("3. 이름 검색 출력");
			System.out.println("4. 성적 수정");
			System.out.println("5. 성적 삭제");
			System.out.println("=====================");
			System.out.print(">> 선택(1~5, -1종료) :");
			String menus = sc.next();
			
			try
			{
				int menu = Integer.parseInt(menus);
				
				if (menu == -1)
				{
					System.out.println();
					System.out.println("프로그램이 종료되었습니다.");
					return;
				}
				
				switch (menu)
				{
					case 1:
						// 성적 입력 기능 수행
						prc.sungjukInsert();
						break;
					case 2:
						// 성적 전체 출력 기능 수행
						prc.sungjukSelctAll();
						break;
					case 3:
						// 이름 검색 출력 기능 수행
						prc.sungjukSearchName();
						break;
					case 4:
						// 성적 수정 기능 수행
						prc.sungjukUpdate();
						break;
					case 5:
						// 성적 삭제 기능 수행
						prc.sungjukDelete();
						break;
				}
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		} while (true);
		
	
	}
}


//실행결과
/*
 
====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 1

2번 학생 성적 입력(이름 국어 영어 수학) : 박길동 88 77 99
성적 입력이 완료되었습니다.

3번 학생 성적 입력(이름 국어 영어 수학) : 임길동 58 90 67
성적 입력이 완료되었습니다.

4번 학생 성적 입력(이름 국어 영어 수학) : 오길동 70 80 88
성적 입력이 완료되었습니다.

5번 학생 성적 입력(이름 국어 영어 수학) : -1
.
java.util.InputMismatchException

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 2

전체 인원: 4명
번호 이름 국어 영어 수학 총점 평균 석차
  1  고길동   10    10    10    30  10.0     4
  2  박길동   88    77    99   264  88.0     1
  3  임길동   58    90    67   215  71.7     3
  4  오길동   70    80    88   238  79.3     2

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 3
검색할 이름 입력 : 오길동
번호 이름 국어 영어 수학 총점 평균 석차
  4  오길동   70    80    88   238  79.3     2

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 4
수정할 번호를 입력하세요 : 2
번호 이름 국어 영어 수학 총점 평균 석차
  2  박길동   88    77    99   264  88.0     1

수정 데이터 입력(이름 국어 영어 수학) : 조길동 90 88 79
수정이 완료되었습니다.

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 2

전체 인원: 4명
번호 이름 국어 영어 수학 총점 평균 석차
  1  고길동   10    10    10    30  10.0     4
  2  조길동   90    88    79   257  85.7     1
  3  임길동   58    90    67   215  71.7     3
  4  오길동   70    80    88   238  79.3     2

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 5
삭제할 번호를 입력하세요 : 4
번호 이름 국어 영어 수학 총점 평균 석차
  4  오길동   70    80    88   238  79.3     2
>> 정말 삭제하시겠습니까?(Y/N) : n
취소되었습니다.

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 3
검색할 이름 입력 : 구길동
검색 결과가 존재하지 않습니다.

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 5
삭제할 번호를 입력하세요 : 5
삭제할 대상이 존재하지 않습니다.

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 4
수정할 번호를 입력하세요 : 4
번호 이름 국어 영어 수학 총점 평균 석차
  4  오길동   70    80    88   238  79.3     2

수정 데이터 입력(이름 국어 영어 수학) : 오길동 77 50 99 
수정이 완료되었습니다.

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 5
삭제할 번호를 입력하세요 : 4
번호 이름 국어 영어 수학 총점 평균 석차
  4  오길동   77    50    99   226  75.3     2
>> 정말 삭제하시겠습니까?(Y/N) : Y
삭제가 완료되었습니다.

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : 2

전체 인원: 3명
번호 이름 국어 영어 수학 총점 평균 석차
  1  고길동   10    10    10    30  10.0     3
  2  조길동   90    88    79   257  85.7     1
  3  임길동   58    90    67   215  71.7     2

====[ 성적 처리 ]====
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
=====================
>> 선택(1~5, -1종료) : -1

프로그램이 종료되었습니다.
*/
