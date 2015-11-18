package com.jiang.shanwe.dao;

import java.util.List;

import com.jiang.shanwe.model.User;

public interface UserDao {

    public User getUserById(int id) throws Exception;

    public User getUserByPhoneNum(String phoneNum) throws Exception;

    public List<User> findAllUser() throws Exception;

}
