package com.lemon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lemon.dao.StudentMapper;
import com.lemon.entity.CSBean;
import com.lemon.entity.TStudent;
import com.lemon.service.MyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shuai.Jing
 * @date 2019/5/30
 */
@Service("myService")
public class MyServiceImpl extends ServiceImpl<StudentMapper, TStudent> implements MyService {
    @Override
    public String sayYe() {
        return "测试 say Ye!";
    }

    @Override
    public TStudent selectByNo(int id) {
        return baseMapper.selectOne(new QueryWrapper<TStudent>().eq("s_no", id));
    }

    /**
     * 获取性别的集合
     */
    @Override
    public List<TStudent> selectList(int flag) {
        return baseMapper.selectList(new QueryWrapper<TStudent>()
                .eq("s_sex", flag));
    }

    /**
     * 自定义 xml,查询当前学生的所修课程及分数
     */
    @Override
    public List<CSBean> getCourseAndGrade(String name) {
        return baseMapper.getCourseAndGrade(name);
    }
}
