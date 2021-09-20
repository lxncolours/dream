package com.dream.business.vo;

import com.dream.server.dto.PageVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class DrugsBillDetVo extends PageVo<DrugsBillDetVo> {


    private Integer billDetId;

    private Integer billId;

    private String goodsName;

    private Integer goodsNum;

    private BigDecimal salePrice;

    private BigDecimal costPrice;

    private BigDecimal goodsAmount;
    
}