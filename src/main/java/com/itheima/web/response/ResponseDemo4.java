package com.itheima.web.response;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * response响应字节数据
 */
@WebServlet("/resp4")
public class ResponseDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取文件输入流
        FileInputStream fis = new FileInputStream("d:/1.jpg");
        
        //2.response响应字节数据
        ServletOutputStream os = response.getOutputStream();

        //3.将输入的文件复制到响应体中
        /*byte[] by = new byte[1024];
        int len;
        while((len=fis.read(by))!=-1) {
            os.write(by,0,len);
        }*/
        //通过工具类简化copy操作
        IOUtils.copy(fis,os);

        //关闭输入流
        fis.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
