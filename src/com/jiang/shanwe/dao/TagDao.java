package com.jiang.shanwe.dao;

import java.util.List;

import com.jiang.shanwe.model.Tag;

public interface TagDao {
    public int uploadTag(List<Tag> tags);

    public void deleteAllTag();
}
