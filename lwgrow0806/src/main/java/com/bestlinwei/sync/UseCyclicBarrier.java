package com.bestlinwei.sync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行
 * @author linwei
 *
 */
public class UseCyclicBarrier {

	public static void main(String[] args) {
		//当四个线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable
		CyclicBarrier barrier = new CyclicBarrier(4,new Runnable() {
			@Override
			public void run() {
				System.out.println("当前线程"+Thread.currentThread().getName());   
			}
		});
		for(int i=0;i<4;i++) {
			new Thread() {
				public void run() {
					try {
						System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据");
						Thread.sleep(5000);      //以睡眠来模拟写入数据操作
						System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
						barrier.await();//阻塞
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("所有线程写入完毕，继续处理其他任务...");
				}
			}.start();
		}
	}
}
