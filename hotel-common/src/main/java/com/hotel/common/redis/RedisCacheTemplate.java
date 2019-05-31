package com.hotel.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @ProjectName: hotel-admin
 * @ClassName: RedisTemplate
 * @Author: cc
 * @Description: redis 基础方法
 * @Date: 2019-05-24 21: * @Version: 1.
 */
@Component
public class RedisCacheTemplate {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String REDIS_CHARSET = "utf-8";

    @Resource
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    /*****************************************************/
    /********************* key 操作 **********************/
    /*****************************************************/
    /**
     * 封装redis缓存服务接口
     *
     * @param keys
     * @return 删除的条数
     * @throws
     * @date 2015年8月25日
     */
    public Long del(final String... keys) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    result = connection.del(keys[i].getBytes());
                }
                return result;
            }
        });
    }
    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public boolean exists(final String key) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.exists(key.getBytes()));
    }

    /*****************************************************/
    /******************** 字符串操作 **********************/
    /*****************************************************/

    /**
     * 添加key value 并且设置存活时间(byte)
     *
     * @param key
     * @param value
     * @param liveTime
     *            存活时间
     * @throws

     * @date 2015年8月26日
     */
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * 添加key value 并且设置存活时间
     *
     * @param key
     * @param value
     * @param liveTime
     *            存活时间
     * @throws

     * @date 2015年8月26日
     */
    public void set(String key, String value, long liveTime) {
        this.set(key.getBytes(), value.getBytes(), liveTime);
    }

    /**
     * 添加key value
     *
     * @param key
     * @param value
     * @throws

     * @date 2015年8月26日
     */
    public void set(String key, String value) {
        this.set(key, value, 0L);
    }

    /**
     * 添加key value (字节)(序列化)
     *
     * @param key
     * @param value
     * @throws

     * @date 2015年8月26日
     */
    public void set(byte[] key, byte[] value) {
        this.set(key, value, 0L);
    }

    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public String get(final String key) {
        return redisTemplate.execute((RedisCallback<String>) connection -> byteToString(connection.get(key.getBytes())));
    }

    /**
     * 通过正则匹配keys
     *
     * @param pattern
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Set<byte[]> getKeys(final String pattern) {
        return redisTemplate.execute((RedisCallback<Set<byte[]>>) connection -> connection.keys(pattern.getBytes()));
    }

    /**
     * 值增加1
     *
     * @param key
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Long incr(final String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.incr(key.getBytes()));
    }

    /**
     * 值增加number
     *
     * @param key
     * @param number
     * @return
     * @throws

     * @date 2015年8月25日
     */
    public Long incrBy(final String key, final long number) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.incrBy(key.getBytes(), number));
    }

    /**
     * 值增加number
     *
     * @param key
     * @param number
     * @return
     * @throws

     * @date 2015年8月25日
     */
    public Double incrBy(final String key, final double number) {
        return redisTemplate.execute((RedisCallback<Double>) connection -> connection.incrBy(key.getBytes(), number));
    }

    /**
     * 值减少1
     *
     * @param key
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Long decr(final String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.decr(key.getBytes()));
    }

    /**
     * 值减少number
     *
     * @param key
     * @param number
     * @return
     * @throws

     * @date 2015年8月25日
     */
    public Long decrBy(final String key, final long number) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.decrBy(key.getBytes(), number));
    }
    /*****************************************************/
    /******************** set集合操作 *********************/
    /*****************************************************/

    /**
     * 往集合key中增加元素集合
     *
     * @param key
     * @param values
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Long sadd(final String key, final byte[]... values) {
        return this.sadd(key, 0l, values);
    }

    /**
     * 往集合key中增加元素集合
     *
     * @param key
     * @param values
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Long sadd(final String key, final List<String> values) {
        if (values == null || values.size() == 0)
            return 0l;
        return this.sadd(key, 0l, (byte[][]) strListToByteList(values).toArray());
    }

    /**
     * 往集合key中增加元素，并设置过期时间
     *
     * @param key
     * @param liveTime
     * @param value
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Long sadd(final String key, final long liveTime, final String value) {
        return this.sadd(key, liveTime, value.getBytes());
    }

    /**
     * 往集合key中增加元素
     *
     * @param key
     * @param value
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Long sadd(final String key, final String value) {
        return this.sadd(key, 0l, value.getBytes());
    }

    /**
     * 往集合key中增加元素集合，并设置过期时间
     *
     * @param key
     * @param values
     * @param liveTime
     *            过期时间(秒)
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Long sadd(final String key, final long liveTime, final byte[]... values) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.sAdd(key.getBytes(), values);
                if (liveTime > 0)
                    connection.expire(key.getBytes(), liveTime);
                return (long) values.length;
            }
        });
    }

    /**
     * 删除集合中集为 values的元素
     *
     * @param key
     * @param values
     * @return 忽略不存在的元素后,真正删除掉的元素的个数
     * @throws

     * @date 2015年8月26日
     */
    public Long sRem(final String key, final byte[]... values) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.sRem(key.getBytes(), values);
            }
        });
    }

    /**
     * 查询并删除集合中key中1个随机元素
     *
     * @param key
     * @return 返回并删除集合中key中1个随机元素
     * @throws

     * @date 2015年8月26日
     */
    public String spop(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return byteToString(connection.sPop(key.getBytes()));
            }
        });
    }

    /**
     * 返回集合中随机一个元素
     *
     * @param key
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public String sRandMember(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return byteToString(connection.sRandMember(key.getBytes()));
            }
        });
    }

    /**
     * 判断value是否在key集合中
     *
     * @param key
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Boolean sIsMember(final String key, final String value) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.sIsMember(key.getBytes(), value.getBytes());
            }
        });
    }

    /**
     * 获取集合中所有的元素
     *
     * @param key
     * @param value
     * @return 返回集合中所有的元素
     * @throws

     * @date 2015年8月26日
     */
    public List<String> sMembers(final String key) {
        Set<byte[]> values = redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
            public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.sMembers(key.getBytes());
            }
        });
        return byteSetToStrSet(values);
    }

    /**
     * 获取集合中元素的个数
     *
     * @param key
     * @param value
     * @return 返回集合中元素的个数
     * @throws

     * @date 2015年8月26日
     */
    public Long scard(final String key) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.sCard(key.getBytes());
            }
        });
    }



    /*****************************************************/
    /******************** hash哈希操作 ********************/
    /*****************************************************/

    /**
     * 把key中 filed域的值设为value 注:如果没有field域,直接添加,如果有,则覆盖原field域的值
     *
     * @param key
     * @param field
     * @param value
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Boolean hSet(final String key, final String field, final String value) {
        return this.hSet(key, field, value, 0l);
    }

    /**
     * 把key中 filed域的值设为value，并设置过期时间 注:如果没有field域,直接添加,如果有,则覆盖原field域的值
     *
     * @param key
     * @param field
     * @param value
     * @param liveTime
     *            过期时间(秒)
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Boolean hSet(final String key, final String field, final String value, final long liveTime) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            connection.hSet(key.getBytes(), field.getBytes(), stringToByte(value));
            if (liveTime > 0)
                connection.expire(key.getBytes(), liveTime);
            return true;
        });
    }

    /**
     * 添加map元素
     *
     * @param key
     * @param hashes
     * @throws

     * @date 2015年8月26日
     */
    public void hSet(final String key, final Map<String, String> hashes) {
        this.hSet(key, hashes, 0l);
    }

    /**
     * 添加map元素
     *
     * @param key
     * @param hashes
     * @param liveTime
     * @throws

     * @date 2015年8月26日
     */
    public void hSet(final String key, final Map<String, String> hashes, final long liveTime) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            Iterator<Map.Entry<String, String>> it = hashes.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> et = it.next();
                hSet(key, et.getKey(), et.getValue());
            }
            if (liveTime > 0)
                connection.expire(key.getBytes(), liveTime);
            return null;
        });
    }

    /**
     * 添加map元素
     *
     * @param key
     * @param hashes
     * @throws

     * @date 2015年8月26日
     */
    public void hSetByteMap(final String key, final Map<byte[], byte[]> hashes) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.hMSet(key.getBytes(), hashes);
            return null;
        });
    }

    /**
     * 返回key中field域的值
     *
     * @param key
     * @param field
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public String hGet(final String key, final String field) {
        return redisTemplate.execute((RedisCallback<String>) connection -> byteToString(connection.hGet(key.getBytes(), field.getBytes())));
    }

    /**
     * 返回key中fields域的值
     *
     * @param key
     * @param fields
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public List<String> hGet(final String key, final byte... fields) {
        return byteListToStrList(this.hGetsByte(key, fields));
    }

    /**
     * 返回key中fields域的值
     *
     * @param key
     * @param fields
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public List<byte[]> hGetsByte(final String key, final byte... fields) {
        return redisTemplate.execute((RedisCallback<List<byte[]>>) connection -> connection.hMGet(key.getBytes(), fields));
    }

    /**
     * 返回key中,所有域与其值
     *
     * @param key
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Map<String, String> hGetAll(final String key) {
        Map<byte[], byte[]> values = redisTemplate.execute((RedisCallback<Map<byte[], byte[]>>) connection -> connection.hGetAll(key.getBytes()));
        return byteMapToStrMap(values);
    }

    /**
     * 删除key中 field域
     *
     * @param key
     * @param field
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Long hDel(final String key, final String field) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.hDel(key.getBytes(), field.getBytes()));
    }

    /**
     * 返回key中元素的数量
     *
     * @param key
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Long hLen(final String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.hLen(key.getBytes()));
    }

    /**
     * 判断key中有没有field域
     *
     * @param key
     * @param field
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Boolean hExists(final String key, final String field) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.hExists(key.getBytes(), field.getBytes()));
    }

    /**
     * 返回key中所有的field
     *
     * @param key
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public List<String> hKeys(final String key) {
        Set<byte[]> values = redisTemplate.execute((RedisCallback<Set<byte[]>>) connection -> connection.hKeys(key.getBytes()));
        return byteSetToStrSet(values);
    }

    /**
     * 返回key中所有的value
     *
     * @param key
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public List<String> hVals(final String key) {
        List<byte[]> values = redisTemplate.execute((RedisCallback<List<byte[]>>) connection -> connection.hVals(key.getBytes()));
        return byteListToStrList(values);
    }

    /**
     * 把key中的field域的值增长整型值delta
     *
     * @param key
     * @param field
     * @param delta
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Long hIncrBy(final String key, final String field, final long delta) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.hIncrBy(key.getBytes(), field.getBytes(), delta));
    }

    /**
     * 把key中的field域的值增长浮点值delta
     *
     * @param key
     * @param field
     * @param delta
     * @return
     * @throws

     * @date 2015年8月26日
     */
    public Double hIncrBy(final String key, final String field, final double delta) {
        return redisTemplate.execute((RedisCallback<Double>) connection -> connection.hIncrBy(key.getBytes(), field.getBytes(), delta));
    }



    /*****************************************************/
    /********************* 其他操作 **********************/
    /*****************************************************/

    /**
     * byte数组转为String
     *
     * @param bytes
     * @return
     * @throws

     * @date 2015年8月26日
     */
    protected String byteToString(byte[] bytes) {
        if (bytes == null)
            return null;
        if (bytes.length == 0)
            return "";
        try {
            return new String(bytes, REDIS_CHARSET);
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            throw new GlobalException("redis");
            logger.error("redis 字符串序列号失败");
        }
        return null;
    }

    /**
     * byte数组集合转为字符串集合
     *
     * @param values
     * @return
     * @throws

     * @date 2015年8月26日
     */
    protected List<String> byteListToStrList(List<byte[]> values) {
        if (values == null)
            return null;
        List<String> results = new LinkedList<String>();
        for (byte[] value : values)
            results.add(byteToString(value));
        return results;
    }

    /**
     * 字符串集合转为byte数组集合
     *
     * @param values
     * @return
     * @throws

     * @date 2015年8月26日
     */
    protected List<byte[]> strListToByteList(List<String> values) {
        if (values == null)
            return null;
        List<byte[]> results = new LinkedList<byte[]>();
        for (String value : values)
            results.add(stringToByte(value));
        return results;
    }

    /**
     * byte数组集合转为字符串集合
     *
     * @param values
     * @return
     * @throws

     * @date 2015年8月26日
     */
    protected List<String> byteSetToStrSet(Set<byte[]> values) {
        if (values == null)
            return null;
        // Set<String> results = new HashSet<String>();
        List<String> results = new LinkedList<String>();
        for (byte[] value : values)
            results.add(byteToString(value));
        return results;
    }

    /**
     * byte数组map转为字符串map
     *
     * @param values
     * @return
     * @throws

     * @date 2015年8月26日
     */
    protected Map<String, String> byteMapToStrMap(Map<byte[], byte[]> values) {
        if (values == null)
            return null;
        Map<String, String> results = new HashMap<String, String>();
        Iterator<Map.Entry<byte[], byte[]>> it = values.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<byte[], byte[]> et = it.next();
            results.put(byteToString(et.getKey()), byteToString(et.getValue()));
        }
        return results;
    }

    /**
     * String转为 byte数组
     *
     * @param value
     * @return
     * @throws

     * @date 2015年8月26日
     */
    protected byte[] stringToByte(String value) {
        if (value == null)
            return null;
        try {
            return value.getBytes(REDIS_CHARSET);
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            throw new BusinessException(e);
            logger.error("redis 字符串序列号失败");
        }
        return null;
    }

    public Long getMaxId(String key) {
        if (this.exists(key)) {
            this.incr(key);
            return Long.parseLong(this.get(key));

        } else {
            this.set(key, "1");
            return 1L;
        }
    }

    public Boolean setNX(final String key, final String value, final long liveTime) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                Boolean flag = connection.setNX(key.getBytes(), value.getBytes());
                connection.expire(key.getBytes(), liveTime);
                return flag;
            }
        });
    }
}
