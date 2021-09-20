package com.dream.business.dao;

import com.dream.business.vo.DrugsBillDetVo;
import com.dream.server.domain.DrugsBillDet;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DrugsBillDetDao {

    List<DrugsBillDetVo> list(DrugsBillDetVo drugsBillDet);

    void insertList(@Param("billId") Integer billId, @Param("list") List<DrugsBillDet> billDet);
}