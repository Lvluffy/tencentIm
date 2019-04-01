package com.luffy.tencentimlib.business;

import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.message.TIMConversationExt;

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
     * @param groupId 群组id
     * @return
     */
    public static TIMConversation getGroupConversation(String groupId) {
        return TIMManager.getInstance().getConversation(TIMConversationType.Group, groupId);
    }

    /**
     * 获取当前群组未读消息数量
     *
     * @param groupId 群组id
     * @return
     */
    public static long getUnreadMsgNum(String groupId) {
        TIMConversation mTIMConversation = getGroupConversation(groupId);
        TIMConversationExt mTIMConversationExt = new TIMConversationExt(mTIMConversation);
        return mTIMConversationExt.getUnreadMessageNum();
    }

    /**
     * 获取当前群组最后一条消息（异步请求）
     *
     * @param groupId
     * @param mTIMValueCallBack
     */
    public static void getLastMessage(String groupId, TIMValueCallBack mTIMValueCallBack) {
        TIMConversation mTIMConversation = getGroupConversation(groupId);
        TIMConversationExt timConversationExt = new TIMConversationExt(mTIMConversation);
        timConversationExt.getMessage(1, null, mTIMValueCallBack);
    }

    /**
     * 获取当前群组最后一条消息（同步请求）
     *
     * @param groupId
     * @return
     */
    public static List<TIMMessage> getLastMessage(String groupId) {
        TIMConversation mTIMConversation = getGroupConversation(groupId);
        TIMConversationExt timConversationExt = new TIMConversationExt(mTIMConversation);
        return timConversationExt.getLastMsgs(1);
    }

    /**
     * 设置会话为已读
     *
     * @param groupId 群组ID
     */
    public static void readMessages(String groupId) {
        TIMConversation conversation = getGroupConversation(groupId);
        TIMConversationExt timConversationExt = new TIMConversationExt(conversation);
        timConversationExt.setReadMessage(null, null);
    }
}
