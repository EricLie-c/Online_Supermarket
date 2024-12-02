package com.onlinesupermarket.service;


//import com.onlinesupermarket.controller.GuestController;
import com.onlinesupermarket.pojo.Commodity;

import java.util.List;

public interface GuestService {

    //展示商品列表
    List<Commodity> guestView(String page);

    Object addInTrolley(String name);

    void guestBrows(String pname);

    List<Commodity> shopTrolleyDetail(String userName);

    void deleteFromTrolley(String name);

    String buy(String name, String nums);

    Object getCommodityByName(String pname);

    Object getCommodityByAddr(String addr);

    Object guestOrderStatus(String userName);
}
