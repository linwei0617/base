package com.bestlinwei;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * 当你想要跳过对象初始化阶段，或绕过构造器的安全检查，或实例化一个没有任何公共构造器的类，
 * allocateInstance方法是非常有用的，使用构造器、反射和unsafe初始化它，将得到不同的结果
 * @author linwei
 *
 */
public class App {
	
	private static sun.misc.Unsafe UNSAFE;

    public static void main(String[] args) {
        try {
        		//不调用构造实例化
            User instance = (User) UNSAFE.allocateInstance(User.class);

            instance.setName("liull");
            System.err.println("instance:" + instance);
            instance.test();
            Field name = instance.getClass().getDeclaredField("name");
            UNSAFE.putObject(instance, UNSAFE.objectFieldOffset(name), "huanghui");
            instance.test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
        	
        }
    }
}
