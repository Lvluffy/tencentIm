package com.luffy.tencentimlib.model;

import java.io.Serializable;

/**
 * Created by lvlufei on 2018/7/11
 *
 * @desc API消息
 */
public class ImApiMessageBean implements Serializable {

    public ImApiMessageBean(boolean isSelf, String groupId, String fromAccount, String content, int messageType, long msgTimeStamp) {
        this.isSelf = isSelf;
        this.groupId = groupId;
        this.fromAccount = fromAccount;
        this.content = content;
        this.messageType = messageType;
        this.msgTimeStamp = msgTimeStamp;
    }

    /*----------本地字段----------*/
    private boolean isSelf;

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean self) {
        isSelf = self;
    }

    /*----------API字段----------*/

    private String groupId;//群组ID
    private String fromAccount;//发送消息用户
    private String content;//消息内容
    private int messageType;//消息类型
    private long msgTimeStamp;//消息时间戳

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public long getMsgTimeStamp() {
        return msgTimeStamp;
    }

    public void setMsgTimeStamp(long msgTimeStamp) {
        this.msgTimeStamp = msgTimeStamp;
    }
}
