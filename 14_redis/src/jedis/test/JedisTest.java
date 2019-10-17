package jedis.test;

import jedis.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author:Nomi
 * @Date: 2019/9/6 20:50
 * @Description:
 */
public class JedisTest {

    /**
     * 快速入门
     */
    @Test
    public void test1() {
        //1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);

        //2.操作
        jedis.set("name", "nomi");

        //3.关闭连接
        jedis.close();
    }

    /**
     * string数据结构操作
     */
    @Test
    public void test2() {
        //1.获取连接
        Jedis jedis = new Jedis();

        jedis.setex("name", 10, "nomi");
        String name = jedis.get("name");
        System.out.println(name);

        //3.关闭连接
        jedis.close();
    }

    /**
     * hash 数据结构操作
     */
    @Test
    public void test3() {
        //1.获取连接
        Jedis jedis = new Jedis();

        jedis.hset("user", "name", "nomi");
        jedis.hset("user", "age", "18");
        jedis.hset("user", "gender", "男");

        String age = jedis.hget("user", "age");

        Map<String, String> user = jedis.hgetAll("user");
        Set<String> set = user.keySet();
        for (String key : set) {
            System.out.println(key + ":" + user.get(key));
        }

        //3.关闭连接
        jedis.close();
    }

    /**
     * list 数据结构操作
     */
    @Test
    public void test4() {
        //1.获取连接
        Jedis jedis = new Jedis();

        jedis.lpush("list", "a", "b", "c");
        jedis.rpush("list", "z", "x", "f");

        List<String> list = jedis.lrange("list", 0, -1);
        System.out.println(list);

        //3.关闭连接
        jedis.close();
    }

    /**
     * set 数据结构操作
     */
    @Test
    public void test5() {
        //1.获取连接
        Jedis jedis = new Jedis();

        jedis.sadd("set", "a", "b", "c");
        Set<String> set = jedis.smembers("set");
        System.out.println(set);

        //3.关闭连接
        jedis.close();
    }

    /**
     * sortedset 数据结构操作
     */
    @Test
    public void test6() {
        //1.获取连接
        Jedis jedis = new Jedis();

        jedis.zadd("sort", 3, "后羿");
        jedis.zadd("sort", 50, "韩信");
        jedis.zadd("sort", 30, "猴子");

        Set<String> sort = jedis.zrange("sort", 0, -1);
        System.out.println(sort);
        //3.关闭连接
        jedis.close();
    }

    /**
     * jedis连接池使用
     */
    @Test
    public void test7() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);

        //创建jedis连接池对象
        JedisPool jedisPool = new JedisPool(config, "localhost", 6379);

        Jedis jedis = jedisPool.getResource();
        jedis.set("name", "lh");
        System.out.println(jedis.get("name"));
        jedis.close();
    }

    /**
     * jedis连接池工具类使用
     */
    @Test
    public void test8() {
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.set("name", "lh");
        System.out.println(jedis.get("name"));
        jedis.close();
    }
}
