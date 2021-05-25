package cn.kgc.demo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class RoomSale {
    /** 消费id */
    private Integer id;

    /** 房间号 */
    private String roomNum;

    /** 客人姓名 */
    private String customerName;

    /** 入住时间 */
    private Date startDate;

    /** 退房时间 */
    private Date endDate;

    /** 天数 */
    private Integer days;

    /** 房屋单价 */
    private Double roomPrice;

    /** 住宿费 */
    private Double rentPrice;

    /** 其它消费 */
    private Double otherPrice;

    /**  */
    private Double salePrice;

    /** 优惠金额 */
    private Double discountPrice;

    //查询的条件:时间范围
    private Date minDate;  //时间上限

    private Date maxDate;  //时间下限

}
