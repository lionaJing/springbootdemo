package com.lemon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lemon.entity.CSBean;
import com.lemon.entity.TStudent;

import java.util.List;

/**
 * @author Shuai.Jing
 * @date 2019/5/30
 */
public interface MyService extends IService<TStudent> {
    String sayYe();
    TStudent selectByNo(int id);
    List<TStudent> selectList(int flag);
    List<CSBean> getCourseAndGrade(String name);
}
