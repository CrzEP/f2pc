package com.dlg.fpc.service.service.impl;

import com.dlg.fpc.service.base.BaseServiceImpl;
import com.dlg.fpc.service.entity.ApiLogEntity;
import com.dlg.fpc.service.mapper.ApiLogMapper;
import com.dlg.fpc.service.service.ApiLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApiLogServiceImpl extends BaseServiceImpl<ApiLogMapper, ApiLogEntity> implements ApiLogService {

}
