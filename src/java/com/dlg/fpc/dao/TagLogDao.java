package com.dlg.fpc.dao;

import com.dlg.fpc.entity.TagLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lingui
 * @Date 2022-09-26 11:43:35
 */
@Repository
public interface TagLogDao extends JpaRepository<TagLogEntity,Long> {

}