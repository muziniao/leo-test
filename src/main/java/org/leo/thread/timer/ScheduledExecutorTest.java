package org.leo.thread.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {
	// 线程池能按时间计划来执行任务，允许用户设定计划执行任务的时间，int类型的参数是设定
	// 线程池中线程的最小数目。当任务较多时，线程池可能会自动创建更多的工作线程来执行任务
	public ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(5);

	// 启动计时器
	public void lanuchTimer() {
		Runnable task = new Runnable() {
			public void run() {
				System.out.println("RuntimeException");
				throw new RuntimeException();
			}
		};
		scheduExec.scheduleWithFixedDelay(task, 1000 * 5, 1000 * 10, TimeUnit.MILLISECONDS);
	}

	// 添加新任务
	public void addOneTask() {
		Runnable task = new Runnable() {
			public void run() {
				System.out.println("welcome to china");
			}
		};
		//scheduExec.scheduleWithFixedDelay(task, 1000 * 1, 1000, TimeUnit.MILLISECONDS);
		scheduExec.schedule(task, 5, TimeUnit.SECONDS);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("-----------start------");
		ScheduledExecutorTest test = new ScheduledExecutorTest();
		test.lanuchTimer();
		System.out.println("-----------1------");
		Thread.sleep(1000 * 5);// 5秒钟之后添加新任务
		test.addOneTask();
		System.out.println("-----------end------");
	}
}
