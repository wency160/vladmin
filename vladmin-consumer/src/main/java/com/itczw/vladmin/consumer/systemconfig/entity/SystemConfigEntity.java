package com.itczw.vladmin.consumer.systemconfig.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.itczw.vladmin.common.core.bean.BaseEntity;

import java.time.LocalDateTime;

import com.itczw.vladmin.consumer.enums.CommonEnums;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置
 *
 * @author wency
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tbl_system_config")
public class SystemConfigEntity extends BaseEntity {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 配置项key
     */
    private String configKey;

    /**
     * 值
     */
    private String value;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 表单id
     */
    private Integer formId;

    /**
     * 状态( ENABLE-启用;DISABLE-禁用)
     */
    private CommonEnums.Status status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
