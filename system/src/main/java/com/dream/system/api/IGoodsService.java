package com.dream.system.api;


import com.dream.system.vo.GoodsVo;
import com.dream.server.domain.Goods;

public interface IGoodsService {

/**
* 列表查询
*/
void list(GoodsVo goodsVo);

/**
* 保存，id有值时更新，无值时新增
*/
void save(GoodsVo goodsVo);

/**
* 新增
*/
void insert(Goods goods);

/**
* 更新
*/
void update(Goods goods);

/**
* 删除
*/
void delete(Integer id);

}