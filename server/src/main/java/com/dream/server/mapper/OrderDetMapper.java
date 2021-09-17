package com.dream.server.mapper;

import com.dream.server.domain.OrderDet;

public interface OrderDetMapper {
    int deleteByPrimaryKey(Integer orderDetId);

    int insert(OrderDet record);

    int insertSelective(OrderDet record);

    OrderDet selectByPrimaryKey(Integer orderDetId);

    int updateByPrimaryKeySelective(OrderDet record);

    int updateByPrimaryKey(OrderDet record);
}