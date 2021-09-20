package com.dream.business.controller;

import com.dream.business.api.IDrugsBillDetService;
import com.dream.business.vo.DrugsBillDetVo;
import com.dream.server.dto.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/drugsBillDet")
public class DrugsBillDetController {

    private static final Logger LOG = LoggerFactory.getLogger(DrugsBillDetController.class);
    public static final String BUSINESS_NAME = "药品管理-账单管理";

    @Resource
    private IDrugsBillDetService drugsBillDetService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseVo list(@RequestBody DrugsBillDetVo drugsBillDetVo) {
        ResponseVo responseVo = new ResponseVo();
        drugsBillDetService.list(drugsBillDetVo);
        responseVo.setContent(drugsBillDetVo);
        return responseVo;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseVo save(@RequestBody DrugsBillDetVo drugsBillDetVo) {
        // 保存校验
        ResponseVo responseVo = new ResponseVo();
        drugsBillDetService.save(drugsBillDetVo);
        responseVo.setContent(drugsBillDetVo);
        return responseVo;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        ResponseVo responseVo = new ResponseVo();
        drugsBillDetService.delete(id);
        return responseVo;
    }
}
