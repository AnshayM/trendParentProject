package pers.anshay.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author anshay
 * @date 2020/7/16
 */
@ApiModel("收益")
@Data
public class Profit {
    private String date;
    private float value;

    public Profit(String date, float value) {
        this.date = date;
        this.value = value;
    }

    public Profit() {
    }
}
