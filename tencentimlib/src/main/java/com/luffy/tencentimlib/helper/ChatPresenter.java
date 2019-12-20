package com.luffy.tencentimlib.helper;

import android.support.annotation.Nullable;
import android.util.Log;

import com.luffy.tencentimlib.business.TencentImGroupBusiness;
import com.luffy.tencentimlib.business.TencentImUserBusiness;
import com.luffy.tencentimlib.model.ImChatMessageBean;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMGroupSystemElem;
import com.tencent.imsdk.TIMGroupTipsElem;
import com.tencent.imsdk.TIMImage;
import com.tencent.imsdk.TIMImageElem;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by lvlufei on 2018/7/21
 *
 * @desc 聊天界面逻辑
 */
public class ChatPresenter implements Observer {

    private static final String TAG = ChatPresenter.class.getSimpleName();

    private final int LAST_MESSAGE_NUM = 10;
    private ChatRoomView mChatRoomView;
    private ChatGroupView mChatGroupView;
    private TIMConversation conversation;

    public ChatPresenter(ChatRoomView mChatRoomView, String groupId) {
        this.mChatRoomView = mChatRoomView;
        conversation = TencentImGroupBusiness.getGroupConversation(groupId);
    }

    public ChatPresenter(ChatGroupView mChatGroupView, String groupId) {
        this.mChatGroupView = mChatGroupView;
        conversation = TencentImGroupBusiness.getGroupConversation(groupId);
    }

    /**
     * 加载页面
     */
    public void start() {
        //注册消息监听
        MessageEvent.getInstance().addObserver(this);
    }

