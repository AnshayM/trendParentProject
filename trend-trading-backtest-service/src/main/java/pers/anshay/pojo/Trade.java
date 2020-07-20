package pers.anshay.pojo;

import lombok.Data;

/**
 * 交易明细
 *
 * @author anshay
 * @date 2020/7/20
 */
@Data
public class Trade {
    private String buyDate;
    private String sellDate;
    private float buyClosePoint;
    private float sellClosePoint;
    private float rate;

    public Trade() {
    }

    public Trade(String buyDate, String sellDate, float buyClosePoint, float sellClosePoint, float rate) {
        this.buyDate = buyDate;
        this.sellDate = sellDate;
        this.buyClosePoint = buyClosePoint;
        this.sellClosePoint = sellClosePoint;
        this.rate = rate;
    }
}
