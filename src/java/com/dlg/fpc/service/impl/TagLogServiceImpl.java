package com.dlg.fpc.service.impl;

import com.dlg.fpc.common.TagConst;
import com.dlg.fpc.comp.TagEnum;
import com.dlg.fpc.dao.TagLogDao;
import com.dlg.fpc.entity.TagLogEntity;
import com.dlg.fpc.service.TagLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lingui
 * @Date 2024/11/6 11:08
 */
@Service
@Slf4j
public class TagLogServiceImpl implements TagLogService {

    @Resource
    TagLogDao tagLogDao;

    @Override
    public void logSitlog() {
        TagLogEntity entity = TagLogEntity.getBaseInfoLog(TagEnum.LSit.name(), "久坐提醒");
        tagLogDao.save(entity);
    }

}
