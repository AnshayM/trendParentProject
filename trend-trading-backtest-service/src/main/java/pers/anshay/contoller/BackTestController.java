package pers.anshay.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pers.anshay.pojo.IndexData;
import pers.anshay.service.BackTestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author anshay
 * @date 2020/7/11
 */
@Controller
public class BackTestController {
    private final BackTestService backTestService;

    public BackTestController(BackTestService backTestService) {
        this.backTestService = backTestService;
    }

    @GetMapping("/simulate/{code}")
    @CrossOrigin
    public Map<String, Object> backTest(@PathVariable("code") String code) {
        List<IndexData> indexDatas = backTestService.listIndexData(code);
        HashMap<String, Object> map = new HashMap<>();
        map.put("indexDatas", indexDatas);
        return map;
    }

}
