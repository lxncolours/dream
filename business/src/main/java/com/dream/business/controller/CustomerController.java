package com.dream.business.controller;

import com.dream.business.vo.BalVo;
import com.dream.business.vo.CustomerVo;
import com.dream.server.dto.ResponseVo;
import com.dream.business.api.ICustomerService;
import com.dream.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 客户控制层
 *
 * @author Liberica
 * @date 2021-07-17
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private ICustomerService customerService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseVo list(@RequestBody CustomerVo customerVo) {
        ResponseVo responseVo = new ResponseVo();
        customerService.list(customerVo);
        responseVo.setContent(customerVo);
        return responseVo;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseVo save(@RequestBody CustomerVo customerVo) {
        // 保存校验
        ValidatorUtil.require(customerVo.getMobile(), "会员电话");
        ValidatorUtil.length(customerVo.getMobile(), "会员电话", 11, 11);

        ResponseVo responseVo = new ResponseVo();
        customerService.save(customerVo);
        responseVo.setContent(customerVo);
        return responseVo;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        ResponseVo responseVo = new ResponseVo();
        customerService.delete(id);
        return responseVo;
    }



    @PostMapping("/recharge")
    public ResponseVo recharge(@RequestBody BalVo balVo) {
        // 保存校验
        ValidatorUtil.require(balVo.getBalance(), "充值金额");
        ResponseVo responseVo = new ResponseVo();
        customerService.recharge(balVo);
        responseVo.setContent(balVo);
        return responseVo;
    }

    @PostMapping("/cashOut")
    public ResponseVo cashOut(@RequestBody BalVo balVo) {
        // 保存校验
        ValidatorUtil.require(balVo.getBalance(), "提现金额");
        ResponseVo responseVo = new ResponseVo();
        customerService.cashOut(balVo);
        responseVo.setContent(balVo);
        return responseVo;
    }
}
