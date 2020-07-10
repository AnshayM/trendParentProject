package pers.anshay.service;

import cn.hutool.core.collection.CollUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pers.anshay.pojo.Index;

import java.util.List;

/**
 * @author anshay
 * @date 2020/6/25
 */
@Service
@CacheConfig(cacheNames = "indexes")
public class IndexService {
    private List<Index> indexes;

    @Cacheable(key = "'all_codes'")
    public List<Index> get() {
        return CollUtil.toList(new Index("000000", "无效指数代码"));
    }
}
