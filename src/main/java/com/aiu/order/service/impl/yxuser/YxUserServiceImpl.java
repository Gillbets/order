package com.aiu.order.service.impl.yxuser;

import com.aiu.order.dao.autoself.AutoSelfParameterDao;
import com.aiu.order.dao.user.YxUserDao;
import com.aiu.order.model.entity.AutoSelfParameterEntity;
import com.aiu.order.model.entity.YxUserEntity;
import com.aiu.order.service.autoself.AutoSelfParameterService;
import com.aiu.order.service.yxuser.YxUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class YxUserServiceImpl extends ServiceImpl<YxUserDao, YxUserEntity> implements YxUserService {
}
