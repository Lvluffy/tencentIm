package com.luffy.tencentimlib.business;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushListener;
import com.tencent.imsdk.TIMOfflinePushSettings;
import com.tencent.imsdk.TIMOfflinePushToken;

/**
 * Created by zxing on 2018/9/30
 *
 * @desc 腾讯Im消息推送
 */
public class TencentImPushBusiness {

    /**
     * 初始化离线推送配置
     */
    public static void initOfflinePushSetting() {
        TIMOfflinePushSettings settings = new TIMOfflinePushSettings();
        //开启离线推送
        settings.setEnabled(true);
        TIMManager.getInstance().setOfflinePushSettings(settings);

    }

    /**
     * 设置离线推送回调
     * @param listener
     */
    public static void setOffinePushListener(TIMOfflinePushListener listener){
        // 只能在主进程进行离线推送监听器注册
        TIMManager.getInstance().setOfflinePushListener(listener);
    }

    /**
     * 登录成功后，上报证书 ID 及设备 token
     * @param param
     * @param callBack
     */
    public static void setOfflinePushToken(TIMOfflinePushToken param, TIMCallBack callBack){
        TIMManager.getInstance().setOfflinePushToken(param, callBack);
    }
}
