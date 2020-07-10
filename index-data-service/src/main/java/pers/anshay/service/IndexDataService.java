package pers.anshay.service;

import cn.hutool.core.collection.CollUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pers.anshay.pojo.IndexData;

import java.util.List;

/**
 * @author anshay
 * @date 2020/7/11
 */
@Service
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {
    @Cacheable(key = "'indexData-code-'+ #p0")
    public List<IndexData> get(String code) {
        return CollUtil.toList();
    }
}
