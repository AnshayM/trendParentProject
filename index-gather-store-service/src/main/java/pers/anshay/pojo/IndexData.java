package pers.anshay.pojo;

import lombok.Data;

/**
 * 指数数据
 *
 * @author anshay
 * @date 2020/6/16
 */
@Data
public class IndexData {
    //日期
    private String date;
    //值
    private float closePoint;

    public IndexData(String date, float closePoint) {
        this.date = date;
        this.closePoint = closePoint;
    }

    public IndexData() {
    }
}
