package cn.kgc.demo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Roles {
    /** 主键 */
    private Integer id;

    /** 角色名 */
    private String roleName;

    /** 角色创建时间 */
    private Date createDate;

    /** 角色禁用启用状态，1启用,0禁用 */
    private String status;

    /** 1超級角色  0普通角色 */
    private String flag;


}
