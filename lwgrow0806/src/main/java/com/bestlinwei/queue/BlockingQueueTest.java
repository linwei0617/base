package com.bestlinwei.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 *  有五种实现
 *  ArrayBlockingQueue 基于数组的阻塞队列，内部维护一个固定长度，内部没有实现读写分离（生产者和消费者不能并行） 有界队列 （非公平锁）
 *  LinkedBlockingQueue 基于链表的阻塞队列，，内部实现了读写分离（两个锁） 无界队列
 *  SynchronousQueue 没有缓冲的队列，生产者产生的数据会直接被消费者消费 （公平锁）
 *  PriorityBlockingQueue  基于优先级的阻塞队列（优先级通过构造传入的Compator对象决定）无界队列（默认采用公平锁）
 *  DelayBlockQueue 无界阻塞队列，延迟期满才可以取出数据 不允许null
 * @author linwei
 *
 */
public class BlockingQueueTest {
	/**
     * 实例化一个队列，队列中的容量为10
     */
    private static BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);//无界队列
    private static BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<String>(10);//有界队列
    
    public static void main(String[] args) {
        ScheduledExecutorService product = Executors.newScheduledThreadPool(1);
        Random random = new Random();
        product.scheduleAtFixedRate(() -> {
            int value = random.nextInt(101);
            try{
                blockingQueue.offer(value);  //offer()方法就是往队列的尾部设置值
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }, 0, 200, TimeUnit.MILLISECONDS);  //每200毫秒执行线程

        new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(2000);
                    System.out.println("开始取值");
                    List<Integer> list = new LinkedList<>();
                    blockingQueue.drainTo(list);  //drainTo()将队列中的值全部从队列中移除，并赋值给对应集合
                    list.forEach(System.out::println);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        blockingQueue1.add("a");//两个线程同时读写会报full
        System.out.println(blockingQueue1.poll());
    }
}
