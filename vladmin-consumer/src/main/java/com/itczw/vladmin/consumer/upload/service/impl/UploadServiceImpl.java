package com.itczw.vladmin.consumer.upload.service.impl;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itczw.vladmin.common.core.util.BeanCopyUtil;
import com.itczw.vladmin.consumer.annex.dto.AnnexSaveOrUpdateDTO;
import com.itczw.vladmin.consumer.annex.entity.AnnexEntity;
import com.itczw.vladmin.consumer.annex.service.AnnexService;
import com.itczw.vladmin.consumer.constants.Constants;
import com.itczw.vladmin.consumer.enums.UploadEnums;
import com.itczw.vladmin.consumer.systemconfig.service.SystemConfigService;
import com.itczw.vladmin.consumer.upload.service.UploadService;
import com.itczw.vladmin.consumer.upload.utils.UploadUtil;
import com.itczw.vladmin.consumer.upload.vo.FileUpdateResultVO;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private AnnexService annexService;

    @Override
    public FileUpdateResultVO image(MultipartFile multipart, String model, Long pid) throws IOException {
        setImageUpload(model);
        FileUpdateResultVO file = UploadUtil.file(multipart);
        //文件入库
        file.setType(file.getType().replace("image/", ""));
        file.setLink(this.localFileUrlHost() + file.getLink());
        AnnexSaveOrUpdateDTO annexSaveOrUpdateDTO = BeanCopyUtil.beanCopy(file, AnnexSaveOrUpdateDTO.class);
        annexSaveOrUpdateDTO.setPid(pid);
        annexSaveOrUpdateDTO.setUpdateType(UploadEnums.UpdateType.UPDATE_LOCAL);
        annexService.saveAnnex(annexSaveOrUpdateDTO);
        return file;
    }

    /**
     * 图片文件
     *
     * @param multipart 上传文件流
     * @param model     上传模块
     */
    @Override
    public FileUpdateResultVO file(MultipartFile multipart, String model, Long pid) throws IOException {
        setFileUpload(model);
        FileUpdateResultVO file = UploadUtil.file(multipart);

        //文件入库
        file.setType(file.getType().replace("file/", ""));
        file.setLink(this.localFileUrlHost() + file.getLink());
        AnnexSaveOrUpdateDTO annexSaveOrUpdateDTO = BeanCopyUtil.beanCopy(file, AnnexSaveOrUpdateDTO.class);
        annexSaveOrUpdateDTO.setPid(pid);
        annexSaveOrUpdateDTO.setUpdateType(UploadEnums.UpdateType.UPDATE_LOCAL);
        annexService.saveAnnex(annexSaveOrUpdateDTO);
        return file;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteLocalFile(String url) {
        annexService.remove(Wrappers.<AnnexEntity>lambdaQuery().eq(AnnexEntity::getLink, url));
        String diskPath = UploadUtil.getRootPath() + url.replace(this.localFileUrlHost(), "");
        FileUtil.del(diskPath);
    }

    @Override
    public List<FileUpdateResultVO> batchUpdateImage(MultipartFile[] files, String model, Long pid) throws IOException {
        List<FileUpdateResultVO> fileUpdateResultVOList = Lists.newArrayList();
        for(MultipartFile file : files) {
            FileUpdateResultVO image = this.image(file, model, pid);
            fileUpdateResultVOList.add(image);
        }
        return fileUpdateResultVOList;
    }

    /**
     * 设置上传图片参数
     */
    private void setImageUpload(String model) {
        UploadUtil.setRootPath(systemConfigService.getValueByKey(Constants.UPLOAD_ROOT_PATH_CONFIG_KEY));
        UploadUtil.setModelPath(model);
        UploadUtil.setExtStr(systemConfigService.getValueByKey(Constants.UPLOAD_IMAGE_EXT_STR_CONFIG_KEY));
        UploadUtil.setSize(Integer.parseInt(systemConfigService.getValueByKey(Constants.UPLOAD_IMAGE_MAX_SIZE_CONFIG_KEY)));
        UploadUtil.setType(Constants.UPLOAD_TYPE_IMAGE);
    }

    /**
     * 设置上传文件参数
     */
    private void setFileUpload(String model) {
        UploadUtil.setRootPath(systemConfigService.getValueByKey(Constants.UPLOAD_ROOT_PATH_CONFIG_KEY));
        UploadUtil.setModelPath(model);
        UploadUtil.setExtStr(systemConfigService.getValueByKey(Constants.UPLOAD_FILE_EXT_STR_CONFIG_KEY));
        UploadUtil.setSize(Integer.parseInt(systemConfigService.getValueByKey(Constants.UPLOAD_FILE_MAX_SIZE_CONFIG_KEY)));
        UploadUtil.setType(Constants.UPLOAD_TYPE_FILE);
    }

    /**
     * 本地代理的http域名地址
     *
     * @return
     */
    private String localFileUrlHost() {
        String valueByKey = systemConfigService.getValueByKey(Constants.LOCAL_FILE_URL_HOST);
        return StringUtils.isBlank(valueByKey) ? "" : valueByKey;
    }
}

