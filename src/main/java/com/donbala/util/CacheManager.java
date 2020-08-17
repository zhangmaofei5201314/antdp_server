package com.donbala.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * {\_/}
 * ( ^.^ )
 * / > @ zmf
 * 内存类，放token和用户信息和超时时间
 * @date 2019/10/28
 */
@Component
public class CacheManager {
    private ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<String, Object>();

    public Object getValue(String key) {
        return cache.get(key);
    }

    public void addCache(String key,Object value) {
        cache.put(key, value);
    }

    public void deleteCache(String key) {
        cache.remove(key);
    }

    public void deleteAllCache() {
        cache.clear();
    }

    public boolean isExistKey(String key) {
        return cache.containsKey(key) ? true : false;
    }
}
