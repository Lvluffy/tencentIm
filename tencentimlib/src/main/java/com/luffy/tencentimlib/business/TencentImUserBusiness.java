package com.luffy.tencentimlib.business;

import android.support.annotation.NonNull;

import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;

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
     * @param cb 回调
     */
    public static void getSelfProfile(TIMValueCallBack<TIMUserProfile> cb) {
        TIMFriendshipManager.getInstance().getSelfProfile(cb);
    }

    /**
     * 获取用户的信息
     *
     * @param users 要获取资料的用户 identifier 列表
     * @param cb    回调
     */
    public static void getUserProfile(List<String> users, @NonNull TIMValueCallBack<List<TIMUserProfile>> cb) {
        TIMFriendshipManager.getInstance().getUsersProfile(users, true, cb);
    }

    /**
     * 获取群成员列表
     *
     * @param groupId 群组ID
     * @param cb      回调
     */
    public static void getGroupMembers(String groupId, @NonNull TIMValueCallBack<List<TIMGroupMemberInfo>> cb) {
        TIMGroupManager.getInstance().getGroupMembers(groupId, cb);
    }
}
