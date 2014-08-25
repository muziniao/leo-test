package org.leo.thread.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.time.DateFormatUtils;

public class MyTimerTask extends TimerTask{
	
	private String name;
	
	private Timer timer;

	public MyTimerTask(String name){
		this.name = name;
	}
	
	public MyTimerTask(String name, Timer timer){
		this.name = name;
		this.timer = timer;
	}

	@Override
	public void run() {
		try {
			System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : "  + name + " start......");
			Thread.sleep(1000 * 2);
			if(timer != null){
				timer.cancel();
				System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : "  + name + " cancel the timer......");
			}
			if(name.equals("task---4")){
				System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : "  + name + " RuntimeException......");
				//String aa = null;
				//aa.trim();
				//throw new RuntimeException(name + " RuntimeException");
			}
			System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : "  + name + " end......");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
