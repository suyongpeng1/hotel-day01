package cn.kgc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ModelController
 * @Description TODO  专门用于视图跳转的控制器
 * @Version 1.0
 */
@Controller
@RequestMapping("model")
public class ModelController {

    //跳转到项目的首页页面
    @RequestMapping("toHome")
    public String showHomeUI(){
        return "home";
    }

    //跳转到入住信息查询页面
    @RequestMapping("toShowInRoomInfo")
    public String toShowInRoomInfo(){
        /*这里必须要添加对应的文件夹路径*/
        return "inRoomInfo/showInRoomInfo";
    }

    //跳转到入住信息添加页面
    @RequestMapping("toSaveInRoomInfo")
    public String toSaveInRoomInfo(){
        return "inRoomInfo/saveInRoomInfo";
    }

    //去到订单查询显示页面
    @RequestMapping("/toShowOrders")
    public String toShowOrders(){
        return "orders/showOrders";
    }

    //跳转到订单支付页面
    @RequestMapping("toOrdersPay")
    public String toOrdersPay(){
        return "alipay/ordersPay";
    }

    //去到支付失败页面
    @RequestMapping("toErrorPay")
    public String toErrorPay(){
        return "alipay/errorPay";
    }

    //跳转到消费记录显示页面
     @RequestMapping("toShowRoomSale")
     public String toShowRoomSale(){
          return "roomSale/showRoomSale";
     }

    //跳转到会员信息管理页面
    @RequestMapping("toShowVip")
    public String toShowVip(){
        return "vip/showVip";
    }

    //跳转到添加会员信息页面
    @RequestMapping("toSaveVip")
    public String toSaveVip(){
        return "vip/saveVip";
    }

    //跳转到客房信息管理页面
    @RequestMapping("toShowRooms")
    public String toShowRooms(){
        return "rooms/showRooms";
    }

    //跳转到房型信息管理界面
    @RequestMapping("toShowRoomType")
    public String toShowRoomType(){
        return "roomType/showRoomType";
    }


    //跳转到系统的登录页面
    @RequestMapping("toLoginUI")
    public String toLoginUI(){
        return "login/login";
    }

    //跳转到数据分析页面
    @RequestMapping("toShowDbi")
    public String toShowDbi(){
        return "dbi/showdbi";
    }
}
