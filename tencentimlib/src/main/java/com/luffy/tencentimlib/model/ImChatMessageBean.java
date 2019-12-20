package com.luffy.tencentimlib.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.tencent.imsdk.TIMMessage;

/**
 * Created by lvlufei on 2018/5/23
 *
 * @desc 业务聊天消息
 */
public class ImChatMessageBean implements MultiItemEntity {

    private TIMMessage mTIMMessage;//SDK消息
    private ImApiMessageBean mImApiMessageBean;//API消息
    private MessageType type;//消息类型
    private String userId;//用户ID
    private String userName;//用户昵称
    private String userUrl;//用户头像
    private String content;//消息内容（文本、图片）
    private long timestamp;//消息时间戳（格林时间）
    private String typeName;//亲情关系
    private boolean isShow;//是否显示亲情关系
    private int userType;//用户类型（1-学生 2-咨询顾问 3-文签顾问 4-客服 5-小希留学管家 6-亲情号）
    private boolean isFirst = false;//是否是第一条数据

    public ImApiMessageBean getmImApiMessageBean() {
        return mImApiMessageBean;
    }

    public void setmImApiMessageBean(ImApiMessageBean mImApiMessageBean) {
        this.mImApiMessageBean = mImApiMessageBean;
    }

    public TIMMessage getmTIMMessage() {
        return mTIMMessage;
    }

    public void setmTIMMessage(TIMMessage mTIMMessage) {
        this.mTIMMessage = mTIMMessage;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    @Override
    public int getItemType() {
        if (getmTIMMessage() != null) {
            if (getmTIMMessage().isSelf()) {
                return ItemType.MINE;
            } else {
                if (getmTIMMessage().getSender().contains("xiaoxi")) {
                    return ItemType.STEWARD;
                }
                return ItemType.OTHERS;
            }
        } else if (getmImApiMessageBean() != null) {
            if (getmImApiMessageBean().isSelf()) {
                return ItemType.MINE;
            } else {
                if (getmImApiMessageBean().getFromAccount().contains("xiaoxi")) {
                    return ItemType.STEWARD;
                }
                return ItemType.OTHERS;
            }
        }
        return ItemType.MINE;
    }

    /**
     * 消息类型
     */
    public enum MessageType {
        TEXT,//文本
        IMAGE,//图片
        GROUP//群消息
    }

    /**
     * 子项类型
     */
    public static class ItemType {
        public static final int MINE = 1;//我的消息
        public static final int OTHERS = 2;//别人的消息
        public static final int STEWARD = 3;//小希留学管家
    }
}
