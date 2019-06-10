package com.lemon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.entity.CSBean;
import com.lemon.entity.TStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Shuai.Jing
 * @date 2019/5/30
 */
@Mapper
public interface StudentMapper extends BaseMapper<TStudent> {
    //获取当前学生的所修课程及分数集合
    List<CSBean> getCourseAndGrade(String name);
}
