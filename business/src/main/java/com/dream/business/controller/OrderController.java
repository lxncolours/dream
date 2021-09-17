package com.dream.business.controller;

import com.dream.business.vo.OrderVo;
import com.dream.server.dto.ResponseVo;
import com.dream.business.api.IOrderService;
import com.dream.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private IOrderService orderService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseVo list(@RequestBody OrderVo orderVo) {
        ResponseVo responseVo = new ResponseVo();
        orderService.list(orderVo);
        responseVo.setContent(orderVo);
        return responseVo;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseVo save(@RequestBody OrderVo orderVo) {
        // 保存校验
        ValidatorUtil.require(orderVo.getCustomerId(), "用户id");
        ValidatorUtil.require(orderVo.getOperator(), "下单人");
        ResponseVo responseVo = new ResponseVo();
        orderService.save(orderVo);
        responseVo.setContent(orderVo);
        return responseVo;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        ResponseVo responseVo = new ResponseVo();
        orderService.delete(id);
        return responseVo;
    }
}
