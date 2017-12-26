package me.wcy.spring.app.chat;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyChannelMap {
    private static Map<String, Channel> map = new ConcurrentHashMap<>();

    public static void add(String clientId, Channel channel) {
        map.put(clientId, channel);
    }

    public static Channel get(String clientId) {
        return map.get(clientId);
    }

    public static void remove(Channel channel) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == channel) {
                String key = (String) entry.getKey();
                map.remove(key);
                System.out.println("socketChannel " + key + " removed");
                break;
            }
        }
    }
}