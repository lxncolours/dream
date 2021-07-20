package com.dream.business.api;


import com.dream.business.vo.BalVo;
import com.dream.business.vo.CustomerVo;
import com.dream.server.domain.Customer;

/**
 * icustomer服务
 *
 * @author Liberica
 * @date 2021-07-17
 */
public interface ICustomerService {

    /**
     * 列表
     * 列表查询
     *
     * @param customerVo 客户实体
     */
    void list(CustomerVo customerVo);

    /**
     * 保存
     * 保存，id有值时更新，无值时新增
     *
     * @param customerVo 客户实体
     */
    void save(CustomerVo customerVo);

    /**
     * 插入
     * 新增
     *
     * @param customer 客户
     */
    void insert(Customer customer);

    /**
     * 更新
     *
     * @param customer 客户
     */
    void update(Customer customer);

    /**
     * 删除
     *
     * @param id id
     */
    void delete(Integer id);

    /**
     * 充值
     *
     * @param balVo 余额实体
     */
    void recharge(BalVo balVo);

    /**
     * 套现
     *
     * @param balVo 余额实体
     */
    void cashOut(BalVo balVo);
}