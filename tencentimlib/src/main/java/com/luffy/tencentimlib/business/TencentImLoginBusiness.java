package com.luffy.tencentimlib.business;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;

/**
 * Created by lvlufei on 2018/6/22
 *
 * @desc 腾讯云通讯-登录相关业务
 */
public class TencentImLoginBusiness {

    private TencentImLoginBusiness() {
    }

    /**
     * 登录imsdk
     *
     * @param identifier 用户帐号
     * @param userSig    用户帐号签名，由私钥加密获得
     * @param cb   登录后回调
     */
    public static void loginIM(String identifier, String userSig, TIMCallBack cb) {
        TIMManager.getInstance().login(identifier, userSig, cb);
    }

    /**
     * 登出imsdk
     *
     * @param cb 登出后回调
     */
    public static void logoutIM(TIMCallBack cb) {
        TIMManager.getInstance().logout(cb);
    }

    /**
     * 获取登录用户
     *
     * @return
     */
    public static String getLoginUser() {
        return TIMManager.getInstance().getLoginUser();
    }

    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        String user = getLoginUser();
        if (user != null && !"".equals(user) && !"null".equals(user) && user.length() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
