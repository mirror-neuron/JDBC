/*============================================
  MemberProcess.java
  - 콘솔 기반 서브 메뉴 입출력 전용 클래스
=============================================*/

package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberProcess
{
	// MemberDAO 타입으로 dao 주요 변수 선언
	private MemberDAO dao;
	
	// 생성자 정의
	public MemberProcess()
	{
		dao = new MemberDAO();
	}
	
	// 직원 정보 입력 메소드 정의
	public void memberInsert()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			// 지역 리스트 구성
			ArrayList<String> citys = dao.searchCity();
			StringBuilder cityStr = new StringBuilder();
			for (String city : citys)
			{
				cityStr.append(city + "/"); 
			}
			
			// 부서 리스트 구성
			ArrayList<String> buseos = dao.searchBuseo();
			StringBuilder buseoStr = new StringBuilder();
			for (String buseo : buseos)
			{
				buseoStr.append(buseo + "/");
			}
			
			// 직위 리스트 구성
			ArrayList<String> jikwis = dao.searchJikwi();
			StringBuilder jikwiStr = new StringBuilder();
			for (String jikwi : jikwis)
			{
				jikwiStr.append(jikwi + "/");
			}
			
			// 사용자에게 보여지는 화면 처리
			/*
			직원 정보 입력 -------------------------------------------------------------
			이름 : 김정규 --5.
			주민등록번호(yymmdd-nnnnnnn) : 900123-1234567
			입사일(yyyy-mm-dd) : 2019-02-12
			지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북) : 경기 --1.
			전화번호 : 010-1111-1111
			부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부) : 개발부 --2.
			직위(사장/전무/상무/이사/부장/차장/과장/대리/사원) : 사원 --3.
			기본급(최소 840000원 이상) : 850000 --4.
			수당 : 2000000
			
			직원 정보 입력 완료~!!!
			----------------------------------------------------------------------------
			 */
			
			System.out.println();
			System.out.println("직원 정보 입력 -------------------------------------------------------------");
			System.out.print("이름 : ");
			String empName = sc.next();
			System.out.print("주민등록번호(yymmdd-nnnnnnn) : ");
			String ssn = sc.next();
			System.out.print("입사일(yyyy-mm-dd) : ");
			String ibsaDate = sc.next();
			System.out.printf("지역(%s) : ", cityStr.toString());
			String cityLoc = sc.next();
			System.out.print("전화번호 : ");
			String tel = sc.next();
			System.out.printf("부서(%s) : ", buseoStr.toString());
			String buseoName = sc.next();
			System.out.printf("직위(%s) : ", jikwiStr.toString());
			String jikwiName = sc.next();
			System.out.printf("기본급(최소 %d원 이상) : ", dao.searchBasicPay(jikwiName));
			int basicPay = sc.nextInt();
			System.out.print("수당 : ");
			int sudang = sc.nextInt();
			System.out.println();
			
			// MemberDTO 구성
			MemberDTO dto = new MemberDTO();
			dto.setEmpName(empName); 
			dto.setSsn(ssn);
			dto.setIbsaDate(ibsaDate);
			dto.setCityLoc(cityLoc);
			dto.setTel(tel);
			dto.setBuseoName(buseoName);
			dto.setJikwiName(jikwiName);
			dto.setBasicpay(basicPay);
			dto.setSudang(sudang);
			
			// 적용된 행의 갯수 확인하여 결과 출력
			int result = dao.add(dto);
			
			if (result>0)
				System.out.println("직원 정보 입력 완료~!!!");
			
			System.out.println("----------------------------------------------------------------------------");	
		
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally // 오류가 나든 안나든 다 수행하게 해주는 것
		{
			try
			{
				dao.close();
			} catch (Exception e2)
			{
				System.out.println(e2.toString());
			}
			
		}//end memberInsert()
	}
	
	// 직원 전체 출력 메소드 정의
	public void memberLists()
	{
		Scanner sc = new Scanner(System.in);
		
		// 서브 메뉴 출력
		System.out.println();
		System.out.println("1. 사번 정렬"); // EMP_ID
		System.out.println("2. 이름 정렬");	// EMP_NAME
		System.out.println("3. 부서 정렬");	// BUSEO_NAME
		System.out.println("4. 직위 정렬");	// JIKWI_NAME
		System.out.println("5. 급여 내림차순 정렬");	// PAY DESC
		System.out.print(">> 항목 선택(1~5, -1종료) : ");
		String menuStr = sc.next();
		
		try
		{
			int menu = Integer.parseInt(menuStr);
			if (menu==-1)
				return;
			
			String key = "";
			switch (menu)
			{
			case 1: 
				key = "EMP_ID";		
				break;
			case 2: 
				key = "EMP_NAME";
				break;
			case 3: 
				key = "BUSEO_NAME";
				break;
			case 4: 
				key = "JIKWI_NAME";
				break;
			case 5: 
				key = "PAY DESC";
				break;
			}
			
			// 데이터베이스 연결
			dao.connection();
			
			// 직원 리스트 출력
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", dao.memberCount());
			System.out.println("사번 이름 주민번호 입사일 지역 전화번호 부서 직위 기본급 수당 급여");
			ArrayList<MemberDTO> memList = dao.lists(key);
			for (MemberDTO memberDTO : memList)
			{
				System.out.printf("%d %s %s %s %s %s %s %s %d %d %d\n"
						, memberDTO.getEmpId(), memberDTO.getEmpName()
						, memberDTO.getSsn(), memberDTO.getIbsaDate()
						, memberDTO.getCityLoc(), memberDTO.getTel()
						, memberDTO.getBuseoName(), memberDTO.getJikwiName()
						, memberDTO.getBasicpay(), memberDTO.getSudang()
						, memberDTO.getPay());
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}//end memberLists()
	
	
	// 직원 검색 출력 메소드 정의
	public void memberSearch()
	{
		Scanner sc = new Scanner(System.in);
		
		// 서브 메뉴 구성
		System.out.println();
		System.out.println("1. 사번 검색"); // EMP_ID
		System.out.println("2. 이름 검색");	// EMP_NAME
		System.out.println("3. 부서 검색");	// BUSEO_NAME
		System.out.println("4. 직위 검색");	// JIKWI_NAME 
		System.out.print(">>항목 선택(1~4, -1종료) : ");
		String menuStr = sc.next();
		
		try
		{
			int menu = Integer.parseInt(menuStr);
			if (menu==-1)
				return;
			
			String key = "";
			String value = "";
			
			switch (menu)
			{
			case 1:
				key = "EMP_ID";
				System.out.print("검색할 사원번호 입력 : ");
				value = sc.next();
				break;
			case 2:
				key = "EMP_NAME";
				System.out.print("검색할 사원이름 입력 : ");
				value = sc.next();
				break;
			case 3:
				key = "BUSEO_NAME";
				System.out.print("검색할 부서이름 입력 : ");
				value = sc.next();
				break;
			case 4:
				key = "JIKWI_NAME";
				System.out.print("검색할 직위이름 입력 : ");
				value = sc.next();
				break;
			}
			
			// 데이터베이스 연결
			dao.connection();
			
			// 검색 결과 출력
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", dao.memberCount(key, value));
			System.out.println("사번 이름 주민번호 입사일 지역 전화번호 부서 직위 기본급 수당 급여");
			ArrayList<MemberDTO> memList = dao.searchLists(key, value);
			for (MemberDTO memberDTO : memList)
			{
				System.out.printf("%d %s %s %s %s %s %s %s %d %d %d\n"
						, memberDTO.getEmpId(), memberDTO.getEmpName()
						, memberDTO.getSsn(), memberDTO.getIbsaDate()
						, memberDTO.getCityLoc(), memberDTO.getTel()
						, memberDTO.getBuseoName(), memberDTO.getJikwiName()
						, memberDTO.getBasicpay(), memberDTO.getSudang()
						, memberDTO.getPay());
			}
			

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally // 오류가 나든 안나든 다 수행하게 해주는 것
		{
			try
			{
				dao.close();
			} catch (Exception e2)
			{
				System.out.println(e2.toString());
			}
			
		}
		
		
	}//end memberSearch()
	
	// 직원 정보 수정 메소드 정의
	public void memberUpdate() 
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			// 수정할 대상 입력받기
			System.out.print("수정할 직원의 사원번호 입력 : ");
			String value = sc.next();
			
			// 데이터베이스 연결 
			dao.connection();
			
			ArrayList<MemberDTO> members = dao.searchLists("EMP_ID", value);
			
			if (members.size() > 0)
			{
				// 지역 리스트 구성
				ArrayList<String> citys = dao.searchCity();
				StringBuilder cityStr = new StringBuilder();
				for (String city : citys)
					cityStr.append(city + "/"); 
				
				// 부서 리스트 구성
				ArrayList<String> buseos = dao.searchBuseo();
				StringBuilder buseoStr = new StringBuilder();
				for (String buseo : buseos)
					buseoStr.append(buseo + "/");
				
				// 직위 리스트 구성
				ArrayList<String> jikwis = dao.searchJikwi();
				StringBuilder jikwiStr = new StringBuilder();
				for (String jikwi : jikwis)
					jikwiStr.append(jikwi + "/");
				
				MemberDTO mMember = members.get(0);
				int mEmpId = mMember.getEmpId();
				String mEmpName = mMember.getEmpName();
				String mSsn = mMember.getSsn();
				String mIbsaDate = mMember.getIbsaDate();
				String mCityLoc = mMember.getCityLoc();
				String mTel = mMember.getTel();
				String mBuseoName = mMember.getBuseoName();
				String mJikwiName = mMember.getJikwiName();
				int mBasicPay = mMember.getBasicpay();
				int mSudang = mMember.getSudang();
				
				System.out.println();
				System.out.println("직원 정보 수정--------------------------------------------");
				System.out.printf("기존 이름 : %s%n", mEmpName);
				System.out.print("수정 이름 : ");
				String empName = sc.next();
				if (empName.equals("-"))
					empName = mEmpName;
				System.out.printf("기존 주민번호(yymmdd-nnnnnnn) : %s%n", mSsn);
				System.out.print("수정 주민번호(yymmdd-nnnnnnn) : ");
				String ssn = sc.next();
				if (ssn.equals("-"))
					ssn = mSsn;
				System.out.printf("기존 입사일(YYYY-MM-DD) : %s%n", mIbsaDate);
				System.out.print("수정 입사일(YYYY-MM-DD) : ");
				String ibsaDate = sc.next();
				if (ibsaDate.equals("-"))
					ibsaDate = mIbsaDate;
				System.out.printf("기존 지역 : %s%n", mCityLoc);
				System.out.printf("수정 지역(%s) : ", cityStr.toString());
				String cityLoc = sc.next();
				if (cityLoc.equals("-"))
					cityLoc = mCityLoc;
				System.out.printf("기존 전화번호 : %s%n", mTel);
				System.out.print("수정 전화번호 : ");
				String tel = sc.next();
				if (tel.equals("-"))
					tel = mTel;
				System.out.printf("기존 부서 : %s%n", mBuseoName);
				System.out.printf("수정 부서(%s) : ",buseoStr.toString());
				String buseoName = sc.next();
				if (buseoName.equals("-"))
					buseoName = mBuseoName;
				System.out.printf("기존 직위 : %s%n", mJikwiName);
				System.out.printf("수정 직위(%s) : ",jikwiStr.toString());
				String jikwiName = sc.next();
				if (jikwiName.equals("-"))
					jikwiName = mJikwiName;
				
				System.out.printf("기존 기본급 : %s%n", mBasicPay);
				System.out.printf("수정 기본급(최소 %d원 이상) : ", dao.searchBasicPay(jikwiName));
				String basicPayStr = sc.next();
				int basicPay = 0;
				if (basicPayStr.equals("-"))
					basicPay = mBasicPay;
				else 
					Integer.parseInt(basicPayStr);
				
				System.out.printf("기존 수당 : %d%n", mSudang);
				System.out.print("수정 수당 : ");
				String sudangStr = sc.next();
				int sudang = 0;
				if (sudangStr.equals("-"))
					sudang = mSudang;
				else 
					Integer.parseInt(sudangStr);
				
				// MemberDTO 생성 및 구성
				MemberDTO member = new MemberDTO();
				/*
				member.setEmpId(mEmpId);  
				member.setEmpName(mEmpName);
				member.setSsn(mSsn);
				member.setIbsaDate(mIbsaDate);
				member.setCityLoc(mCityLoc);
				member.setTel(mTel);
				member.setBuseoName(mBuseoName);
				member.setJikwiName(mJikwiName);
				member.setBasicpay(mBasicPay);
				member.setSudang(mSudang);
				*/
				member.setEmpId(mEmpId);
				member.setEmpName(empName);
				member.setSsn(ssn);
				member.setIbsaDate(ibsaDate);
				member.setCityLoc(cityLoc);
				member.setTel(tel);
				member.setBuseoName(buseoName);
				member.setJikwiName(jikwiName);
				member.setBasicpay(basicPay);
				member.setSudang(sudang);
				
				
				
				System.out.println(mSsn);
				
				int result = dao.modify(member);
				if(result >0)
					System.out.println("직원정보 수정 완료~!!!");		
				
				System.out.println("----------------------------------------------------------");
				
			} else
			{
				System.out.println("수정 대상을 검색하지 못했습니다.");
			}
						
			
			/*	
			 각자 추가로 작성한 내용	
		 	// 지역 리스트 구성
			ArrayList<String> citys = dao.searchCity();
			StringBuilder cityStr = new StringBuilder();
			for (String city : citys)
			{
				cityStr.append(city + "/"); 
			}	
			// 부서 리스트 구성
			ArrayList<String> buseos = dao.searchBuseo();
			StringBuilder buseoStr = new StringBuilder();
			for (String buseo : buseos)
			{
				buseoStr.append(buseo + "/");
			}
			
			// 직위 리스트 구성
			ArrayList<String> jikwis = dao.searchJikwi();
			StringBuilder jikwiStr = new StringBuilder();
			for (String jikwi : jikwis)
			{
				jikwiStr.append(jikwi + "/");
			}
			
			System.out.println();
			System.out.println("직원 정보 수정 -------------------------------------------------------------");
			System.out.printf("이름 : %s", members.get(0).getEmpName());
			System.out.println("수정 이름 : ");
			String empName = sc.next();
			if (empName.equals("-"))
				empName = members.get(0).getEmpName();
			System.out.printf("주민등록번호(yymmdd-nnnnnnn) : %s", members.get(0).getSsn());
			System.out.println("수정 주민등록번호 : ");
			String ssn = sc.next();
			if (ssn.equals("-"))
				ssn = members.get(0).getSsn();
			System.out.printf("입사일(yyyy-mm-dd) : %s", members.get(0).getIbsaDate());
			System.out.println("수정 입사일 : ");
			String ibsaDate = sc.next();
			if (ibsaDate.equals("-"))
				ibsaDate = members.get(0).getIbsaDate();
			System.out.printf("지역(%s) : %s", cityStr.toString(), members.get(0).getCityLoc());
			String cityLoc = sc.next();
			if (cityLoc.equals("-"))
				cityLoc = members.get(0).getCityLoc();
			System.out.printf("전화번호 : %s", members.get(0).getTel());
			String tel = sc.next();
			if (tel.equals("-"))
				tel = members.get(0).getTel();
			System.out.printf("부서(%s) : %s", buseoStr.toString(), members.get(0).getBuseoName());
			String buseoName = sc.next();
			if (buseoName.equals("-"))
				buseoName = members.get(0).getBuseoName();
			System.out.printf("직위(%s) : %s", jikwiStr.toString(), members.get(0).getJikwiName());
			String jikwiName = sc.next();
			if (jikwiName.equals("-"))
				jikwiName = members.get(0).getJikwiName();
			System.out.printf("기본급(최소 %d원 이상) : ", dao.searchBasicPay(jikwiName), members.get(0).getBasicpay());
			String basicPay = sc.next();
			if (basicPay.equals("-"))
				basicPay = Integer.toString(members.get(0).getBasicpay()); 
			System.out.printf("수당 : %s", members.get(0).getSudang());
			String sudang = sc.next();
			if (sudang.equals("-"))
				sudang = Integer.toString(members.get(0).getSudang());
			System.out.println();
			
			
			// MemberDTO 구성
			MemberDTO dto = new MemberDTO();
			dto.setEmpName(empName);  
			dto.setIbsaDate(ibsaDate);
			dto.setCityLoc(cityLoc);
			dto.setTel(tel);
			dto.setBuseoName(buseoName);
			dto.setJikwiName(jikwiName);
			dto.setBasicpay(Integer.parseInt(basicPay));
			dto.setSudang(Integer.parseInt(sudang));
			
			// 적용된 행의 갯수 확인하여 결과 출력
			int result = dao.add(dto);
			
			if (result>0)
				System.out.println("직원 정보 입력 완료~!!!");
			
			System.out.println("----------------------------------------------------------------------------");	
			*/

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
				
			} catch (Exception e2)
			{
				System.out.println(e2.toString());
			}
		}
	}//end memberUpdate()
	
	
	// 직원 정보 삭제 메소드 정의
	
}












