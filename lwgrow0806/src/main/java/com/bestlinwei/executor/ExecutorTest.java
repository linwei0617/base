package com.bestlinwei.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *   线程池四种实现
 *  newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * @author linwei
 *
 */
public class ExecutorTest {
	static ExecutorService cachedThreadPool = Executors.newCachedThreadPool(); 
	static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
	static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
	static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
	
	public static void main(String[] args) {
		//线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程
//		for (int i = 0; i < 10; i++) {  
//			final int index = i;  
//			try {  
//				Thread.sleep(index * 1000);  
//			} catch (InterruptedException e) {  
//				e.printStackTrace();  
//			}  
//			cachedThreadPool.execute(new Runnable() {  
//				public void run() {  
//					System.out.println(index);
//				}
//			});  
//		} 
		//因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
		for (int i = 0; i < 10; i++) {
			final int index = i;  
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		//延迟三秒执行 
		scheduledThreadPool.schedule(new Runnable() {
			public void run() {
				System.out.println("delay 3 seconds"); 
			}
		}, 3, TimeUnit.SECONDS);
		//定期执行，表示延迟1秒后每3秒执行一次
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {  
			public void run() {  
				System.out.println("delay 1 seconds, and excute every 3 seconds");  
			}  
		}, 1, 3, TimeUnit.SECONDS);  
		//用唯一的工作线程来执行任务，相当于顺序执行各个任务
		for (int i = 0; i < 10; i++) {
			final int index = i;
			singleThreadExecutor.execute(new Runnable() {
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	} 
}
