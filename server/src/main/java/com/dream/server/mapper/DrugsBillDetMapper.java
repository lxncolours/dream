package com.dream.server.mapper;

import com.dream.server.domain.DrugsBillDet;

public interface DrugsBillDetMapper {
    int deleteByPrimaryKey(Integer billDetId);

    int insert(DrugsBillDet record);

    int insertSelective(DrugsBillDet record);

    DrugsBillDet selectByPrimaryKey(Integer billDetId);

    int updateByPrimaryKeySelective(DrugsBillDet record);

    int updateByPrimaryKey(DrugsBillDet record);
}