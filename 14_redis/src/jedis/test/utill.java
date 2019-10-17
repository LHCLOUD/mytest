package jedis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

public class utill {
    private static JedisPool pool;

    static {
        Properties prop = new Properties();
        try {
            prop.load(utill.class.getClassLoader().getResourceAsStream("jedis.properties"));

            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(prop.getProperty("maxTotal")));
            config.setMaxIdle(Integer.parseInt(prop.getProperty("maxIdle")));
            pool = new JedisPool(config, prop.getProperty("host"), Integer.parseInt(prop.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }
}
