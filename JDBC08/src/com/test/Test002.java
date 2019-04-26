/*======================================================
 	Test002.java
 	- CallableStatement 를 활용한 SQL 구문 전송 실습2
======================================================*/

package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

public class Test002
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn!=null)
			{
				System.out.println("데이터베이스 연결 성공~!!!");
				
				try
				{
					String sql = "{call PRC_MEMBERSELECT(?)}";
					CallableStatement cstmt = conn.prepareCall(sql);
					
					// check~!!!
					// 프로시저 내부에서 sys_refcursor 를 사용하고 있기 때문에
					// OrcaleTypes.CURSOR 를 사용하기 위한 등록 과정이 필요한 상황.
					// 1. Project Explorer 상에서 해당 프로젝트 마우스 우클릭
					//    > Build Path > Configure Build Path > Libraries 탭 선택
					//    > 『ojdbc6.jar』 또는 『ojdbc14.jar』파일 추가 등록
					//    (외부 jar 파일 연결)
					// 2. 『import oracle.jdbc.OracleTypes;』 구문 추가
					
					// check~!!!				
					cstmt.registerOutParameter(1, OracleTypes.CURSOR);
					cstmt.execute();
					ResultSet rs = (ResultSet)cstmt.getObject(1);
					
					while (rs.next())
					{
						String sid = rs.getString("SID");
						String name = rs.getString("NAME");
						String tel = rs.getString("TEL");
						
						String str = String.format("%3s %7s %10s", sid, name, tel);
						
						System.out.println(str);
					}
					rs.close();
					cstmt.close();
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
			DBConn.close();
			System.out.println("\n데이터베이스 연결 닫힘~!!!!");
			System.out.println("프로그램 종료됨~!!!");

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}

/*
 데이터베이스 연결 성공~!!!
  1     전훈의 010-1111-1111
  2     유진석 010-2222-2222
  3     최보라 010-3333-3333
  4     이지혜 010-1212-1212
  5     이지혜 010-1212-1212
  6     이기승 010-9090-9090
  7     윤희진 010-7777-7777
  8     조수연 010-8888-8888
  9     조목연 010-9999-9999
 10     조금연 010-9876-9876
 11     유진석 010-1111-1111
 12     가나다 010-1111-1111
 13     두울리 010-9898-9898

데이터베이스 연결 닫힘~!!!!
프로그램 종료됨~!!!
 */
 