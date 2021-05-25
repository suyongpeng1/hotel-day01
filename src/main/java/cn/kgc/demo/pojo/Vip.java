package cn.kgc.demo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Vip {
    /** 主键 */
    private Integer id;

    /** 会员卡编号 */
    private String vipNum;

    /** 会员姓名 */
    private String customerName;

    /** 1~9折 */
    private Float vipRate;

    /** 会员身份证 */
    private String idcard;

    /** 手机号码 */
    private String phone;

    /** 会员办理日期 */
    private Date createDate;

    /** 性别：1男 0女 */
    private String gender;

}
