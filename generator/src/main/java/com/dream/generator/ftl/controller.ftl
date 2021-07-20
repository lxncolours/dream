package com.dream.${module}.controller;

import com.dream.${module}.vo.${Domain}Vo;
import com.dream.server.dto.ResponseVo;
import com.dream.${module}.api.I${Domain}Service;
import com.dream.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/${domain}")
public class ${Domain}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);
    public static final String BUSINESS_NAME = "${tableNameCn}";

    @Resource
    private I${Domain}Service ${domain}Service;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseVo list(@RequestBody ${Domain}Vo ${domain}Vo) {
        ResponseVo responseVo = new ResponseVo();
        ${domain}Service.list(${domain}Vo);
        responseVo.setContent(${domain}Vo);
        return responseVo;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseVo save(@RequestBody ${Domain}Vo ${domain}Vo) {
        // 保存校验
        <#list fieldList as field>
        <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt" && field.nameHump!="sort">
            <#if !field.nullAble>
        ValidatorUtil.require(${domain}Vo.get${field.nameBigHump}(), "${field.nameCn}");
            </#if>
            <#if (field.length > 0)>
        ValidatorUtil.length(${domain}Vo.get${field.nameBigHump}(), "${field.nameCn}", 1, ${field.length?c});
            </#if>
        </#if>
        </#list>

        ResponseVo responseVo = new ResponseVo();
        ${domain}Service.save(${domain}Vo);
        responseVo.setContent(${domain}Vo);
        return responseVo;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseVo delete(@PathVariable Integer id) {
        ResponseVo responseVo = new ResponseVo();
        ${domain}Service.delete(id);
        return responseVo;
    }
}
