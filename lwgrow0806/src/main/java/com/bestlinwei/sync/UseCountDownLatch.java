package com.bestlinwei.sync;

import java.util.concurrent.CountDownLatch;

/**
 * 程序计数器 同步辅助类，可以替代wait,notify
 * @author linwei
 *
 */
public class UseCountDownLatch {
	//允许一个或多个线程，等待其他一组线程完成操作，再继续执行。
	static CountDownLatch latch = new CountDownLatch(2);
	
	public static void main(String[] args) {
		new Thread() {
			public void run() {
				
				try {
					System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
					Thread.sleep(3000);
					System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				
				try {
					System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
					Thread.sleep(3000);
					System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		new Thread() {
			public void run() {
				
				try {
					System.out.println("阻塞子线程等待2个子线程执行完毕...");
		            latch.await();//阻塞
		            System.out.println("阻塞子线程2个子线程已经执行完毕");
		            System.out.println("继续子线程");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		try {
			System.out.println("等待2个子线程执行完毕...");
            latch.await();//阻塞
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
