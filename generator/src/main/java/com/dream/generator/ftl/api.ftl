package com.dream.${module}.api;


import com.dream.${module}.vo.${Domain}Vo;
import com.dream.server.domain.${Domain};

public interface I${Domain}Service {

/**
* 列表查询
*/
void list(${Domain}Vo ${domain}Vo);

/**
* 保存，id有值时更新，无值时新增
*/
void save(${Domain}Vo ${domain}Vo);

/**
* 新增
*/
void insert(${Domain} ${domain});

/**
* 更新
*/
void update(${Domain} ${domain});

/**
* 删除
*/
void delete(Integer id);

}