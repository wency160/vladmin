package com.itczw.vladmin.consumer.systemconfig.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itczw.vladmin.common.core.util.ApiAssert;
import com.itczw.vladmin.common.core.util.BeanCopyUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.itczw.vladmin.common.core.vo.PageVO;
import com.itczw.vladmin.consumer.enums.CommonEnums;
import com.itczw.vladmin.consumer.systemconfig.dto.SystemConfigPageDTO;
import com.itczw.vladmin.consumer.systemconfig.dto.SystemConfigSaveOrUpdateDTO;
import com.itczw.vladmin.consumer.systemconfig.entity.SystemConfigEntity;
import com.itczw.vladmin.consumer.systemconfig.mapper.SystemConfigMapper;
import com.itczw.vladmin.consumer.systemconfig.service.SystemConfigService;
import com.itczw.vladmin.consumer.systemconfig.vo.SystemConfigInfoVO;
import com.itczw.vladmin.consumer.systemconfig.vo.SystemConfigPageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 系统配置服务实现类
 *
 * @author wency
 */
@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfigEntity> implements SystemConfigService {

    @Override
    public PageVO<SystemConfigPageVO> pageSystemConfigs(SystemConfigPageDTO dto) {
        IPage<SystemConfigEntity> page = this.lambdaQuery().orderByDesc(SystemConfigEntity::getId)
                .page(new Page<>(dto.getPageNum(), dto.getPageSize()));
        return BeanCopyUtil.pageCopy(page, SystemConfigPageVO.class);
    }

    @Override
    public SystemConfigInfoVO getSystemConfig(Long id) {
        SystemConfigEntity oriSystemConfig = this.getById(id);
        ApiAssert.notNull(oriSystemConfig, String.format("id：%s不存在", id));
        return BeanCopyUtil.beanCopy(oriSystemConfig, SystemConfigInfoVO.class);
    }

    @Override
    public void saveSystemConfig(SystemConfigSaveOrUpdateDTO dto) {
        SystemConfigEntity systemConfig = BeanCopyUtil.beanCopy(dto, SystemConfigEntity.class);
        this.save(systemConfig);
    }

    @Override
    public void updateSystemConfig(Long id, SystemConfigSaveOrUpdateDTO dto) {
        SystemConfigEntity oriSystemConfig = this.getById(id);
        ApiAssert.notNull(oriSystemConfig, String.format("id：%s不存在", id));
        SystemConfigEntity systemConfig = BeanCopyUtil.beanCopy(dto, SystemConfigEntity.class);
        systemConfig.setId(id);
        this.updateById(systemConfig);
    }

    @Override
    public void removeSystemConfigs(Long[] ids) {
        for (Long id : ids) {
            SystemConfigEntity oriSystemConfig = this.getById(id);
            ApiAssert.notNull(oriSystemConfig, String.format("id：%s不存在", id));
            this.removeById(id);
        }
    }

    @Override
    @Cacheable(value = "system_config_value_key", key = "#key")
    public String getValueByKey(String key) {
        LambdaQueryWrapper<SystemConfigEntity> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(SystemConfigEntity::getStatus, CommonEnums.Status.ENABLE)
                .eq(SystemConfigEntity::getConfigKey, key);
        SystemConfigEntity systemConfigEntity = this.getOne(lambdaQueryWrapper);
        if (null == systemConfigEntity || StringUtils.isBlank(systemConfigEntity.getValue())) {
            return null;
        }
        return systemConfigEntity.getValue();
    }
}