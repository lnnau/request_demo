package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Select("select *from tb_user where username=#{username} and password=#{password}")
    User select(@Param("username")String username,@Param("password") String password);

    /**
     * 用户注册，用户名重复
     */
    @Select("select *from tb_user where username=#{username}")
    User selectByName(String username);

    /**
     * 用户成功注册
     */
    @Insert("insert into tb_user values (null,#{username},#{password})")
    void add(User user);
}
