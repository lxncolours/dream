package com.dream.system.service;

import com.dream.server.domain.Category;
import com.dream.server.exception.BusinessException;
import com.dream.server.exception.BusinessExceptionCode;
import com.dream.system.vo.CategoryVo;
import com.dream.server.mapper.CategoryMapper;
import com.dream.server.util.CopyUtil;
import com.dream.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.dream.system.api.ICategoryService;
import com.dream.system.dao.CategoryDao;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private CategoryDao categoryDao;

    /**
     * 列表查询
     */
    @Override
    public void list(CategoryVo categoryVo) {
        PageHelper.startPage(categoryVo.getPage(), categoryVo.getSize());
        List<CategoryVo> categoryList = categoryDao.list(categoryVo);
        PageInfo<CategoryVo> pageInfo = new PageInfo<>(categoryList);
        categoryVo.setTotal(pageInfo.getTotal());
        categoryVo.setList(categoryList);

    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @Override
    public void save(CategoryVo categoryVo) {
        Category category = CopyUtil.copy(categoryVo, Category.class);
        if (StringUtils.isEmpty(categoryVo.getCategoryId())) {
            category.setCategoryId(UuidUtil.getShortUuid());
            this.insert(category);
        } else {
            this.update(category);
        }
    }

    /**
     * 新增
     */
    @Override
    public void insert(Category category) {
        categoryMapper.insert(category);
    }

    /**
     * 更新
     */
    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 删除
     */
    @Override
    public void delete(String id) {
        //删除之前先查出所有子节点,如果存在子节点则不允许删除
        List<CategoryVo> categoryVos = categoryDao.queryByParentId(id);
        if (!categoryVos.isEmpty()) {
            throw new BusinessException(BusinessExceptionCode.EXISTS_CHILDREN);
        }
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void listCategory(CategoryVo categoryVo) {
        List<CategoryVo> categoryVos = categoryDao.listCategory(categoryVo);
        categoryVo.setList(categoryVos);
    }
}
