package com.aiu.order.service.produce;

import com.aiu.order.model.entity.AiuProduce;
import com.aiu.order.model.entity.AutoSelfParameterEntity;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author DH.WANG
 */
@DS("local")
public interface ProduceService extends IService<AiuProduce> {

}
