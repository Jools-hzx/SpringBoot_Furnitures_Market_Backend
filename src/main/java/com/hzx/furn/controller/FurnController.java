package com.hzx.furn.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzx.furn.bean.Furn;
import com.hzx.furn.service.FurnService;
import com.hzx.furn.utils.Result;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
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

    /**
     * 分页按照关键字模糊查询数据
     *
     * @param currentPage 当前页码
     * @param pageSize    页面数据容量
     * @param search      模糊搜索的关键字
     * @return 查询到的页面数据
     */
    @GetMapping("/listByPage")
    public Result<Page<Furn>> listByPage(@RequestParam("currentPage") String currentPage,
                                         @RequestParam("pageSize") String pageSize,
                                         @RequestParam("search") String search) {
        log.info("查询分页数据，页数:{}, 页面总容量:{}", currentPage, pageSize);
        try {
            Page<Furn> page = new Page<>(); //创建一个分页模型
            page.setCurrent(Long.parseLong(currentPage));
            page.setSize(Long.parseLong(pageSize));

            //构建一个查询条件包装类
            QueryWrapper<Furn> queryWrapper = Wrappers.query();
            if (StringUtils.isNotBlank(search)) {
                queryWrapper.like("name", search);
            }

            Page<Furn> furnPage = furnService.page(page, queryWrapper);
            if (null != furnPage.getRecords()) {
                return Result.success(furnPage);
            } else {
                return Result.failServerError();
            }
        } catch (NumberFormatException e) {
            return Result.failServerError();
        }
    }

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
    public Result<?> save(@Validated @RequestBody Furn furn, Errors errors) { //接收到的请求参数为json格式

        List<FieldError> fieldErrors = errors.getFieldErrors();
        //构建集合封装错误信息返回给前端
        HashMap<String, Object> map = new HashMap<>();
        for (FieldError error : fieldErrors) {
            map.put(error.getField(), error.getDefaultMessage());
        }
        //判断结果
        if (map.isEmpty()) {
            try {
                System.out.println("---准备添加家具信息--- furn:" + furn);
                boolean affectedRow = furnService.save(furn);
            } catch (Exception e) {
                return Result.failServerError();
            }
            return Result.success("添加家具成功");
        }
        //如果存在错误，返回给前端
        Result result = Result.failClientError();
        result.setData(map);
        return result;
    }
}
