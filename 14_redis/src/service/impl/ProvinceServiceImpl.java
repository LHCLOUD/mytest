package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ProvinceDao;
import dao.impl.ProvinceDaoImpl;
import domain.Province;
import jedis.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;
import service.ProvinceService;

import java.util.List;

/**
 * @Author:Nomi
 * @Date: 2019/9/7 9:26
 * @Description:
 */
public class ProvinceServiceImpl implements ProvinceService {
    ProvinceDao dao = new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    /**
     * 使用redis缓存
     *
     * @return
     */
    @Override
    public String findAllJson() {
        Jedis jedis = JedisPoolUtils.getJedis();
        String json = jedis.get("province");
        if (json == null || json.length() == 0) {
            System.out.println("redis中没有数据,从数据库中查询");

            List<Province> list = dao.findAll();
            ObjectMapper mapper = new ObjectMapper();
            try {
                json = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("province", json);
            jedis.close();
        }
        return json;
    }
}
