package pers.anshay.pojo;

import lombok.Data;

/**
 * 指数数据
 *
 * @author anshay
 * @date 2020/6/16
 */
@Data
public class AnnualProfit {
    private int year;
    private float indexIncome;
    private float trendIncome;
}