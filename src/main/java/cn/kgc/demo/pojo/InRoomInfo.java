package cn.kgc.demo.pojo;

import lombok.Data;

import java.util.Date;

//入住信息表
@Data
public class InRoomInfo {
    /** 主键 */
    private Integer id;

    /** 客人姓名 */
    private String customerName;

    /** 性别(1男 0女) */
    private String gender;

    /** 0普通，1vip */
    private String isVip;

    /** 身份证号 */
    private String idcard;

    /** 手机号 */
    private String phone;

    /** 押金 */
    private Float money;

    /** 入住时间 */
    private Date createDate;

    /** 房间表主键 */
    private Integer roomId;

    /** 显示状态：1显示，0隐藏 */
    private String status;

    /** 退房状态：0未退房 1已经退房 */
    private String outRoomStatus;

    /*引入客房对象*/
    private Rooms rooms;

}
