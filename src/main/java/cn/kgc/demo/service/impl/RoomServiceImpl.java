package cn.kgc.demo.service.impl;

import cn.kgc.demo.pojo.Rooms;
import cn.kgc.demo.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName RoomServiceImpl
 * @Description TODO 客房信息的实现类
 * @Author xiaosu
 * @Date 2021/5/10 11:28
 * @Version 1.0
 */
@Service
@Transactional(readOnly = false)
public class RoomServiceImpl extends BaseServiceImpl<Rooms> implements RoomService {
}
