package me.wcy.spring.app.chat;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

import java.util.HashSet;
import java.util.Set;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
    // 已注册的账号
    private Set<LoginInfo> loginInfos = new HashSet<>();

    public NettyServerHandler() {
        LoginInfo wcy = new LoginInfo();
        wcy.setAccount("wcy");
        wcy.setToken("123456");
        LoginInfo wcy2 = new LoginInfo();
        wcy2.setAccount("wcy2");
        wcy2.setToken("123456");
        loginInfos.add(wcy);
        loginInfos.add(wcy2);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        // Channel失效，从Map中移除
        NettyChannelMap.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        CMessage message = JSON.parseObject(msg, CMessage.class);
        if (message.getType() == MsgType.PING) {
            Channel channel = NettyChannelMap.get(message.getFrom());
            if (channel != null) {
                channel.writeAndFlush(message.toJson());
            }
        } else if (message.getType() == MsgType.LOGIN) {
            LoginInfo loginInfo = JSON.parseObject(message.getContent(), LoginInfo.class);
            if (verifyLoginInfo(loginInfo)) {
                loginInfo.setResult(200);
                message.setContent(loginInfo.toJson());
                ctx.channel().writeAndFlush(message.toJson());
                NettyChannelMap.add(loginInfo.getAccount(), ctx.channel());
                System.out.println(loginInfo.getAccount() + " login");
            } else {
                loginInfo.setResult(400);
                message.setContent(loginInfo.toJson());
                ctx.channel().writeAndFlush(message.toJson());
            }
        } else if (message.getType() == MsgType.TEXT) {
            Channel channel = NettyChannelMap.get(message.getTo());
            if (channel != null) {
                channel.isWritable();
                channel.writeAndFlush(message.toJson()).addListener((ChannelFutureListener) future -> {
                    if (!future.isSuccess()) {
                        System.out.println("send msg to " + message.getTo() + " failed");
                    }
                });
            }
        }
        ReferenceCountUtil.release(msg);
    }

    private boolean verifyLoginInfo(LoginInfo loginInfo) {
        for (LoginInfo info : loginInfos) {
            if (info.equals(loginInfo)) {
                return true;
            }
        }
        return false;
    }
}