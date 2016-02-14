package com.jiang.shanwe.dao.mybatisImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.jiang.shanwe.dao.TagDao;
import com.jiang.shanwe.model.Record;
import com.jiang.shanwe.model.Tag;

public class TagDaoMybatisImpl extends SqlSessionDaoSupport implements TagDao {

    @Override
    public int uploadTag(List<Tag> tags) {
        try {
            for (int i = 0; i < tags.size(); i++) {
                SqlSession sqlSession = this.getSqlSession();
                sqlSession.insert("insertTag", tags.get(i));
            }
            return 1; // 上传成功
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 上传失败
        }
    }

    @Override
    public void deleteAllTag() {
        SqlSession sqlSession = this.getSqlSession();
        sqlSession.delete("deleteAllTag");
    }

    @Override
    public List<Tag> findAllTags(long userId) {
        List<Tag> tags = new ArrayList<>();
        try {
            SqlSession sqlSession = this.getSqlSession();
            tags = sqlSession.selectList("findAllTags");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }

}
