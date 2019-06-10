package com.lemon.entity;

import java.io.Serializable;

/**
 * @author Shuai.Jing
 * @date 2019/5/28
 */
public class TStudent implements Serializable {
    private static final long serialVersionUID = 1L;
    private int SNo;
    private String SName;
    private int SSex;
    private int SAge;

    public int getSNo() {
        return SNo;
    }

    public void setSNo(int SNo) {
        this.SNo = SNo;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public int getSSex() {
        return SSex;
    }

    public void setSSex(int SSex) {
        this.SSex = SSex;
    }

    public int getSAge() {
        return SAge;
    }

    public void setSAge(int SAge) {
        this.SAge = SAge;
    }
}
