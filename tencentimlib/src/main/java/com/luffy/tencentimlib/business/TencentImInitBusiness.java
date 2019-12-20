package com.luffy.tencentimlib.business;

import android.content.Context;

import com.luffy.tencentimlib.helper.MessageEvent;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.imsdk.session.SessionWrapper;

/**
 * Created by lvlufei on 2018/6/22
 *
 * @desc 腾讯云通讯-初始化相关业务
 */
public class TencentImInitBusiness {
    /**
     * 初始化SDK
     *
     * @param context
     * @param sdkAppId
     * @param timForceOffline
     */
    public static void initImsdk(Context context, int sdkAppId, final TIMForceOffline timForceOffline) {
        //判断是否是在主线程
        if (SessionWrapper.isMainProcess(context)) {
            //初始化SDK基本配置
            TIMSdkConfig config = new TIMSdkConfig(sdkAppId)
                    .setAppidAt3rd(String.valueOf(sdkAppId))
                    .setLogLevel(TIMLogLevel.DEBUG);
            //初始化SDK
            TIMManager.getInstance().init(context, config);
        }

        //设置用户基本配置
        TIMUserConfig userConfig = new TIMUserConfig()
                //设置用户状态变更事件监听器
                .setUserStatusListener(new TIMUserStatusListener() {
                    @Override
                    public void onForceOffline() {
                        //被其他终端踢下线
                        if (timForceOffline != null) {
                            timForceOffline.onForceOffline();
                        }
                    }

                    @Override
                    public void onUserSigExpired() {
                        //用户签名过期了，需要刷新 userSig 重新登录 SDK
                        if (timForceOffline != null) {
                            timForceOffline.onForceOffline();
                        }
                    }
                });
        //将用户配置与通讯管理器进行绑定
        TIMManager.getInstance().setUserConfig(userConfig);
        //设置消息监听器，收到新消息时，通过此监听器回调
        TIMManager.getInstance().addMessageListener(MessageEvent.getInstance());
    }

    public interface TIMForceOffline {
        /**
         * 强制下线（被踢下线）
         */
        void onForceOffline();
    }
}
