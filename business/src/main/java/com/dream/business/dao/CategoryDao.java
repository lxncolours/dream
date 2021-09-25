package com.dream.business.dao;

import com.dream.business.vo.CategoryVo;

import java.util.List;


public interface CategoryDao {

    List<CategoryVo> list(CategoryVo category);

    List<CategoryVo> queryByParentId(String id);

    List<CategoryVo> listCategory(CategoryVo categoryVo);
}