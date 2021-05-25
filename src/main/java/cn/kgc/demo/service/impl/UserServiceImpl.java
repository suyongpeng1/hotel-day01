package cn.kgc.demo.service.impl;

import cn.kgc.demo.pojo.User;
import cn.kgc.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author xiaosu
 * @Date 2021/5/20  21:59
 * TODO 用户业务层实现类
 */

@Service
@Transactional(readOnly = false)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
}
