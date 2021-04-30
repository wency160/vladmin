package com.itczw.vladmin.consumer.upload.service;

import com.itczw.vladmin.consumer.upload.vo.FileUpdateResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Mr.Zhang
 * @Description StoreProductService 接口
 * @since 2020-05-06
 */
public interface UploadService {

    /**
     * 图片上传
     *
     * @param multipart
     * @param model
     * @param pid
     * @return
     * @throws IOException
     */
    FileUpdateResultVO image(MultipartFile multipart, String model, Long pid) throws IOException;

    /**
     * 图片文件上传
     *
     * @param multipart
     * @param model
     * @param pid
     * @return
     * @throws IOException
     */
    FileUpdateResultVO file(MultipartFile multipart, String model, Long pid) throws IOException;

    /**
     * 删除本地存储文件
     *
     * @param url
     */
    void deleteLocalFile(String url);

    /**
     * 批量上传图片
     *
     * @param files
     * @param model
     * @param pid
     * @return
     */
    List<FileUpdateResultVO> batchUpdateImage(MultipartFile[] files, String model, Long pid) throws IOException;
}
