package com.example.admin.myapplication.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.myapplication.AppConstant;
import com.example.admin.myapplication.R;

public class SelTimeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PAY_FOR_ERROR = 1;
    private static final int PAY_FOR_SUCCESS = 2;
    private int radioBtnIdList[] =
            {R.id.rb_0, R.id.rb_1, R.id.rb_2, R.id.rb_3, R.id.rb_4,
                    R.id.rb_5, R.id.rb_6, R.id.rb_7, R.id.rb_8, R.id.rb_9, R.id.rb_10,};
    private int timeScheduleRadioButtonIndex = 0;
    private RadioGroup rgSelTime;
    private TextView tvPay;
    private String TAG = "SelTimeActivity";
    private TextView tvShowMoney;
    public double mComboMoney;   // price
    public String mComboName;     // comboName
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case PAY_FOR_ERROR:
                    break;
                case PAY_FOR_SUCCESS:
                    Toast.makeText(getApplicationContext(),
                            "Pay for successful, please care about the notification.", Toast.LENGTH_LONG).show();
                    sendPINViaNotification();
                    finish();
                    break;
            }
            return false;
        }
    });
    private NotificationManager mNotificationManager;

    private void sendPINViaNotification() {
        if(mNotificationManager == null)
            mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        style.setBigContentTitle("PIN of pick up food");
        style.addLine("You pick up food time is: ").
                addLine("2017.10.24 10:15").
                addLine("Thank you for you using our produce");

        android.support.v4.app.NotificationCompat.Builder nb = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_cart)   // must have small icon
                .setContentTitle("PIN of pick up food")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logo))
                .setContentInfo("STIEI")
                .setTicker("scroll information text")
                .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)  // sound and vibrate
                .setOngoing(false)   // Forever show, can't remove by yourself.
                .setStyle(style);

        mNotificationManager.notify(0, nb.build());
    }

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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_pay){
            // when you click "go to pay"
            int mBoxId = requireServerToPayFor(mComboMoney, "2017.10.24");
            if(mBoxId > 0){   // if the box id is less than 0, it mean have error in request server.
                handler.sendEmptyMessage(PAY_FOR_SUCCESS);
            }
        }
    }

    /*
    * Requesting the server with argument that have time and price, and if request success,
    *     will return the id of the box where is pick up food.
    * @arg money double
    * @arg time String
    * @return int, the combo number. If return -1, it mean have error at pay for.
     */
    private int requireServerToPayFor(double money, String time) {
        if(false){  // if request server error, should send the message by handle
            handler.sendEmptyMessage(PAY_FOR_ERROR);
            return -1;
        }

        // if pay for is successful, return the combo number
        return 1;
    }
}
