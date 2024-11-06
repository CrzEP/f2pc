package com.dlg.fpc.service.impl;

import com.dlg.fpc.dao.ApiLogDao;
import com.dlg.fpc.entity.ApiLogEntity;
import com.dlg.fpc.service.ApiLogService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;


/**
 * @author lingui
 * @Date 2024/11/6 10:25
 */
@Service("ApiLogServiceImpl")
public class ApiLogServiceImpl implements ApiLogService {

    @Resource
    ApiLogDao apiLogDao;

    @Override
    public ApiLogEntity save(ApiLogEntity entity) {
        return apiLogDao.save(entity);
    }



}
