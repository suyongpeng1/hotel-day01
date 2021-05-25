package cn.kgc.demo.controller;

import cn.kgc.demo.pojo.User;
import cn.kgc.demo.service.UserService;
import cn.kgc.demo.utils.MD5;
import cn.kgc.demo.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName UserController
 * @Description TODO 用户控制器
 * @Author xiaosu
 * @Date 2021/5/20 20:53
 * @Version 1.0
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;

    //生成验证码，返回到页面显示验证码图片
    @RequestMapping("getVerifyCode")
    public void getVerifyCode(HttpServletResponse response, HttpSession session){
        //response: 通过响应流把生成的验证图片，响应到页面上
        //session: 使用session来存储服务器生成的验证码

        //1.生成5位数的验证码
        String verifyCode = VerifyCodeUtils.generateVerifyCode(5);
        //2.把验证码转换为小写放入到session中
        session.setAttribute("verifyCode",verifyCode.toLowerCase());
        //3.验证码图片响应到页面上显示
        try {
            VerifyCodeUtils.outputImage(220,35,response.getOutputStream(),verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //实现验证码服务器验证
    @RequestMapping("checkVerifyCode")
    @ResponseBody
    public String checkVerifyCode(String yzm, HttpSession session){
        //1.获取服务器上session保存的验证码
        String verifyCode = (String) session.getAttribute("verifyCode");
        //2.把服务器上session保存的验证码跟前端传递过来的验证码进行对比
        //yzm.toLowerCase() : 需要转换为小写
        if(verifyCode.equals(yzm.toLowerCase())){
            return "success"; // 验证成功
        }else{
            return "fail"; //验证失败
        }
    }

    //根据用户名和密码验证是否登录成功
    @RequestMapping("checkLogin")
    @ResponseBody
    public String checkLogin(User user, HttpSession session){
        //1.把前端传递过来的密码加密之后，跟数据库中的密码进行对比
        //md5crypt: MD5的加密方法
        user.setPwd(MD5.md5crypt(user.getPwd()));
        try{
            //2.根据用户名和密码查询当前用户是否存在
            User loginUser = userService.findTByParams(user);
            if(loginUser!=null){
                //把用户信息加入到session中
                session.setAttribute("loginUser",loginUser);
                return "success";  //查询成功
            }else{
                return "fail"; //查询失败
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //退出平台
    @RequestMapping("exitUser")
    @ResponseBody
    public String exitUser(HttpSession session){
        //从session中去除当前登录用户
        try{
            session.removeAttribute("loginUser");
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
      }
