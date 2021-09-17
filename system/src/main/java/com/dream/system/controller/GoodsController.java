package com.dream.system.controller;

import com.dream.system.vo.GoodsVo;
import com.dream.server.dto.ResponseVo;
import com.dream.system.api.IGoodsService;
import com.dream.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger LOG = LoggerFactory.getLogger(GoodsController.class);
    public static final String BUSINESS_NAME = "商品管理";

    @Resource
    private IGoodsService goodsService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseVo list(@RequestBody GoodsVo goodsVo) {
        ResponseVo responseVo = new ResponseVo();
        goodsService.list(goodsVo);
        responseVo.setContent(goodsVo);
        return responseVo;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseVo save(@RequestBody GoodsVo goodsVo) {
        // 保存校验
        ValidatorUtil.require(goodsVo.getGoodsName(), "商品名称");
        ValidatorUtil.require(goodsVo.getCategory1Id(), "一级分类");
        ValidatorUtil.require(goodsVo.getCategory2Id(), "二级分类");
        ValidatorUtil.require(goodsVo.getCategory3Id(), "三级分类");
        ValidatorUtil.require(goodsVo.getStoreName(), "商家名称");
        ResponseVo responseVo = new ResponseVo();
        goodsService.save(goodsVo);
        responseVo.setContent(goodsVo);
        return responseVo;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        ResponseVo responseVo = new ResponseVo();
        goodsService.delete(id);
        return responseVo;
    }

}
