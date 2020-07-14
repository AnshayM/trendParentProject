package pers.anshay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pers.anshay.pojo.IndexData;

import java.util.List;

/**
 * 表示访问不了的时候，就去找 IndexDataClientFeignHystrix 要数据了。
 *
 * @author anshay
 * @date 2020/7/11
 */
@FeignClient(value = "INDEX-DATA-SERVICE", fallback = IndexDataClientFeignHystrix.class)
public interface IndexDataClient {
    @GetMapping("/data/{code}")
    List<IndexData> getIndexData(@PathVariable("code") String code);

}
