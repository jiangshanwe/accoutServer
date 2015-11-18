package com.jiang.shanwe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.jiang.shanwe.model.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    @Override
    public User getUserById(int id) throws Exception {
        SqlSession sqlSession = this.getSqlSession();
        User user = sqlSession.selectOne("getUserById", id);
        return user;
    }

    @Override
    public User getUserByPhoneNum(String phoneNum) throws Exception {
        SqlSession sqlSession = this.getSqlSession();
        User user = sqlSession.selectOne("getUserByPhoneNum", phoneNum);
        return user;
    }

    @Override
    public List<User> findAllUser() throws Exception {
        SqlSession sqlSession = this.getSqlSession();
        return sqlSession.selectList("findAllUser");
    }

}
