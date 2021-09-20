package com.dream.business.api;


import com.dream.business.vo.DrugsBillDetVo;
import com.dream.server.domain.DrugsBillDet;

public interface IDrugsBillDetService {

    /**
     * 列表查询
     */
    void list(DrugsBillDetVo drugsBillDetVo);

    /**
     * 保存，id有值时更新，无值时新增
     */
    void save(DrugsBillDetVo drugsBillDetVo);

    /**
     * 新增
     */
    void insert(DrugsBillDet drugsBillDet);

    /**
     * 更新
     */
    void update(DrugsBillDet drugsBillDet);

    /**
     * 删除
     */
    void delete(Integer id);

}