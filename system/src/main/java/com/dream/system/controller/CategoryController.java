package com.dream.system.controller;

import com.dream.system.vo.CategoryVo;
import com.dream.server.dto.ResponseVo;
import com.dream.system.api.ICategoryService;
import com.dream.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private ICategoryService categoryService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseVo list(@RequestBody CategoryVo categoryVo) {
        ResponseVo responseVo = new ResponseVo();
        categoryService.list(categoryVo);
        responseVo.setContent(categoryVo);
        return responseVo;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseVo save(@RequestBody CategoryVo categoryVo) {
        // 保存校验
        ValidatorUtil.length(categoryVo.getCategoryName(), "", 1, 16);

        ResponseVo responseVo = new ResponseVo();
        categoryService.save(categoryVo);
        responseVo.setContent(categoryVo);
        return responseVo;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable String id) {
        ResponseVo responseVo = new ResponseVo();
        categoryService.delete(id);
        return responseVo;
    }
}
