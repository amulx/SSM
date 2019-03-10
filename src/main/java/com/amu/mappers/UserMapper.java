package com.amu.mappers;

import com.amu.Entities.NewsInfo;
import com.amu.Entities.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM users_reg WHERE userid = #{userId}")
    public UserInfo getUserDetail(@Param("userId") int abc);

    @Select("select * from users_reg")
    public List<UserInfo> getUserList();

    @Insert("insert into users_reg (username,useremail) values (#{username},#{useremail})")
    @SelectKey(statement = "select LAST_INSERT_ID()",keyProperty = "userid",before = false,resultType = Integer.class)
    public int addUser(UserInfo userInfo);

    @Update("update users_reg set username=#{user.username},useremail=#{user.useremail} where userid=#{user.userid}")
    public int updateUser(@Param("user") UserInfo userInfo);

    @Delete("delete from users_reg where userid=#{userid}")
    public int deleteUser(@Param("userid") int abc);

}