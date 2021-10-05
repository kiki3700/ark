package com.example.demo.util;
/*
 * 문서 작성자 : 이성현
 */
//싸이보스 연결과 요청에 관한 문서
//주 메서드 
public class CybosConnection {
	private dashin.cputil.ICpCybos cybos = dashin.cputil.ClassFactory.createCpCybos();
	public int connectionCheck() {
		return cybos.isConnect();
		// return값 0 연결 끊김, 1: cybosplus 써버 2: hts서버
		//=> 1이 나와여 조회나 트레이딩 가능
	}
	
	
	public void runCybos(String id, String pwd, String pwdcert) {
		  try {
			    Process p = Runtime.getRuntime().exec("runas C:\\Daishin\\Starter\\ncStarter.exe /id:"+id+"/pwd:"+pwd+"/pwdcert:"+pwdcert+"/prj:cp /autostart");

			  } catch (Exception e) {
			    System.err.println(e);
			  }
		}
}
