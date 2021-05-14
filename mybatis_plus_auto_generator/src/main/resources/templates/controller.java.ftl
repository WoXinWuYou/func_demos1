package ${cfg.serverPackage};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import java.util.function.Function;
import ${package.Service}.${table.serviceName};
import ${cfg.modelPackage}.${entity};
import ${cfg.requestPackage}.${entity}Request;
import ${cfg.voPackage}.${entity}Vo;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#--<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>-->
<#if cfg.systemUserClass??>
import ${cfg.systemUserClass};
</#if>
<#if cfg.CommonUtilsClass??>
import ${cfg.CommonUtilsClass};
</#if>
<#if cfg.responseClass??>
import ${cfg.responseClass};
</#if>
<#if cfg.isExport>
import javax.servlet.http.HttpServletResponse;
import ${cfg.exportUtilsClass};
</#if>
<#if cfg.baseControllerClass??>
import ${cfg.baseControllerClass};
</#if>


/**
 *
 * ${table.comment!} 控制器
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@Api(value = "${table.comment}", tags = "${table.comment}接口")
@RequestMapping("/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private I${entity}Service ${table.entityPath}Service;

    /**
     * 查询${table.comment!}列表
     */
    @ResponseBody
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "列表")
    public R<IPage<${entity}Vo>> list(@RequestBody ${entity}Request ${table.entityPath}Request) {
        ${entity} ${table.entityPath} = CommonUtils.convertBean(${table.entityPath}Request,${entity}.class);
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<${entity}>().setEntity(${table.entityPath});
        IPage<${entity}> iPage = ${table.entityPath}Service.page(new Page<>(${table.entityPath}Request.getCurrPage(),${table.entityPath}Request.getPageSize()),queryWrapper);
        IPage<${entity}Vo> result = iPage.convert(new Function<${entity}, ${entity}Vo>() {
            @Override
            public ${entity}Vo apply(${entity} ${table.entityPath}) {
                return CommonUtils.convertBean(${table.entityPath},${entity}Vo.class);
            }
        });
        return R.success(result);
    }

    /**
     * 查询${table.comment!}详情
     */
    @ResponseBody
    @PostMapping("/info")
    @ApiOperation(value = "详情", notes = "详情")
    public R<${entity}Vo> info(@RequestBody ${entity}Request ${table.entityPath}Request) {
        //if (${table.entityPath}Request.getId() == null) {
        //    return R.fail("缺少主键id!");
        //}
        ${entity} ${table.entityPath} = CommonUtils.convertBean(${table.entityPath}Request,${entity}.class);
        ${entity} data =  ${table.entityPath}Service.getOne(new QueryWrapper<${entity}>().setEntity(${table.entityPath}));
        return R.success(CommonUtils.convertBean(data,${entity}Vo.class));
    }


    /**
     * 新增${table.comment!}
     */
    @ResponseBody
    @PostMapping("/add")
    @ApiOperation(value = "新增", notes = "新增")
    public R save(@RequestBody ${entity}Request ${table.entityPath}Request) {
        ${entity} ${table.entityPath} = CommonUtils.convertBean(${table.entityPath}Request,${entity}.class);
        SysUser currUser = getCurrentUser();
        //${table.entityPath}.setCreator(currUser.getAccount());
        ${table.entityPath}Service.save(${table.entityPath});
        return R.success();
    }


    /**
     * 更新${table.comment!}数据
     */
    @ResponseBody
    @PostMapping("/update")
    @ApiOperation(value = "更新", notes = "更新")
    public R update(@RequestBody ${entity}Request ${table.entityPath}Request) {
        //if (${table.entityPath}Request.getId() == null) {
          //  return R.fail("缺少主键id!");
        //}
        ${entity} ${table.entityPath} = CommonUtils.convertBean(${table.entityPath}Request,${entity}.class);
        SysUser currUser = getCurrentUser();
        //${table.entityPath}.setCreator(currUser.getAccount());
        ${table.entityPath}Service.updateById(${table.entityPath});
        return R.success();
    }

    /**
     * 删除${table.comment!}数据
     */
    @ResponseBody
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "删除")
    public R delete(@RequestBody ${entity}Request ${table.entityPath}Request) {
        //if (${table.entityPath}Request.getId() == null) {
          //  return R.fail("缺少主键id!");
        //}
        ${entity} ${table.entityPath} = CommonUtils.convertBean(${table.entityPath}Request,${entity}.class);
        ${table.entityPath}Service.remove(new QueryWrapper<${entity}>().setEntity(${table.entityPath}));
        return R.success();
    }

    <#if cfg.isExport>
    /**
     * ${table.comment!}数据导出
     */
    @ApiOperation(value = "导出", notes = "导出")
    @PostMapping("/export")
    public void exportExcel(@RequestBody ${entity}Request ${table.entityPath}Request,  HttpServletResponse response) {
        ${entity} ${table.entityPath} = CommonUtils.convertBean(${table.entityPath}Request,${entity}.class);
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<${entity}>().setEntity(${table.entityPath});
        IPage<${entity}> iPage = ${table.entityPath}Service.page(new Page<>(${table.entityPath}Request.getCurrPage(),${table.entityPath}Request.getPageSize()),queryWrapper);
        IPage<${entity}Vo> result = iPage.convert(new Function<${entity}, ${entity}Vo>() {
            @Override
            public ${entity}Vo apply(${entity} ${table.entityPath}) {
                return CommonUtils.convertBean(${table.entityPath},${entity}Vo.class);
            }
        });
        EasyPoiUtil.exportExcel(result.getRecords(),"${table.comment!}","第一页",${entity}Vo.class,"${table.comment!}.xlsx",response) ;
    }

    </#if>

}
</#if>
