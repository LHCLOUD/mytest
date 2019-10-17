package jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author:Nomi
 * @Date: 2019/9/6 21:46
 * @Description:
 */
public class JedisPoolUtils {
    private static JedisPool jedisPool;

    static {
        //读取配置文件
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建Properties对象
        Properties prop = new Properties();
        try {
            //关联文件
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取数据,设置到JdedisPoolConfig中
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(prop.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(prop.getProperty("maxIdle")));

        jedisPool = new JedisPool(config, prop.getProperty("host"), Integer.parseInt(prop.getProperty("port")));
    }

    /**
     * 获取连接方法
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

}
