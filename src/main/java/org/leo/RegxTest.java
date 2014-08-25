package org.leo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class RegxTest {
	
	private ThreadPoolExecutor executor;
	
	public void init(){
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
	    executor = new ThreadPoolExecutor(1000, 1000, 0L, TimeUnit.SECONDS, workQueue);
	    executor.allowCoreThreadTimeOut(false);
	}
	
	public void start(){
		List<String> mailList = new ArrayList<String>();
		for(int i = 1; i <= 30000; i++){
			//String ss = RandomStringUtils.random(5, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ") + RandomStringUtils.random(1, "._-/") + RandomStringUtils.random(10, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
			String ss = RandomStringUtils.random(5, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ") + RandomStringUtils.random(1, "._-/") + RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ") + RandomStringUtils.random(1, "._-/") + RandomStringUtils.random(10, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
			mailList.add(ss);
		}
		int size = mailList.size();
		for(int i = 0; i < size; i++){			
			executor.execute(new Worker(StringUtils.leftPad("" + i, 5, "0"), mailList.get(i)));
		}
	}
	
	public static void main(String[] args) {
		/**
		String mail = "sss-leo.li//sd_s";
		String ss = mail.replaceAll("[.-/_]", "");
		System.out.println(ss);
		
		String mail = "sssleo.lisdksusd";
		for (int i = 1000; i < 2000; ++i){
			Thread t = new Thread(new Worker(String.valueOf(i), mail));
			t.start();
		}
		
		RegxTest test = new RegxTest();
		test.init();
		test.start();
		*/
		System.out.println(RandomStringUtils.random(3, "0123456789"));
	}
	
	
}

class Worker implements Runnable {
	private String name;
	private String mail;
	
	public Worker(String name, String mail){
		this.name = name;
		this.mail = mail;
	}

	@Override
	public void run() {
		String ss = mail.replaceAll("[.-//_]", "");
		System.out.println(name + ": " + mail + "--->" + ss);
	}
}



