package com.itheima.web;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2. 调用mapper查询，返回User对象
        //2.1加载mybatis核心配置文件，获取SqlSessionFactory对象
        /*String resource = "mybatis-config.xml";     //需要根据实际修改
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);*/

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.2获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //2.3获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //2.4执行sql
        User user = mapper.select(username, password);
        //2.5释放资源
        sqlSession.close();
        //解决中文乱码和返回格式
        response.setContentType("text/html;charset=utf-8");
        //获取响应字符流对象
        PrintWriter writer = response.getWriter();
        //3，判断User对象是否为null
//        writer.write(user.getUsername());
        if(user!=null) {
            writer.write("<h1>登录成功</h1>");
        } else {
            writer.write("<h1>登录失败</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
