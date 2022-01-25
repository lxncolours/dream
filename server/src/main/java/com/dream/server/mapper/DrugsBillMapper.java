package com.dream.server.mapper;

import com.dream.server.domain.DrugsBill;

public interface DrugsBillMapper {
    int deleteByPrimaryKey(Integer billId);

    int insert(DrugsBill record);

    int insertSelective(DrugsBill record);

    DrugsBill selectByPrimaryKey(Integer billId);

    int updateByPrimaryKeySelective(DrugsBill record);

    int updateByPrimaryKey(DrugsBill record);
}