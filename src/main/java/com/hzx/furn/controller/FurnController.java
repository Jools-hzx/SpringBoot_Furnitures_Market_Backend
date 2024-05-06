package com.hzx.furn.controller;

import com.hzx.furn.bean.Furn;
import com.hzx.furn.service.FurnService;
import com.hzx.furn.utils.Result;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jools He
 * @version 1.0
 * @date 2024/5/5 12:27
 * @description: TODO
 */
@Slf4j
@RestController
@SuppressWarnings("all")
public class FurnController {

    @Resource
    private FurnService furnService;

    @DeleteMapping("/delById")
    public Result<?> delFurnById(@RequestParam("id") Integer id) {
        log.info("删除 Furn-id: {}", id);
        boolean removed = furnService.removeById(id);
        if (removed) {
            Result<String> success = Result.success("删除成功");
            return success;
        } else return Result.failServerError();
    }

    @GetMapping("/findById")
    public Result<Furn> findFurnById(@RequestParam("id") Integer id) {
        log.info("查询 Furn-id:{}", id);
        Furn furn = furnService.getById(id);
        if (null != furn) {
            Result<Furn> success = Result.success(furn);
            return success;
        } else {
            Result error = Result.failServerError();
            return error;
        }
    }

    @PutMapping("/update")
    public Result<?> updateFurnById(@RequestBody Furn furn) {
        try {
            log.info("接收到更新 furn 请求:{}", furn);
            boolean success = furnService.updateById(furn);
            if (success) return Result.success("更新成功");
            else {
                Result result = Result.failServerError();
                result.setData("更新失败");
                return result;
            }
        } catch (Exception e) {
            return Result.failServerError();
        }
    }

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
