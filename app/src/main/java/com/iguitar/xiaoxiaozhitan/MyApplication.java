package com.iguitar.xiaoxiaozhitan;

import android.app.Application;
import android.content.Context;

import com.iguitar.xiaoxiaozhitan.utils.CommonUtil;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;

import me.iwf.photopicker.utils.MyPhotoUtil;


/**
 * Created by Jiang.
 */

public class MyApplication extends Application {
    private static Context context;
    private MyPhotoUtil myPhotoUtil;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        String headUrl = CommonUtil.getSaveHeadIcon(getApplicationContext());
        myPhotoUtil = new MyPhotoUtil();
        ArrayList<String> headurls = new ArrayList<>();
        headurls.add(headUrl);
        myPhotoUtil.putPhotoMap(headurls);

        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = CommonUtil.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, "b5365217de", false, strategy);
        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
        // CrashReport.initCrashReport(context, strategy);

    }
}
