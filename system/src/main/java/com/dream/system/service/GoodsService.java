package com.dream.system.service;

import com.dream.server.domain.Goods;
import com.dream.server.domain.Stock;
import com.dream.server.exception.BusinessException;
import com.dream.server.exception.BusinessExceptionCode;
import com.dream.server.mapper.StockMapper;
import com.dream.system.vo.GoodsVo;
import com.dream.server.mapper.GoodsMapper;
import com.dream.server.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.dream.system.api.IGoodsService;
import com.dream.system.dao.GoodsDao;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class GoodsService implements IGoodsService {

    private static final Logger LOG = LoggerFactory.getLogger(GoodsService.class);

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private StockMapper stockMapper;

    /**
     * 列表查询
     */
    @Override
    public void list(GoodsVo goodsVo) {
        PageHelper.startPage(goodsVo.getPage(), goodsVo.getSize());
        List<GoodsVo> goodsList = goodsDao.list(goodsVo);
        PageInfo<GoodsVo> pageInfo = new PageInfo<>(goodsList);
        goodsVo.setTotal(pageInfo.getTotal());
        goodsVo.setList(goodsList);

    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @Override
    public void save(GoodsVo goodsVo) {
        Goods goods = CopyUtil.copy(goodsVo, Goods.class);
        if (StringUtils.isEmpty(goodsVo.getGoodsId())) {
            //库存初始化为0
            goods.setNum(0);
            goods.setPrice(BigDecimal.ZERO);
            this.insert(goods);
        } else {
            this.update(goods);
        }
    }

    /**
     * 新增
     */
    @Override
    public void insert(Goods goods) {
        goodsMapper.insert(goods);
    }

    /**
     * 更新
     */
    @Override
    public void update(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Integer id) {
        //删除之前要查询是否还有库存,有库存不允许删除
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        if (goods != null) {
            if (goods.getNum() != 0) {
                throw new BusinessException(BusinessExceptionCode.EXISTS_NUM);
            }
            goodsMapper.deleteByPrimaryKey(id);
        }
    }

}
