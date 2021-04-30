package com.itczw.vladmin.consumer.systemconfig.dto;

import com.itczw.vladmin.common.core.bean.BaseEntity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置新增或者更新参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("系统配置新增或者更新参数")
public class SystemConfigSaveOrUpdateDTO extends BaseEntity {
}
