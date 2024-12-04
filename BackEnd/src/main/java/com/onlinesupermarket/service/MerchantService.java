package com.onlinesupermarket.service;


import com.onlinesupermarket.controller.MerchantController;
import com.onlinesupermarket.pojo.Result;
import org.apache.ibatis.annotations.Delete;

public interface MerchantService {

    void deleteFromOrder(String ordernum);

    void completeOrder(String ordernum);

    Result orderManagement(MerchantController.orderManagementRequest orderManagementRequest);

    Result statisticalForm(MerchantController.orderManagementRequest orderManagementRequest);

    Result guestManagement(MerchantController.orderManagementRequest orderManagementRequest);

    Result guestLog(MerchantController.orderManagementRequest orderManagementRequest);

    void deleteGuest(String gname);

    void addCommodity(MerchantController.changeCommodityRequest changeCommodityRequest);

    void deleteCommodity(MerchantController.changeCommodityRequest changeCommodityRequest);

    void editCommodity(MerchantController.changeCommodityRequest changeCommodityRequest);
}
