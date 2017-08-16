package com.example.ytian.young.controller.fragment;

import android.view.View;
import android.widget.RadioButton;

import com.example.ytian.young.R;
import com.hyphenate.easeui.ui.EaseContactListFragment;

/**
 * Created by YTian on 2017/8/15.
 */

public class ContactListFragment extends EaseContactListFragment {
    private RadioButton rb_add_friend;
    @Override
    protected void initView() {
        titleBar.setVisibility(View.GONE);
        super.initView();
        //头布局添加
        View headerView = View.inflate(getActivity(), R.layout.header_fragment_contact,null);
        listView.addHeaderView(headerView);
    }

}
