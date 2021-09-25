package com.dream.business.dao;

import com.dream.business.vo.GoodsVo;

import java.util.List;


public interface GoodsDao {

    List<GoodsVo> list(GoodsVo goods);

}