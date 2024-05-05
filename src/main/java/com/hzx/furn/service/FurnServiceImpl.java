package com.hzx.furn.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzx.furn.bean.Furn;
import com.hzx.furn.mapper.FurnMapper;
import org.springframework.stereotype.Service;

/**
 * @author Jools He
 * @version 1.0
 * @date 2024/5/5 12:24
 * @description:
 * FurnService 的实现类， 需要继承 class ServiceImpl<M extends BaseMapper<T>, T>
 * 因为其实现了 IService<T>
 */
@Service
public class FurnServiceImpl
        extends ServiceImpl<FurnMapper, Furn>
        implements FurnService {
}
