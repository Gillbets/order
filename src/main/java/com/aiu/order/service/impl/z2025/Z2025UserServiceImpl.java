package com.aiu.order.service.impl.z2025;

import com.aiu.order.dao.user.YxUserDao;
import com.aiu.order.dao.z2025.user.Z2025UserDao;
import com.aiu.order.model.entity.YxUserEntity;
import com.aiu.order.model.entity.Z2025User;
import com.aiu.order.service.yxuser.YxUserService;
import com.aiu.order.service.z2025.Z2025UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class Z2025UserServiceImpl extends ServiceImpl<Z2025UserDao, Z2025User> implements Z2025UserService {
}
