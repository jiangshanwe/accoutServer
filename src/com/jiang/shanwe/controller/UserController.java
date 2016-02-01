package com.jiang.shanwe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiang.shanwe.dao.UserDao;
import com.jiang.shanwe.model.User;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/user")
    @ResponseBody
    public String user(@RequestParam(value = "id", defaultValue = "5") int id) throws Exception {
        return userDao.getUserById(id).getRegistedTime().getTime() + userDao.getUserById(id).getPhoneNum();
    }

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public String getUser(@RequestParam(value = "phoneNum", required = false) String phoneNum) {
        JSONObject userInfoObject = new JSONObject();
        if (phoneNum == null || phoneNum.isEmpty()) {
            userInfoObject.put("status", -1);
            return userInfoObject.toString(); // 未输入手机号
        }
        try {
            User user = userDao.getUserByPhoneNum(phoneNum);
            if (user != null && user.getStatus() == 1) {
                userInfoObject.put("status", 1);
                userInfoObject.put("info", user); // 存在手机用户
            } else {
                userInfoObject.put("status", 0); // 手机号不存在
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfoObject.toString();
    }

}
