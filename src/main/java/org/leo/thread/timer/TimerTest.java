package org.leo.thread.timer;

import java.util.Date;
import java.util.Timer;

import org.apache.commons.lang3.time.DateFormatUtils;

public class TimerTest {
	
	public static void main(String[] args) throws InterruptedException {
		TimerTest test = new TimerTest();
		test.test1();
	}
	
	
	public void test1() {
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : " + "the main thread started.... ");
		Timer timer = new Timer(false);
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : " + "the timer inited.... ");
		timer.schedule(new MyTimerTask("task---1"), 1000 * 1);
		timer.schedule(new MyTimerTask("task---2"), 1000 * 1);
		timer.schedule(new MyTimerTask("task---3"), 1000 * 1);
		timer.schedule(new MyTimerTask("task---4"), 1000 * 1);
		timer.schedule(new MyTimerTask("task---5"), 1000 * 1);
		timer.schedule(new MyTimerTask("task---6"), 1000 * 1);
		timer.schedule(new MyTimerTask("task---7"), 1000 * 1);
		//timer.schedule(new MyTimerTask("CancelTimerTask",timer), 1000 * 12);
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : "  + "the timer scheduled.... ");
		//timer.cancel();
		//System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : "  + "the timer canceled.... ");
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : " + "the main thread end.... ");
	}
	
	
	public void test2() throws InterruptedException {
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : " + "the main thread started.... ");
		TimerHelper timerHelper = TimerHelper.TIMER;
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : " + "the timer inited.... ");
		timerHelper.schedule(new MyTimerTask("task---1"), 1000 * 1);
		timerHelper.schedule(new MyTimerTask("task---2"), 1000 * 1);
		timerHelper.schedule(new MyTimerTask("task---3"), 1000 * 1);
		timerHelper.schedule(new MyTimerTask("task---4"), 1000 * 1);
		timerHelper.schedule(new MyTimerTask("task---5"), 1000 * 1);
		//timerHelper.schedule(new MyTimerTask("CancelTimerTask",timerHelper.getTimer()), 1000 * 1);
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : "  + "the timer scheduled.... ");
		//timer.cancel();
		//System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : "  + "the timer canceled.... ");
		
		//Thread.sleep(1000 * 12);
		timerHelper = TimerHelper.TIMER;
		timerHelper.schedule(new MyTimerTask("task---6"), 1000 * 1);
		timerHelper.schedule(new MyTimerTask("task---7"), 1000 * 1);
		//Thread.sleep(1000 * 6);
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " : " + "the main thread end.... ");
	}
}


