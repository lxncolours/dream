package com.dream.business.vo;

import com.dream.server.dto.PageVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class DrugsBillVo extends PageVo<DrugsBillVo> {

    /**
     *
     */
    private Integer billId;

    /**
     *
     */
    private String customerName;

    /**
     *
     */
    private BigDecimal billAmount;

    private List<DrugsBillDetVo> billDet;
    
}