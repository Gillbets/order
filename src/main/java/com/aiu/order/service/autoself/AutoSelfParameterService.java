package com.aiu.order.service.autoself;

import com.aiu.order.model.entity.AutoSelfParameterEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
@DS("z2025")
public interface AutoSelfParameterService extends IService<AutoSelfParameterEntity> {

}
