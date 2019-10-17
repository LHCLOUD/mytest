package dao.impl;

import dao.ProvinceDao;
import domain.Province;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

/**
 * @Author:Nomi
 * @Date: 2019/9/7 9:24
 * @Description:
 */
public class ProvinceDaoImpl implements ProvinceDao {
    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        String sql="select * from province";

        List<Province> list = jt.query(sql, new BeanPropertyRowMapper<Province>(Province.class));

        return list;
    }
}
