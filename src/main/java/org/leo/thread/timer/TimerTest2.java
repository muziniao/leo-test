package org.leo.thread.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 *  Timer的另一个问题在于，如果TimerTask抛出未检查的异常，Timer将会产生无法预料的行为。
 *  Timer线程并不捕获异常，所以 TimerTask抛出的未检查的异常会终止timer线程。
 *  这种情况下，Timer也不会再重新恢复线程的执行了;它错误的认为整个Timer都被取消了。
 *  此时，已经被安排但尚未执行的TimerTask永远不会再执行了，新的任务也不能被调度了。
 *  
 *  运行该程序，Timer会抛出一个RumtimeException和java.lang.IllegalStateException:Timer already cancelled.

 *  常言道，真是祸不单行，Timer还将它的问题传染给下一个倒霉的调用者，
 * 这个调用者原本试图提交一个TimerTask的，你可能希望程序会一直运行下去，然而实际情况如程序所示5秒钟后就中止了，还伴随着一个异常，异常的消息是"Timer already cancelled"。
 *  ScheduledThreadPoolExector妥善地处理了这个异常的任务，所以说在java5.0或更高的JDK中，几乎没有理由再使用 Timer了。
 *  
 * @author leo
 *
 */
public class TimerTest2 {
	private Timer timer = new Timer();

	// 启动计时器
	public void lanuchTimer() {
		timer.schedule(new TimerTask() {
			public void run() {
				throw new RuntimeException();
			}
		}, 1000 * 3, 500);
	}

	// 向计时器添加一个任务
	public void addOneTask() {
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("hello world");
			}
		}, 1000 * 1, 1000 * 5);
	}

	public static void main(String[] args) throws Exception {
		TimerTest2 test = new TimerTest2();
		test.lanuchTimer();
		Thread.sleep(1000 * 5);// 5秒钟之后添加一个新任务
		test.addOneTask();
	}
}
