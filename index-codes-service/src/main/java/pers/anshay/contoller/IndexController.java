package pers.anshay.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.anshay.config.IpConfiguration;
import pers.anshay.pojo.Index;
import pers.anshay.service.IndexService;

import java.util.List;

/**
 * @author anshay
 * @date 2020/6/25
 */
@Slf4j
@RestController
public class IndexController {
    private final IndexService indexService;
    private final IpConfiguration ipConfiguration;

    public IndexController(IndexService indexService, IpConfiguration ipConfiguration) {
        this.indexService = indexService;
        this.ipConfiguration = ipConfiguration;
    }

    /**
     * @return
     * @CrossOrigin 表示允许跨域，因为后续的回测视图是另一个端口号的，访问这个服务是属于跨域了。
     */
    @GetMapping("/codes")
    @CrossOrigin
    public List<Index> codes() {
        log.info("current instance's port is {}", ipConfiguration.getPort());
        return indexService.get();
    }
}
