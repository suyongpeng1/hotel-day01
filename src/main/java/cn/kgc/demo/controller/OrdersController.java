package cn.kgc.demo.controller;

import cn.kgc.demo.pojo.Orders;
import cn.kgc.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("orders")
public class OrdersController extends BaseController<Orders> {
    @Autowired
    private OrdersService ordersService;

    //该方法是orders特有的方法
    //支付完成之后，执行的操作
    //out_trade_no: 表示支付完成之后，回显的订单编号！！！
    //注意：该方法不能返回@ResponseBody ，因为需要通过视图解析器返回首页或者出错页面
    @RequestMapping("afterOrdersPay")
    public String afterOrdersPay(String out_trade_no){
        try{
            return ordersService.afterOrdersPay(out_trade_no);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
