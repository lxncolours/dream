package com.dream.business.dao;

import com.dream.business.vo.DrugsBillVo;

import java.util.List;


public interface DrugsBillDao {

    List<DrugsBillVo> list(DrugsBillVo drugsBill);

    void updateBillAmountById(Integer billId);
}