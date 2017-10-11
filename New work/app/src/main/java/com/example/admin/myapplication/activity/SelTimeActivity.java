package com.example.admin.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.admin.myapplication.AppConstant;
import com.example.admin.myapplication.R;

import java.util.ArrayList;

/**
 * Created by ADMIN on 2017/10/9.
 */


public class SelTimeActivity extends AppCompatActivity {
    private int radioBtnIdList[] =
            {R.id.rb_0, R.id.rb_1, R.id.rb_2, R.id.rb_3, R.id.rb_4,
                    R.id.rb_5, R.id.rb_6, R.id.rb_7, R.id.rb_8, R.id.rb_9, R.id.rb_10,};
    private int radioBtnIndex = 0;
    private RadioGroup rgSelTime;
    private TextView tvPay;
    private String TAG = "SelTimeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_time);

        initView();
        initData();
    }

    private void initData() {
        // get the selected combo from front activity
        int combo = getIntent().getIntExtra(AppConstant.COMBO_TAG, 0);
        if(combo == 0){ // don't have a combo of ID is 0
            finish();
        }

        // get user select time,
        rgSelTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.rb_0:
                        radioBtnIndex = 0;
                        break;
                    case R.id.rb_1:
                        radioBtnIndex = 1;
                        break;
                    case R.id.rb_2:
                        radioBtnIndex = 2;
                        break;
                    case R.id.rb_3:
                        radioBtnIndex = 3;
                        break;
                    case R.id.rb_4:
                        radioBtnIndex = 4;
                        break;
                    case R.id.rb_5:
                        radioBtnIndex = 5;
                        break;
                    case R.id.rb_6:
                        radioBtnIndex = 6;
                        break;
                    case R.id.rb_7:
                        radioBtnIndex = 7;
                        break;
                    case R.id.rb_8:
                        radioBtnIndex = 8;
                        break;
                    case R.id.rb_9:
                        radioBtnIndex = 9;
                        break;
                    case R.id.rb_10:
                        radioBtnIndex = 10;
                        break;
                }
            }
        });
    }

    private void initView() {
        rgSelTime= (RadioGroup)findViewById(R.id.rg_sel_time);
        tvPay = (TextView)findViewById(R.id.tv_pay);

        // when you click "go to pay"
        tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // should connect to Wechat or AliPay
            }
        });
    }

    public void goToPay(int money, String time){

    }
}
