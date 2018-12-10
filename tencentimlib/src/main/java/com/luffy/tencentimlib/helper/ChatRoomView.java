package com.luffy.tencentimlib.helper;


import com.luffy.tencentimlib.model.ImApiMessageBean;
import com.luffy.tencentimlib.model.ImChatMessageBean;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMUserProfile;

import java.util.List;

/**
 * Created by lvlufei on 2018/7/21
 *
 * @desc IM聊天界面的接口
 */
public interface ChatRoomView {

    /*-----------------------1，用户资料------------------------*/

    /**
     * 获取自己资料成功
     *
     * @param userProfile 用户资料
     */
    void getSelfProfileSuccess(TIMUserProfile userProfile);


    /**
     * 获取用户的信息成功
     *
     * @param userProfile        用户资料
     * @param mImChatMessageBean 业务消息
     * @param totalNum           消息总数
     */
    void getUserProfileSuccess(TIMUserProfile userProfile, ImChatMessageBean mImChatMessageBean, int totalNum);

    /*-----------------------2，历史消息------------------------*/

    /**
     * 获取SDK历史消息
     *
     * @param messages SDK消息列表
     */
    void getSdkHistoryMessage(List<TIMMessage> messages);


    /**
     * 解析SDK历史消息
     *
     * @param message  SDK消息
     * @param totalNum 消息总数
     */
    void parseSdkHistoryMessage(TIMMessage message, int totalNum);

    /**
     * 显示SDK历史消息
     *
     * @param message     SDK消息
     * @param content     内容（文本、图片地址）
     * @param messageType 消息类型（TEXT=文本，IMAGE=图片）
     * @param totalNum    消息总数
     */
    void showSdkHistoryMessage(TIMMessage message, String content, ImChatMessageBean.MessageType messageType, int totalNum);

    /**
     * 解析API历史消息
     *
     * @param message  API消息
     * @param totalNum 消息总数
     */
    void parseAPIHistoryMessage(ImApiMessageBean message, int totalNum);

    /**
     * 显示API历史消息
     *
     * @param message     API消息
     * @param content     内容（文本、图片地址）
     * @param messageType 消息类型（1=文本、2=图片地址）
     * @param totalNum    消息总数
     */
    void showAPIHistoryMessage(ImApiMessageBean message, String content, int messageType, int totalNum);

    /*-----------------------3，最新消息------------------------*/

    /**
     * 显示最新消息
     *
     * @param message SDK消息
     */
    void parseLatestMessage(TIMMessage message);

    /**
     * 解析最新消息
     *
     * @param message     SDK消息
     * @param content     内容（文本、图片地址）
     * @param messageType 消息类型（TEXT=文本，IMAGE=图片）
     */
    void showLatestMessage(TIMMessage message, String content, ImChatMessageBean.MessageType messageType);

    /*-----------------------4，异常提示------------------------*/

    /**
     * 异常提示
     *
     * @param code 异常码
     * @param desc 异常描述
     */
    void errorToast(int code, String desc);
}
