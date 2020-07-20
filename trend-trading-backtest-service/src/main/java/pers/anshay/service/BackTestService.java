package pers.anshay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.anshay.client.IndexDataClient;
import pers.anshay.pojo.IndexData;
import pers.anshay.pojo.Profit;

import java.util.*;

/**
 * 提供所有模拟回测数据的微服务
 *
 * @author anshay
 * @date 2020/7/11
 */
@Service
@Slf4j
public class BackTestService {
    @Autowired
    private IndexDataClient indexDataClient;

    public List<IndexData> listIndexData(String code) {
        List<IndexData> indexDataList = indexDataClient.getIndexData(code);
        Collections.reverse(indexDataList);
        indexDataList.forEach(v -> log.info("日期{}", v.getDate()));
        return indexDataList;
    }

    /**
     * @param ma
     * @param sellRate
     * @param buyRate
     * @param serviceCharge
     * @param indexDatas
     * @return
     */
    public Map<String, Object> simulate(int ma, float sellRate, float buyRate, float serviceCharge, List<IndexData> indexDatas) {
        List<Profit> profits = new ArrayList<>();
        float initCash = 1000;
        float cash = initCash;
        float share = 0;
        float value = 0;
        float init = 0;
        if (!indexDatas.isEmpty()) {
            init = indexDatas.get(0).getClosePoint();
        }
        for (int i = 0; i < indexDatas.size(); i++) {
            IndexData indexData = indexDatas.get(i);
            float closePoint = indexData.getClosePoint();
            float avg = getMA(i, ma, indexDatas);
            float max = getMax(i, ma, indexDatas);

            float increase_rate = closePoint / avg;
            float decrease_rate = closePoint / max;

            if (avg != 0) {
                //buy 超过了均线
                if (increase_rate > buyRate) {
                    //如果没买
                    if (0 == share) {
                        share = cash / closePoint;
                        cash = 0;
                    }
                }
                //sell 低于了卖点
                else if (decrease_rate < sellRate) {
                    //如果没卖
                    if (0 != share) {
                        cash = closePoint * share * (1 - serviceCharge);
                        share = 0;
                    }
                }
                //do nothing
                else {

                }
            }

            if (share != 0) {
                value = closePoint * share;
            } else {
                value = cash;
            }
            float rate = value / initCash;

            Profit profit = new Profit();
            profit.setDate(indexData.getDate());
            profit.setValue(rate * init);

            log.info("profit.value:{}", profit.getValue());
            profits.add(profit);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("profits", profits);
        return map;
    }

    private static float getMax(int i, int day, List<IndexData> list) {
        int start = i - 1 - day;
        start = Math.max(start, 0);
        int now = i - 1;

        float max = 0;
        for (int j = start; j < now; j++) {
            IndexData bean = list.get(j);
            if (bean.getClosePoint() > max) {
                max = bean.getClosePoint();
            }
        }
        return max;
    }

    private static float getMA(int i, int ma, List<IndexData> list) {
        int start = i - 1 - ma;
        int now = i - 1;

        if (start < 0) {
            return 0;
        }
        float sum = 0;
        float avg = 0;
        for (int j = start; j < now; j++) {
            IndexData bean = list.get(j);
            sum += bean.getClosePoint();
        }
        avg = sum / (now - start);
        return avg;
    }
}