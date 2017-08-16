package com.example.ytian.young.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ytian.young.R;
import com.example.ytian.young.controller.activity.LoginActivity;
import com.example.ytian.young.moduel.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by YTian on 2017/8/15.
 */

public class SettingFragment extends Fragment {
    private Button bt_setting_out;
    private TextView tv_setting_text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_setting,null);
        
        initView(view);
        return view;
    }

    private void initView(View view) {
        bt_setting_out = (Button) view.findViewById(R.id.bt_setting_out);
        tv_setting_text = (TextView) view.findViewById(R.id.tv_setting_text);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        initData();
    }

    private void initData() {
        //在tv_setting_text上显示当前用户名称
        tv_setting_text.setText("您好！" + EMClient.getInstance().getCurrentUser());
//        退出登录的处理
        bt_setting_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        //退出环信服务器登录
                        EMClient.getInstance().logout(false, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //跳转到登录页面
                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();

                                        //更新UI显示
                                        Toast.makeText(getActivity(),"注销成功",Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }

                            @Override
                            public void onError(int i, final String s) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(),"注销失败" + s,Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                });
            }
        });
    }
}
