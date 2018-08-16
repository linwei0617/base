package com.bestlinwei.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty 实现心跳检测
 * @author linwei
 *
 */
public class Client {
	
	public static void main(String[] args) {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel sc) throws Exception {
					sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
					sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
					sc.pipeline().addLast(new ClienHeartBeattHandler());
				}
				
			});
	}

}
