package com.crystal.wx.entity;

/**
 * Created by xieql2 on 2016/11/7.
 */
public class AccessToken {
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(int expiresin) {
        this.expiresin = expiresin;
    }

    private String accessToken;
    private int expiresin;
}
