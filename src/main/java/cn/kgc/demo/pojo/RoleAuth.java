package cn.kgc.demo.pojo;

import lombok.Data;

@Data
public class RoleAuth {
    /** 主键 */
    private Integer id;

    /** 角色id */
    private Integer roleId;

    /** 权限id */
    private Integer authId;

}
