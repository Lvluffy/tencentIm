package com.luffy.tencentimlib.business;

import android.support.annotation.NonNull;

import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;

import java.util.List;

/**
 * Created by lvlufei on 2018/6/22
 *
 * @desc 腾讯云通讯-用户相关业务
 */
public class TencentImUserBusiness {

    private TencentImUserBusiness() {
    }

    /**
     * 获取自己的信息
     *
     * @param timValueCallBack 回调
     */
    public static void getSelfProfile(TIMValueCallBack<TIMUserProfile> timValueCallBack) {
        TIMFriendshipManager.getInstance().getSelfProfile(timValueCallBack);
    }

    /**
     * 获取用户的信息
     *
     * @param timValueCallBack 回调
     */
    public static void getUserProfile(List<String> users, @NonNull TIMValueCallBack<List<TIMUserProfile>> timValueCallBack) {
        TIMFriendshipManager.getInstance().getUsersProfile(users, timValueCallBack);
    }

    /**
     * 获取群成员列表
     *
     * @param groupId          群组ID
     * @param timValueCallBack 回调
     */
    public static void getGroupMembers(String groupId, @NonNull TIMValueCallBack<List<TIMGroupMemberInfo>> timValueCallBack) {
        TIMGroupManagerExt.getInstance().getGroupMembers(groupId, timValueCallBack);
    }
}
