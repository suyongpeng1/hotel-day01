package cn.kgc.demo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    /** 主键 */
    private Integer id;

    /** 账号 */
    private String username;

    /** 密码 */
    private String pwd;

    /** 创建时间 */
    private Date createDate;

    /** 启用状态：1启用，0禁用 */
    private String useStatus;

    /** 1超级管理员，0普通管理员 */
    private String isAdmin;

    /** 角色id */
    private Integer roleId;

}
