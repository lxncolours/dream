package com.dream.business.service;

import com.dream.business.api.IDrugsBillService;
import com.dream.business.dao.DrugsBillDao;
import com.dream.business.dao.DrugsBillDetDao;
import com.dream.business.vo.DrugsBillDetVo;
import com.dream.business.vo.DrugsBillVo;
import com.dream.server.domain.DrugsBill;
import com.dream.server.domain.DrugsBillDet;
import com.dream.server.mapper.DrugsBillMapper;
import com.dream.server.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DrugsBillService implements IDrugsBillService {

    @Resource
    private DrugsBillMapper drugsBillMapper;

    @Resource
    private DrugsBillDao drugsBillDao;

    @Resource
    private DrugsBillDetDao drugsBillDetDao;

    /**
     * 列表查询
     */
    @Override
    public void list(DrugsBillVo drugsBillVo) {
        List<DrugsBillVo> drugsBillList = drugsBillDao.list(drugsBillVo);
        drugsBillVo.setList(drugsBillList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @Override
    public void save(DrugsBillVo drugsBillVo) {
        DrugsBill drugsBill = CopyUtil.copy(drugsBillVo, DrugsBill.class);
        drugsBill.setBillDate(new Date());
        if (StringUtils.isEmpty(drugsBillVo.getBillId())) {
            //先插入订单表
            this.insert(drugsBill);
            //插入明细表
            List<DrugsBillDetVo> billDetVoList = drugsBillVo.getBillDet();
            List<DrugsBillDet> billDetList = CopyUtil.copyList(billDetVoList, DrugsBillDet.class);
            drugsBillDetDao.insertList(drugsBill.getBillId(), billDetList);
            //更新金额
            drugsBillDao.updateBillAmountById(drugsBill.getBillId());

        } else {
            this.update(drugsBill);
        }
    }

    /**
     * 新增
     */
    @Override
    public void insert(DrugsBill drugsBill) {
        Date now = new Date();
        drugsBillMapper.insert(drugsBill);
    }

    /**
     * 更新
     */
    @Override
    public void update(DrugsBill drugsBill) {
        drugsBillMapper.updateByPrimaryKeySelective(drugsBill);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Integer id) {
        drugsBillMapper.deleteByPrimaryKey(id);
    }
}
