package cn.kgc.demo.service.impl;


import cn.kgc.demo.pojo.Vip;
import cn.kgc.demo.service.VipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @ClassName InRoomInfoSeviceImpl
 * @Description TODO 会员信息实现类
 * @Author xiaosu
 * @Date 2021/5/7 10:48
 * @Version 1.0
 */
@Service
@Transactional(readOnly = false)
public class VipServiceImpl extends BaseServiceImpl<Vip> implements VipService {

}
