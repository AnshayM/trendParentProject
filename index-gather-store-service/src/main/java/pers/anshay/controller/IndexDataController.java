package pers.anshay.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.anshay.pojo.IndexData;
import pers.anshay.service.IndexDataService;

import java.util.List;

/**
 * @author anshay
 * @date 2020/6/16
 */
@Api("指数数据")
@RestController
public class IndexDataController {
    private final IndexDataService indexDataService;

    public IndexDataController(IndexDataService indexDataService) {
        this.indexDataService = indexDataService;
    }
    //	http://127.0.0.1:8001/freshIndexData/000300
    //	http://127.0.0.1:8001/getIndexData/000300
    //	http://127.0.0.1:8001/removeIndexData/000300

    @GetMapping("/freshIndexData/{code}")
    public String fresh(@PathVariable("code") String code) {
        indexDataService.fresh(code);
        return "fresh index data successfully";
    }

    @GetMapping("/getIndexData/{code}")
    public List<IndexData> get(@PathVariable("code") String code) {
        return indexDataService.get(code);
    }

    @GetMapping("/removeIndexData/{code}")
    public String remove(@PathVariable("code") String code) {
        indexDataService.remove(code);
        return "remove index data successfully";
    }
}
