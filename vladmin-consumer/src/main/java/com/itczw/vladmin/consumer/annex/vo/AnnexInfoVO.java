package com.itczw.vladmin.consumer.annex.vo;

import com.itczw.vladmin.common.core.bean.BaseEntity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 附件管理信息
 *
 * @author wency
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("附件管理信息")
public class AnnexInfoVO extends BaseEntity {
}
