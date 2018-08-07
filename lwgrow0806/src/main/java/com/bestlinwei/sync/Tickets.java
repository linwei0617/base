package com.bestlinwei.sync;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 同步类与非同步类容器
 */
public class Tickets{

    public static void main(String[] args) {

        // 内部实现依然是通过同步关键字
        final Vector<String> tickets = new Vector<String>();

        Map<String,Object> map = Collections.synchronizedMap(new HashMap<String, Object>());//等同

        for (int i = 1; i < 1000; i++) {
            tickets.add("火车票"+i);
        }

        for(int i = 1; i<10; i++){
            new Thread("线程"+i){
                public void run(){
                    while(true){
                        if(tickets.isEmpty()) break;
                        System.out.println(Thread.currentThread().getName()+"---"+tickets.remove(0));
                    }
                }
            }.start();
        }
    }
}