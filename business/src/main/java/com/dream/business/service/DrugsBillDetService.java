package com.dream.business.service;

import com.dream.business.api.IDrugsBillDetService;
import com.dream.business.dao.DrugsBillDetDao;
import com.dream.business.vo.DrugsBillDetVo;
import com.dream.server.domain.DrugsBillDet;
import com.dream.server.mapper.DrugsBillDetMapper;
import com.dream.server.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DrugsBillDetService implements IDrugsBillDetService {

    @Resource
    private DrugsBillDetMapper drugsBillDetMapper;

    @Resource
    private DrugsBillDetDao drugsBillDetDao;


    /**
     * 列表查询
     */
    @Override
    public void list(DrugsBillDetVo drugsBillDetVo) {
        PageHelper.startPage(drugsBillDetVo.getPage(), drugsBillDetVo.getSize());
        List<DrugsBillDetVo> drugsBillDetList = drugsBillDetDao.list(drugsBillDetVo);
        PageInfo<DrugsBillDetVo> pageInfo = new PageInfo<>(drugsBillDetList);
        drugsBillDetVo.setTotal(pageInfo.getTotal());
        drugsBillDetVo.setList(drugsBillDetList);

    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @Override
    public void save(DrugsBillDetVo drugsBillDetVo) {
        DrugsBillDet drugsBillDet = CopyUtil.copy(drugsBillDetVo, DrugsBillDet.class);
        if (StringUtils.isEmpty(drugsBillDetVo.getBillDetId())) {
            this.insert(drugsBillDet);
        } else {
            this.update(drugsBillDet);
        }
    }

    /**
     * 新增
     */
    @Override
    public void insert(DrugsBillDet drugsBillDet) {
        drugsBillDetMapper.insert(drugsBillDet);
    }

    /**
     * 更新
     */
    @Override
    public void update(DrugsBillDet drugsBillDet) {
        drugsBillDetMapper.updateByPrimaryKeySelective(drugsBillDet);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Integer id) {
        drugsBillDetMapper.deleteByPrimaryKey(id);
    }
}
