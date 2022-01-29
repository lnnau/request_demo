package com.itheima.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*解决中文乱码*/
@WebServlet("/req3")
public class RequestDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.解决中文乱码 Post
        req.setCharacterEncoding("UTF-8");  //根据网页的编码设置
        //2.获取username
        String username = req.getParameter("username");
        System.out.println(username);

        //3.解决中文乱码 post
//        //3.1将乱码数据编码为字节数据
//        byte[] bytes = username.getBytes("iso-8859-1");
//        //3.2将字节数据解码为utf-8
//        username = new String(bytes, "utf-8");
        username=new String(username.getBytes("iso-8859-1"),"utf-8");
        System.out.println(username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
