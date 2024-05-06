package com.hzx.furn.service;

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
}
