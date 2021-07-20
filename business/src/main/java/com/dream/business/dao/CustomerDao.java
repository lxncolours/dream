package com.dream.business.dao;

import com.dream.server.domain.Bal;
import com.dream.server.domain.Customer;
import com.dream.business.vo.CustomerVo;

import java.util.List;


public interface CustomerDao {

    List<CustomerVo> list(CustomerVo customer);

    Bal queryBalByCustomer(Integer id);
}