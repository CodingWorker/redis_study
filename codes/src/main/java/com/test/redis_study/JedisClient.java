package com.test.redis_study;

import redis.clients.jedis.Jedis;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/10/20
 * Project: redis-test-app
 */
public class JedisClient extends RedisWrapper{
    private Jedis client;

    @Override
    public String stringSet(String key, String value) {
        return client.set(key,value);
    }

    @Override
    public String stringGet(String key) {
        return client.get(key);
    }
}
