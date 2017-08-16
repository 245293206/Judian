package com.example.ytian.young.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

import com.example.ytian.young.R;
import com.example.ytian.young.moduel.Model;
import com.example.ytian.young.moduel.bean.UserInfo;
import com.hyphenate.chat.EMClient;

//欢迎界面
public class SplashActivity extends Activity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //如果当前Activity已经退出，不处理handler中的消息
            if (isFinishing()){
                return;
            }
            //判断进入主页面还是登录界面
            toMainOrLogin();
        }
    };

    private void toMainOrLogin() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //判断帐号之前是否登录过
                if (EMClient.getInstance().isLoggedInBefore()){
                    //获取当前登录用户的信息
                   UserInfo account =  Model.getInstance().getUserAccountDao().getAccountByHxId(EMClient.getInstance().getCurrentUser());
                    if (account == null){
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        //登录过，跳转到主页面
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                else {
                    //未登录,跳转登录界面
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                //结束当前页面
                finish();

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        //发送两秒钟的延时消息
        handler.sendMessageDelayed(Message.obtain(),2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁消息
        handler.removeCallbacksAndMessages(null);
    }
}
