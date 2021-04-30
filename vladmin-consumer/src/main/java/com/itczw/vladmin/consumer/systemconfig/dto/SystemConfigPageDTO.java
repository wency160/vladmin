package com.itczw.vladmin.consumer.systemconfig.dto;

import com.itczw.vladmin.common.core.bean.SplitPage;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置查询参数
 *
 * @author wency
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("系统配置查询参数")
public class SystemConfigPageDTO extends SplitPage {
}
