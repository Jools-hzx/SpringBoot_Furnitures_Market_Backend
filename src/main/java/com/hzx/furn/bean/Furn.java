package com.hzx.furn.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Jools He
 * @version 1.0
 * @date 2024/5/5 12:23
 * @description: TODO
 */
@Data
@NoArgsConstructor
@TableName("furn")
public class Furn implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotEmpty(message = "请输入家具名称")
    private String name;

    @NotEmpty(message = "请输入厂商")
    private String maker;

    @Range(min = 0, message = "价格不能小于0")
    @NotNull(message = "请输入数字")
    private Double price;

    @Range(min = 0, message = "库存不能小于0")
    @NotNull(message = "请输入数字")
    private Integer sales;

    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "库存不能小于0")
    private Integer stock;
}
