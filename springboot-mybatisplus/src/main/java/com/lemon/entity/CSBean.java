package com.lemon.entity;

import java.io.Serializable;

/**
 * 课程+学生 组合bean
 * @author Shuai.Jing
 * @date 2019/5/29
 */
public class CSBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sName;
    private String cName;
    private int cGrade;

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcGrade() {
        return cGrade;
    }

    public void setcGrade(int cGrade) {
        this.cGrade = cGrade;
    }
}
