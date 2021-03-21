package com.lmj.zhangyunkai.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmj.zhangyunkai.student.dao.StudentMapper;
import com.lmj.zhangyunkai.student.entity.Student;
import com.lmj.zhangyunkai.student.service.IStudentService;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto
 * @since 2021-03-21
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
