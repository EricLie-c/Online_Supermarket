package com.onlinesupermarket.controller;

import com.onlinesupermarket.pojo.Merchant;
import com.onlinesupermarket.pojo.User;
import com.onlinesupermarket.service.GuestService;
import com.onlinesupermarket.service.MerchantService;
import com.onlinesupermarket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "*",allowCredentials = "true") //解决跨域问题 跨域会先发一个询问请求options，默认是拒绝的
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registry")
    public Object registry(@RequestBody User user){
        log.info("注册账号");
        log.info(user.getUserName());
        log.info(user.getRole());
        return userService.registry(user);
    }

    @GetMapping("/deleteUser")
    public void deleteUser(@RequestParam String userName){
        log.info("注销账号");
        userService.deleteUser(userName);
    }

    @PostMapping("/changeAccount")
    public Object changeAccount(@RequestBody User user){
        log.info("切换账号");
        return userService.changeAccount(user);
    }

    @GetMapping("/username")
    public Object getUserName(){
        log.info("获取用户名");
        return userService.getUserName();
    }

}
