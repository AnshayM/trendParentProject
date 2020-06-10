package pers.anshay.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.anshay.pojo.Index;
import pers.anshay.service.IndexService;

import java.util.List;

/**
 * @author anshay
 * @date 2020/6/11
 */
@Api("指数")
@RestController
public class IndexController {
    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("/getCodes")
    public List<Index> get() {
        return indexService.fetchIndexesFromThirdPart();
    }
}
