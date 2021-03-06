package com.evan.demo.yizhu.shouye;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.evan.demo.yizhu.R;
import com.evan.demo.yizhu.yuding.yuding_search;

import java.util.Timer;
import java.util.TimerTask;

public class zhihuixiyi_yijinru extends AppCompatActivity {
    private long mMin = 45;//分钟,
    private long mSecond = 00;//秒

    private Timer mTimer;
    private TextView daojishi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihuixiyi_yijinru);
        ImageButton back = (ImageButton)findViewById(R.id.yijinru_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zhihuixiyi_yijinru.this.finish();
            }
        });
        TextView tixing_time = (TextView)findViewById(R.id.tixing_xiyi_time);
        tixing_time.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        Button yijinru = (Button)findViewById(R.id.xiyifang_yijinru);
        yijinru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(zhihuixiyi_yijinru.this,com.evan.demo.yizhu.MainActivity.class);
                startActivity(i);
                zhihuixiyi_yijinru.this.finish();
            }
        });
        daojishi = (TextView)findViewById(R.id.daojishi_xiyi);
        mTimer = new Timer();
        startRun();
    }
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                String str ="  " +  mMin + ":" + mSecond;
                daojishi.setText(str);
                if (mSecond == 0  && mMin == 0 ) {
                    mTimer.cancel();
                }
            }
        }
    };

    private String getTv(long l){
        if(l>=10){
            return l+"";
        }else{
            return "0"+l;
        }
    }

    private void startRun() {
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = 1;
                timeHandler.sendMessage(message);
            }
        };
        mTimer.schedule(mTimerTask,0,1000);
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
            }
        }
    }
}
