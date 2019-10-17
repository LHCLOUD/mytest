package service;

import domain.Province;

import java.util.List;

/**
 * @Author:Nomi
 * @Date: 2019/9/7 9:25
 * @Description:
 */
public interface ProvinceService {

    public List<Province> findAll();

    public String findAllJson();
}
