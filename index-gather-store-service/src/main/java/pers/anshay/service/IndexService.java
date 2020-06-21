package pers.anshay.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pers.anshay.pojo.Index;
import pers.anshay.util.SpringContextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author anshay
 * @date 2020/6/11
 */
@Api("IndexService服务类")
@Slf4j
@Service
@CacheConfig(cacheNames = "indexes")
public class IndexService {
    private List<Index> indices;
    private final RestTemplate restTemplate;

    public IndexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "thirdPartNotConnected")
    public List<Index> fresh() {
        indices = fetchIndexesFromThirdPart();
        IndexService indexService = SpringContextUtil.getBean(IndexService.class);
        indexService.remove();
        return indexService.store();
    }

    @Cacheable(key = "'all_codes'")
    public List<Index> store() {
        return indices;
    }

    @CacheEvict(key = "'all_codes'", allEntries = true)
    public void remove() {
    }

    @Cacheable(key = "'all_codes'")
    public List<Index> get() {
        return CollUtil.toList();
    }

    @Cacheable(key = "'all_codes'")
    public List<Index> fetchIndexesFromThirdPart() {
        //TODO 调试后创建实体类来接收
        List<Map<String, String>> temp = restTemplate.getForObject("http://127.0.0.1:8090/indexes/codes.json", List.class);
        return map2Index(temp);
    }

    /**
     * 处理请求结果，返回指数集合
     *
     * @param temp List<Map<String, String>>
     * @return List<Index>
     */
    private List<Index> map2Index(List<Map<String, String>> temp) {
        if (temp == null) {
            return new ArrayList<>();
        }
        List<Index> indices = new ArrayList<>();
        temp.forEach(item -> {
            indices.add(new Index(item.get("code"), item.get("name")));
        });
        return indices;

    }

    /**
     * 断路器返回的方法
     *
     * @return List<Index>
     */
    public List<Index> thirdPartNotConnected() {
        log.info("第三方数据服务未连接");
        Index index = new Index();
        index.setCode("000000");
        index.setName("无效指数代码");
        return CollectionUtil.toList(index);
    }


}
