package org.leo.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest2 {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();

		for (int i = 0; i < 5; ++i) {
			CBRunnable cb = new CBRunnable(i);
			es.submit(cb);
		}
		
		es.shutdown();
	}
	
}


class CBRunnable implements Runnable {
	private static CyclicBarrier cb = new CyclicBarrier(5);
	private int idx;
	
	public CBRunnable(int idx) {
		this.idx = idx;
	}
	
	@Override
	public void run() {
		System.out.println("Thread " + idx + " starts.");
		try {
			Thread.sleep(idx * 1000);
			System.out.println("step1 : Thread " + idx + " wake up.");
			cb.await();	//第一次被使用
			System.out.println("Thread " + idx + " step1 ends. ");
			Thread.sleep(idx * 1000);
			System.out.println("step2 : Thread " + idx + " wake up.");
			cb.await();	//第二次被使用
			System.out.println("Thread " + idx + " step2 ends.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Thread " + idx + " ends.");
	}
	
}
