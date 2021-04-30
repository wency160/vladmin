package com.itczw.vladmin.consumer.annex.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itczw.vladmin.consumer.annex.dto.AnnexSaveOrUpdateDTO;
import com.itczw.vladmin.consumer.annex.entity.AnnexEntity;
import com.itczw.vladmin.consumer.annex.vo.AnnexInfoVO;

/**
 * 附件管理
 * @author wency
 */
public interface AnnexService extends IService<AnnexEntity> {

    /**
     * 根据id查询附件管理信息
     *
     * @param id id
     * @return com.itczw.vladmin.annex.vo.tbl.AnnexInfoVO
     */
    AnnexInfoVO getAnnex(Long id);

    /**
     * 新增附件管理
     *
     * @param dto 附件管理新增参数
     */
    void saveAnnex(AnnexSaveOrUpdateDTO dto);

    /**
     * 更新附件管理
     *
     * @param id id
     * @param dto 附件管理更新参数
     */
    void updateAnnex(Long id, AnnexSaveOrUpdateDTO dto);

    /**
     * 删除附件管理
     *
     * @param ids id数组
     */
    void removeAnnexs(Long[] ids);
}
