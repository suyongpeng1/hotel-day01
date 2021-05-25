package cn.kgc.demo.service.impl;

import cn.kgc.demo.pojo.RoomType;
import cn.kgc.demo.service.RoomTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//TODO 客房类型业务层实现类

@Service
@Transactional(readOnly = false)
public class RoomTypeServiceImpl extends BaseServiceImpl<RoomType> implements RoomTypeService {
}
