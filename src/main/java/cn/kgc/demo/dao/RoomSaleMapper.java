package cn.kgc.demo.dao;

import cn.kgc.demo.pojo.RoomSale;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface RoomSaleMapper extends BaseMapper<RoomSale>{
    //根据房间编号分组查询消费情况
    @Select("select room_num roomNum,SUM(sale_price) salePriceAll from roomsale group by room_num")
    List<Map<String,Object>> selRoomSaleFromGroupBy();
}