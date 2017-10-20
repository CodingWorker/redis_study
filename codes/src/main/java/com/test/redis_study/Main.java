package com.test.redis_study;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/10/20
 * Project: redis-test-app
 */
public class Main{
    public static void main(String[] args) {
        Jedis redisClient=new Jedis("localhost",6379);
        redisClient.auth("foobared");
        redisClient.set("a","hello");
        System.out.println(redisClient.get("a"));
        redisClient.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                super.onMessage(channel, message);
                System.out.println("chanel:"+channel+",message:"+message);
            }

            @Override
            public void subscribe(String... channels) {
                super.subscribe(channels);
                for(String channel:channels){
                    System.out.println(channel);
                }
            }

            @Override
            public void ping() {
                super.ping();
                System.out.println("PONG");
            }

            @Override
            public int getSubscribedChannels() {
                System.out.println("inner getSubscribedChannels:"+super.getSubscribedChannels());
                return super.getSubscribedChannels();
            }
        },"aa");

        System.out.println("hello");
        redisClient.close();
    }
}
