package com.onlinesupermarket.service.impl;

import com.onlinesupermarket.controller.MerchantController;
import com.onlinesupermarket.mapper.GuestMapper;
import com.onlinesupermarket.mapper.MerchantMapper;
import com.onlinesupermarket.pojo.Commodity;
import com.onlinesupermarket.pojo.Result;
import com.onlinesupermarket.service.MerchantService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private GuestMapper guestMapper;
    @Override
    public void deleteFromOrder(String ordernum){
        merchantMapper.deleteFromOrderByordernum(ordernum);
    }

    @Override
    public void completeOrder(String ordernum){
        merchantMapper.completeOrder(ordernum);
    }

    @Data
    @NoArgsConstructor
    public static class orderManagementResult{
        private String ordernum;
        private String orderday;
        private String pname;
        private String gname;
        private String address;
        private int status;
        private String price;
        private String consumption;
        private String watchday;
        private String nums;
        private String amount;
    }
    @Override
    public Result orderManagement(MerchantController.orderManagementRequest orderManagementRequest){
        log.info("订单管理页面");

        Result result = new Result();

        Integer start = (Integer.parseInt(orderManagementRequest.getPage()) - 1) * 20;

        if(orderManagementRequest.getOrdernum()!=null){
            List<orderManagementResult> orderManagementResult = merchantMapper.orderManagementByNum(orderManagementRequest.getOrdernum());
            if(orderManagementResult.size()==0){
                result.setCode("0");
                result.setMsg("failed");
                return result;
            }
            String address = merchantMapper.getAddrByGname(orderManagementResult.get(0).gname);
            orderManagementResult.get(0).address = address;
            result.setData(orderManagementResult);
        } else if(orderManagementRequest.getGname()!=null){
            List<orderManagementResult> orderManagementResult = merchantMapper.orderManagementByGname(orderManagementRequest.getGname(), start);
            if(orderManagementResult.size()==0){
                result.setCode("0");
                result.setMsg("failed");
                return result;
            }
            for(int i=0;i<orderManagementResult.size();i++)
            {
                String address = merchantMapper.getAddrByGname(orderManagementResult.get(i).gname);
                orderManagementResult.get(i).address = address;
            }
            result.setData(orderManagementResult);
        } else if (orderManagementRequest.getStatus()!=null) {
            Integer status;
            if(orderManagementRequest.getStatus().equals("已完成")){
                status = 1;
            }
            else status = 0;
            List<orderManagementResult> orderManagementResult = merchantMapper.orderManagementByStatus(status, start);
            if(orderManagementResult.size()==0){
                result.setCode("0");
                result.setMsg("failed");
                return result;
            }
            for(int i=0;i<orderManagementResult.size();i++)
            {
                String address = merchantMapper.getAddrByGname(orderManagementResult.get(i).gname);
                orderManagementResult.get(i).address = address;
            }
            result.setData(orderManagementResult);
        }else{
            List<orderManagementResult> orderManagementResult = merchantMapper.orderManagementAll(start);
            if(orderManagementResult.size()==0){
                result.setCode("0");
                result.setMsg("failed");
                return result;
            }
            for(int i=0;i<orderManagementResult.size();i++)
            {
                String address = merchantMapper.getAddrByGname(orderManagementResult.get(i).gname);
                orderManagementResult.get(i).address = address;
            }
            result.setData(orderManagementResult);
        }
        result.setCode("1");
        result.setMsg("success");
        return result;
    }

    @Override
    public Result statisticalForm(MerchantController.orderManagementRequest orderManagementRequest){
        log.info("统计报表页面");
        String date;
        if (Integer.parseInt(orderManagementRequest.getMonth()) <10){
            date =  orderManagementRequest.getYear() + "-0" + orderManagementRequest.getMonth();
        }
        else{
            date = orderManagementRequest.getYear() + "0" +orderManagementRequest.getMonth();
        }

        log.info(date);
        Integer start = (Integer.parseInt(orderManagementRequest.getPage()) - 1) * 20;
        log.info(String.valueOf(start));
        Result result = new Result();
        List<orderManagementResult> orderManagementResults = merchantMapper.statisticalForm(date, start);
        result.setMsg("success");
        result.setCode("1");
        result.setData(orderManagementResults);
        return result;
    }

    @Override
    public Result guestManagement(MerchantController.orderManagementRequest orderManagementRequest){
        Result result = new Result();
        Integer start = (Integer.parseInt(orderManagementRequest.getPage()) - 1) * 20;
        result.setCode("1");
        result.setMsg("success");
        List<orderManagementResult> orderManagementResult = merchantMapper.guestManagement(start);
        for(int i=0;i<orderManagementResult.size();i++)
        {
            String address = merchantMapper.getAddrByGname(orderManagementResult.get(i).gname);
            DecimalFormat df=new DecimalFormat("0.00");
            orderManagementResult.get(i).consumption = df.format(Float.parseFloat(orderManagementResult.get(i).consumption));
            orderManagementResult.get(i).address = address;
        }
        result.setData(orderManagementResult);
        return result;
    }

    @Override
    public Result guestLog(MerchantController.orderManagementRequest orderManagementRequest){
        log.info("客户日志");
        List<orderManagementResult> orderManagementResults;
        Integer start = (Integer.parseInt(orderManagementRequest.getPage()) - 1) * 20;
        if (Objects.equals(orderManagementRequest.getWatchOrBuy(), "watch")){
            orderManagementResults = merchantMapper.guestLogWatch(start);
        }else{
            orderManagementResults = merchantMapper.guestLogBuy(start);
        }
        Result result = new Result();
        result.setCode("1");
        result.setMsg("success");
        result.setData(orderManagementResults);
        return result;
    }

    @Override
    public void deleteGuest(String gname){
        merchantMapper.deleteFromOrderBygname(gname);
        merchantMapper.deleteFromBrowsingHistoryBygname(gname);
    }

    @Override
    public void addCommodity(MerchantController.changeCommodityRequest changeCommodityRequest){
        try {

            log.info(String.format("添加商品%s", changeCommodityRequest.getFile().getOriginalFilename()));
            String filePath = "http://113.44.84.103:8080/images/" + changeCommodityRequest.getFile().getOriginalFilename();
            log.info(String.format("商品路径%s", filePath));

//            File imageFolder = new File("http://localhost:8080/images");
//            File imageFile = new File(imageFolder, filePath);
//            if(!imageFile.getParentFile().exists())
//                imageFile.getParentFile().mkdirs();

//            MultipartFile image = changeCommodityRequest.getFile();
//            System.out.println(image.getContentType());
            // 新建的File默认在C:\Users\Admin\AppData\Local\Temp\tomcat.8080.16221729839011319990\work\Tomcat\localhost\ROOT下
            // 一开始我用的路径是http://localhost:8080前缀，导致无法识别成地址，于是保存在默认路径下，把原来的路径参数认为是文件名加在后面。因此报错
            // 直接指定路径前缀（D:/images/）就能识别了
            // File建立的文件不能识别网络映射路径，只能识别真实硬盘路径
            changeCommodityRequest.getFile().transferTo(new File("/home/images/"+changeCommodityRequest.getFile().getOriginalFilename()));
            merchantMapper.addCommodity(changeCommodityRequest.getName(), changeCommodityRequest.getPrice(), filePath, changeCommodityRequest.getTime(), changeCommodityRequest.getAddr());

        } catch (IOException e) {
            log.info("ERROR:",e);
        }
    }

    @Override
    public void deleteCommodity(MerchantController.changeCommodityRequest changeCommodityRequest){
        merchantMapper.deleteCommodity(changeCommodityRequest.getName());
    }

    @Override
    public void editCommodity(MerchantController.changeCommodityRequest changeCommodityRequest){
        try {
            String filePath = "http://113.44.84.103:8080/images/" + changeCommodityRequest.getFile().getOriginalFilename();
            changeCommodityRequest.getFile().transferTo(new File("/home/images/"+changeCommodityRequest.getFile().getOriginalFilename()));
            //这里还有一点坑。。埋起来有点麻烦。就是要判断每一项是不是空的
            log.info("原名称:"+changeCommodityRequest.getPastname());
            merchantMapper.changeCommodity(changeCommodityRequest.getPastname(), changeCommodityRequest.getName(), changeCommodityRequest.getPrice(), filePath, changeCommodityRequest.getTime(), changeCommodityRequest.getAddr());

        } catch (IOException e) {
            log.info("ERROR:", e);
        }
    }

}
