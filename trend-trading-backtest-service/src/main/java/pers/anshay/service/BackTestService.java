package pers.anshay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.anshay.client.IndexDataClient;
import pers.anshay.pojo.IndexData;

import java.util.Collections;
import java.util.List;

/**
 * 提供所有模拟回测数据的微服务
 *
 * @author anshay
 * @date 2020/7/11
 */
@Service
@Slf4j
public class BackTestService {
    @Autowired
    private IndexDataClient indexDataClient;

    public List<IndexData> listIndexData(String code) {
        List<IndexData> indexDataList = indexDataClient.getIndexData(code);
        Collections.reverse(indexDataList);
        indexDataList.forEach(v -> log.info("日期{}", v.getDate()));
        return indexDataList;
    }
}
