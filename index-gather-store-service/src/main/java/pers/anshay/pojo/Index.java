package pers.anshay.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author anshay
 * @date 2020/6/10
 */
@Data
@ApiModel("指数类，用于指数里的名称和代码")
public class Index {
    private String code;
    private String name;

    public Index(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Index() {
    }
}
