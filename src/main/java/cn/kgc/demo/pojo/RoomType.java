package cn.kgc.demo.pojo;

import lombok.Data;

@Data
public class RoomType {
    /** 主键 */
    private Integer id;

    /** 房间类型名 */
    private String roomTypeName;

    /** 房间的单价 */
    private Float roomPrice;

}
