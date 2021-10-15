package com.example.demo.util;

import org.springframework.stereotype.Component;
import dashin.cputil.*;
/*
 * 문서 작성자 : 이성현
 */
//싸이보스 연결과 요청에 관한 문서
//주 메서드
@Component
public class CybosConnection {
	
	
	
	private ICpCybos cybos = ClassFactory.createCpCybos();
	public void connectionCheck() {
		int connetcionStatus = cybos.isConnect();
		if(connetcionStatus==0) {
			//연결 안됨 => 로그인 구현 후 로그인으로
		}else if(connetcionStatus==1) {
			//시세 조회만 가능
		}else {
			//다 연결 걍 하면 된다.
		}
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
