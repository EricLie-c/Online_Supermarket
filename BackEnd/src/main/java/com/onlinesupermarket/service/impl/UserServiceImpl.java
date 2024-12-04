package com.onlinesupermarket.service.impl;

import com.onlinesupermarket.mapper.UserMapper;
import com.onlinesupermarket.pojo.User;
import com.onlinesupermarket.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Data
    private static class registryResult{
        private String status;
        private String account;

    }

    @Data
    private static class changeAccountResult{
        private String status;
        private String userName;

    }

    @Data
    private static class getUserName{
        private String userName;

    }

    @Override
    public Object registry(User user){

        Integer account =  userMapper.account();
        if(account==null) account=0;
        registryResult result = new registryResult();
        User.userN = user.getUserName();

        try {
            userMapper.registry(user.getUserName(), account + 1, user.getPassword(), user.getAddress(), user.getRole());
        }catch (DataAccessException | SQLException e){
            result.status = "0";
            return result;
        }
        result.status = "1";
        result.account = String.valueOf(account + 1);
        return result;
    }

    @Override
    public void deleteUser(String userName){

        User.userN = "";
        userMapper.deleteUserRegistry(userName);
        userMapper.deleteUserShopTrolley(userName);
        userMapper.deleteUserBrowsingHistory(userName);

    }

    @Override
    public Object changeAccount(User user){

        changeAccountResult result = new changeAccountResult();

        String validAccount =  userMapper.validAccount(user.getAccount());
        log.info(validAccount);
        if(validAccount == null) {
        //不能和空字符串比较。返回的根本不是空字符串，而是null
            result.status = "2";
            return result;
        }
        else{
            String validPassword = userMapper.validPassword(user.getAccount());
            if(!Objects.equals(validPassword, user.getPassword())){
                result.status = "3";
                return result;
            }
            else{
                String validRole = userMapper.validRole(user.getAccount());
                if(!Objects.equals(validRole, user.getRole())){
                    result.status = "4";
                    return result;
                }
                result.status = "1";
                User.userN = userMapper.getUserName(user.getAccount());
                result.userName = User.userN;
                return result;
            }
        }
    }

    @Override
    public getUserName getUserName(){
        getUserName getUserName = new getUserName();
        getUserName.userName = User.userN;
        return getUserName;
    }

}
