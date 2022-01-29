package com.itheima.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/req2")
public class RequestDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get请求逻辑
        //1.获取所有参数的map集合
        Map<String, String[]> map = req.getParameterMap();
        //2.遍历集合
        for(String key:map.keySet()) {
            System.out.print(key+":");
            //获取值
            String[] values = map.get(key);
            for(String value:values) {
                System.out.print(value);
            }
            System.out.println();
        }

        //获取值的数组
        String[] hobbies = req.getParameterValues("hobby");
        for(String value:hobbies) {
            System.out.println(value+" ");
        }

        //获取单个值
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //直接调用get的请求逻辑
        doGet(req,resp);
    }
}
