package com.hzx.furn.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzx.furn.bean.Furn;
import com.sun.org.apache.bcel.internal.generic.LMUL;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jools He
 * @version 1.0
 * @date 2024/5/6 10:34
 * @description: TODO
 */
@SpringBootTest
public class FurnServiceTest {

    @Resource
    private FurnService furnService;

    @Test
    public void listFurns() {
        List<Furn> list = furnService.list();
        for (Furn furn : list) {
            System.out.println(furn);
        }
    }

    @Test
    public void updateFurns() {
        Furn furn = new Furn();
        furn.setId(10);
        furn.setName("AHAHAHAH");
        boolean success = furnService.updateById(furn);
        if (success) System.out.println("更新成功~~");
    }

    @Test
    public void queryById() {
        Furn furn = furnService.getById("5");
        System.out.println("查询到的结果为:" + furn);
    }

    @Test
    public void delFurnById() {
        boolean removed = furnService.removeById(11);
        if (removed) System.out.println("删除成功~");
    }

    @Test
    public void listPage() {
        Page<Furn> page = new Page<>();
        page.setCurrent(3); //当前页
        page.setSize(5);    //每页存放的数据总量
        Page<Furn> furnPage = furnService.page(page);
        long total = furnPage.getTotal();
        List<Furn> records = furnPage.getRecords(); //获取到查询成功的数据
        //遍历查询结果
        for (Furn furn : records) {
            System.out.println(furn);
        }
        System.out.println("---------");
        System.out.println("查询到的总记录数目:" + total);
        System.out.println("当前查询第: " + furnPage.getCurrent() + "页");
        System.out.println("当前页面的 size:" + furnPage.getSize());
    }

    @Test
    public void testListPagesLikeName() {
        Page<Furn> page = new Page<>();
        //确认实际数据数量符号要求，否则不会执行
        //具体数据的 SQL 语句（如 SELECT * FROM furn WHERE (name LIKE ?) LIMIT ?, ?）
        page.setCurrent(3); //当前页
        page.setSize(5);    //每页存放的数据总量

        // Step1：创建一个 QueryWrapper 对象
        QueryWrapper<Furn> queryWrapper = Wrappers.query();

        // Step2： 构造查询条件
        /*
        queryWrapper.select()
                .eq("age", 20)
                .like("name", "X");
         */
        queryWrapper.like("name", "小");

        // Step3：执行查询
        // Step3：执行查询
        Page<Furn> furnPage = furnService.page(page, queryWrapper);
        List<Furn> records = furnPage.getRecords();
        for (Furn record : records) {
            System.out.println(record);
        }
    }

    @Test
    public void testLambdaQueryExamples() {

        /*
         这个函数式接口的源码如下
        @FunctionalInterface
        public interface SFunction<T, R> extends Function<T, R>, Serializable {
        }

        表示根据类型 T 的参数获取到类型 R 的结果
        @FunctionalInterface
        public interface Function<T, R> {
           R apply(T t);
           //另外就是一些默认实现的方法
        }

        传入 Furn::getName 之后，就相当于实现了 SFunction 的 apply 方法
        底层会通过你传入的 Furn::getName 得到该方法对应的属性映射的表字段
         */
        LambdaQueryWrapper<Furn> lambdaQuery = Wrappers.lambdaQuery();
        String search = "X";
        lambdaQuery.like(Furn::getName, search);

        List<Furn> furns = furnService.list(lambdaQuery);
        for (Furn furn : furns) {
            System.out.println(furn);
        }
    }
}
