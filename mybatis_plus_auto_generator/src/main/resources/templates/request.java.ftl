package ${cfg.requestPackage};


import com.fasterxml.jackson.annotation.JsonFormat;
<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;
</#if>
<#if cfg.hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.time.LocalDateTime;
import java.time.LocalDate;
import ${cfg.requestSuperClass};

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
@EqualsAndHashCode(callSuper = false)
</#if>
<#if swagger2>
@ApiModel(value="${entity}对象", description="${table.comment!}")
</#if>
public class ${entity}Request extends BaseRequest {


<#if entitySerialVersionUID>
    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
        <#if swagger2>
    @ApiModelProperty(value = "${field.comment}")
        <#else>
    /**
     * ${field.comment}
     */
        </#if>
    </#if>
    <#if field.keyFlag>
        <#-- 普通字段 -->
    <#elseif field.fill??>
    <#-- -----   存在字段填充设置   ----->
    <#elseif field.convert>
    </#if>
    <#-- 乐观锁注解 -->
    <#if (versionFieldName!"") == field.name>
    @Version
    </#if>
    <#-- 逻辑删除注解 -->
    <#if (logicDeleteFieldName!"") == field.name>
    @TableLogic
    </#if>
    <#if field.propertyType == "LocalDateTime">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>
    public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
    }

    <#if entityBuilderModel>
    public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    <#else>
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if entityBuilderModel>
        return this;
        </#if>
    }
    </#list>
</#if>

<#if entityColumnConstant>
    <#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";

    </#list>
</#if>
<#if activeRecord>
    @Override
    protected Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }

</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
        return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName}=" + ${field.propertyName} +
        <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
        "}";
    }
</#if>
}
