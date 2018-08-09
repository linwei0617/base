package com.bestlinwei.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁是可以完全替代synchronized关键字,可中断响应、锁申请等待限时、公平锁。另外可以结合Condition来使用
 * @author linwei
 *
 */
public class UseReentrantLock implements Runnable{
	public static Lock lock = new ReentrantLock();
	static Condition condition = lock.newCondition();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();
            condition.signal();//condition对象的signal方法可以唤醒wait线程
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
    		UseReentrantLock test = new UseReentrantLock();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();
        t1.join(); 
        t2.join(); // main线程会等待t1和t2都运行完再执行以后的流程
        System.out.println(i);
    }
}
