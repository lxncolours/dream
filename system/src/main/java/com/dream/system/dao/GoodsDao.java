package com.dream.system.dao;

import com.dream.server.domain.Goods;
import com.dream.system.vo.GoodsVo;

import java.util.List;


public interface GoodsDao {

    List<GoodsVo> list(GoodsVo goods);

}