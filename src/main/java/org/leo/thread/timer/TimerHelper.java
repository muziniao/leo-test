package org.leo.thread.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public enum TimerHelper {

	TIMER;
	
	private Timer timer;

	private TimerHelper(){
		timer = new Timer();
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public void schedule(TimerTask task, long delay){
		timer.schedule(new DelegateTask(task), delay);
	}
	
	public void schedule(TimerTask task, Date time){
		timer.schedule(new DelegateTask(task), time);
	}
	
	public void schedule(TimerTask task, long delay, long period){
		timer.schedule(new DelegateTask(task), delay, period);
	}
	
	public void schedule(TimerTask task, Date firstTime, long period){
		timer.schedule(new DelegateTask(task), firstTime, period);
	}
	
	public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
		timer.scheduleAtFixedRate(new DelegateTask(task), delay, period);
	}
	
	public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period) {
		timer.scheduleAtFixedRate(new DelegateTask(task), firstTime, period);
	}
	
	public void cancel() {
		timer.cancel();
	}
	private class DelegateTask extends TimerTask{
		
		private TimerTask task;
		
		DelegateTask(TimerTask task){
			this.task = task;
		}

		@Override
		public void run() {
			try {
				task.run();
			} catch (Throwable t) {
				t.printStackTrace();
			}
			
		}
		
		
	}
}
