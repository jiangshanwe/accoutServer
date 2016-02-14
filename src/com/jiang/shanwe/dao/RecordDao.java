package com.jiang.shanwe.dao;

import java.util.List;

import com.jiang.shanwe.model.Record;

public interface RecordDao {

    public int uploadRecord(List<Record> records);

    public List<Record> findAllRecords(long userId);

    public void deleteAllRecords();
}
