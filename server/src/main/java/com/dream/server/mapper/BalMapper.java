package com.dream.server.mapper;

import com.dream.server.domain.Bal;

public interface BalMapper {
    int deleteByPrimaryKey(Integer balId);

    int insert(Bal record);

    int insertSelective(Bal record);

    Bal selectByPrimaryKey(Integer balId);

    int updateByPrimaryKeySelective(Bal record);

    int updateByPrimaryKey(Bal record);
}