package com.aiu.order.dao.autoself;

import com.aiu.order.model.entity.AutoSelfParameterEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
@DS("z2025")
public interface AutoSelfParameterDao extends BaseMapper<AutoSelfParameterEntity> {
}
