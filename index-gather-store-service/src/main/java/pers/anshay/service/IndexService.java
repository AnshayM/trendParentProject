package pers.anshay.service;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pers.anshay.pojo.Index;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author anshay
 * @date 2020/6/11
 */
@Api("IndexService服务类")
@Service
public class IndexService {
    private final RestTemplate restTemplate;

    public IndexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Index> fetchIndexesFromThirdPart() {
        List<Map<String, String>> temp = restTemplate.getForObject("http://127.0.0.1:8090/indexes/codes.json", List.class);
        return map2Index(temp);
    }


    private List<Index> map2Index(List<Map<String, String>> temp) {
        if (temp == null) {
            return new ArrayList<>();
        }
        List<Index> indices = new ArrayList<>();
        temp.forEach(item -> {
            indices.add(new Index(item.get("code"), item.get("name")));
        });
        return indices;

    }
}
