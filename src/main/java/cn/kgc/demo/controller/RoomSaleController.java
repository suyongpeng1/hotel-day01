package cn.kgc.demo.controller;

import cn.kgc.demo.pojo.RoomSale;
import cn.kgc.demo.service.RoomSaleSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName RoomSaleController
 * @Description TODO 消费记录的控制器
 * @Author xizosu
 * @Date 2021/5/13 17:56
 * @Version 1.0
 */
@Controller
@RequestMapping("roomSale")
public class RoomSaleController extends BaseController<RoomSale> {

    @Autowired
    private RoomSaleSerivce roomSaleSerivce;

    //根据房间编号分组查询消费情况
    @RequestMapping("loadRoomSale")
    @ResponseBody
    public Map<String,Object> loadRoomSale(){
        try{
            return roomSaleSerivce.findRoomSale();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
