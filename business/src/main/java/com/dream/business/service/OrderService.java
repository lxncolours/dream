package com.dream.business.service;

import com.dream.server.domain.Order;
import com.dream.business.vo.OrderVo;
import com.dream.server.domain.OrderDet;
import com.dream.server.domain.Stock;
import com.dream.server.mapper.OrderDetMapper;
import com.dream.server.mapper.OrderMapper;
import com.dream.server.mapper.StockMapper;
import com.dream.server.util.CopyUtil;
import com.dream.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.dream.business.api.IOrderService;
import com.dream.business.dao.OrderDao;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements IOrderService{
    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetMapper orderDetMapper;

    @Resource
    private StockMapper stockMapper;

    @Resource
    private OrderDao orderDao;

    /**
     * 列表查询
     */
    @Override
    public void list(OrderVo orderVo) {
        PageHelper.startPage(orderVo.getPage(), orderVo.getSize());
        List<OrderVo> orderList = orderDao.list(orderVo);
        PageInfo<OrderVo> pageInfo = new PageInfo<>(orderList);
        orderVo.setTotal(pageInfo.getTotal());
        orderVo.setList(orderList);

    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(OrderVo orderVo) {
        Order order = CopyUtil.copy(orderVo, Order.class);

        if (StringUtils.isEmpty(orderVo.getOrderId())) {
            order.setOrderTime(new Date());
            this.insert(order);
            Integer orderId = order.getOrderId();
            BigDecimal orderMoney = BigDecimal.ZERO;
            List<OrderDet> orderDet = orderVo.getOrderDet();
            for (OrderDet det : orderDet) {
                //更新入库记录的剩余数量
                Stock stock = stockMapper.selectByPrimaryKey(det.getStockId());
                stock.setStockNum(stock.getStockNum() - det.getNum());
                stockMapper.updateByPrimaryKeySelective(stock);
                //设置订单id以及计算订单金额
                det.setOrderId(orderId);
                orderDetMapper.insert(det);
                orderMoney = orderMoney.add(det.getOutMoney());
            }
            //更新订单金额
            order.setOrderMoney(orderMoney);
            orderMapper.updateByPrimaryKeySelective(order);
        } else {
            this.update(order);
        }
    }

    /**
     * 新增
     */
    @Override
    public void insert(Order order) {
        orderMapper.insert(order);
    }

    /**
     * 更新
     */
    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Integer id) {
        orderMapper.deleteByPrimaryKey(id);
    }
}
