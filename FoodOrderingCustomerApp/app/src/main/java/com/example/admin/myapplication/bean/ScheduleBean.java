package com.example.admin.myapplication.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by GogHox on 2017/10/30.
 */

public class ScheduleBean {
    public String pickup_time;
    public int available;

    public ScheduleBean(){}
    public ScheduleBean(JSONObject obj) {
        try {
            this.pickup_time = obj.getString("pickup_time");
            this.available = obj.getInt("available");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
