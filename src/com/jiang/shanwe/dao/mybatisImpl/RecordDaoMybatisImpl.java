package com.jiang.shanwe.dao.mybatisImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.jiang.shanwe.dao.RecordDao;
import com.jiang.shanwe.model.Record;

public class RecordDaoMybatisImpl extends SqlSessionDaoSupport implements RecordDao {

    @Override
    public int uploadRecord(List<Record> records) {
        try {
            for (int i = 0; i < records.size(); i++) {
                SqlSession sqlSession = this.getSqlSession();
                sqlSession.insert("insertRecord", records.get(i));
            }
            return 1; // 上传成功
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 上传失败
        }
    }

    @Override
    public List<Record> syncRecord(long userId) {
        return null;
    }

    @Test
    public void testInsert() {
        // List<Record> records = new ArrayList<Record>();
        // records.add(new Record(1, 1, 1212, "aaaa", new Date(), new Date(),
        // new Date(), 1, 1));
        // records.add(new Record(2, 1, 343, "bbb", new Date(), new Date(), new
        // Date(), 1, 1));
        // records.add(new Record(3, 1, 67.89, "cvcv", new Date(), new Date(),
        // new Date(), 1, 1));
        // System.out.println(this.uploadRecord(records));
        SimpleDateFormat sdf = new SimpleDateFormat("MM dd, yyyy HH:mm:ss", Locale.ENGLISH);
        try {
            Date date = sdf.parse("01 29, 2016 10:49:11 PM");
            System.out.println(date.getTime());
            System.out.println(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllRecords() {
        SqlSession sqlSession = this.getSqlSession();
        sqlSession.delete("deleteAllRecords");
    }

}
