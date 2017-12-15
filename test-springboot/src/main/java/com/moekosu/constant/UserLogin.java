package com.moekosu.constant;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录信息表
 */
public class UserLogin implements Serializable {

    private String msisdn;

    private String mbrId;

    private Date firstLoginTime;

    private Date lastLoginTime;

    private Integer loginCount;// 登录次数

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getMbrId() {
        return mbrId;
    }

    public void setMbrId(String mbrId) {
        this.mbrId = mbrId;
    }

    public Date getFirstLoginTime() {
        return firstLoginTime;
    }

    public void setFirstLoginTime(Date firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public String toString()
    {
        // User: 134123123 login for 4 times, firstLoginTime: 2017-12-14, lastLoginTime: 2017-12-14
        return "UserLoginMsg: [User: "+msisdn+" login for "+loginCount+" times, firstLoginTime: "+firstLoginTime+", lastLoginTime: "+lastLoginTime+"]";
    }
}
