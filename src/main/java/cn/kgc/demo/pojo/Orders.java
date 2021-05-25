package cn.kgc.demo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Orders {
    /** 主键 */
    private Integer id;

    /** 订单编号 */
    private String orderNum;

    /** 订单总价 */
    private Double orderMoney;

    /** 订单备注 */
    private String remark;

    /** 0未结算，1已结算 */
    private String orderStatus;

    /** 入住信息主键 */
    private Integer iriId;

    /** 下单时间 */
    private Date createDate;

    /** 1显示，0隐藏 */
    private String flag;

    /** 退房时的客人信息时间等等 */
    private String orderOther;

    /** 退房时的各种金额 */
    private String orderPrice;

    //入住信息对象
    private InRoomInfo inRoomInfo;

    //查询的条件:时间范围
    private Date minDate; //时间上限
    private Date maxDate; //时间下限

}
