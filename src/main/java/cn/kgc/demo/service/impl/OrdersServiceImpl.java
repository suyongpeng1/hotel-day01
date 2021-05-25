package cn.kgc.demo.service.impl;

import cn.kgc.demo.dao.InRoomInfoMapper;
import cn.kgc.demo.dao.OrdersMapper;
import cn.kgc.demo.dao.RoomSaleMapper;
import cn.kgc.demo.dao.RoomsMapper;
import cn.kgc.demo.pojo.InRoomInfo;
import cn.kgc.demo.pojo.Orders;
import cn.kgc.demo.pojo.RoomSale;
import cn.kgc.demo.pojo.Rooms;
import cn.kgc.demo.service.OrdersService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private InRoomInfoMapper inRoomInfoMapper;

    @Autowired
    private RoomsMapper roomsMapper;

    @Autowired
    private RoomSaleMapper roomSaleMapper;


    //订单业务层方法重写父类的保存数据方法
    @Override
    public String saveT(Orders orders) {
        //1.生成订单数据(以订单的添加为主)
        int ordersCount = ordersMapper.insert(orders);
        //2.入住信息是否退房的状态的修改（未退房-->已退房）  0 -> 1
        //创建查询条件实体对象
        InRoomInfo inRoomInfo = new InRoomInfo();
        inRoomInfo.setId(orders.getIriId());
        inRoomInfo.setOutRoomStatus("1");
        int updRoomInfoCount = inRoomInfoMapper.updateByPrimaryKeySelective(inRoomInfo);
        //3.客房的状态修改（已入住-->打扫）  1 -> 2
        //先根据入住信息的id查询出来入住信息
        InRoomInfo info = inRoomInfoMapper.selectByPrimaryKey(orders.getIriId());
        Rooms rooms = new Rooms();
        rooms.setId(info.getRoomId());
        rooms.setRoomStatus("2");
        int roomsCount = roomsMapper.updateByPrimaryKeySelective(rooms);
        if(ordersCount>0 && updRoomInfoCount>0 && roomsCount>0){
            return "success";
        }
        return "fail";
    }

    //支付完成之后的操作
    //out_trade_no: 表示订单编号
    @Override
    public String afterOrdersPay(String out_trade_no) throws Exception{
        //1. 把订单状态修改为 ：  0(未结算)  ->  1（已结算）
        //1-1 : 先根据订单编号，查询单个订单数据
        Orders parOrders=new Orders();
        parOrders.setOrderNum(out_trade_no);
        parOrders =ordersMapper.selTByParams(parOrders);
        //1-2 : 根据查询出来的订单id和状态进行修改
        Orders updOrders =new Orders();
        updOrders.setId(parOrders.getId());
        updOrders.setOrderStatus("1");
        int updOrdersCount =ordersMapper.updateByPrimaryKeySelective(updOrders);

        //2. 生成消费记录表数据
        RoomSale roomSale = new RoomSale();
        //2-1: 获取order_other字段，通过,号分割字符串，得到数据
        String[] orderOther = parOrders.getOrderOther().split(",");
        //设置房间号
        roomSale.setRoomNum(orderOther[0]);
        //设置客户名称
        roomSale.setCustomerName(orderOther[1]);
        //设置入住时间
        roomSale.setStartDate(DateUtils.parseDate(orderOther[2],new String[]{"yyyy/MM/dd HH:mm:ss"}));
        //设置退房时间
        roomSale.setEndDate(DateUtils.parseDate(orderOther[3],new String[]{"yyyy/MM/dd HH:mm:ss"}));
        //设置入住天数
        roomSale.setDays(Integer.valueOf(orderOther[4]));
        //2-2 : 获取order_price字段，通过,号分割字符串，得到数据
        String[] orderPrice = parOrders.getOrderPrice().split(",");
        //设置房间单价
        roomSale.setRoomPrice(Double.valueOf(orderPrice[0]));
        /** 住宿费（实际的住房费用） */
        roomSale.setRentPrice(Double.valueOf(orderPrice[2]));
        //其他消费金额
        roomSale.setOtherPrice(Double.valueOf(orderPrice[1]));
        /** 订单的实际支付金额 */
        roomSale.setSalePrice(parOrders.getOrderMoney());
        //优惠金额
        roomSale.setDiscountPrice(roomSale.getRoomPrice()*roomSale.getDays() - roomSale.getRentPrice());
        int roomSaleCount = roomSaleMapper.insert(roomSale);
        if(updOrdersCount>0 && roomSaleCount>0){
            //去到项目的首页
            return "redirect:/model/toHome";
        }else{
            //去到错误提示页面
            return "redirect:/model/toErrorPay";
        }

    }
}
