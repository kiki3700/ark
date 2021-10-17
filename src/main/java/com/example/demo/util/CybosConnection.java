package com.example.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	public void connectionCheck() throws IOException {
		int connetcionStatus = cybos.isConnect();
		if(connetcionStatus==0) {
			//연결 안됨 => 로그인 구현 후 로그인으로
			CybosConnection cyCon = new CybosConnection();
			cyCon.runCybos();
		}else if(connetcionStatus==1) {
			//시세 조회만 가능
		}else {
			//다 연결 걍 하면 된다.
		}
		// return값 0 연결 끊김, 1: cybosplus 써버 2: hts서버
		//=> 1이 나와여 조회나 트레이딩 가능
	}
	
	
	public void runCybos() throws IOException {
		String[] command = new String[] {"C:\\DAISHIN\\STARTER\\ncStarter.exe","/prj:cp","/id:kiki3700","/pwd:Lo50!@","/pwdcert:Lolo5050!@", "/autostart"};
		Process process = new ProcessBuilder(command).start();
		InputStream is = process.getInputStream();//Get an inputstream from the process which is being executed
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
		System.out.println(line);//Prints all the outputs.Which is coming from the executed Process
		}
	}
}
