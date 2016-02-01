package com.jiang.shanwe.dao;

import java.util.List;

import com.jiang.shanwe.model.RecordTagAss;

public interface RecordTagAssDao {

    public int uploadRecordTagAsses(List<RecordTagAss> recordTagAsses);

    public void deleteAllRecordTagAsses();
}
