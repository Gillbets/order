package com.aiu.order.service.impl.produce;

import com.aiu.order.dao.autoself.AutoSelfParameterDao;
import com.aiu.order.dao.local.ProduceDao;
import com.aiu.order.model.entity.AiuProduce;
import com.aiu.order.model.entity.AutoSelfParameterEntity;
import com.aiu.order.service.autoself.AutoSelfParameterService;
import com.aiu.order.service.produce.ProduceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author DH.WANG
 */
@Service
public class ProduceServiceImpl extends ServiceImpl<ProduceDao, AiuProduce> implements ProduceService {
}
