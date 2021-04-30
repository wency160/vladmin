package com.itczw.vladmin.consumer.upload.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.itczw.vladmin.common.core.exception.ChangeSysResponseStatusException;
import com.itczw.vladmin.consumer.upload.vo.FileUpdateResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 上传工具类
 */
@Slf4j
public class UploadUtil {

    private static UploadUtil uploadUtil = new UploadUtil();

    //服务器存储地址
//    private static String rootPath  = "/www/wwwroot/upload";
    private static String rootPath  = "";

    //类型
    private static String type = "/image";


    //模块
    private static String modelPath = "/public";

    //扩展名
    private static String extStr = "png,jpg";

    //文件大小上限
    private static int size = 2;

    //是否压缩图片
    private static boolean isCompress = false;

    public static String getRootPath() {
        return rootPath;
    }

    public static void setRootPath(String rootPath) {
        UploadUtil.rootPath = (rootPath + "/").replace(" ", "").replace("//", "/");
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        UploadUtil.type = type + "/";
    }

    public static String getModelPath() {
        return modelPath;
    }

    public static void setModelPath(String modelPath) {
        UploadUtil.modelPath = modelPath + "/";
    }

    public static String getExtStr() {
        return extStr;
    }

    public static void setExtStr(String extStr) {
        UploadUtil.extStr = extStr;
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        UploadUtil.size = size;
    }

    public static boolean isIsCompress() {
        return isCompress;
    }

    public static void setIsCompress(boolean isCompress) {
        UploadUtil.isCompress = isCompress;
    }

    /**
     * 获取单例
     * @author Mr.Zhang
     * @since 2020-05-08
     */
    public static UploadUtil getInstance() {
        if (uploadUtil == null) {
            uploadUtil = new UploadUtil();
        }
        return uploadUtil;
    }


    /**
     * 根据文件的绝对路径创建一个文件对象.
     * @return 返回创建的这个文件对象
     * @author Mr.Zhang
     * @since 2020-05-08
     */
    public static File createFile(String filePath) throws IOException {
        // 获取文件的完整目录
        String fileDir = FilenameUtils.getFullPath(filePath);
        // 判断目录是否存在，不存在就创建一个目录
        File file = new File(fileDir);
        if (!file.isDirectory()) {
            //创建失败返回null
            if (!file.mkdirs()) {
                log.error("文件目录创建失败...");
                throw ChangeSysResponseStatusException.error500("文件目录创建失败...");
            }
        }
        // 判断这个文件是否存在，不存在就创建
        file = new File(filePath);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                log.error("目标文件创建失败...");
                throw ChangeSysResponseStatusException.error500("目标文件创建失败...");
            }
        }
        return file;
    }

    /**
     * 判断文件扩展名是否合法
     * @param extName 文件的后缀名
     * @author Mr.Zhang
     * @since 2020-05-08
     */
    private static void isContains(String extName) {
        if (StringUtils.isNotEmpty(getExtStr())) {
            // 切割文件扩展名
            List<String> extensionList = stringToArrayStrRegex(getExtStr(), ",");

            if (extensionList.size() > 0) {
                //判断
                if (!extensionList.contains(extName)) {
                    log.error("上传文件的类型错误...");
                    throw ChangeSysResponseStatusException.error500("上传文件的类型只能是：" + getExtStr());
                }
            } else {
                log.error("上传文件的类型错误...");
                throw ChangeSysResponseStatusException.error500("上传文件的类型只能是：" + getExtStr());
            }
        }
    }

    /**
     * 字符串分割，转化为数组
     * @param str 字符串
     * @param regex 分隔符有
     * @author Mr.Zhang
     * @since 2020-04-22
     * @return List<String>
     */
    public static List<String> stringToArrayStrRegex(String str, String regex ){
        List<String> list = new ArrayList<>();
        if (str.contains(regex)){

            String[] split = str.split(regex);

            for (String value : split) {
                if(!StringUtils.isBlank(value)){
                    list.add(value);
                }
            }
        }else {
            list.add(str);
        }
        return list;
    }

    /**
     * 生成文件文件名
     * @param fileName 文件名
     * @author Mr.Zhang
     * @since 2020-05-08
     */
    public static String getDestPath(String fileName) {
        //规则：  子目录/年/月/日.后缀名
        return getServerPath() + fileName;
    }

    public static String fileName(String extName){
        return getUuid() + RandomUtil.randomString(10) + "." + extName;
    }

    /**
     * 获取uuid
     * @author Mr.Zhang
     * @since 2020-05-06
     * @
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    /**
     * 生成文件在的实际的路径
     * @author Mr.Zhang
     * @since 2020-05-08
     */
    private static String getServerPath() {
        // 文件分隔符转化为当前系统的格式
        return FilenameUtils.separatorsToSystem( getRootPath() + getWebPath());
    }

    /**
     * web目录可访问的路径
     * @author Mr.Zhang
     * @since 2020-05-08
     */
    private static String getWebPath() {
        // 文件分隔符转化为当前系统的格式
        return getType() + getModelPath() + DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN).replace("-", "/") + "/";
    }

    /**
     * 检测文件大小上限
     * @author Mr.Zhang
     * @since 2020-05-08
     */
    private static void checkSize(Long size) {
        // 文件分隔符转化为当前系统的格式
        float fileSize = (float)size / 1024 / 1024;
        String fs = String.format("%.2f", fileSize);
        if( fileSize > getSize()){
            throw ChangeSysResponseStatusException.error500("最大允许上传" + getSize() + " MB的文件, 当前文件大小为 " + fs + " MB");
        }
    }


    /**
     * 上传文件
     * @param multipartFile 上传的文件对象，必传
     * @author Mr.Zhang
     * @since 2020-05-08
     */
    private static FileUpdateResultVO saveFile(MultipartFile multipartFile) throws IOException {
        if (null == multipartFile || multipartFile.isEmpty()) {
            throw ChangeSysResponseStatusException.error500("上传的文件对象不存在...");
        }
        // 文件名
        String fileName = multipartFile.getOriginalFilename();
        // 文件后缀名
        String extName = FilenameUtils.getExtension(fileName);
        if (StringUtils.isEmpty(extName)) {
            throw ChangeSysResponseStatusException.error500("文件类型未定义不能上传...");
        }

        //文件大小验证
        checkSize(multipartFile.getSize());

        // 判断文件的后缀名是否符合规则
        isContains(extName);

        //文件名
        String newFileName = fileName(extName);
        // 创建目标文件的名称，规则请看destPath方法
        String destPath = getDestPath(newFileName);
        // 创建文件
        File file = createFile(destPath);
        // 保存文件
        multipartFile.transferTo(file);

        // 拼装返回的数据
        FileUpdateResultVO result = new FileUpdateResultVO();
        //如果是图片，就进行图片处理
        if (BooleanUtils.isTrue(isIsCompress())) {

        } else {
            result.setFileSize(multipartFile.getSize());
            result.setFileName(fileName);
            result.setDisk(destPath);
            result.setLink(getWebPath() + newFileName);
            result.setType(multipartFile.getContentType());
        }
        return result;
    }

    /**
     * 上传
     * @param multipartFile 上传的文件对象，必传
     * @since 2020-05-08
     * @author Mr.Zhang
     */
    public static FileUpdateResultVO file(MultipartFile multipartFile) throws IOException {
        return saveFile(multipartFile);
    }
}
