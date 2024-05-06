package com.hzx.furn.lambda;

import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.HighGoDialect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sun.security.krb5.internal.crypto.Des;

/**
 * @author Jools He
 * @version 1.0
 * @date 2024/5/6 15:32
 * @description: TODO
 */
public class Test {

    public static void main(String[] args) {

        //hf 可以认为就是实现了 HspFunction 接口的对象, 运行类型是: Test$lambda@673
        //也就可以调用 HspFunction 接口方法
        HspFunction<Desk, String> hf = Desk::getName;
        hf.hi();

        //当调用 apply 方法时，最终会走到 getName
        String val = hf.apply(new Desk());
        System.out.println("val=" + val);

        HspInterface hi = () -> System.out.println("自定义 hi 方法");
        hi.hi();
    }
}

/**
 * 自定义函数式接口
 *
 * @FunctionalInterface 标识一个接口是函数式接口
 * 函数式接口的特点是有且仅有一个抽象方法
 */
@FunctionalInterface
interface HspFunction<T, R> {

    //这个方法表示的是，根据类型 T 的参数获取到类型 R 的结果
    R apply(T t);

    default void hi() {
        System.out.println("hi");
    }
}

@FunctionalInterface
interface HspInterface {
    void hi();
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Desk {
    private String name = "desk";
    private String brand;
    private Integer id;
}



