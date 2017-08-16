package com.example.ytian.young;

import android.app.Application;

import com.example.ytian.young.moduel.Model;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

/**
 * Created by YTian on 2017/8/14.
 */

public class IMApplication extends Application {
    EMOptions options = new EMOptions();

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化EasUI
        options.setAcceptInvitationAlways(false);//设置需要同意后才能接受邀请
        options.setAutoAcceptGroupInvitation(false);//设置需要同意后才能接受群邀请
        EaseUI.getInstance().init(this,options);
        //初始化数据模型全层类
        Model.getInstance().init(this);
    }
}
