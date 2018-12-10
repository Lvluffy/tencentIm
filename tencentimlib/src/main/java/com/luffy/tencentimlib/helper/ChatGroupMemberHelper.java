package com.luffy.tencentimlib.helper;

import com.luffy.tencentimlib.business.TencentImUserBusiness;
import com.luffy.tencentimlib.model.ImGroupMemberBean;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvlufei on 2018/11/23.
 */

public class ChatGroupMemberHelper {

    /**
     * 获取群组成员列表
     *
     * @param groupId
     * @return
     */
    public static void getGroupMemberList(String groupId, final GroupMemberListCallback groupMemberListCallback) {
        TencentImUserBusiness.getGroupMembers(groupId, new TIMValueCallBack<List<TIMGroupMemberInfo>>() {
            @Override
            public void onError(int i, String s) {
                groupMemberListCallback.onGroupMemberListError();
            }

            @Override
            public void onSuccess(List<TIMGroupMemberInfo> timGroupMemberInfos) {
                List<String> users = new ArrayList<>();
                for (TIMGroupMemberInfo mTIMGroupMemberInfo : timGroupMemberInfos) {
                    users.add(mTIMGroupMemberInfo.getUser());
                }
                TencentImUserBusiness.getUserProfile(users, new TIMValueCallBack<List<TIMUserProfile>>() {
                    @Override
                    public void onError(int i, String s) {
                        groupMemberListCallback.onGroupMemberListError();
                    }

                    @Override
                    public void onSuccess(List<TIMUserProfile> timUserProfiles) {
                        List<ImGroupMemberBean> groupMemberBeanList = new ArrayList<>();
                        for (TIMUserProfile userProfile : timUserProfiles) {
                            ImGroupMemberBean mImGroupMemberBean = new ImGroupMemberBean();
                            mImGroupMemberBean.setUserId(userProfile.getIdentifier());
                            mImGroupMemberBean.setUserName(userProfile.getNickName());
                            mImGroupMemberBean.setUserUrl(userProfile.getFaceUrl());
                            groupMemberBeanList.add(mImGroupMemberBean);
                        }
                        if (groupMemberListCallback != null) {
                            groupMemberListCallback.onGroupMemberListGetSuccess(groupMemberBeanList);
                        }
                    }
                });
            }
        });
    }

    public interface GroupMemberListCallback {
        void onGroupMemberListGetSuccess(List<ImGroupMemberBean> imGroupMemberBeanList);

        void onGroupMemberListError();
    }
}
