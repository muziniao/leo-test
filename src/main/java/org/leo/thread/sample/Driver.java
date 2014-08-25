package org.leo.thread.sample;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

public class Driver {
	static int N = 10;
	
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i) // create and start threads
            new Thread(new Worker("Worder-" + StringUtils.leftPad("" + i, String.valueOf(N).length(), "0"), startSignal, doneSignal)).start();

        doSomethingElse("01");            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        //doSomethingElse("02");
        doneSignal.await();           // wait for all to finish
        System.out.println("the end......");
    }
    
    public static void doSomethingElse(String flag){
    	System.out.println("Driver doSomethingElse....start..." + flag);
    	try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("Driver doSomethingElse....end..." + flag);
    }
}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    private String name;
    
    Worker(String name, CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.name = name;
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {} // return;
    }

    void doWork() {
    	System.out.println("The Worker doWork.." + name);
    	try {
			TimeUnit.MILLISECONDS.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
}
