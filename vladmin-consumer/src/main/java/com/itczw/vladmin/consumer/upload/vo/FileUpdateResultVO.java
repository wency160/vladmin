package com.itczw.vladmin.consumer.upload.vo;

import com.itczw.vladmin.consumer.enums.UploadEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("文件上传结果VO")
public class FileUpdateResultVO implements Serializable {

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "文件大小，字节")
    private Long fileSize;

    @ApiModelProperty(value = "文件存储相对地址")
    private String disk;

    @ApiModelProperty(value = "访问的url")
    private String link;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "上传类型：UPDATE_LOCAL-本地 | UPDATE_QIU_NIU_YUN-七牛云")
    private UploadEnums.UpdateType updateType;

}
