package com.example.admin.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication.AppConstant;
import com.example.admin.myapplication.R;

public class SelTimeActivity extends AppCompatActivity implements View.OnClickListener {
    private int radioBtnIdList[] =
            {R.id.rb_0, R.id.rb_1, R.id.rb_2, R.id.rb_3, R.id.rb_4,
                    R.id.rb_5, R.id.rb_6, R.id.rb_7, R.id.rb_8, R.id.rb_9, R.id.rb_10,};
    private int timeScheduleRadioButtonIndex = 0;
    private RadioGroup rgSelTime;
    private TextView tvPay;
    private String TAG = "SelTimeActivity";
    private TextView tvShowMoney;
    private double mComboMoney;
    private String mComboName;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_time);

        // get the selected combo from the activity before
        mComboMoney = getIntent().getDoubleExtra(AppConstant.SEL_COMBO_MONEY_TAG, 0);
        mComboName = getIntent().getStringExtra(AppConstant.SEL_COMBO_NAME_TAG);
        Log.i(TAG, "onCreate: money is " + mComboMoney + "; name is " + mComboName);

        if(mComboName.isEmpty()){ // error exception (no combo selected)
            // TODO Here should push out a dialog. When click confirm, return to previous page.
            Toast.makeText(this, "Please select a combo, then continue pay.", Toast.LENGTH_SHORT);
        }

        initView();
        initData();
    }

    private void initData() {
        tvShowMoney.setText("RMB ￥" + mComboMoney);

        // get user scheduled food pick up time
        rgSelTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.rb_0:
                        timeScheduleRadioButtonIndex = 0;
                        break;
                    case R.id.rb_1:
                        timeScheduleRadioButtonIndex = 1;
                        break;
                    case R.id.rb_2:
                        timeScheduleRadioButtonIndex = 2;
                        break;
                    case R.id.rb_3:
                        timeScheduleRadioButtonIndex = 3;
                        break;
                    case R.id.rb_4:
                        timeScheduleRadioButtonIndex = 4;
                        break;
                    case R.id.rb_5:
                        timeScheduleRadioButtonIndex = 5;
                        break;
                    case R.id.rb_6:
                        timeScheduleRadioButtonIndex = 6;
                        break;
                    case R.id.rb_7:
                        timeScheduleRadioButtonIndex = 7;
                        break;
                    case R.id.rb_8:
                        timeScheduleRadioButtonIndex = 8;
                        break;
                    case R.id.rb_9:
                        timeScheduleRadioButtonIndex = 9;
                        break;
                    case R.id.rb_10:
                        timeScheduleRadioButtonIndex = 10;
                        break;
                }
            }
        });
    }

    private void initView() {
        rgSelTime= (RadioGroup)findViewById(R.id.rg_sel_time);
        tvPay = (TextView)findViewById(R.id.tv_pay);
        tvShowMoney = (TextView) findViewById(R.id.tv_show_money);

        tvPay.setOnClickListener(this);
    }

    public void goToPay(int money, String time){

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_pay){
            // when you click "go to pay"

        }
    }
}
