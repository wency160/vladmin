package com.itczw.vladmin.consumer.annex.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.itczw.vladmin.common.core.bean.BaseEntity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 附件管理表
 *
 * @author wency
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tbl_annex")
public class AnnexEntity extends BaseEntity {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 磁盘相对路径
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
     * 图片访问地址（完整访问地址= link + bucket）
     */
    private String link;

    /**
     * 关联id,预留字段
     */
    private Long pid;

    /**
     * 上传类型
     */
    private String updateType;

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
