package com.crystal.wx.common;

import com.crystal.wx.entity.AccessToken;

/**
 * Created by xieql2 on 2016/11/7.
 */
public class AccessTokenInfo {
    public static AccessToken getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(AccessToken accessToken) {
        AccessTokenInfo.accessToken = accessToken;
    }

    public static AccessToken accessToken=null;
}
