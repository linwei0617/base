package com.bestlinwei.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *  高性能队列
 *  性能高于BlockQueue，他是一个基于链接节点的无界线程安全队列
 *  不允许null元素
 * @author linwei
 *
 */
public class ConcurrentLinkedQueueTest {
	
	public static void main(String[] args) {
		ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
		q.offer("a");
        q.offer("b");
        q.offer("c");
        q.offer("d");//两种方法5无任何区别
        q.add("e");

        System.out.println("从头部取出元素，并从队列里删除 >> "+q.poll());    //a 从头部取出元素，并从队列里删除
        System.out.println("删除后的长度 >> "+q.size());    //4
        System.out.println("取出头部元素 >> "+q.peek());    //b
        System.out.println("长度 >> "+q.size());    //4
	}

}
