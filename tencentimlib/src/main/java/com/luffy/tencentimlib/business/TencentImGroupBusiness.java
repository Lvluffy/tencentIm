package com.luffy.tencentimlib.business;

import android.support.annotation.NonNull;

import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupDetailInfoResult;

import java.util.List;

/**
 * Created by lvlufei on 2018/7/2
 *
 * @desc 腾讯云通讯-群组相关业务
 */

public class TencentImGroupBusiness {

    /**
     * 获取当前群组会话
     *
     * @param groupId 群组ID
     * @return
     */
    public static TIMConversation getGroupConversation(String groupId) {
        return TIMManager.getInstance().getConversation(TIMConversationType.Group, groupId);
    }

    /**
     * 获取当前群组未读消息数量
     *
     * @param groupId 群组ID
     * @return
     */
    public static long getUnreadMsgNum(String groupId) {
        return getGroupConversation(groupId).getUnreadMessageNum();
    }

    /**
     * 获取群组列表未读消息数量
     *
     * @param groupIdList 群组ID列表
     * @return
     */
    public static long getUnreadMsgNum(List<String> groupIdList) {
        long unreadMessageNum = 0;
        for (String groupId : groupIdList) {
            unreadMessageNum = unreadMessageNum + getGroupConversation(groupId).getUnreadMessageNum();
        }
        return unreadMessageNum;
    }

    /**
     * 获取当前群组最后一条消息
     *
     * @param groupId 群组ID
     * @return
     */
    public static TIMMessage getLastMessage(String groupId) {
        return getGroupConversation(groupId).getLastMsg();
    }

    /**
     * 设置会话为已读
     *
     * @param groupId 群组ID
     */
    public static void readMessages(String groupId) {
        TIMConversation conversation = getGroupConversation(groupId);
        conversation.setReadMessage(null, null);
    }

    /**
     * 群成员获取群组资料
     *
     * @param groupIdList 群组ID列表，一次最多 50 个
     * @param cb          回调
     */
    public static void getGroupInfo(List<String> groupIdList, @NonNull TIMValueCallBack<List<TIMGroupDetailInfoResult>> cb) {
        TIMGroupManager.getInstance().getGroupInfo(groupIdList, cb);
    }
}
