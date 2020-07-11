package pers.anshay.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.anshay.config.IpConfiguration;
import pers.anshay.pojo.IndexData;
import pers.anshay.service.IndexDataService;

import java.util.List;

/**
 * @author anshay
 * @date 2020/6/25
 */
@Slf4j
@RestController
public class IndexDataController {
    private final IndexDataService indexDataService;
    private final IpConfiguration ipConfiguration;

    public IndexDataController(IndexDataService indexService, IpConfiguration ipConfiguration) {
        this.indexDataService = indexService;
        this.ipConfiguration = ipConfiguration;
    }
    //exp:  http://127.0.0.1:8021/data/000300
    @GetMapping("/data/{code}")
    public List<IndexData> get(@PathVariable("code") String code) throws Exception {
        log.info("current instance is :{}", ipConfiguration.getPort());
        return indexDataService.get(code);
    }
}
