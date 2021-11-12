package com.example.demo.data.industry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import dashin.cputil.ClassFactory;
import dashin.cputil.ICpCodeMgr;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndustryNameTest {
	@Test
	public void main() {
		ICpCodeMgr codeMgr = ClassFactory.createCpCodeMgr();
		Object[] a = (Object[]) codeMgr.getIndustryList();
		for(int i = 0; i<a.length;i++) {
			System.out.println(a[i]);
			System.out.println(codeMgr.getIndustryName((String) a[i]));
		}
	}
}
