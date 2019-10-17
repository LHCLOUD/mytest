package jedis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class Demo {

    @Test
    public void test1() {
        Jedis jedis = utill.getJedis();
        jedis.set("name", "lh");
        System.out.println(jedis.get("name"));
        jedis.close();
    }
}