    /**
     * 中止页面
     */
    public void stop() {
        //注销消息监听
        MessageEvent.getInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof MessageEvent) {
            if (data instanceof TIMMessage || data == null) {
                TIMMessage msg = (TIMMessage) data;
                if (msg == null || msg.getConversation().getPeer().equals(conversation.getPeer()) && msg.getConversation().getType() == conversation.getType()) {
                    Log.i("update", "有新消息");
                    if (mChatRoomView != null) {
                        mChatRoomView.parseLatestMessage(msg);
                    }
                    if (mChatGroupView != null) {
                        mChatGroupView.parseLatestMessage(msg);
                    }
                }
            }
        }
    }

    /*----------历史消息----------*/

    /**
     * 获取历史消息
     *
     * @param message 最后一条消息（SDK消息）
     */
    public void getHistoryMessage(@Nullable TIMMessage message) {
        conversation.getMessage(LAST_MESSAGE_NUM, message, new TIMValueCallBack<List<TIMMessage>>() {
            @Override
            public void onError(int code, String desc) {
                Log.i(TAG, "get message failed. code: " + code + " errmsg: " + desc);
                mChatRoomView.errorToast(code, desc);
            }

            @Override
            public void onSuccess(List<TIMMessage> timMessages) {
                Log.i(TAG, "get message success");
                mChatRoomView.getSdkHistoryMessage(timMessages);
            }
        });
    }

    /**
     * 解析历史消息
     *
     * @param message  SDK消息
     * @param totalNum 消息总数
     */
    public void parseHistoryMessage(TIMMessage message, int totalNum) {
        long elementCount = message.getElementCount();
        for (int i = 0; i < elementCount; ++i) {
            TIMElem elem = message.getElement(i);
            //获取当前元素的类型
            TIMElemType elemType = elem.getType();
            switch (elemType) {
                /*处理文本消息*/
                case Text:
                    TIMTextElem timTextElem = (TIMTextElem) elem;
                    mChatRoomView.showSdkHistoryMessage(message, timTextElem.getText(), ImChatMessageBean.MessageType.TEXT, totalNum);
                    break;
                /*处理图片消息*/
                case Image:
                    List<String> stringList = new ArrayList<>();
                    TIMImageElem imageElem = (TIMImageElem) elem;
                    for (TIMImage image : imageElem.getImageList()) {
                        stringList.add(image.getUrl());
                        switch (image.getType()) {
                            case Large:
                                mChatRoomView.showSdkHistoryMessage(message, image.getUrl(), ImChatMessageBean.MessageType.IMAGE, totalNum);
                                break;
                        }
                    }
                    break;
                /*群组提示*/
                case GroupTips:
                    TIMGroupTipsElem timGroupTipsElem = (TIMGroupTipsElem) elem;
                    mChatRoomView.showSdkHistoryMessage(message, timGroupTipsElem.getGroupId(), ImChatMessageBean.MessageType.GROUP, totalNum);
                    break;
                /*群组系统*/
                case GroupSystem:
                    TIMGroupSystemElem timGroupSystemElem = (TIMGroupSystemElem) elem;
                    mChatRoomView.showSdkHistoryMessage(message, timGroupSystemElem.getGroupId(), ImChatMessageBean.MessageType.GROUP, totalNum);
                    break;
            }
        }
    }

    /*----------最新消息----------*/

    /**
     * 解析最新消息
     *
     * @param message SDK消息
     */
    public void parseLatestMessage(TIMMessage message) {
        long elementCount = message.getElementCount();
        for (int i = 0; i < elementCount; ++i) {
            TIMElem elem = message.getElement(i);
            //获取当前元素的类型
            TIMElemType elemType = elem.getType();
            switch (elemType) {
                /*处理文本消息*/
                case Text:
                    TIMTextElem timTextElem = (TIMTextElem) elem;
                    mChatRoomView.showLatestMessage(message, timTextElem.getText(), ImChatMessageBean.MessageType.TEXT);
                    break;
                /*处理图片消息*/
                case Image:
                    List<String> stringList = new ArrayList<>();
                    TIMImageElem imageElem = (TIMImageElem) elem;
                    for (TIMImage image : imageElem.getImageList()) {
                        stringList.add(image.getUrl());
                        switch (image.getType()) {
                            case Large:
                                mChatRoomView.showLatestMessage(message, image.getUrl(), ImChatMessageBean.MessageType.IMAGE);
                                break;
                        }
                    }
                    break;
            }
        }
    }

    /*----------发送群组消息----------*/

    /**
     * 发送群组消息
     *
     * @param content     发送的消息
     * @param messageType 消息类型（TEXT=文本，IMAGE=图片）
     */
    public void sendGroupMessage(String content, ImChatMessageBean.MessageType messageType) {
        /*1，获取群组会话（1，TIMConversationType[会话类型]；2，String[参与会话的对方, C2C 会话为对方帐号 identifier, 群组会话为群组 Id]）*/
        /*2，发送消息（1，TIMMessage[消息]）；1，TIMValueCallBack<TIMMessage>[回调]*/
        /*3，文本消息发送[TIMTextElem]*/
        /*3-1，构造一条消息*/
        TIMMessage message = new TIMMessage();
        switch (messageType) {
            case TEXT:
                /*3-2，添加文本内容*/
                TIMTextElem textElem = new TIMTextElem();
                textElem.setText(content);
                /*3-3，将文本内容添加到消息*/
                if (message.addElement(textElem) != 0) {
                    Log.i(TAG, "addElement failed");
                    return;
                }
                break;
            case IMAGE:
                /*3-2，添加图片*/
                TIMImageElem imageElem = new TIMImageElem();
                imageElem.setPath(content);
                /*3-3，将图片添加到消息*/
                if (message.addElement(imageElem) != 0) {
                    Log.i(TAG, "addElement failed");
                    return;
                }
                break;
        }
        /*3-4，发送消息*/
        conversation.sendMessage(message, new TIMValueCallBack<TIMMessage>() {

            @Override
            public void onError(int code, String desc) {
                mChatRoomView.errorToast(code, desc);
            }

            @Override
            public void onSuccess(TIMMessage msg) {
                //发送消息成功,消息状态已在sdk中修改，此时只需更新界面
                MessageEvent.getInstance().onNewMessage(msg);
            }
        });
    }

    /**
     * 设置会话为已读
     */
    public void readMessages() {
        conversation.setReadMessage(null, null);
    }

    /*----------用户资料----------*/

    /**
     * 获取自己的资料
     */
    public void getSelfProfile() {
        TencentImUserBusiness.getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
            @Override
            public void onError(int code, String desc) {
                mChatRoomView.errorToast(code, desc);
            }

            @Override
            public void onSuccess(TIMUserProfile result) {
                mChatRoomView.getSelfProfileSuccess(result);
            }
        });
    }

    /**
     * 获取用户的资料
     *
     * @param users              用户列表
     * @param mImChatMessageBean 业务消息
     * @param totalNum           消息总数
     */
    public void getUserProfile(List<String> users, final ImChatMessageBean mImChatMessageBean, final int totalNum) {
        TencentImUserBusiness.getUserProfile(users, new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int code, String desc) {
                mChatRoomView.errorToast(code, desc);

            }

            @Override
            public void onSuccess(List<TIMUserProfile> timUserProfiles) {
                for (TIMUserProfile result : timUserProfiles) {
                    mChatRoomView.getUserProfileSuccess(result, mImChatMessageBean, totalNum);
                }
            }
        });
    }
}
