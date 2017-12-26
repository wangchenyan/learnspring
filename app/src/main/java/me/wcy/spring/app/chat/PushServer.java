package me.wcy.spring.app.chat;

public class PushServer {

    public static void start() {
        new NettyServerBootstrap(8300);
    }
}
