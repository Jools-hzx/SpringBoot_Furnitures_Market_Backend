package com.hzx.furn.controller;

import com.hzx.furn.bean.Furn;
import com.hzx.furn.service.FurnService;
import com.hzx.furn.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jools He
 * @version 1.0
 * @date 2024/5/5 12:27
 * @description: TODO
 */
@RestController
@SuppressWarnings("all")
public class FurnController {

    @Resource
    private FurnService furnService;

    @GetMapping("/list")
    public Result<List<Furn>> listAllFurns() {
        List<Furn> list = null;
        try {
            list = furnService.list();
        } catch (Exception e) {
            return Result.failServerError();
        }
        Result<List<Furn>> result = Result.success(list);
        return result;
    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody Furn furn) { //接收到的请求参数为json格式
        try {
            System.out.println("---准备添加家具信息--- furn:" + furn);
            boolean affectedRow = furnService.save(furn);
        } catch (Exception e) {
            return Result.failServerError();
        }
        return Result.success("添加家具成功");
    }
}
