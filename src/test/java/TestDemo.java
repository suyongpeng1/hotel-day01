
import cn.kgc.demo.pojo.InRoomInfo;
import cn.kgc.demo.service.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TestDemo
 * @Description TODO
 * @Author zhaojing
 * @Date 2021/4/26 15:33
 * @Version 1.0
 */
//指定在单元测试启动的时候创建spring的工厂类对象
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
//RunWith的value属性指定以spring test的SpringJUnit4ClassRunner作为启动类
//如果不指定启动类，默认启用的junit中的默认启动类
@RunWith(value = SpringJUnit4ClassRunner.class)
public class TestDemo {

    @Autowired
    private BaseService<InRoomInfo> baseService;

    //测试分页查询入住信息（客房信息，客房类型）
    @Test
    public void test01(){
        //新建查询条件
        InRoomInfo inRoomInfo = new InRoomInfo();
        Map<String, Object> map = baseService.findTByPageAndParams(1, 3, inRoomInfo);
        System.out.println("总共有："+ map.get("count") + "条记录");
        List<InRoomInfo> list = (List<InRoomInfo>)map.get("data");
        for (InRoomInfo roomInfo : list) {
            System.out.println(roomInfo.getCustomerName()+","+roomInfo.getPhone());
            System.out.println("------------------------------");
            System.out.println(roomInfo.getRooms());
            System.out.println("-------------------------------");
            System.out.println(roomInfo.getRooms().getRoomType());
        }
    }
}
