package pers.anshay.contoller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.anshay.pojo.IndexData;
import pers.anshay.pojo.Profit;
import pers.anshay.pojo.Trade;
import pers.anshay.service.BackTestService;

import java.util.*;

/**
 * @author anshay
 * @date 2020/7/11
 */
@RestController
public class BackTestController {
    private final BackTestService backTestService;

    public BackTestController(BackTestService backTestService) {
        this.backTestService = backTestService;
    }

    @GetMapping("/simulate/{code}/{startDate}/{endDate}")
    @CrossOrigin
    public Map<String, Object> backTest(@PathVariable("code") String code, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        List<IndexData> list = backTestService.listIndexData(code);
        List<IndexData> resultList = filterByDateRange(list, startDate, endDate);
        int ma = 20;
        float sellRate = 0.95f;
        float buyRate = 1.05f;
        float serviceCharge = 0f;
        Map<String,?> simulateResult= backTestService.simulate(ma,sellRate, buyRate,serviceCharge, resultList);
        List<Profit> profits = (List<Profit>) simulateResult.get("profits");
        List<Trade> trades = (List<Trade>) simulateResult.get("trades");

        String indexStartDate = resultList.get(0).getDate();
        String indexEndDate = resultList.get(resultList.size() - 1).getDate();

        HashMap<String, Object> map = new HashMap<>();
        map.put("indexDatas", resultList);
        map.put("profits", profits);
        map.put("trades", trades);
        map.put("indexStartDate", indexStartDate);
        map.put("indexEndDate", indexEndDate);
        return map;
    }

    /**
     * 取时间范围内的数据
     *
     * @param allIndexData 数据集合
     * @param strStartDate 结束日期
     * @param strEndDate   开始时间
     * @return List<IndexData>
     */
    private List<IndexData> filterByDateRange(List<IndexData> allIndexData, String strStartDate, String strEndDate) {
        if (StrUtil.isBlankOrUndefined(strStartDate) || StrUtil.isBlankOrUndefined(strEndDate)) {
            return allIndexData;
        }

        List<IndexData> result = new ArrayList<>();
        Date startDate = DateUtil.parse(strStartDate);
        Date endDate = DateUtil.parse(strEndDate);

        for (IndexData indexData : allIndexData) {
            Date date = DateUtil.parse(indexData.getDate());
            if (date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime()) {
                result.add(indexData);
            }
        }
        return result;
    }

}
