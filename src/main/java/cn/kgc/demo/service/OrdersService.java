package cn.kgc.demo.service;

import cn.kgc.demo.pojo.Orders;

//TODO 订单的业务层接口

public interface OrdersService extends BaseService <Orders> {
    String afterOrdersPay(String out_trade_no) throws Exception;
}
