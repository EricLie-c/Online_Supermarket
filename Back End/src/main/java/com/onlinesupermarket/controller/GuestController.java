package com.onlinesupermarket.controller;

import com.onlinesupermarket.pojo.Commodity;
import com.onlinesupermarket.pojo.Guest;
import com.onlinesupermarket.pojo.Result;
import com.onlinesupermarket.service.GuestService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "*",allowCredentials = "true") //解决跨域问题 跨域会先发一个询问请求options，默认是拒绝的
@Slf4j
@RestController
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping("/guestView")
    public Result guestView(@RequestBody Guest page){
        log.info("展示商品列表");

        //调用service层获取商品数据
        List<Commodity> commodityList =  guestService.guestView(page.getPage());

        return Result.success(commodityList);
    }

    //guestparam只能一对一，guestbody可以多对一
    @GetMapping("/addInTrolley")
    public Object addInTrolley(@RequestParam String name){
        log.info("加入购物车");
        log.info(name);
        return guestService.addInTrolley(name);
    }

    @GetMapping("/guestBrows")
    public void guestBrows(@RequestParam String pname){
        log.info("客户浏览了" + pname);
        guestService.guestBrows(pname);
    }

    @GetMapping("/shopTrolleyDetail")
    public Object shopTrolleyDetail(@RequestParam String userName){
        log.info("查看购物车");
        Result result = new Result();
        result.setData(guestService.shopTrolleyDetail(userName));
        return result;
    }

    @GetMapping("/deleteFromTrolley")
    public void deleteFromTrolley(@RequestParam String name){
        log.info("删除购物车条目");
        guestService.deleteFromTrolley(name);

    }

//    JSONObject buyObject = new JSONObject();
    @Data
    private static class buyObject{
        private String name;
        private String nums;
}
    @Data
    private static class buyObjectResult{
        private String amount;
    }


    @PostMapping("/buy")
    public Object buy(@RequestBody buyObject buyObj){
        log.info("购买" + buyObj.getName());
        String name = buyObj.getName();
        String nums = buyObj.getNums();
        String amount = guestService.buy(name, nums);
        buyObjectResult result = new buyObjectResult();
        result.amount = amount;
        return result;
    }


    @Data
    private static class getCommodityRequest{
        private String pname;
        private String address;
    }

//    getCommodityRequest getCommodityRequest = new getCommodityRequest();
    @PostMapping("/getCommodity")
    public Object getCommodity (@RequestBody getCommodityRequest getCommodityRequest){

        if(getCommodityRequest.pname!=null)
        {
            log.info("搜索商品" + getCommodityRequest.pname);
            String pname = getCommodityRequest.pname;
            return guestService.getCommodityByName(pname);
        }
        else{
            log.info("搜索地址" + getCommodityRequest.address);
            String addr = getCommodityRequest.address;
            return guestService.getCommodityByAddr(addr);
        }
    }

    @GetMapping("/guestOrderStatus")
    public Object guestOrderStatus(@RequestParam String userName){
        return guestService.guestOrderStatus(userName);
    }

}
