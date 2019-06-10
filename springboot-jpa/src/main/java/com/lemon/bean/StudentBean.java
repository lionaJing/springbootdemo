package com.lemon.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lemon
 */
@Data
@Table(name = "student")
@Entity
public class StudentBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; //主键(必须)

    @Column(name = "S_NAME", nullable = false, length = 50)
    private String name;
    @Column(name = "AGE")
    private int age;
    @Basic
    private String email; //Basic 字段名默认就是挡墙的属性名

    @Temporal(TemporalType.DATE)
    private Date birth;

    //不需要映射
    @Transient
    private String info() {
        return this.name + "," + this.age;
    }
}
