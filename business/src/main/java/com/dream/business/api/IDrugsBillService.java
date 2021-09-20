package com.dream.business.api;


import com.dream.business.vo.DrugsBillVo;
import com.dream.server.domain.DrugsBill;

public interface IDrugsBillService {

    /**
     * 列表查询
     */
    void list(DrugsBillVo drugsBillVo);

    /**
     * 保存，id有值时更新，无值时新增
     */
    void save(DrugsBillVo drugsBillVo);

    /**
     * 新增
     */
    void insert(DrugsBill drugsBill);

    /**
     * 更新
     */
    void update(DrugsBill drugsBill);

    /**
     * 删除
     */
    void delete(Integer id);

}