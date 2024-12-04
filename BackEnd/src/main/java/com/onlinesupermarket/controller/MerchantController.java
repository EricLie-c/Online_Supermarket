package com.onlinesupermarket.controller;

import com.onlinesupermarket.pojo.Result;
import com.onlinesupermarket.service.MerchantService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@CrossOrigin(originPatterns = "*",allowCredentials = "true") //解决跨域问题 跨域会先发一个询问请求options，默认是拒绝的
@Slf4j
@RestController
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping("/deleteFromOrder")
    public void deleteFromOrder(@RequestParam String ordernum){
        merchantService.deleteFromOrder(ordernum);
    }

    @GetMapping("/completeOrder")
    public void completeOrder(@RequestParam String ordernum){
        merchantService.completeOrder(ordernum);
    }

    @NoArgsConstructor
    @Data
    public static class orderManagementRequest{
        private String ordernum;
        private List<String> orderdayperiod;
        private String gname;
        private String status;
        private String month;
        private String year;
        private String watchOrBuy;
        private String forWhat;
        private String page;
    }

    @Data
    public static class changeCommodityRequest{
        private MultipartFile file;
        private String name;
        private String price;
        private String addr;
        private String status;
        private String time;
        private String pastname;
    }

    @PostMapping("/orderManagement")
    public Result orderManagement(@RequestBody orderManagementRequest orderManagementRequest){
        if(Objects.equals(orderManagementRequest.forWhat, "orderManagement")){
            return merchantService.orderManagement(orderManagementRequest);

        } else if(Objects.equals(orderManagementRequest.forWhat, "statisticalForm")){
            return merchantService.statisticalForm(orderManagementRequest);

        } else if (Objects.equals(orderManagementRequest.forWhat, "guestManagement")) {
            return merchantService.guestManagement(orderManagementRequest);

        }
            return merchantService.guestLog(orderManagementRequest);

    }

    @GetMapping("/deleteGuest")
    public void deleteGuest(@RequestParam String gname){
        log.info("删除客户");
        log.info(gname);
        merchantService.deleteGuest(gname);
    }


    @PostMapping("/changeCommodity")
    public void changeCommodity(changeCommodityRequest changeCommodityRequest){
        if(Objects.equals(changeCommodityRequest.status, "add")){
            merchantService.addCommodity(changeCommodityRequest);
        }
        else if(Objects.equals(changeCommodityRequest.status, "delete")){
            merchantService.deleteCommodity(changeCommodityRequest);
        }
        else{
            merchantService.editCommodity(changeCommodityRequest);
        }
    }

}
