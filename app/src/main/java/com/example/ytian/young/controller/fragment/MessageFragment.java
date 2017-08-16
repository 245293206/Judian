package com.example.ytian.young.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.ytian.young.R;
import com.example.ytian.young.controller.activity.AddContactActivity;

/**
 * Created by YTian on 2017/8/15.
 */

public class MessageFragment extends Fragment {
    private ChatFragment mChatFragment;
    private Fragment mFragment;
    private ContactListFragment mContactListFragment;
    private RadioGroup rg_main_top;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_message,null);
//初始化界面控件
        initView(view);
        //初始化Fragment
        initData();
        //初始化RadioGroup中的监听事件
        initListner();

        return view;
    }

    private void initData() {

        mChatFragment = new ChatFragment();
        mContactListFragment = new ContactListFragment();
    }

    private void initListner() {
            rg_main_top.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_main_chat:
                        mFragment = mChatFragment;
                        break;
                    case R.id.rb_main_contact:
                        mFragment = mContactListFragment;
                        break;
                    case R.id.rb_add_friend:
                        addContact();
                }
                //实现fragment切换
                switchFragment(mFragment);
            }
        });
//        默认选择一个页面
        rg_main_top.check(R.id.rb_main_chat);
    }

    private void addContact() {
        Intent intent = new Intent(getActivity(),AddContactActivity.class);
        startActivity(intent);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();

    }

    private void initView(View view) {
        rg_main_top = (RadioGroup) view.findViewById(R.id.rg_main_top);
    }

}
