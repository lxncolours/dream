package com.dream.system.dao;

import com.dream.server.domain.Category;
import com.dream.system.vo.CategoryVo;

import java.util.List;


public interface CategoryDao {

    List<CategoryVo> list(CategoryVo category);

    List<CategoryVo> queryByParentId(String id);
}