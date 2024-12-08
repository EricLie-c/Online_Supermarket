package com.onlinesupermarket.mapper;

import com.onlinesupermarket.service.impl.MerchantServiceImpl;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MerchantMapper {

    @Delete("delete from orderlog where 订单号=#{ordernum}")
    void deleteFromOrderByordernum(String ordernum);

    @Update("update orderlog set 订单状态=1 where 订单号=#{ordernum}")
    void completeOrder(String ordernum);

    @Select("select 日期 as orderday, 订单号 as ordernum, 商品名 as pname, 客户名 as gname, 订单状态 as status, 金额 as price from orderlog where 订单号=#{ordernum}")
    List<MerchantServiceImpl.orderManagementResult> orderManagementByNum(String ordernum);

    @Select("select 日期 as orderday, 订单号 as ordernum, 商品名 as pname, 客户名 as gname, 订单状态 as status, 金额 as price from orderlog where 客户名=#{gname} limit #{start}, 20")
    List<MerchantServiceImpl.orderManagementResult> orderManagementByGname(String gname, Integer start);

    @Select("select 日期 as orderday, 订单号 as ordernum, 商品名 as pname, 客户名 as gname, 订单状态 as status, 金额 as price from orderlog where 订单状态=#{status} limit #{start}, 20")
    List<MerchantServiceImpl.orderManagementResult> orderManagementByStatus(Integer status, Integer start);

    @Select("select 日期 as orderday, 订单号 as ordernum, 商品名 as pname, 金额 as price from orderlog where 日期 like '${date}%' limit #{start}, 20")
    List<MerchantServiceImpl.orderManagementResult> statisticalForm(String date, Integer start);

    @Select("select 地址 from registry where 用户名=#{gname}")
    String getAddrByGname(String gname);

    @Select("select 客户名 as gname, sum(cast(金额 as float)) as consumption from orderlog group by 客户名 limit #{start}, 20")
    List<MerchantServiceImpl.orderManagementResult> guestManagement(Integer start);

    @Select("select 日期 as watchday, 客户名 as gname, 商品名 as pname from browsinghistory limit #{start}, 20")
    List<MerchantServiceImpl.orderManagementResult> guestLogWatch(Integer start);

    @Select("select 日期 as orderday, 客户名 as gname, 商品名 as pname, 数量 as nums, 金额 as amount from orderlog limit #{start}, 20")
    List<MerchantServiceImpl.orderManagementResult> guestLogBuy(Integer start);

    @Select("select 订单号 as ordernum, 日期 as orderday, 客户名 as gname, 商品名 as pname, 数量 as nums, 金额 as price, 订单状态 as status from orderlog limit #{start}, 20")
    List<MerchantServiceImpl.orderManagementResult> orderManagementAll(Integer start);

    @Delete("delete from browsinghistory where 客户名=#{gname}")
    void deleteFromBrowsingHistoryBygname(String gname);

    @Delete("delete from orderlog where 客户名=#{gname}")
    void deleteFromOrderBygname(String gname);

    @Insert("insert into product values (#{name}, #{price}, #{filePath}, #{time}, #{addr})")
    void addCommodity(String name, String price, String filePath, String time, String addr);

    @Delete("delete from product where name=#{name}")
    void deleteCommodity(String name);

    @Update("update product set name=#{name}, price=#{price}, picture=#{filePath}, deliv_time=#{time}, deliv_addr=#{addr} where name=#{pastname}")
    void changeCommodity(String pastname, String name, String price, String filePath, String time, String addr );
}
