package com.example.ytian.young.controller.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.RadioGroup;

import com.example.ytian.young.R;
import com.example.ytian.young.controller.fragment.MessageFragment;
import com.example.ytian.young.controller.fragment.SettingFragment;

public class MainActivity extends FragmentActivity {
    private RadioGroup rg_main_buttom;
    private SettingFragment mSettingFragment;
    private MessageFragment mMessageFragment;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListner();
    }

    private void initListner() {
        mFragment = null;
        rg_main_buttom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_main_message:
                        mFragment = mMessageFragment;
                        break;
                    case R.id.rb_main_setting:
                        mFragment = mSettingFragment;
                        break;
                }
                //实现fragment切换
                switchFragment(mFragment);
            }
        });
//        默认选择一个页面
        rg_main_buttom.check(R.id.rb_main_message);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();

    }

    private void initData() {
        //创建2个Fragment对象
        mSettingFragment = new SettingFragment();
        mMessageFragment = new MessageFragment();


    }

    private void initView() {
        rg_main_buttom = (RadioGroup) findViewById(R.id.rg_main_buttom);
    }
}
