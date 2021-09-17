package com.dream.business.controller;

import com.dream.business.vo.StockVo;
import com.dream.server.dto.ResponseVo;
import com.dream.business.api.IStockService;
import com.dream.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/stock")
public class StockController {

    private static final Logger LOG = LoggerFactory.getLogger(StockController.class);
    public static final String BUSINESS_NAME = "入库记录";

    @Resource
    private IStockService stockService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseVo list(@RequestBody StockVo stockVo) {
        ResponseVo responseVo = new ResponseVo();
        stockService.list(stockVo);
        responseVo.setContent(stockVo);
        return responseVo;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseVo save(@RequestBody StockVo stockVo) {
        // 保存校验
        ValidatorUtil.require(stockVo.getStockId(), "库存id");

        ResponseVo responseVo = new ResponseVo();
        stockService.save(stockVo);
        responseVo.setContent(stockVo);
        return responseVo;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        ResponseVo responseVo = new ResponseVo();
        stockService.delete(id);
        return responseVo;
    }

    @PostMapping("/stock")
    public ResponseVo stock(@RequestBody StockVo stockVo) {
        // 保存校验
        ValidatorUtil.require(stockVo.getInNum(), "入库数量");
        ValidatorUtil.require(stockVo.getInPrice(), "入库金额");
        ResponseVo responseVo = new ResponseVo();
        stockService.stock(stockVo);
        responseVo.setContent(stockVo);
        return responseVo;
    }
}
