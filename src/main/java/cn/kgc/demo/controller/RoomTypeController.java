package cn.kgc.demo.controller;

import cn.kgc.demo.pojo.RoomType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//TODO 客房类型控制器

@Controller
@RequestMapping("roomType")
public class RoomTypeController extends BaseController <RoomType> {
}
