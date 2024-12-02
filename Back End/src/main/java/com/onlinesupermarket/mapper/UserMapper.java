package com.onlinesupermarket.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select max(cast(账号 as unsigned)) from registry")
    Integer account();
    @Insert("insert into registry (用户名, 账号, 密码, 地址, 角色) values (#{userName}, #{account}, #{password}, #{address}, #{role});")
    void registry(String userName, Integer account, String password, String address, String role) throws SQLException;

    @Delete("delete from registry where 用户名=#{userName}")
    void deleteUserRegistry(String userName);

    @Select("select 用户名 from registry where 账号=#{account}")
    String validAccount(String account);

    @Select("select 密码 from registry where 账号=#{account}")
    String validPassword(String account);

    @Select("select 用户名 from registry where 账号=#{account}")
    String getUserName(String account);

    @Select("select 角色 from registry where 账号=#{account}")
    String validRole(String account);

    @Delete("delete from shoptrolley where 客户名=#{userName}")
    void deleteUserShopTrolley(String userName);

    @Delete("delete from browsinghistory where 客户名=#{userName}")
    void deleteUserBrowsingHistory(String userName);
}
