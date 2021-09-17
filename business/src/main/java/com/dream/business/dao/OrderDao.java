package com.dream.business.dao;

import com.dream.server.domain.Order;
import com.dream.business.vo.OrderVo;

import java.util.List;


public interface OrderDao {

    List<OrderVo> list(OrderVo order);

}