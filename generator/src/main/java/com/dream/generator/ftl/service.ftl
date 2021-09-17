package com.dream.${module}.service;

import com.dream.server.domain.${Domain};
import com.dream.${module}.vo.${Domain}Vo;
import com.dream.server.mapper.${Domain}Mapper;
import com.dream.server.util.CopyUtil;
import com.dream.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.dream.${module}.api.I${Domain}Service;
import com.dream.${module}.dao.${Domain}Dao;

import javax.annotation.Resource;
import java.util.List;
<#list typeSet as type>
    <#if type=='Date'>
import java.util.Date;
    </#if>
</#list>

@Service
public class ${Domain}Service implements I${Domain}Service{

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    @Resource
    private ${Domain}Dao ${domain}Dao;

    /**
     * 列表查询
     */
    @Override
    public void list(${Domain}Vo ${domain}Vo) {
        PageHelper.startPage(${domain}Vo.getPage(), ${domain}Vo.getSize());
        List<${Domain}Vo> ${domain}List = ${domain}Dao.list(${domain}Vo);
        PageInfo<${Domain}Vo> pageInfo = new PageInfo<>(${domain}List);
        ${domain}Vo.setTotal(pageInfo.getTotal());
        ${domain}Vo.setList(${domain}List);

    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @Override
    public void save(${Domain}Vo ${domain}Vo) {
        ${Domain} ${domain} = CopyUtil.copy(${domain}Vo, ${Domain}.class);
        if (StringUtils.isEmpty(${domain}Vo.get${Domain}Id())) {
            this.insert(${domain});
        } else {
            this.update(${domain});
        }
    }

    /**
     * 新增
     */
    @Override
    public void insert(${Domain} ${domain}) {
        <#list typeSet as type>
            <#if type=='Date'>
        Date now = new Date();
            </#if>
        </#list>
        <#list fieldList as field>
            <#if field.nameHump=='createdAt'>
        ${domain}.setCreatedAt(now);
            </#if>
            <#if field.nameHump=='updatedAt'>
        ${domain}.setUpdatedAt(now);
            </#if>
        </#list>
        ${domain}Mapper.insert(${domain});
    }

    /**
     * 更新
     */
    @Override
    public void update(${Domain} ${domain}) {
        <#list fieldList as field>
            <#if field.nameHump=='updatedAt'>
        ${domain}.setUpdatedAt(new Date());
            </#if>
        </#list>
        ${domain}Mapper.updateByPrimaryKeySelective(${domain});
    }

    /**
     * 删除
     */
    @Override
    public void delete(Integer id) {
        ${domain}Mapper.deleteByPrimaryKey(id);
    }
}
