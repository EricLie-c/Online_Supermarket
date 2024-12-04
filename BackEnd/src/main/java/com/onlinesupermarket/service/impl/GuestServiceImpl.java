package com.onlinesupermarket.service.impl;

import com.onlinesupermarket.mapper.GuestMapper;
import com.onlinesupermarket.pojo.Commodity;
import com.onlinesupermarket.pojo.Result;
import com.onlinesupermarket.pojo.User;
import com.onlinesupermarket.service.GuestService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Slf4j
@Service
public class GuestServiceImpl implements GuestService {

    @Data
    private static class addInTrolleyResult{
        private String code;
    }

//    @Data
//    private static class data{
//        private String name;
//        private String price;
//    }
//
//    @Data
//    private static class shopTrolleyDetailResult{
//        data result;
//    }

//    @Data
//    private static class getCommodityResult{
//        private List<Commodity> data;
//    }



    @Autowired
    private GuestMapper guestMapper;

    //展示商品列表 实现类
    @Override
    public List<Commodity> guestView(String page)  {
        Integer p = Integer.valueOf(page);

        Random random = new Random();
        // 生成一个介于0（包括）和10（不包括）之间的随机整数
        int randomNumber = random.nextInt(6);
        int start = p*randomNumber*6;
        List<Commodity> commodityList = guestMapper.guestView(start);

        for (int i=0;i<commodityList.size();i++)
        {
            String url = commodityList.get(i).getPicture();
            if(url.charAt(0) != 'h'){
                url = "https:" + url.substring(23, url.length()-3);
            }
            commodityList.get(i).setPicture(url);
            log.info(url);
        }
        return commodityList;
    }

    @Override
    public Object addInTrolley (String name){

        addInTrolleyResult result = new addInTrolleyResult();

        String username = guestMapper.validTrolley(name, User.userN);
        if(username!=null){
            result.code = "failed";
            return result;
        }
        else{

            result.code = "successful";
            guestMapper.addInTrolley(name, User.userN);
            return result;
        }
    }

    @Override
    public void guestBrows(String pname){
        String date = String.valueOf(LocalDateTime.now());
        String guestName = User.userN;
        guestMapper.guestBrows(pname, date, guestName);
    }

    @Override
    public List<Commodity> shopTrolleyDetail(String userName){

        List<Commodity> result = new ArrayList<Commodity>();
        List<String> pname = guestMapper.shopTrolleyDetail(userName);
        for(int i=0;i<pname.size();i++){
            Commodity temp = new Commodity();
            temp.setName(pname.get(i));

            String price = guestMapper.getPrice(pname.get(i));
            temp.setPrice(price);
            result.add(temp);
        }
        return result;
    }

    @Override
    public void deleteFromTrolley(String name){
        log.info(name);
        guestMapper.deleteFromTrolley(name, User.userN);
    }

    @Override
    public String buy(String name, String nums){
        log.info(name);
        String tm = String.valueOf(LocalDateTime.now());
        String price = guestMapper.getPrice(name);
        Integer ordernum = guestMapper.ordernum();
        if(ordernum==null) ordernum = 0;
        Float amount = Float.parseFloat(price)*Float.parseFloat(nums);
        guestMapper.buy(tm, name, User.userN, nums,"0",  String.valueOf(amount), ordernum + 1);
        return String.valueOf(amount);
    }

    @Override
    public Object getCommodityByName(String pname){
        log.info(pname);
        List<Commodity> temp = guestMapper.getCommodityByName(pname);
        for (int i=0;i<temp.size();i++)
        {
            String url = temp.get(i).getPicture();
            if(url.charAt(0) != 'h'){
                url = "https:" + url.substring(23, url.length()-3);
            }
            temp.get(i).setPicture(url);
//            log.info(url);
        }
        Result result = new Result();
        result.setData(temp) ;
        return result;
    }

    @Override
    public Object getCommodityByAddr(String addr){
        log.info(addr);
        List<Commodity> temp = guestMapper.getCommodityByAddr(addr);
        for (int i=0;i<temp.size();i++)
        {
            String url = temp.get(i).getPicture();
            if(url.charAt(0) != 'h'){
                url = "https:" + url.substring(23, url.length()-3);
            }
            temp.get(i).setPicture(url);
//            log.info(url);
        }
        Result result = new Result();
        result.setData(temp) ;
        return result;
    }

    @Data
    public static class guestOrderStatusRes{
        private String date;
        private String orderID;
        private String address;
        private String price;
        private Integer status;
        private String pname;
        private String count;
        private String time;
    }
    @Data
    private static class guestOrderStatusResult{
        private List<guestOrderStatusRes> data;
    }


    @Override
    public Object guestOrderStatus(String userName){
        log.info("客户查询订单状态");

        List<guestOrderStatusRes> temp = guestMapper.guestOrderStatus(userName);

        log.info(String.valueOf(temp.size()));

        for(int i=0;i<temp.size();i++)
        {
            String pname = guestMapper.getNamesbyOrdernum(temp.get(i).orderID);
            log.info(pname);
            List<Commodity> list = guestMapper.getCommodityByName(pname);
            temp.get(i).pname = pname;
            temp.get(i).time = list.get(0).getDelivTime();
            temp.get(i).address = list.get(0).getDelivAddr();
        }

        guestOrderStatusResult result = new guestOrderStatusResult();

        result.data = temp;

        return result;

    }



}
