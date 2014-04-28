package org.leo.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterSample extends Thread {
    private Counter atomicCounter;

    public AtomicCounterSample(Counter atomicCounter) {
        this.atomicCounter = atomicCounter;
    }

    @Override
    public void run() {
        long sleepTime = (long) (Math.random() * 100);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        atomicCounter.counterIncrement();
    }

    public static void main(String[] args) throws Exception {
    	Counter atomicCounter = new MyCounter();

        for (int i = 0; i < 5000; i++) {
            new AtomicCounterSample(atomicCounter).start();
        }

        Thread.sleep(3000);

        System.out.println("counter=" + atomicCounter.getCounter());
    }
}

interface Counter{
	public int getCounter();
	public void counterIncrement();
}

class MyCounter implements Counter{
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void counterIncrement() {
    	counter++;
    }
}

class AtomicCounter implements Counter{
    private AtomicInteger counter = new AtomicInteger(0);

    public int getCounter() {
        return counter.get();
    }

    public void counterIncrement() {
        for (; ;) {
            int current = counter.get();
            int next = current + 1;
            if (counter.compareAndSet(current, next))
                return;
        }
    }
}

class AtomicCounter2 implements Counter{
    private volatile int counter;
    private static final AtomicIntegerFieldUpdater<AtomicCounter2> counterUpdater = AtomicIntegerFieldUpdater.newUpdater(AtomicCounter2.class, "counter");

    public int getCounter() {
        return counter;
    }

    public void counterIncrement() {
//        return counter++;
        counterUpdater.getAndIncrement(this);
    }
}