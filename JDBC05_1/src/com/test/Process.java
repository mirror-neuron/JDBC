package com.test;

import java.sql.SQLException;
import java.util.Scanner;

public class Process
{
	/*
	 * 직원 정보 입력 ------------------------------------ 이름 : 김정규 주민등록번호(YYMMDD-NNNNNNN)
	 * : 900123-1234567 입사일 (YYYY-MM-DD) : 2019-02-12
	 * 지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북) : 전화번호 : ~~~~ 부서
	 * (개발부/기획부/영업부/인사부/자재부/총무부/홍보부) : 개발부 직위 (......) : 사원 기본급(최소 840000 이상) :
	 * 850000 수당 : 2000000
	 * 
	 * 직원 정보 입력 완료 ~!!! -----------------------------------------------------------
	 * 
	 */

	private MemberDAO dao;

	public Process()
	{

		dao = new MemberDAO();
	}

	/*
	 * public void insertInformation() {
	 * 
	 * Scanner sc = new Scanner(System.in);
	 * 
	 * dao.connection();
	 * 
	 * System.out.println("직원 정보 입력 ------------------------------------");
	 * System.out.print(" 이름 : ");
	 * 
	 * String name = sc.next();
	 * 
	 * System.out.print("주민등록번호(yymmdd-nnnnnnn) : "); String ssn = sc.next();
	 * 
	 * System.out.print("입사일 (YYYY-MM-DD) : "); String ibsadate = sc.next();
	 * 
	 * System.out.printf("지역 : "); String city = sc.next();
	 * 
	 * System.out.print("전화번호 : "); String tel = sc.next();
	 * 
	 * System.out.print("부서 : "); String buseo = sc.next();
	 * 
	 * System.out.print("직위 : "); String jikwi = sc.next();
	 * 
	 * System.out.print("기본급(최소 840000 이상) : "); int basicpay = sc.nextInt();
	 * 
	 * System.out.print("수당 : "); int sudang = sc.nextInt();
	 * 
	 * MemberDTO dto = new MemberDTO();
	 * 
	 * dto.setName(name); dto.setSsn(ssn); dto.setIbsadate(ibsadate);
	 * dto.setCity(city); dto.setTel(tel); dto.setBuseo(buseo); dto.setJikwi(jikwi);
	 * dto.setBasicpay(basicpay); dto.setSudang(sudang);
	 * 
	 * int result = dao.add(dto);
	 * 
	 * }
	 */

	public void searchAll() throws SQLException
	{

		Scanner sc = new Scanner(System.in);

		dao.connection();

		System.out.println("1. 사번 정렬         ");
		System.out.println("2. 이름 정렬         ");
		System.out.println("3. 부서 정렬         ");
		System.out.println("4. 직위 정렬         ");
		System.out.println("5. 급여 내림차순 정렬    ");
		System.out.print(">> 선택(1~5 -1 종료) : ");

		int num = sc.nextInt();

		if (num == -1)
		{
			return;

		}

		System.out.println("=========================================");
		System.out.printf("전체인원 : %d명\n", dao.count());
		System.out.println("=========================================");
		System.out.println("사번 	이름 	주민번호 	입사일 	지역 	전화번호  부서 	직위 	기본급 	수당 	급여");
		for (MemberDTO dto : dao.lists(num))

		{
			System.out.printf("%3d %7s %12s %12s %12s %12s %12s %10s %10d %10d %10d %n", dto.getEMP_ID(), dto.getEMP_NAME(),
					dto.getSSN(), dto.getIBSADATE(), dto.getCITY_LOC(), dto.getTEL(), dto.getBUSEO_NAME(), dto.getJIKWI_NAME(),
					dto.getMIN_BASICPAY(), dto.getSUDANG(), dto.getBASICPAY());

		}
		// dao 의 close()메소드 호출 -> 데이터베잇 연결 종료
		try
		{
			dao.close();
		} catch (SQLException e)
		{
			System.out.println(e.toString());

		}
		System.out.println("-----------------------------");
		sc.close();

	}

	private void extracted()
	{
		return;
	}

	public void searchSelect() throws SQLException
	{
		Scanner sc = new Scanner(System.in);

		dao.connection();

		System.out.println("1. 사번 검색         ");
		System.out.println("2. 이름 검색         ");
		System.out.println("3. 부서 검색         ");
		System.out.println("4. 직위 검색         ");

		System.out.print(">> 선택(1~4 -1 종료) : ");

		int num = sc.nextInt();

		String cmd = null;

		switch (num)
		{
		case 1:
			System.out.print("\n >> 사번 입력 : ");
			cmd = sc.next();

			break;
		case 2:
			System.out.print("\n >> 이름 입력 : ");
			cmd = sc.next();

			break;
		case 3:
			System.out.print("\n >> 부서 입력 : ");
			cmd = sc.next();

			break;
		case 4:
			System.out.print("\n >> 직위 입력 : ");
			cmd = sc.next();

			break;

		default:
			System.out.println("다시 입력하세요");
			break;
		}

		for (MemberDTO dto : dao.lists(num,cmd))

		{
			System.out.printf("%3d %7s %12s %12s %12s %12s %12s %10s %10d %10d %10d %n", dto.getEMP_ID(), dto.getEMP_NAME(),
					dto.getSSN(), dto.getIBSADATE(), dto.getCITY_LOC(), dto.getTEL(), dto.getBUSEO_NAME(), dto.getJIKWI_NAME(),
					dto.getMIN_BASICPAY(), dto.getSUDANG(), dto.getBASICPAY());

		}
		sc.close();
	}

}