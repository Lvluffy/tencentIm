package com.luffy.tencentimlib.helper;

import com.tencent.imsdk.TIMMessage;

/**
 * Created by lvlufei on 2018/7/21
 *
 * @desc IM群组界面的接口
 */
public interface ChatGroupView {

    /**
     * 显示最新消息
     *
     * @param message SDK消息
     */
    void parseLatestMessage(TIMMessage message);

}
