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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.myapplication.AppConstant;
import com.example.admin.myapplication.R;
import com.example.admin.myapplication.bean.ScheduleBean;
import com.example.admin.myapplication.utils.Net;
import com.example.admin.myapplication.view.ScheduleRadioButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SelTimeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PAY_FOR_ERROR     = 1;
    private static final int PAY_FOR_SUCCESS   = 2;
    private static final int UPDATE_VIEW       = 3;

    private ArrayList<ScheduleBean> scheduleList = new ArrayList<>();
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
                case UPDATE_VIEW:
                    for(int i = 0; i < scheduleList.size(); i++){
                        ScheduleRadioButton button = new ScheduleRadioButton(getApplicationContext());
                        ScheduleBean scheduleBean = scheduleList.get(i);
                        button.setText("" + scheduleBean.pickup_time);
                        if(scheduleBean.available == 0){
                            button.setEnabled(false);
                        }
                        rgSelTime.addView(button);
                    }
                    break;
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
    private Net net;

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
        this.net = Net.getInstance();

        // show the price
        tvShowMoney.setText("RMB ￥" + mComboMoney);

        // TODO get the schedule to show
        getScheduleFromServer();

        // get user scheduled food pick up time
        rgSelTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {

            }
        });
    }


    private void getScheduleFromServer() {
        net.get(AppConstant.SERVER_SCHEDULE_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: connect error; " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr = response.body().string();
                Log.i(TAG, "onResponse: --------------------------------" + jsonStr);
                parseJsonAndUpdateView(jsonStr.trim());
            }
        });
    }

    private void parseJsonAndUpdateView(String json) {
        try {
            JSONArray scheduleArr = new JSONArray(json);

            for(int i = 0; i < scheduleArr.length(); i++){
                JSONObject scheduleItemObj = scheduleArr.getJSONObject(i);
                ScheduleBean scheduleBean = new ScheduleBean(scheduleItemObj);
                scheduleList.add(scheduleBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // TODO parse finish, should update the view
        if(scheduleList.size() > 0){
            handler.sendEmptyMessage(UPDATE_VIEW);
        }else{
            Toast.makeText(this, "get data error, please try repeat.", Toast.LENGTH_SHORT).show();
        }
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
            if(rgSelTime.getCheckedRadioButtonId() <= 0){
                Toast.makeText(this, "Please select a pickup time", Toast.LENGTH_LONG).show();
                return;
            }
            // get the time of selected button via checked radio button
            RadioButton checkBtn = findViewById(rgSelTime.getCheckedRadioButtonId());
            int mBoxId = requireServerToPayFor(mComboMoney, (String)checkBtn.getText());

            Log.i(TAG, "onClick: " + checkBtn.getText());
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
