package com.itczw.vladmin.consumer.annex.dto;

import com.itczw.vladmin.common.core.bean.BaseEntity;

import com.itczw.vladmin.consumer.enums.UploadEnums;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 附件管理新增或者更新参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("附件管理新增或者更新参数")
public class AnnexSaveOrUpdateDTO extends BaseEntity {

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 文件存储相对地址
     */
    private String disk;

    /**
     * 附件大小
     */
    private String fileSize;

    /**
     * 附件类型
     */
    private String type;

    /**
     * 图片访问地址
     */
    private String link;

    /**
     * 关联id,预留字段
     */
    private Long pid;

    /**
     * 上传类型
     */
    private UploadEnums.UpdateType updateType;
}
