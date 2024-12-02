package com.onlinesupermarket.mapper;

import com.onlinesupermarket.pojo.Commodity;
//import com.onlinesupermarket.service.impl.GuestServiceImpl;
import com.onlinesupermarket.service.impl.GuestServiceImpl;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//public class sql{
//    static Random random = new Random();
//    // 生成一个介于0（包括）和10（不包括）之间的随机整数
//    static int randomNumber = random.nextInt(15);
//    static int apagenum = 30;
//
////    String sql = String.format("select * from shopping_mall limit %d, %d", randomNumber, apagenum);
//
//    public static final char[] sql = String.format("select * from shopping_mall limit %d, %d", randomNumber, apagenum).toCharArray();
//}
@Mapper
public interface GuestMapper {


//    String sql = String.format("select * from shopping_mall limit %d, %d", randomNumber, apagenum);

//    public static final char[] sql = String.format("select * from shopping_mall limit %d, %d", randomNumber, apagenum).toCharArray();

   //随机展示全部商品列表
    @Select("select * from product limit #{start}, 6")
    List<Commodity> guestView(Integer start);

    @Select("select 客户名 from shoptrolley where 客户名=#{userN} and 商品名=#{name}")
    String validTrolley(String name, String userN);

    @Insert("insert into shoptrolley (商品名, 客户名) values (#{name}, #{userName})")
    void addInTrolley(String name, String userName);

    @Insert("insert into browsinghistory (商品名, 日期, 客户名) values (#{pname}, #{date}, #{guestName})")
    void guestBrows(String pname, String date, String guestName);

    @Select("select 商品名 from shoptrolley as name where 客户名=#{userName}")
    List<String> shopTrolleyDetail(String userName);

    @Select("select price from product where name=#{pname}")
    String getPrice(String pname);

    @Delete("delete from shoptrolley where 商品名=#{name} and 客户名=#{userName}")
    void deleteFromTrolley(String name, String userName);

    @Select("select max(cast(订单号 as unsigned)) from orderlog")
    Integer ordernum();

    @Insert("insert into orderlog (日期, 商品名, 客户名, 数量, 订单状态, 金额, 订单号) values (#{tm}, #{name}, #{userN}, #{nums}, #{status}, #{amount}, #{ordernum})")
    void buy(String tm, String name, String userN, String nums, String status, String amount, Integer ordernum);

    @Select("select * from product where name like '%${pname}%' ")
    List<Commodity> getCommodityByName(String pname);

    @Select("select * from product where deliv_addr=#{addr} ")
    List<Commodity> getCommodityByAddr(String addr);

    @Select("select 日期 as date, 订单号 as orderID, 金额 as price, 订单状态 as status, 数量 as count from orderlog where 客户名=#{userName}")
    List<GuestServiceImpl.guestOrderStatusRes> guestOrderStatus(String userName);

    @Select("select 商品名 as orderID from orderlog where 订单号=#{orderID}")
    String getNamesbyOrdernum(String orderID);
}
