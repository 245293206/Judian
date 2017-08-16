package com.example.ytian.young.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ytian.young.R;
import com.example.ytian.young.moduel.Model;
import com.example.ytian.young.moduel.bean.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class LoginActivity extends Activity implements View.OnClickListener{
    private EditText edit_login_username;
    private EditText edit_login_password;
    private Button bt_login_register;
    private Button bt_login_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化控件
        initView();

        //初始化监听
        initListner();


    }
    private void initView() {
        edit_login_username = (EditText) findViewById(R.id.edit_login_username);
        edit_login_password = (EditText) findViewById(R.id.edit_login_password);
        bt_login_register = (Button) findViewById(R.id.bt_login_register);
        bt_login_login = (Button) findViewById(R.id.bt_login_login);
    }

    private void initListner() {
        bt_login_login.setOnClickListener(this);
        bt_login_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login_register:
                loginRegister();
                break;
            case R.id.bt_login_login:
                loginLogin();
                break;
            default:
                break;
        }

    }
    //注册业务处理
    private void loginRegister(){
        //1 获取输入的用户名和密码
        final String registeUser = edit_login_username.getText().toString();
        final String registePwd = edit_login_password.getText().toString();
        //2 校验输入的用户名和密码
        if (TextUtils.isEmpty(registeUser) || TextUtils.isEmpty(registePwd)){
            Toast.makeText(LoginActivity.this,"输入的用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        //3 注册帐号存至服务器
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {

                try {
                    //注册帐号存至服务器
                    EMClient.getInstance().createAccount(registeUser,registePwd);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }
    //登录业务处理
    private void loginLogin(){
        //1 获取输入的用户名和密码
        final String loginUser = edit_login_username.getText().toString();
        final String loginPwd = edit_login_password.getText().toString();
        //2 校验输入的用户名和密码
        if (TextUtils.isEmpty(loginUser) || TextUtils.isEmpty(loginPwd)){
            Toast.makeText(LoginActivity.this,"输入的用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        //3 登录逻辑处理
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //从环信服务器登录
                EMClient.getInstance().login(loginUser, loginPwd, new EMCallBack() {
                    //登录成功
                    @Override
                    public void onSuccess() {
                        //对模型层的处理
                        Model.getInstance().loginSuccess();

                        //保存用户帐号信息到本地数据库
                        Model.getInstance().getUserAccountDao().addAccount(new UserInfo(loginUser));
                        //提示登录成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                        //登录成功后页面跳转
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                    //登录失败
                    @Override
                    public void onError(int i, final String s) {
                        //提示登录失败
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登录失败，" + s,Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    //登录失败
                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });

    }
}
