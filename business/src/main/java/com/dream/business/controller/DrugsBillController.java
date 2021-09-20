package com.dream.business.controller;

import com.dream.business.api.IDrugsBillService;
import com.dream.business.vo.DrugsBillVo;
import com.dream.server.dto.ResponseVo;
import com.dream.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/drugsBill")
public class DrugsBillController {

    private static final Logger LOG = LoggerFactory.getLogger(DrugsBillController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private IDrugsBillService drugsBillService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseVo list(@RequestBody DrugsBillVo drugsBillVo) {
        ResponseVo responseVo = new ResponseVo();
        drugsBillService.list(drugsBillVo);
        responseVo.setContent(drugsBillVo);
        return responseVo;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseVo save(@RequestBody DrugsBillVo drugsBillVo) {
        // 保存校验
        ValidatorUtil.length(drugsBillVo.getCustomerName(), "", 1, 10);
        ResponseVo responseVo = new ResponseVo();
        drugsBillService.save(drugsBillVo);
        responseVo.setContent(drugsBillVo);
        return responseVo;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        ResponseVo responseVo = new ResponseVo();
        drugsBillService.delete(id);
        return responseVo;
    }
}
