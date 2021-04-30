package com.itczw.vladmin.consumer;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @author wency
 * @description: 统一删除缓存
 **/
@Service
public class CacheToolsService {

    @Caching(evict = {
            @CacheEvict(value = "freight_templates_list", allEntries = true),
            @CacheEvict(value = "freight_templates_details", allEntries = true),
            @CacheEvict(value = "fetch_page_category_list", allEntries = true),
            @CacheEvict(value = "fetch_category_details", allEntries = true),
            @CacheEvict(value = "fetch_category_tree", allEntries = true),
            @CacheEvict(value = "city_tree_list", allEntries = true)
    })
    public String deleteCache() {
        return "ok";
    }
}
