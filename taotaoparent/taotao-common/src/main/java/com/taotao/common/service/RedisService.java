package com.taotao.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author zjj
 * @date 2018/12/8 22:36
 */
@Service
public class RedisService {

    @Autowired(required = false)
    private ShardedJedisPool shardedJedisPool;

    private <T> T excute(Function<ShardedJedis, T> function){
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();
            return function.callBack(shardedJedis);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }
        return null;
    }

    public String set(final String key, final String value){
        return excute(new Function<ShardedJedis, String>() {
            @Override
            public String callBack(ShardedJedis shardedJedis) {
                return shardedJedis.set(key, value);
            }
        });
    }

    public String set (final String k, final String v, final int seconds){
        return excute((shardedJedis) -> {
            String result = shardedJedis.set(k, v);
            shardedJedis.expire(k, seconds);
            return result;
        });
    }

    public Long setnx(final String k, final String v){
        return excute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                return shardedJedis.setnx(k, v);
            }
        });
    }

    public Long setnx(final String k, final String v, final int seconds){
        return excute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                Long setnx = shardedJedis.setnx(k, v);
                if (setnx.longValue() == 1L){
                    shardedJedis.expire(k, seconds);
                }
                return setnx;
            }
        });
    }


    public String get(final String key){
        return excute(new Function<ShardedJedis, String>() {
            @Override
            public String callBack(ShardedJedis shardedJedis) {
                return shardedJedis.get(key);
            }
        });
    }

    public Long del(final String key){
        return excute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                return shardedJedis.del(key);
            }
        });
    }

    /**
     * 设置有效期,单位秒
     * @param key
     * @return
     */
    public Long expire(final String key, final int seconds){
        return excute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callBack(ShardedJedis shardedJedis) {
                return shardedJedis.expire(key, seconds);
            }
        });
    }


    public String setOld(String key, String value){
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.set(key, value);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }
        return null;
    }

    public String getOld(String key){
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis.get(key);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != shardedJedis) {
                // 关闭，检测连接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }
        return null;
    }
}
