package com.example.admin.myapplication.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by GogHox on 2017/10/17.
 */

public class ComboBean {
    public String name;
    public double money;
    public int status;
    public String description;
    public String picture;

    public ComboBean(){}
    public ComboBean(JSONObject comboItemObj){
        try {
            /* //old api
            this.name = comboItemObj.getString("name");
            this.money = comboItemObj.getDouble("money");
            this.status = comboItemObj.getInt("status");
            this.description = comboItemObj.getString("description");
            this.picture = comboItemObj.getString("picture");
            */
            this.name = comboItemObj.getString("name");
            this.money = comboItemObj.getDouble("price");


            this.description = "Combo Name: " + name;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
