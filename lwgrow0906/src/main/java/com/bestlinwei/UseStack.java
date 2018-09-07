package com.bestlinwei;

import java.util.Stack;

/**
 *  LIFO 后进先出 
 *  继承自Vector
 * @author linwei
 *
 */
public class UseStack {
	
	public static void main(String[] args) {
		Stack<String> stack =  new Stack<String>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		System.out.println(stack.firstElement());
		System.out.println(stack.pop());
		System.out.println(stack.peek());
	}

}
