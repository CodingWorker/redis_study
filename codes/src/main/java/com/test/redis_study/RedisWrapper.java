package com.test.redis_study;

/**
 * Created by IntelliJ IDEA
 * User:    DaiYan
 * Date:    2017/10/20
 * Project: redis-test-app
 */
public abstract class RedisWrapper {
    abstract public String stringSet(String key,String value);
    abstract public String stringGet(String key);
}


