package com.jiang.shanwe.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jiang.shanwe.dao.RecordDao;
import com.jiang.shanwe.dao.RecordTagAssDao;
import com.jiang.shanwe.dao.TagDao;
import com.jiang.shanwe.model.Record;
import com.jiang.shanwe.model.RecordTagAss;
import com.jiang.shanwe.model.Tag;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class RecordController {

    @Autowired
    private RecordDao recordDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private RecordTagAssDao recordTagAssDao;

    /**
     * 本地数据备份到服务器
     * @param dataJsonObject
     * @return
     */
    @RequestMapping(value = "/backupData", method = RequestMethod.POST)
    @ResponseBody
    public String backupData(@RequestBody String dataJsonObject) {
        JSONObject resultObject = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(dataJsonObject);
        try {
            JSONArray recordsJsonArray = JSONObject.fromObject(dataJsonObject).getJSONArray("records");
            JSONArray tagsJsonArray = JSONObject.fromObject(dataJsonObject).getJSONArray("tags");
            JSONArray recordTagAssJsonArray = JSONObject.fromObject(dataJsonObject).getJSONArray("recordTagAsses");

            List<Record> records = new ArrayList<>();
            List<Tag> tags = new ArrayList<Tag>();
            List<RecordTagAss> recordTagAsses = new ArrayList<RecordTagAss>();

            for (int i = 0; i < recordsJsonArray.size(); i++) {
                Record record = new Record();
                JSONObject recordJsonObject = recordsJsonArray.getJSONObject(i);
                record.setId(recordJsonObject.getInt("id"));
                record.setOwnerId(recordJsonObject.getInt("ownerId"));
                record.setCount(recordJsonObject.getDouble("count"));
                record.setComments(recordJsonObject.getString("comments"));
                record.setConsumeDate(sdf.parse(recordJsonObject.getString("consumeDate")));
                record.setCreatedTime(sdf.parse(recordJsonObject.getString("createdTime")));
                record.setUpdatedTime(sdf.parse(recordJsonObject.getString("updatedTime")));
                record.setStatus(recordJsonObject.getInt("status"));
                record.setSyncStatus(1);
                records.add(record);
            }
            recordDao.deleteAllRecords();
            recordDao.uploadRecord(records);

            for (int i = 0; i < tagsJsonArray.size(); i++) {
                Tag tag = new Tag();
                JSONObject tagJsonObject = tagsJsonArray.getJSONObject(i);
                tag.setId(tagJsonObject.getInt("id"));
                tag.setName(tagJsonObject.getString("name"));
                tag.setCreatedTime(sdf.parse(tagJsonObject.getString("createdTime")));
                tag.setUpdatedTime(sdf.parse(tagJsonObject.getString("updatedTime")));
                tag.setIcon(tagJsonObject.getString("icon"));
                tag.setStatus(tagJsonObject.getInt("status"));
                tag.setSyncStatus(1);
                tags.add(tag);
            }
            tagDao.deleteAllTag();
            tagDao.uploadTag(tags);

            for (int i = 0; i < recordTagAssJsonArray.size(); i++) {
                RecordTagAss recordTagAss = new RecordTagAss();
                JSONObject recordTagAssJsonObject = recordTagAssJsonArray.getJSONObject(i);
                recordTagAss.setId(recordTagAssJsonObject.getInt("id"));
                recordTagAss.setRecordId(recordTagAssJsonObject.getInt("recordId"));
                recordTagAss.setTagId(recordTagAssJsonObject.getInt("tagId"));
                recordTagAss.setCreatedTime(sdf.parse(recordTagAssJsonObject.getString("createdTime")));
                recordTagAss.setUpdatedTime(sdf.parse(recordTagAssJsonObject.getString("updatedTime")));
                recordTagAss.setStatus(recordTagAssJsonObject.getInt("status"));
                recordTagAss.setSyncStatus(1);
                recordTagAsses.add(recordTagAss);
            }
            recordTagAssDao.deleteAllRecordTagAsses();
            recordTagAssDao.uploadRecordTagAsses(recordTagAsses);

            resultObject.put("status", 1); // 上传成功
        } catch (ParseException e) {
            e.printStackTrace();
            resultObject.put("status", 0); // 上传失败
        } catch (Exception e) {
            e.printStackTrace();
            resultObject.put("status", 0); // 上传失败
        }
        return resultObject.toString();
    }

    /**
     * 同步数据到本地
     * @param userId
     * @return
     */
    @RequestMapping(value = "/syncData", method = RequestMethod.GET)
    @ResponseBody
    public String syncDate(@RequestParam int userId) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
        org.json.JSONObject resultJsonObject = new org.json.JSONObject();
        resultJsonObject.put("records", new org.json.JSONArray(gson.toJson(recordDao.findAllRecords(userId))));
        resultJsonObject.put("tags", new org.json.JSONArray(gson.toJson(tagDao.findAllTags(userId))));
        resultJsonObject.put("recordTagAsses",
                new org.json.JSONArray(gson.toJson(recordTagAssDao.findAllRecordTagAsses())));
        System.out.println(resultJsonObject.toString());
        return resultJsonObject.toString();
    }

}
