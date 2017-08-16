package com.example.ytian.young.moduel;

import android.content.Context;

import com.example.ytian.young.moduel.dao.UserAccountDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by YTian on 2017/8/14.
 */
//数据模型层全局类
public class Model {
    private Context mContext;
    private ExecutorService executors = Executors.newCachedThreadPool();

    //创建Model对象
    private static Model model = new Model();
    private UserAccountDao mUserAccountDao;
    //私有化构造

    private Model() {
    }
    //获取单例对象
    public static Model getInstance(){
        
        return model;
    }
    //初始化
    public void init(Context context){
        mContext = context;
        mUserAccountDao = new UserAccountDao(context);

    }
    //获取全局线程池对象
    public ExecutorService getGlobalThreadPool(){
        return executors;
    }
    //用户登录成功后的模型处理方法
    public void loginSuccess() {
    }
    //获取用户帐号数据库的操作类
    public UserAccountDao getUserAccountDao(){
        return mUserAccountDao;
    }
}
