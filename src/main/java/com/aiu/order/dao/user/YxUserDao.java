package com.aiu.order.dao.user;

import com.aiu.order.model.entity.AutoSelfParameterEntity;
import com.aiu.order.model.entity.YxUserEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
@DS("shop")
public interface YxUserDao extends BaseMapper<YxUserEntity> {
}
