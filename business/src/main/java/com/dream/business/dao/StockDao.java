package com.dream.business.dao;

import com.dream.server.domain.Stock;
import com.dream.business.vo.StockVo;

import java.util.List;


public interface StockDao {

    List<StockVo> list(StockVo stock);

}