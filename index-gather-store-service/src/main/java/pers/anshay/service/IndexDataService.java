package pers.anshay.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pers.anshay.pojo.IndexData;
import pers.anshay.util.SpringContextUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 指数数据service
 *
 * @author anshay
 * @date 2020/6/16
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {
    private final Map<String, List<IndexData>> indexDataMap = new HashMap<>();

    private final RestTemplate restTemplate;

    public IndexDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "thirdPartNotConnected")
    public List<IndexData> fresh(String code) {
        List<IndexData> indexDataList = fetchIndexesFromThirdPart(code);
        indexDataMap.put(code, indexDataList);

        log.info("code:" + code);
        log.info("indexDataMap:" + indexDataMap.get(code).size());

        IndexDataService indexDataService = SpringContextUtil.getBean(IndexDataService.class);
        indexDataService.remove(code);
        return indexDataService.store(code);
    }

    /**
     * 移除指定code的数据
     *
     * @param code 对应code
     */
    @CacheEvict(key = "'indexData-code-'+ #p0")
    public void remove(String code) {
    }

    /**
     * 保存方法，每次去获取，获取后更新
     *
     * @param code 对应code
     * @return List<IndexData>
     */
    @CachePut(key = "'indexData-code-'+ #p0")
    public List<IndexData> store(String code) {
        return indexDataMap.get(code);
    }

    /**
     * 获取对应code的数据
     *
     * @param code 对应code
     * @return List<IndexData>
     */
    @Cacheable(key = "'indexData-code-'+ #p0")
    public List<IndexData> get(String code) {
        return CollUtil.toList();
    }

    public List<IndexData> fetchIndexesFromThirdPart(String code) {
        List<Map> temp = restTemplate.getForObject("http://127.0.0.1:8090/indexes/" + code + ".json", List.class);
        return map2IndexData(temp);
    }

    private List<IndexData> map2IndexData(List<Map> temp) {
        List<IndexData> indexDataList = new ArrayList<>();
        for (Map map : temp) {
            String date = map.get("data").toString();
            Float closePoint = Convert.toFloat(map.get("closePoint"));
            indexDataList.add(new IndexData(date, closePoint));
        }
        return indexDataList;
    }

    /**
     * 断路器回调的失败数据
     *
     * @param code 对应指数
     * @return List<IndexData>
     */
    public List<IndexData> thirdPartNotConnected(String code) {
        log.info("thirdPartNotConnected()");
        IndexData index = new IndexData();
        index.setClosePoint(0);
        index.setDate("n/a");
        return CollectionUtil.toList(index);
    }
}
