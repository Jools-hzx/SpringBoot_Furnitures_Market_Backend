package com.hzx.furn.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzx.furn.bean.Furn;
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
}
