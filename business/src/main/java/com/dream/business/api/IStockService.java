package com.dream.business.api;


import com.dream.business.vo.StockVo;
import com.dream.server.domain.Stock;

import java.text.ParseException;

public interface IStockService {

    /**
     * 列表查询
     */
    void list(StockVo stockVo);

    /**
     * 保存，id有值时更新，无值时新增
     */
    void save(StockVo stockVo);

    /**
     * 新增
     */
    void insert(Stock stock);

    /**
     * 更新
     */
    void update(Stock stock);

    /**
     * 删除
     */
    void delete(Integer id);

    void stock(StockVo stockVo);
}