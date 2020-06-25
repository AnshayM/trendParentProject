package pers.anshay.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 指数数据
 *
 * @author anshay
 * @date 2020/6/25
 */
@Data
public class Index implements Serializable {
    String code;
    String name;
}
