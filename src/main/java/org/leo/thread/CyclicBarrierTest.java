package org.leo.thread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
* @ClassName: CyclicBarrierTest 
* @Description: 线程同步工具类,CyclicBarrier的主要作用是等待所有人都汇合了,才往下一站出发,日常应用中较少涉及
*/
public class CyclicBarrierTest {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for(int i=0;i<3;i++){
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("线程"+Thread.currentThread().getName()+"即将到达集合点1"+",当前已有"+(cyclicBarrier.getNumberWaiting()==2?(cyclicBarrier.getNumberWaiting()+1)+"人,人数已经集合完毕,即将向下一站进发":(cyclicBarrier.getNumberWaiting()+1)+"人"));
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } 
                    
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("线程"+Thread.currentThread().getName()+"即将到达集合点2"+",当前已有"+(cyclicBarrier.getNumberWaiting()==2?(cyclicBarrier.getNumberWaiting()+1)+"人,人数已经集合完毕,即将向下一站进发":(cyclicBarrier.getNumberWaiting()+1)+"人"));
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } 
                    
                }
            };
            newCachedThreadPool.execute(runnable);
        }
        newCachedThreadPool.shutdown();
    }
}
