package pers.anshay.client;

import org.springframework.stereotype.Component;
import pers.anshay.pojo.IndexData;

import java.util.Collections;
import java.util.List;

/**
 * @author anshay
 * @date 2020/7/11
 */
@Component
public class IndexDataClientFeignHystrix implements IndexDataClient{
    @Override
    public List<IndexData> getIndexData(String code) {
        return Collections.singletonList(new IndexData("0000-00-00", 0));
    }
}
