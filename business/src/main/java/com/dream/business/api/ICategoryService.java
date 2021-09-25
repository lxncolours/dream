package com.dream.business.api;


import com.dream.business.vo.CategoryVo;
import com.dream.server.domain.Category;

public interface ICategoryService {

    /**
     * 列表查询
     */
    void list(CategoryVo categoryVo);

    /**
     * 保存，id有值时更新，无值时新增
     */
    void save(CategoryVo categoryVo);

    /**
     * 新增
     */
    void insert(Category category);

    /**
     * 更新
     */
    void update(Category category);

    /**
     * 删除
     */
    void delete(String id);

    void listCategory(CategoryVo categoryVo);
}