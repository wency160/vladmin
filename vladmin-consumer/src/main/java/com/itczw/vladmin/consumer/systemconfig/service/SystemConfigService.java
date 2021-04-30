package com.itczw.vladmin.consumer.systemconfig.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itczw.vladmin.common.core.vo.PageVO;
import com.itczw.vladmin.consumer.systemconfig.dto.SystemConfigPageDTO;
import com.itczw.vladmin.consumer.systemconfig.dto.SystemConfigSaveOrUpdateDTO;
import com.itczw.vladmin.consumer.systemconfig.entity.SystemConfigEntity;
import com.itczw.vladmin.consumer.systemconfig.vo.SystemConfigInfoVO;
import com.itczw.vladmin.consumer.systemconfig.vo.SystemConfigPageVO;

/**
 * 系统配置
 *
 * @author wency
 */
public interface SystemConfigService extends IService<SystemConfigEntity> {

    /**
     * 分页查询系统配置列表
     *
     * @param dto 系统配置查询参数
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.itczw.vladmin.comsumer.vo.systemconfig.SystemConfigEntityPageVO>
     */
    PageVO<SystemConfigPageVO> pageSystemConfigs(SystemConfigPageDTO dto);

    /**
     * 根据id查询系统配置信息
     *
     * @param id id
     * @return com.itczw.vladmin.comsumer.vo.systemconfig.SystemConfigEntityInfoVO
     */
    SystemConfigInfoVO getSystemConfig(Long id);

    /**
     * 新增系统配置
     *
     * @param dto 系统配置新增参数
     */
    void saveSystemConfig(SystemConfigSaveOrUpdateDTO dto);

    /**
     * 更新系统配置
     *
     * @param id  id
     * @param dto 系统配置更新参数
     */
    void updateSystemConfig(Long id, SystemConfigSaveOrUpdateDTO dto);

    /**
     * 删除系统配置
     *
     * @param ids id数组
     */
    void removeSystemConfigs(Long[] ids);

    /**
     * 通过key获取值
     *
     * @param key
     * @return
     */
    String getValueByKey(String key);
}
