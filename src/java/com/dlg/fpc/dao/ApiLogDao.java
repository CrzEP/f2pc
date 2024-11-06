package com.dlg.fpc.dao;

import com.dlg.fpc.entity.ApiLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiLogDao extends JpaRepository<ApiLogEntity,Long> {

}