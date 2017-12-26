package me.wcy.spring.app.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class NettyServerBootstrap {
    private int port;

    public NettyServerBootstrap(int port) {
        this.port = port;
        bind();
    }

    private void bind() {
        new ServerBootstrap()
                .group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                // 不延迟，直接发送
                .option(ChannelOption.TCP_NODELAY, true)
                // 保持长连接状态
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new ObjectEncoder());
                        pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                        pipeline.addLast(new NettyServerHandler());
                    }
                })
                .bind(port)
                .addListener((ChannelFutureListener) future -> {
                    if (future.isSuccess()) {
                        System.out.println("netty server start");
                    } else {
                        System.out.println("netty server start failed");
                    }
                });
    }
}
