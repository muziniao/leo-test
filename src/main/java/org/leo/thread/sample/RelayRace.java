package org.leo.thread.sample;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 用java模拟4X100接力赛
 * 当结束后 报成绩，应用CyclicBarrier
 */
class Player implements Runnable{
	private String name;
	private CyclicBarrier barrier;
	private Player next;//下一棒
	private int time;//用时
	private boolean run;//第一棒
	public Player(String name, CyclicBarrier barrier, boolean run) {
		super();
		this.name = name;
		this.barrier = barrier;
		this.run = run;
	}
	@Override
	public void run() {
		try {
			synchronized (this) {
				while(!run){//等待队员
					wait();
				}
			}
			Random r = new Random();
			TimeUnit.MILLISECONDS.sleep(r.nextInt(2000));
			next(next,11 + r.nextInt(2));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	private void next(Player next, int time) {
		System.out.println(name + " 用时：" + time + ",交接棒");
		if(next != null){
			next.setTime(this.time + time);
			synchronized (next) {
				next.setRun(true);
				next.notify();
			}
		}else{
			System.out.println("跑完，总用时：" + (this.time + time));
		}
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getTime(){
		return this.time;
	}
	public void setNext(Player next) {
		this.next = next;
	}
	public void setRun(boolean run) {
		this.run = run;
	}
	
}
public class RelayRace {
	
	public static void main(String[] args) throws InterruptedException {
		final Player[] players = new Player[4];
		ExecutorService exec = Executors.newCachedThreadPool();
		CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("结束，总用时：" + players[3].getTime());
			}
		});
		for(int i = 0; i < 4; i++){
			players[i] = new Player("队员" + ( i + 1), barrier, i == 0);
		}
		for(int i = 0; i < 4; i++){
			if( i < 3){
				players[i].setNext(players[i + 1]);
				exec.execute(players[i]);
			}else{
				exec.execute(players[3]);
				break;
			}
		}
		/*TimeUnit.SECONDS.sleep(3);
		 * CyclicBarrier 可以重用
		for(int i = 0; i < 4; i++){
			if( i < 3){
				players[i].setNext(players[i + 1]);
				exec.execute(players[i]);
			}else{
				exec.execute(players[3]);
				break;
			}
		}*/
	}

}

