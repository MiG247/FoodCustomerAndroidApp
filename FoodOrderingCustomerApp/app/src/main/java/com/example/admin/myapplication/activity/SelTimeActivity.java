package com.example.admin.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.admin.myapplication.AppConstant;
import com.example.admin.myapplication.R;

public class SelTimeActivity extends AppCompatActivity {
    private int radioBtnIdList[] =
            {R.id.rb_0, R.id.rb_1, R.id.rb_2, R.id.rb_3, R.id.rb_4,
                    R.id.rb_5, R.id.rb_6, R.id.rb_7, R.id.rb_8, R.id.rb_9, R.id.rb_10,};
    private int timeSchedulreRadioButtonIndex = 0;
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
        // get the selected combo from the activity before
        int combo = getIntent().getIntExtra(AppConstant.COMBO_TAG, 0);
        if(combo == 0){ // error exception (no combo selected)
            finish();
        }

        // get user scheduled food pick up time
        rgSelTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.rb_0:
                        timeSchedulreRadioButtonIndex = 0;
                        break;
                    case R.id.rb_1:
                        timeSchedulreRadioButtonIndex = 1;
                        break;
                    case R.id.rb_2:
                        timeSchedulreRadioButtonIndex = 2;
                        break;
                    case R.id.rb_3:
                        timeSchedulreRadioButtonIndex = 3;
                        break;
                    case R.id.rb_4:
                        timeSchedulreRadioButtonIndex = 4;
                        break;
                    case R.id.rb_5:
                        timeSchedulreRadioButtonIndex = 5;
                        break;
                    case R.id.rb_6:
                        timeSchedulreRadioButtonIndex = 6;
                        break;
                    case R.id.rb_7:
                        timeSchedulreRadioButtonIndex = 7;
                        break;
                    case R.id.rb_8:
                        timeSchedulreRadioButtonIndex = 8;
                        break;
                    case R.id.rb_9:
                        timeSchedulreRadioButtonIndex = 9;
                        break;
                    case R.id.rb_10:
                        timeSchedulreRadioButtonIndex = 10;
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
                // should connect to WeChat or AliPay
            }
        });
    }

    public void goToPay(int money, String time){

    }
}
