package org.leo.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;

public class TestPriorityQueue2 {
	
	private PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
	
	private ThreadPoolExecutor executor = null;
	
	public TestPriorityQueue2(){
		init();
	}
	
	public void init(){
		for(int i = 0 ; i < 1000; i++){
			InnerTask task = new InnerTask("task_" + StringUtils.leftPad(i + "", 3, "0"), RandomUtils.nextInt(10), String.valueOf(i));
			queue.add(task);
		}
		System.out.println(queue.toString());
		executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.MINUTES, queue);
	}
	
	public void execute(InnerTask task){
		executor.execute(task);
	}
	
	public boolean isEnableAdd(){
		if(queue.size() > 500){
			return false;
		}
		return true;
	}
	
	public void test1(){
		InnerTask task = new InnerTask("task_init_1", -4, "init 1 task");
		execute(task);

		task = new InnerTask("task_init_2", -11, "init 2 task");
		execute(task);

		task = new InnerTask("task_init_3", -12, "init 3 task");
		execute(task);

		task = new InnerTask("task_init_4", -13, "init 4 task");
		execute(task);

		task = new InnerTask("task_init_5", -14, "init 5 task");
		execute(task);

		task = new InnerTask("task_init_6", -15, "init 6 task");
		execute(task);
	}
	
	public void test2(){
		InnerTask task = new InnerTask("task_init_1", -1, "init 1 task");
		execute(task);
		
		ExecutorService se = Executors.newCachedThreadPool();
		for(int i = 0 ; i < 5; i++){
			Producer producer = new Producer("P" + StringUtils.leftPad(i + "", 2, "0"));
			se.execute(producer);
		}
		
		se.execute(new Runnable() {
			public void run() {
				while (true) {
					System.out.println("the size of queue is " + queue.size());
					try {
						TimeUnit.MILLISECONDS.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public static void main(String args[]) {
		TestPriorityQueue2 test = new TestPriorityQueue2();
		test.test2();
	}
	
	
	
	class Producer implements Runnable{
		
		private String name;
		
		public Producer(String name){
			this.name = name;
		}
		
		@Override
		public void run() {
			int i = 0;
			while (true) {
				i++;
				String taskId = name + "_task_" + StringUtils.leftPad(i + "", 4, "0");
				if(isEnableAdd()){
					InnerTask task = new InnerTask(taskId, RandomUtils.nextInt(10) + 10, String.valueOf(i));
					execute(task);
				}else{
					System.out.println("the task of Producer " + name + " can't run, return : " + taskId);
				}
				try {
					TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(200));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	class InnerTask implements Runnable,Comparable<InnerTask> {

		private String taskId;
		private int priority;
		private String msg;
		
		public InnerTask(String taskId, int priority, String msg){
			this.taskId = taskId;
			this.priority = priority;
			this.msg = msg;
		}

		/**
		 * 数字大，优先级高
		 * @param o
		 * @return
		 * @author leo
		 * @addTime 2014年12月11日下午5:14:16
		 */
		@Override
		public int compareTo(InnerTask o) {
			return this.priority < o.priority ? 1 : this.priority > o.priority ? -1 : 0;
		}

		@Override
		public void run() {
			System.out.println("taskId=" + taskId + ", priority=" + priority + ", msg=" + msg);	
			try {
				TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public String toString() {
			return taskId + "# [msg=" + msg + " priority=" + priority + "]";
		}
	}
}

