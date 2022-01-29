package com.itheima.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
@WebServlet("/req1")
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String getMethod: 获取请求方式  GET
        String method = req.getMethod();
        System.out.println(method);
        //String getContexPath():   获取虚拟目录（项目访问路径）/request_demo
        String contextPath = req.getContextPath();
        System.out.println(contextPath);

        //StringBUffer getRequestURL(): 获取URL（统一资源定位符）:http://localhost:8080/request_demo/req1
        StringBuffer requestURL = req.getRequestURL();
        System.out.println(requestURL);

        //String getRequestURI():   获取URI（同一资源标识符）：/request_demo/req1
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);

        //String getQueryString():  过去请求参数（Get方式） username=zahngsan&password=123
        String queryString = req.getQueryString();
        System.out.println(queryString);


        //获取请求头 浏览器版本信息
        String header = req.getHeader("user-agent");
        System.out.println(header);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取post请求体

        //1.获取输入流
        BufferedReader reader = req.getReader();
        //2.读取数据
        String readLine = reader.readLine();
        System.out.println(readLine);

    }
}
