package com.itczw.vladmin.consumer.constants;

/**
 *  配置类
 */
public class Constants {

    //上传类型
    public static final String UPLOAD_TYPE_IMAGE = "image";

    //上传类型
    public static final String UPLOAD_TYPE_FILE = "file";

    public static final String UPLOAD_ROOT_PATH_CONFIG_KEY = "upload_root_path";//上传地址

    //图片上传-扩展名
    public static final String UPLOAD_IMAGE_EXT_STR_CONFIG_KEY = "image_ext_str";//图片上传

    //上传图片大小上限
    public static final String UPLOAD_IMAGE_MAX_SIZE_CONFIG_KEY = "image_max_size";

    //文件上传上限
    public static final String UPLOAD_FILE_EXT_STR_CONFIG_KEY = "file_ext_str";
    //最大上传文件
    public static final String UPLOAD_FILE_MAX_SIZE_CONFIG_KEY = "file_max_size";

    /**
     * 本地文件访问的http域名地址
     */
    public static final String LOCAL_FILE_URL_HOST = "local_url_domain";
}
