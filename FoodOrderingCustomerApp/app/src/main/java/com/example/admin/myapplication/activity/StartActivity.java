package com.example.admin.myapplication.activity;

        import android.content.Intent;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.example.admin.myapplication.R;

public class StartActivity extends AppCompatActivity {

    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        
        initView();
        initData();
    }

    /*demo time slot (countdown to jump after one second to
    food selection or login/register activity */

    private void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 1000);//1 second = 1000 milli seconds
    }

    private void initView() {
    }

}
