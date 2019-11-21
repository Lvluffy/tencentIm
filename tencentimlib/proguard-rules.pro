#############################################
#
# 一、对于一些基本指令的添加
#
#############################################

#############################################
#
# 二、Android开发中一些需要保留的公共部分
#
#############################################

#############################################
#
# 三、自身项目相关处理（必须的，否则出问题-包括：实体类）
# 在开发的时候我们可以将所有的实体类放在一个包内，这样我们写一次混淆就行了。
#
#############################################

#############################################
#
# 四、处理第三方依赖库
#
#############################################
# 腾讯IM
-keep class com.tencent.**{*;}
-dontwarn com.tencent.**

-keep class tencent.**{*;}
-dontwarn tencent.**

-keep class qalsdk.**{*;}
-dontwarn qalsdk.**

-libraryjars libs/bugly_2.4.0_imsdk_release.jar
-libraryjars libs/imsdk.jar
-libraryjars libs/imsdk_group_ext.jar
-libraryjars libs/imsdk_msg_ext.jar
-libraryjars libs/imsdk_sns_ext.jar
-libraryjars libs/mobilepb.jar
-libraryjars libs/qalsdk.jar
-libraryjars libs/soload.jar
-libraryjars libs/tls_sdk.jar
-libraryjars libs/wup-1.0.0-SNAPSHOT.jar