package com.dream.business.service;

import com.dream.business.api.IStockService;
import com.dream.business.dao.StockDao;
import com.dream.business.vo.StockVo;
import com.dream.server.domain.Goods;
import com.dream.server.domain.Stock;
import com.dream.server.mapper.GoodsMapper;
import com.dream.server.mapper.StockMapper;
import com.dream.server.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockService implements IStockService {

    @Resource
    private StockMapper stockMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private StockDao stockDao;

    /**
     * 列表查询
     */
    @Override
    public void list(StockVo stockVo) {
        PageHelper.startPage(stockVo.getPage(), stockVo.getSize());
        List<StockVo> stockList = stockDao.list(stockVo);
        PageInfo<StockVo> pageInfo = new PageInfo<>(stockList);
        stockVo.setTotal(pageInfo.getTotal());
        stockVo.setList(stockList);

    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @Override
    public void save(StockVo stockVo) {
        Stock stock = CopyUtil.copy(stockVo, Stock.class);
        if (StringUtils.isEmpty(stockVo.getStockId())) {
            this.insert(stock);
        } else {
            this.update(stock);
        }
    }

    /**
     * 新增
     */
    @Override
    public void insert(Stock stock) {
        Date now = new Date();
        stockMapper.insert(stock);
    }

    /**
     * 更新
     */
    @Override
    public void update(Stock stock) {
        stockMapper.updateByPrimaryKeySelective(stock);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Integer id) {
        stockMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stock(StockVo stockVo) {
        //计算本次入库单价
        Integer stockNum = stockVo.getInNum();
        BigDecimal putMonet = stockVo.getInPrice().multiply(new BigDecimal(stockNum));
        stockVo.setInMoney(putMonet);
        stockVo.setStockDate(new Date());
        stockVo.setStockNum(stockNum);
        Stock stock = CopyUtil.copy(stockVo, Stock.class);
        //插入Stock库存记录表
        stockMapper.insert(stock);
        //插入完成后更新goods商品表上次单价price,库存数量num
        Goods goods = goodsMapper.selectByPrimaryKey(stock.getGoodsId());
        goods.setNum(goods.getNum() + stock.getInNum());
        goods.setPrice(stock.getInPrice());
        goodsMapper.updateByPrimaryKeySelective(goods);
    }
}
