package cn.kgc.demo.pojo;

import lombok.Data;

@Data
public class Authority {
    /** 主键 */
    private Integer id;

    /** 权限名 */
    private String authorityName;

    /** 权限跳转地址 */
    private String authorityUrl;

    /** 记住上级的主键，0为一级节点 */
    private Integer parent;

    /** 1超级权限，0普通权限 */
    private String flag;
}
