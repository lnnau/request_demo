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

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收用户数据
        String username = request.getParameter("username");
        username = new String(username.getBytes("iso-8859-1"),"utf-8");
        String password = request.getParameter("password");

        //封装用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //2.调用mybatis，和数据库建立连接
        //2.1加载mybatis核心配置文件，获取SqlSessionFactory对象
       /* String resource = "mybatis-config.xml";     //需要根据实际修改
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);*/

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.2获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);    //自动提交事务

        //2.3获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

         //2.4调用方法
        User user1 = mapper.selectByName(username);

        //3.判断用户名是否存在，即username是否为null
        //设置响应数据的字符集，和返回格式
        response.setContentType("text/html;charset=utf-8");
        if(user1!=null) {
            //用户已存在，返回提示信息
            PrintWriter writer = response.getWriter();
            writer.write("用户已存在");
        } else {
            //用户不存在,插入数据，给出提示
            System.out.println(user.getUsername());
            mapper.add(user);
            response.getWriter().write("注册成功");
            //释放资源  已经自动提交事务
            sqlSession.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
