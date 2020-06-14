package pers.anshay.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("获取指数")
    public List<Index> get() {
        return indexService.get();
    }

    @GetMapping("/freshCodes")
    @ApiOperation("更新指数")
    public String fresh() {
        indexService.fresh();
        return "fresh codes successfully";
    }

    @GetMapping("/removeCodes")
    @ApiOperation("移除指数")
    public String remove() {
        indexService.remove();
        return "remove codes successfully";
    }
}
