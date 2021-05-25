package cn.kgc.demo.service;
import cn.kgc.demo.pojo.RoomSale;

import java.util.Map;

//TODO 消费记录的业务层接口
public interface RoomSaleSerivce extends  BaseService<RoomSale> {

    //根据房间编号分组查询消费情况
    Map<String, Object> findRoomSale();
}
