package com.example.demo.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunCybos {
	public void runCybos(String id, String pwd, String pwdcert) {
	  try {
		    Process p = Runtime.getRuntime().exec("runas C:\\Daishin\\Starter\\ncStarter.exe /id:"+id+"/pwd:"+pwd+"/pwdcert:"+pwdcert+"/prj:cp /autostart");

		  } catch (Exception e) {
		    System.err.println(e);
		  }
	}

}
