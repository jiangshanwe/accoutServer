package com.jiang.shanwe.dao.mybatisImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.jiang.shanwe.dao.RecordTagAssDao;
import com.jiang.shanwe.model.RecordTagAss;

public class RecordTagAssDaoMyBatisImpl extends SqlSessionDaoSupport implements RecordTagAssDao {

    @Override
    public int uploadRecordTagAsses(List<RecordTagAss> recordTagAsses) {
        try {
            for (int i = 0; i < recordTagAsses.size(); i++) {
                SqlSession sqlSession = this.getSqlSession();
                sqlSession.insert("insertRecordTagAss", recordTagAsses.get(i));
            }
            return 1; // 上传成功
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 上传失败
        }
    }

    @Override
    public void deleteAllRecordTagAsses() {
        SqlSession sqlSession = this.getSqlSession();
        sqlSession.delete("deleteAllRecordTagAsses");
    }

}
