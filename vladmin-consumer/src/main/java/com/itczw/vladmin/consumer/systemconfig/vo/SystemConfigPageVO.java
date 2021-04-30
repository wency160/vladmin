package com.itczw.vladmin.consumer.systemconfig.vo;


import com.itczw.vladmin.common.core.bean.BaseEntity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置查询信息
 * @author wency
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("系统配置查询信息")
public class SystemConfigPageVO extends BaseEntity {
}
