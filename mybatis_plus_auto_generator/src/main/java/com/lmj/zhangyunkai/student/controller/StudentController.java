package com.lmj.zhangyunkai.student.controller;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmj.zhangyunkai.base.BaseController;
import com.lmj.zhangyunkai.base.CommonUtils;
import com.lmj.zhangyunkai.base.response.R;
import com.lmj.zhangyunkai.student.entity.Student;
import com.lmj.zhangyunkai.student.request.StudentRequest;
import com.lmj.zhangyunkai.student.service.IStudentService;
import com.lmj.zhangyunkai.student.vo.StudentVO;
import com.lmj.zhangyunkai.sys.entity.TSysUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 *
 *  控制器
 *
 * @author auto
 * @since 2021-03-21
 */
@Controller
@Api(value = "", tags = "接口")
@RequestMapping("/student")
public class StudentController extends BaseController {

    @Autowired
    private IStudentService studentService;

    /**
     * 查询列表
     */
    @ResponseBody
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "列表")
    public R<IPage<StudentVO>> list(@RequestBody StudentRequest studentRequest) {
        Student student = CommonUtils.convertBean(studentRequest,Student.class);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>().setEntity(student);
        IPage<Student> iPage = studentService.page(new Page<>(studentRequest.getCurrPage(),studentRequest.getPageSize()),queryWrapper);
        IPage<StudentVO> result = iPage.convert(new Function<Student, StudentVO>() {
            @Override
            public StudentVO apply(Student student) {
                return CommonUtils.convertBean(student,StudentVO.class);
            }
        });
        return R.success(result);
    }

    /**
     * 查询详情
     */
    @ResponseBody
    @PostMapping("/info")
    @ApiOperation(value = "详情", notes = "详情")
    public R<StudentVO> info(@RequestBody StudentRequest studentRequest) {
        if (studentRequest.getId() == null) {
            return R.fail("缺少主键id!");
        }
        Student student = CommonUtils.convertBean(studentRequest,Student.class);
        Student data =  studentService.getOne(new QueryWrapper<Student>().setEntity(student));
        return R.success(CommonUtils.convertBean(data,StudentVO.class));
    }


    /**
     * 新增
     */
    @ResponseBody
    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "新增")
    public R save(@RequestBody StudentRequest studentRequest) {
        Student student = CommonUtils.convertBean(studentRequest,Student.class);
        TSysUser currUser = getCurrentUser();
        //student.setCreator(currUser.getAccount());
        studentService.save(student);
        return R.success();
    }


    /**
     * 更新数据
     */
    @ResponseBody
    @PostMapping("/update")
    @ApiOperation(value = "更新", notes = "更新")
    public R update(@RequestBody StudentRequest studentRequest) {
        if (studentRequest.getId() == null) {
            return R.fail("缺少主键id!");
        }
        Student student = CommonUtils.convertBean(studentRequest,Student.class);
        TSysUser currUser = getCurrentUser();
        //student.setCreator(currUser.getAccount());
        studentService.updateById(student);
        return R.success();
    }

    /**
     * 删除数据
     */
    @ResponseBody
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    public R delete(@RequestBody StudentRequest studentRequest) {
        if (studentRequest.getId() == null) {
            return R.fail("缺少主键id!");
        }
        Student student = CommonUtils.convertBean(studentRequest,Student.class);
        studentService.remove(new QueryWrapper<Student>().setEntity(student));
        return R.success();
    }


}
