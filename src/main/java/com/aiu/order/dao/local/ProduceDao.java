package com.aiu.order.dao.local;

import com.aiu.order.model.entity.AiuProduce;
import com.aiu.order.model.entity.YxUserEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author DH.WANG
 */
@Mapper
@DS("local")
public interface ProduceDao extends BaseMapper<AiuProduce> {
}
