package com.aiu.order.dao.z2025.user;

import com.aiu.order.model.entity.YxUserEntity;
import com.aiu.order.model.entity.Z2025User;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
@DS("copy")
public interface Z2025UserDao extends BaseMapper<Z2025User> {
}
