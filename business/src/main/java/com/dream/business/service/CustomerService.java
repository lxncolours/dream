package com.dream.business.service;

import com.dream.business.vo.BalVo;
import com.dream.server.domain.Bal;
import com.dream.server.domain.Customer;
import com.dream.business.vo.CustomerVo;
import com.dream.server.exception.BusinessException;
import com.dream.server.exception.BusinessExceptionCode;
import com.dream.server.mapper.BalMapper;
import com.dream.server.mapper.CustomerMapper;
import com.dream.server.util.CopyUtil;
import com.dream.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.dream.business.api.ICustomerService;
import com.dream.business.dao.CustomerDao;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 客户服务
 *
 * @author Liberica
 * @date 2021-07-17
 */
@Service
public class CustomerService implements ICustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private CustomerDao customerDao;

    @Resource
    private BalMapper balMapper;

    /**
     * 列表查询
     */
    @Override
    public void list(CustomerVo customerVo) {
        PageHelper.startPage(customerVo.getPage(), customerVo.getSize());
        List<CustomerVo> customerList = customerDao.list(customerVo);
        PageInfo<CustomerVo> pageInfo = new PageInfo<>(customerList);
        customerVo.setTotal(pageInfo.getTotal());
        customerVo.setList(customerList);

    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @Override
    public void save(CustomerVo customerVo) {
        Customer customer = CopyUtil.copy(customerVo, Customer.class);
        if (StringUtils.isEmpty(customerVo.getCustomerId())) {
            //新增时默认给消费金额插0
            customer.setConsumptionAmount(new BigDecimal(0));
            this.insert(customer);
        } else {
            this.update(customer);
        }
    }

    /**
     * 新增
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(Customer customer) {
        customerMapper.insert(customer);
        //新增时需要初始化余额表bal
        Bal bal = new Bal();
        bal.setCustomerId(customer.getCustomerId());
        bal.setBalance(BigDecimal.ZERO);
        balMapper.insert(bal);
    }

    /**
     * 更新
     */
    @Override
    public void update(Customer customer) {
        customerMapper.updateByPrimaryKey(customer);
    }

    /**
     * 删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        //删除之前需要先查询余额
        Bal bal = customerDao.queryBalByCustomer(id);
        if (bal != null) {
            BigDecimal balance = bal.getBalance();
            int i = balance.compareTo(BigDecimal.ZERO);
//        i=0:表示bigDecimal的值  等于 0
//        i=1:表示bigDecimal的值与 大于0
//        i=-1:表示bigDecimal的值与 小于 0
            if (i > 0) {
                throw new BusinessException(BusinessExceptionCode.EXISTS_BAL);
            } else {
                balMapper.deleteByPrimaryKey(bal.getBalId());
            }
        }
        customerMapper.deleteByPrimaryKey(id);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recharge(BalVo balVo) {
        //查询客户当前余额
        Bal bal = queryBalByCustomer(balVo);
        if (bal == null) {
            //如果没有余额记录,新增一条余额记录
            Bal insertBal = CopyUtil.copy(balVo, Bal.class);
            balMapper.insert(insertBal);
        } else {
            BigDecimal balance = bal.getBalance();
            bal.setBalance(balance.add(balVo.getBalance()));
            balMapper.updateByPrimaryKeySelective(bal);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cashOut(BalVo balVo) {
        //提现前先查询前台传入余额与余额表是否一致
        //查询客户当前余额
        Bal bal = queryBalByCustomer(balVo);
        if (bal == null) {
            //如果没有余额记录直接返回
            throw new BusinessException(BusinessExceptionCode.NOT_EXISTS_BAL);
        } else {
            BigDecimal balance = bal.getBalance();
            int i = balVo.getBalance().compareTo(bal.getBalance());
            if (i!= 0){
                throw new BusinessException(BusinessExceptionCode.NOT_MATCH_BAL);
            }else {
                //更新余额为0
                bal.setBalance(BigDecimal.ZERO);
                balMapper.updateByPrimaryKeySelective(bal);
            }
        }
    }

    private Bal queryBalByCustomer(BalVo balVo) {
        return customerDao.queryBalByCustomer(balVo.getCustomerId());
    }
}
