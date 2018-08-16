package com.bestlinwei.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *   客户端 
 *   采用线程池和任务队列可以实现一种叫做伪异步的I/O通信框架，解决同步阻塞I/O面临的一个链路需要一个线程处理的问题
 *   由于线程池和消息队列都是有界的，因此，无论客户端并发连接数多大，它都不会导致线程个数过于膨胀或者内存溢出，相对于传统的一连接一线程模型，是一种改良。
 *   阻塞的时间取决于对方IO线程的处理速度和网络IO的传输速度，可靠性差
 * @author linwei
 *
 */
public class Client {

	static final String ADDRESS = "127.0.0.1";
	static final int PORT = 8765;
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			socket = new Socket(ADDRESS, PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//服务端发过来的
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Client request");
			String response = in.readLine();
			System.out.println("Client:" + response);
		}catch(Exception e) {
			
		}finally {
			if(in != null){
				try {
					in.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(socket != null){
				try {
					socket.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
			socket = null;		
		}
	}
}
