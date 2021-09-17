package com.dream.business.api;


import com.dream.business.vo.OrderVo;
import com.dream.server.domain.Order;

public interface IOrderService {

/**
* 列表查询
*/
void list(OrderVo orderVo);

/**
* 保存，id有值时更新，无值时新增
*/
void save(OrderVo orderVo);

/**
* 新增
*/
void insert(Order order);

/**
* 更新
*/
void update(Order order);

/**
* 删除
*/
void delete(Integer id);

}