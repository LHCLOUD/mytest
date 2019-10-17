package web.servlet;

import service.ProvinceService;
import service.impl.ProvinceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:Nomi
 * @Date: 2019/9/7 9:32
 * @Description:
 */
@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        //调用service查询
        ProvinceService service = new ProvinceServiceImpl();
/*
    方法一 :在数据库中查询
        List<Province> list = service.findAll();

        //响应
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);

        response.getWriter().print(json);*/

        //方法2:在redis中查询
        String json = service.findAllJson();
        response.getWriter().print(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
