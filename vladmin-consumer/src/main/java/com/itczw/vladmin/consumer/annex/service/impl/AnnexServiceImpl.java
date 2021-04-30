package com.itczw.vladmin.consumer.annex.service.impl;

import com.itczw.vladmin.common.core.util.ApiAssert;
import com.itczw.vladmin.common.core.util.BeanCopyUtil;
import com.itczw.vladmin.consumer.annex.dto.AnnexSaveOrUpdateDTO;
import com.itczw.vladmin.consumer.annex.entity.AnnexEntity;
import com.itczw.vladmin.consumer.annex.mapper.AnnexMapper;
import com.itczw.vladmin.consumer.annex.service.AnnexService;
import com.itczw.vladmin.consumer.annex.vo.AnnexInfoVO;
import com.itczw.vladmin.consumer.systemconfig.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 附件管理服务实现类
 *
 * @author wency
 */
@Service
public class AnnexServiceImpl extends ServiceImpl<AnnexMapper, AnnexEntity> implements AnnexService {

    @Autowired
    private SystemConfigService systemConfigService;

    @Override
    public AnnexInfoVO getAnnex(Long id) {
        AnnexEntity oriAnnex = this.getById(id);
        ApiAssert.notNull(oriAnnex, String.format("id：%s不存在", id));
        return BeanCopyUtil.beanCopy(oriAnnex, AnnexInfoVO.class);
    }

    @Override
    public void saveAnnex(AnnexSaveOrUpdateDTO dto) {
        AnnexEntity annex = BeanCopyUtil.beanCopy(dto, AnnexEntity.class);
        annex.setUpdateType(dto.getUpdateType() != null ? dto.getUpdateType().name() : null);
        this.save(annex);
    }

    @Override
    public void updateAnnex(Long id, AnnexSaveOrUpdateDTO dto) {
        AnnexEntity oriAnnex = this.getById(id);
        ApiAssert.notNull(oriAnnex, String.format("id：%s不存在", id));
        AnnexEntity annex = BeanCopyUtil.beanCopy(dto, AnnexEntity.class);
        annex.setId(id);
        this.updateById(annex);
    }

    @Override
    public void removeAnnexs(Long[] ids) {
        for (Long id : ids) {
            AnnexEntity oriAnnex = this.getById(id);
            ApiAssert.notNull(oriAnnex, String.format("id：%s不存在", id));
            this.removeById(id);
        }
    }

}